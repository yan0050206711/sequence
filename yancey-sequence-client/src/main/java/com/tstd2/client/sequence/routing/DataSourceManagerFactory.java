package com.tstd2.client.sequence.routing;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * 数据库连接池构造器
 *
 * @author yancey
 * @date 2018/3/23 15:17
 */
public class DataSourceManagerFactory {

    /**
     * 数据库连接池配置
     */
    private Map<Integer, DataSourceInfo> dataSourcePool;

    /**
     * 数据路由策略
     */
    private RouteStrategy routeStrategy = new RandomRouteStrategy();

    /**
     * 初始化数据库连接池
     *
     * @param dataSourceList
     */
    public void setDataSourceList(List<DataSource> dataSourceList) {
        dataSourcePool = new HashMap<Integer, DataSourceInfo>();
        for (int i = 0; i < dataSourceList.size(); i++) {
            dataSourcePool.put(i, new DataSourceInfo(i, true, dataSourceList.get(i)));
        }

    }

    /**
     * 将某个数据库连接设置为无效
     *
     * @param dataSourceNo
     */
    public void invalidDataSource(Integer dataSourceNo) {
        DataSourceInfo deadDataSource = dataSourcePool.get(dataSourceNo);
        // 设为无效
        deadDataSource.setStatus(false);
    }

    /**
     * 将某个数据库连接设置为有效
     *
     * @param dataSourceNo
     */
    public void validDataSource(Integer dataSourceNo) {
        DataSourceInfo deadDataSource = dataSourcePool.get(dataSourceNo);
        // 设为有效
        deadDataSource.setStatus(true);
    }

    /**
     * 查询是否全部数据连接都失效了
     *
     * @return
     */
    public boolean isAllInvalid() {
        for (Map.Entry<Integer, DataSourceInfo> entry : this.dataSourcePool.entrySet()) {
            if (entry.getValue().status) {
                return false;
            }
        }

        return true;
    }

    /**
     * 查询是否全部数据连接都有效
     *
     * @return
     */
    public boolean isAllValid() {
        for (Map.Entry<Integer, DataSourceInfo> entry : this.dataSourcePool.entrySet()) {
            if (!entry.getValue().status) {
                return false;
            }
        }

        return true;
    }

    /**
     * 总数据库数量
     * @return
     */
    public int getDataSourceCount() {
        return this.getDataSourcePool().size();
    }

    public Map<Integer, DataSourceInfo> getDataSourcePool() {
        return dataSourcePool;
    }

    public RouteStrategy getRouteStrategy() {
        return routeStrategy;
    }

    public void setRouteStrategy(RouteStrategy routeStrategy) {
        this.routeStrategy = routeStrategy;
    }

    public class DataSourceInfo {
        private int dataSourceNo;
        private boolean status;
        private DataSource dataSource;

        public DataSourceInfo(int dataSourceNo, boolean status, DataSource dataSource) {
            this.dataSourceNo = dataSourceNo;
            this.status = status;
            this.dataSource = dataSource;
        }

        public int getDataSourceNo() {
            return dataSourceNo;
        }

        public void setDataSourceNo(int dataSourceNo) {
            this.dataSourceNo = dataSourceNo;
        }

        public boolean getStatus() {
            return status;
        }

        public void setStatus(boolean status) {
            this.status = status;
        }

        public DataSource getDataSource() {
            return dataSource;
        }

        public void setDataSource(DataSource dataSource) {
            this.dataSource = dataSource;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            DataSourceInfo that = (DataSourceInfo) o;
            return dataSourceNo == that.dataSourceNo;
        }

        @Override
        public int hashCode() {

            return Objects.hash(dataSourceNo);
        }
    }
}
