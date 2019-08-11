package com.tstd2.client.sequence.util;

/**
 * @author yancey
 * @date 2018/3/28 21:39
 */
public class Constant {

    /**
     * 唯一索引冲突的sqlState
     */
    public static String DuplicateKeySqlState = "23000";

    /**
     * 数据库连接异常sqlState前缀
     */
    public static String DataAccessSqlStatePrefix = "08";

    /**
     * capacity到达多少的时候可以开启异步加载机制
     */
    public static int capacityLimit = 1000000;

    /**
     * 号池中剩余多少开始load数据
     */
    public static double surplusOfBuffer = 0.8;
}
