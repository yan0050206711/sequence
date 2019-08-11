package com.tstd2.client.sequence.task;

import com.tstd2.client.sequence.impl.DefaultGlobalSequence;
import com.tstd2.client.sequence.routing.DataSourceHolder;
import com.tstd2.client.sequence.routing.DataSourceRouter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author yancey
 * @date 2018/3/23 18:19
 */
public class GlobalSequenceBufferPoolCheckTask implements Runnable {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalSequenceBufferPoolCheckTask.class);

    private DefaultGlobalSequence sequence;

    private String appName;

    public GlobalSequenceBufferPoolCheckTask(DefaultGlobalSequence sequence, String appName) {
        this.sequence = sequence;
        this.appName = appName;
    }

    @Override
    public void run() {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug(sequence.getClass().getSimpleName() + "开始load数据");
        }

        // 选择数据源
        DataSourceRouter.DataSourcePair dataSourcePair = DataSourceRouter.dataSourceSelect(sequence.getDataSourceManagerFactory());
        // 绑定数据源
        DataSourceHolder.bindDataSource(dataSourcePair);

        this.sequence.loadValue(appName);

        DataSourceHolder.unbindDataSource();
    }

}
