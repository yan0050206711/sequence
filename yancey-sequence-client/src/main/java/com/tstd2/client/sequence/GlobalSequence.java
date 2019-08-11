package com.tstd2.client.sequence;

/**
 * 全局自增ID生成服务
 *
 * @author yancey
 * @date:2016年3月18日 下午8:25:43   
 * @version V1.0   
 *
 */
public interface GlobalSequence {

	/**
	 * 获取一个全局唯一自增号
	 *
	 * @param appName 业务编码
	 * @return
	 */
	public long nextValue(String appName); 
}
