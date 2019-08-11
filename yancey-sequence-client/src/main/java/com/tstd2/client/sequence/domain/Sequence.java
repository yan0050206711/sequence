package com.tstd2.client.sequence.domain;

import java.io.Serializable;

/**
 * 自增序列实体类
 * 
 * @author yancey
 * @date:2016年4月7日 下午4:15:50
 * @version V1.0
 * 
 */
public class Sequence implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 业务名称
	 */
	protected String appName;

	/**
	 * 当前值
	 */
	protected Long appValue;

	public Sequence() {
	}

	public Sequence(String appName, Long appValue) {
		this.appName = appName;
		this.appValue = appValue;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public Long getAppValue() {
		return appValue;
	}

	public void setAppValue(Long appValue) {
		this.appValue = appValue;
	}
}
