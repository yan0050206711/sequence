package com.tstd2.client.sequence.util;

import com.tstd2.client.sequence.routing.DataSourceHolder;
import com.tstd2.client.sequence.routing.DataSourceRouter;

/**
 * 多台数据库连接下的本地号段分片分配算法工具类
 *
 * @author yancey
 * @date 2018/3/28 21:11
 */
public class ShardingOffsetUtil {

    /**
     * 计算下一个偏移量
     *
     * @return
     */
    public static int nextOffset(int shardingDBCount, int capacity) {
        return shardingDBCount * capacity;
    }

    /**
     * 计算sequence的下一个号段的结束值
     *
     * @return
     */
    public static long nextValue(long oldValue, int shardingDBCount, int capacity) {
        return oldValue + shardingDBCount * capacity;
    }

    /**
     * 根据对当前value对数据库数取模，判断是否符合当前的db
     *
     * @param value
     * @param shardingDBCount
     * @param dataBaseNo
     * @return
     */
    public static boolean holding(long value, int shardingDBCount, int dataBaseNo) {
        return value % shardingDBCount == dataBaseNo;
    }

}
