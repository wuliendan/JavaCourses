package com.lh.mysql.separation1.config;

import com.lh.mysql.separation1.dynamic.DynamicDataSourceHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.util.ReflectionUtils;

import javax.sql.DataSource;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 定义动态数据源，实现通过集成Spring提供的AbstractRoutingDataSource，只需要实现determineCurrentLookupKey方法即可
 * 由于DynamicDataSource是单例的，线程不安全的，所以采用ThreadLocal保证线程安全，由DynamicDataSourceHolder完成。
 *
 */

@Slf4j
public class DynamicDataSource extends AbstractRoutingDataSource {

    private Integer slaveCount;

    // 轮询计数,初始为-1,AtomicInteger是线程安全的
    private AtomicInteger counter = new AtomicInteger(-1);

    // 记录读库的key
    private List<Object> slaveDataSource = new ArrayList<>();

    @Override
    protected Object determineCurrentLookupKey() {
        // 使用DynamicDataSourceHolder保证线程安全，并且得到当前线程中的数据源key
        if (DynamicDataSourceHolder.isMaster()) {
            Object key = DynamicDataSourceHolder.getDataSourceKey();
            if (log.isDebugEnabled()) {
                log.debug("当前DataSource的Key: " + key);
            }
            return key;
        }

        Object key = getSlaveKey();
        if (log.isDebugEnabled()) {
            log.debug("当前DataSource的Key为: " + key);
        }

        return key;
    }

    @Override
    public void afterPropertiesSet() {
        super.afterPropertiesSet();

        // 由于父类的resolvedDataSources属性是私有的子类获取不到，需要使用反射获取
        Field field = ReflectionUtils.findField(AbstractRoutingDataSource.class, "resolvedDataSource");
        field.setAccessible(true);

        try {
            Map<Object, DataSource> resolvedDataSources = (Map<Object, DataSource>) field.get(this);
            // 读库的数据量等于数据源总数减去写库的数量
            this.slaveCount = resolvedDataSources.size() - 1;
            for (Map.Entry<Object, DataSource> entry : resolvedDataSources.entrySet()) {
                if (DynamicDataSourceHolder.MASTER.equals(entry.getKey())) {
                    continue;
                }
                slaveDataSource.add(entry.getKey());
            }
        } catch (IllegalAccessException e) {
            log.error("AfterPropertiesSet error!", e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * 轮询算法实现
     *
     * @return
     */
    public Object getSlaveKey() {
        // 得到的下标为：0、1、2、3……
        Integer index = counter.incrementAndGet() % slaveCount;
        if (counter.get() > 9999) {
            counter.set(-1);
        }

        return slaveDataSource.get(index);
    }
}
