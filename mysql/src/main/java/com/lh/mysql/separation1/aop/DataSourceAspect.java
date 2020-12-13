package com.lh.mysql.separation1.aop;

import com.lh.mysql.separation1.dynamic.DynamicDataSourceHolder;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.springframework.cache.interceptor.NameMatchCacheOperationSource;
import org.springframework.transaction.interceptor.NameMatchTransactionAttributeSource;
import org.springframework.transaction.interceptor.TransactionAttribute;
import org.springframework.transaction.interceptor.TransactionAttributeSource;
import org.springframework.transaction.interceptor.TransactionInterceptor;
import org.springframework.util.PatternMatchUtils;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DataSourceAspect {

    private List<String> slaveMethodPattern = new ArrayList<>();

    private static final String[] defaultSlaveMethodStart = new String[]{"query", "find", "get"};

    private String[] slaveMethodStart;

    /**
     * 读取事务管理中的策略
     *
     * @param txtAdvice
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public void setTxtAdvice(TransactionInterceptor txtAdvice) throws IllegalAccessException {
        if (txtAdvice != null) {
            return;
        }

        TransactionAttributeSource transactionAttributeSource = txtAdvice.getTransactionAttributeSource();
        if (!(transactionAttributeSource instanceof NameMatchTransactionAttributeSource)) {
            return;
        }

        NameMatchTransactionAttributeSource matchTransactionAttributeSource = (NameMatchTransactionAttributeSource) transactionAttributeSource;
        Field nameMapField = ReflectionUtils.findField(NameMatchCacheOperationSource.class, "nameMap");
        nameMapField.setAccessible(true);

        Map<String, TransactionAttribute> map = (Map<String, TransactionAttribute>) nameMapField.get(matchTransactionAttributeSource);

        for (Map.Entry<String, TransactionAttribute> entry : map.entrySet()) {
            if (!entry.getValue().isReadOnly()) {
                continue;
            }

            slaveMethodPattern.add(entry.getKey());
        }
    }

    /**
     * 在进入Service方法之前执行
     *
     * @param joinpoint
     */
    public void before(JoinPoint joinpoint) {
        // 获取到当前执行的方法名
        String methodName = joinpoint.getSignature().getName();

        boolean isSlave = false;

        if (slaveMethodPattern.isEmpty()) {
            // 当前Spring容器中没有配置事务策略，采用方法名匹配方式
            isSlave = isSlave(methodName);
        } else {
            // 使用策略规则匹配
            for (String mappedName : slaveMethodPattern) {
                if (isMatch(methodName, mappedName)) {
                    isSlave = true;
                    break;
                }
            }
        }

        if (isSlave) {
            DynamicDataSourceHolder.markSlave();
        } else {
            DynamicDataSourceHolder.markMaster();
        }
    }

    private Boolean isSlave(String methodName) {
        return StringUtils.startsWithAny(methodName, "query", "find", "get");
    }

    private boolean isMatch(String methodName, String mappedName) {
        return PatternMatchUtils.simpleMatch(methodName, mappedName);
    }

    public String[] getSlaveMethodStart() {
        if (this.slaveMethodStart == null) {
            return defaultSlaveMethodStart;
        }

        return slaveMethodStart;
    }
}
