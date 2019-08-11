package com.tstd2.client.sequence.routing;

import java.util.Collection;
import java.util.List;

/**
 * datasource分片策略
 *
 * @author yancey
 * @date 2018/3/22 23:51
 */
public interface RouteStrategy {

    int sharding(int shardingDBCount, Collection<DataSourceManagerFactory.DataSourceInfo> dataSourcePool);

}
