package com.tstd2.client.sequence.dao;

import com.tstd2.client.sequence.domain.Sequence;
import com.tstd2.client.sequence.routing.DataSourceHolder;
import com.tstd2.client.sequence.routing.DataSourceRouter;
import com.tstd2.client.sequence.schema.GlobalSequenceTable;
import com.tstd2.client.sequence.util.JdbcHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

/**
 * 全局自增ID生成服务DAO实现类
 *
 * @author yancey
 * @date:2016年4月7日 下午5:57:15
 * @version V1.0
 *
 */
public class DefaultGlobalSequenceDao implements GlobalSequenceDao {

	private static final Logger LOGGER = LoggerFactory.getLogger(DefaultGlobalSequenceDao.class);

	/**
	 * 数据库表结构
	 */
	private GlobalSequenceTable sequenceTable = new GlobalSequenceTable();

	@Override
	public Sequence nextValue(String appName) {

		StringBuilder selectSql = new StringBuilder();
		selectSql.append("SELECT ").append(this.sequenceTable.getAppValueName()).append(" FROM ").append(this.sequenceTable.getTableName())
				.append(" WHERE ").append(this.sequenceTable.getAppKeyName()).append(" = ?");

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("nextValue--->{}", selectSql.toString());
        }

		Sequence sequence = null;

        DataSourceRouter.DataSourcePair dataSourcePair = DataSourceHolder.getDataSource();
		JdbcHolder jdbcHolder = new JdbcHolder(dataSourcePair.getDataSource());
		List<Map<String, Object>> list = jdbcHolder.executeQuery(selectSql.toString(), new String[] { appName });
		if (list != null && list.size() > 0) {
			Map<String, Object> map = list.get(0);
			if (map.get(this.sequenceTable.getAppValueName()) instanceof Long) {
				sequence = new Sequence(appName, (Long) map.get(this.sequenceTable.getAppValueName()));
			}
		}

		return sequence;
	}

	@Override
	public int updateValueByAppName(String appName, Long oldValue, Integer offset) {

		StringBuilder updateSql = new StringBuilder();

		//update {tableName} set {appValueName} = {appValueName} + ?, {updateTimeName} = now() where {appKeyName} = ? and {appValueName} = ?
		updateSql.append("UPDATE ").append(this.sequenceTable.getTableName())
                .append(" SET ").append(this.sequenceTable.getAppValueName()).append(" = ").append(this.sequenceTable.getAppValueName()).append(" + ?, ")
                .append(this.sequenceTable.getUpdateTimeName()).append(" = now() WHERE ")
				.append(this.sequenceTable.getAppKeyName()).append(" = ? AND ")
                .append(this.sequenceTable.getAppValueName()).append(" = ?");

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("updateValueByAppName--->{}", updateSql.toString());
        }

        DataSourceRouter.DataSourcePair dataSourcePair = DataSourceHolder.getDataSource();
        JdbcHolder jdbcHolder = new JdbcHolder(dataSourcePair.getDataSource());
		int ret = jdbcHolder.executeUpdate(updateSql.toString(), new Object[] { offset, appName, oldValue });

		return ret;
	}

    public void setSequenceTable(GlobalSequenceTable sequenceTable) {
		this.sequenceTable = sequenceTable;
	}

}
