package com.fhdone.java2022.april.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 返回对象
 */
@Data
@NoArgsConstructor
public class ResultInfo implements Serializable {
	
	/** 定义公共成功状态 */
	private static final int SUCCESS = 1;
    
	/** 定义公共失败状态 */
	private static final int FAIL = -1;
	
	public int code;
	public String msg;
	public Object data;
	

	public ResultInfo setFail(String failMsg) {
		this.code = FAIL;
		this.msg = failMsg;
		return this;
	}
	
	public ResultInfo setSuccess(Object obj) {
		this.code = SUCCESS;
		this.data = obj;
		return this;
	}
	
	public static ResultInfo instanceSuccess(Object obj) {
		ResultInfo resultInfo = new ResultInfo();
		resultInfo.setSuccess(obj);
		return resultInfo;
	}
	
	public static ResultInfo instanceFail(String failMsg) {
		ResultInfo resultInfo = new ResultInfo();
		resultInfo.setFail(failMsg);
		return resultInfo;
	}
	
	
}
