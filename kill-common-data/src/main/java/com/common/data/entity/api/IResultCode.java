package com.common.data.entity.api;

/**
 * 返回码接口
 *
 * @author wangjian
 */
public interface IResultCode {

	/**
	 * 返回码
	 *
	 * @return int
	 */
	int getCode();

	/**
	 * 返回消息
	 *
	 * @return String
	 */
	String getMsg();
}
