package com.tstd2.client.sequence.routing;

import java.util.Collection;
import java.util.Random;

/**
 * 随机分片策略
 *
 * @author yancey
 * @date 2018/3/23 09:57
 */
public class RandomRouteStrategy implements RouteStrategy {
    @Override
    public int sharding(int shardingDBCount, Collection<DataSourceManagerFactory.DataSourceInfo> dataSourcePool) {

        return new Random().nextInt(shardingDBCount);
    }
}
