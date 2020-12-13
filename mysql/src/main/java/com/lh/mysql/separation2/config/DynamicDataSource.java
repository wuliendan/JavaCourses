package com.lh.mysql.separation2.config;

import org.apache.shardingsphere.driver.api.ShardingSphereDataSourceFactory;
import org.apache.shardingsphere.infra.config.RuleConfiguration;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.*;

public class DynamicDataSource {

    public DynamicDataSource() throws SQLException {
    }

    Map<String, DataSource> dataSourceMap = new HashMap<>();
    Collection<RuleConfiguration> configurations = new Collection<RuleConfiguration>() {
        @Override
        public int size() {
            return 0;
        }

        @Override
        public boolean isEmpty() {
            return false;
        }

        @Override
        public boolean contains(Object o) {
            return false;
        }

        @Override
        public Iterator<RuleConfiguration> iterator() {
            return null;
        }

        @Override
        public Object[] toArray() {
            return new Object[0];
        }

        @Override
        public <T> T[] toArray(T[] a) {
            return null;
        }

        @Override
        public boolean add(RuleConfiguration ruleConfiguration) {
            return false;
        }

        @Override
        public boolean remove(Object o) {
            return false;
        }

        @Override
        public boolean containsAll(Collection<?> c) {
            return false;
        }

        @Override
        public boolean addAll(Collection<? extends RuleConfiguration> c) {
            return false;
        }

        @Override
        public boolean removeAll(Collection<?> c) {
            return false;
        }

        @Override
        public boolean retainAll(Collection<?> c) {
            return false;
        }

        @Override
        public void clear() {

        }
    };

    Properties props = new Properties();

    DataSource dataSource = ShardingSphereDataSourceFactory.createDataSource(dataSourceMap, configurations, props);

}
