package com.lh.mysql.separation1.dynamic;

import lombok.Data;

@Data
public class DynamicDataSourceHolder {

    public static final String MASTER = "master";

    public static final String SLAVE = "slave";

    private static final ThreadLocal<String> holder = new ThreadLocal<>();

    public static void putDataSourceKey(String key) {
        holder.set(key);
    }

    public static String getDataSourceKey() {
        return holder.get();
    }

    public static void markMaster() {
        putDataSourceKey(MASTER);
    }

    public static void markSlave() {
        putDataSourceKey(SLAVE);
    }

    public static boolean isMaster() {

        return false;
    }
}
