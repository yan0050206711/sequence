package com.tstd2.client.sequence.routing;

import com.tstd2.client.sequence.exception.ApplicationException;

import javax.sql.DataSource;
import java.util.Collection;

/**
 * 数据源路由器
 *
 * @author yancey
 * @date 2018/3/22 23:39
 */
public class DataSourceRouter {

    public static DataSourcePair dataSourceSelect(DataSourceManagerFactory dataSourceManagerFactory) {

        if (dataSourceManagerFactory.isAllInvalid()) {
            throw new ApplicationException("全部数据连接都失效了.");
        }

        RouteStrategy routeStrategy = dataSourceManagerFactory.getRouteStrategy();
        if (routeStrategy == null) {
            routeStrategy = new RandomRouteStrategy();
        }

        int shardingDBCount = dataSourceManagerFactory.getDataSourceCount();
        Collection<DataSourceManagerFactory.DataSourceInfo> dataSourceInfoCollection = dataSourceManagerFactory.getDataSourcePool().values();

        int dataSourceNo = routeStrategy.sharding(shardingDBCount, dataSourceInfoCollection);

        /**
         * 如果选出来的db处于无效状态，则重新进行选举
         */
        while (!dataSourceManagerFactory.getDataSourcePool().get(dataSourceNo).getStatus()) {
            if (dataSourceManagerFactory.isAllInvalid()) {
                throw new ApplicationException("全部数据连接都失效了.");
            }
            dataSourceNo = routeStrategy.sharding(shardingDBCount, dataSourceInfoCollection);
        }

        DataSource dataSource = dataSourceManagerFactory.getDataSourcePool().get(dataSourceNo).getDataSource();

        return new DataSourcePair(dataSourceNo, dataSource);
    }

    public static class DataSourcePair {
        private int dataSourceNo;
        private DataSource dataSource;

        public DataSourcePair(int dataSourceNo, DataSource dataSource) {
            this.dataSourceNo = dataSourceNo;
            this.dataSource = dataSource;
        }

        public int getDataSourceNo() {
            return dataSourceNo;
        }

        public void setDataSourceNo(int dataSourceNo) {
            this.dataSourceNo = dataSourceNo;
        }

        public DataSource getDataSource() {
            return dataSource;
        }

        public void setDataSource(DataSource dataSource) {
            this.dataSource = dataSource;
        }
    }

}
