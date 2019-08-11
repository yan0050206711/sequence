package com.tstd2.client.sequence.routing;

/**
 * @author yancey
 * @date 2018/3/23 15:02
 */
public class DataSourceHolder {

    /**
     * 用于存放当前使用的数据源号
     */
    private static final ThreadLocal<DataSourceRouter.DataSourcePair> CURRENT_DS_HOLDER = new ThreadLocal<DataSourceRouter.DataSourcePair>();

    /**
     * 将数据源对象绑定到当前线程
     */
    public static void bindDataSource(DataSourceRouter.DataSourcePair dataSource) {
        CURRENT_DS_HOLDER.set(dataSource);
    }

    /**
     * 从当前线程获得绑定的数据源
     */
    public static DataSourceRouter.DataSourcePair getDataSource() {
        return CURRENT_DS_HOLDER.get();
    }

    /**
     * 解除绑定的数据源
     */
    public static void unbindDataSource() {
        CURRENT_DS_HOLDER.remove();
    }

}
