package com.tstd2.client.sequence.domain;


/**
 * 自增序列实体类
 * 
 * @author yancey
 * @date:2016年3月18日 下午8:06:05
 * @version V1.0
 * 
 */
public class SequenceByDay extends Sequence {

	private static final long serialVersionUID = 1L;

	/**
	 * 序列号生成日期
	 */
	private String createDay;

	public SequenceByDay() {
	}

	public SequenceByDay(String appName, Long appValue, String createDay) {
		super(appName, appValue);
		this.createDay = createDay;
	}

	public String getCreateDay() {
		return createDay;
	}

	public void setCreateDay(String createDay) {
		this.createDay = createDay;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
