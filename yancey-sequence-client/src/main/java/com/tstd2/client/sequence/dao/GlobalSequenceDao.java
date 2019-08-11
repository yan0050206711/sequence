package com.tstd2.client.sequence.dao;

import com.tstd2.client.sequence.domain.Sequence;

/**
 * 全局自增ID生成服务DAO接口
 *
 * @author yancey
 * @date:2016年3月18日 下午8:25:43   
 * @version V1.0   
 *
 */
public interface GlobalSequenceDao {
	
	/**
	 * 获取一个全局唯一自增号
	 *
	 * @param appName 业务编码
	 * @return 
	 * @author: yanhuajian 2016年3月18日下午8:38:00
	 */
	public Sequence nextValue(String appName);
	
	/**
	 * 根据业务编码进行更新
     * @param appName 业务编码
     * @param oldValue 旧值，数据库做乐观锁用
     * @param offset 要增加的偏移量
	 */
	public int updateValueByAppName(String appName, Long oldValue, Integer offset);
}
