package com.fcsservice.utils;

import java.io.Serializable;
/**
 * Ajax请求Json响应结果模型.
 * @date 2017-10-25 上午13:20:59
 */
@SuppressWarnings("serial")
public class Result implements Serializable {

	/**
	 * 成功
	 */
	public static final int SUCCESS = 1;
	/**
	 * 失败
	 */
	public static final int FAIL = 0;

	/**
	 * 结果状态码(可自定义结果状态码) 1:操作成功 0:操作失败
	 */
	private int code;
	/**
	 * 响应信息
	 */
	private String msg;
	/**
	 * 响应主数据
	 */
	private Object obj;
	/**
	 * 额外响应数据1
	 */
	private Object obj1;
	/**
	 * 额外响应数据2
	 */
	private Object obj2;

	public Result() {
		super();
	}

	/**
	 * 
	 * @param code
	 *            结果状态码(1:操作成功 0:操作失败)
	 * @param msg
	 *            响应消息
	 * @param obj
	 *            响应主数据
	 * @param obj1
	 * 			  额外响应数据1
	 * @param obj2
	 * 			  额外响应数据2
	 */
	public Result(int code, String msg, Object obj,Object obj1,Object obj2) {
		super();
		this.code = code;
		this.msg = msg;
		this.obj = obj;
		this.obj1 = obj1;
		this.obj2 = obj2;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getObj() {
		return obj;
	}

	public void setObj(Object obj) {
		this.obj = obj;
	}

	public Object getObj1() {
		return obj1;
	}

	public void setObj1(Object obj1) {
		this.obj1 = obj1;
	}

	public Object getObj2() {
		return obj2;
	}

	public void setObj2(Object obj2) {
		this.obj2 = obj2;
	}
}
