package com.wei.common.resttemplate;

import java.io.Serializable;

public class ApiResult<T> implements Serializable {

	private static final long serialVersionUID = -9021762279173741675L;
	private String retCode;
	private String retMsg;
	private T data;

	//没有空参构造会报错
	public ApiResult() {
	}

	//有参构造暂时用不上
	/*public ApiResult(String retCode, String retMsg, T data) {
		this.retCode = retCode;
		this.retMsg = retMsg;
		this.data = data;
	}*/

	public String getRetCode() {
		return retCode;
	}

	public void setRetCode(String retCode) {
		this.retCode = retCode;
	}

	public String getRetMsg() {
		return retMsg;
	}

	public void setRetMsg(String retMsg) {
		this.retMsg = retMsg;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "ApiResult{" +
				"retCode='" + retCode + '\'' +
				", retMsg='" + retMsg + '\'' +
				", data=" + data +
				'}';
	}
}