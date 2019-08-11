package com.tstd2.client.sequence.schema;

import java.io.Serializable;

/**
 * 全局sequence表结构
 *
 * @author yancey
 * @date:2016年12月30日 下午2:58:24
 * @version V1.0
 *
 */
public class GlobalSequenceTable implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**
	 * 数据库表名称
	 */
	private String tableName = "sequence_global";
	/**
	 * 键字段名
	 */
	private String appKeyName = "app_name";
	/**
	 * 值字段名
	 */
	private String appValueName = "app_value";
	/**
	 * 创建时间字段名
	 */
	private String createTimeName = "create_time";
	/**
	 * 更新时间字段名
	 */
	private String updateTimeName = "update_time";

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getAppKeyName() {
		return appKeyName;
	}

	public void setAppKeyName(String appKeyName) {
		this.appKeyName = appKeyName;
	}

	public String getAppValueName() {
		return appValueName;
	}

	public void setAppValueName(String appValueName) {
		this.appValueName = appValueName;
	}

	public String getCreateTimeName() {
		return createTimeName;
	}

	public void setCreateTimeName(String createTimeName) {
		this.createTimeName = createTimeName;
	}

	public String getUpdateTimeName() {
		return updateTimeName;
	}

	public void setUpdateTimeName(String updateTimeName) {
		this.updateTimeName = updateTimeName;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
