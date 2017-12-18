package com.x3.core;

/**
 * response状态返回码
 * 
 * @author heming
 *
 */
public interface ResponseState {
	public static int SUCCESS = 1001;// 成功

	public static int ERROR = 1002;// 系统错误

	public static int FAILED = 1003;// 业务错误

	public static int INVALID_TOKEN = 1004;// 无效token

	public static int INVALID_USER = 1005;// 无效用户
}
