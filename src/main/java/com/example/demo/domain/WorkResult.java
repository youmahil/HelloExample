/**
 * 
 */
package com.example.demo.domain;

import java.io.Serializable;

/**
 * @author yaong
 *
 */
public class WorkResult implements Serializable {

	private static final long serialVersionUID = 6818535074924293792L;

	private String code;
	private String detailMessage;
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDetailMessage() {
		return detailMessage;
	}
	public void setDetailMessage(String detailMessage) {
		this.detailMessage = detailMessage;
	}
	@Override
	public String toString() {
		return "WorkResult [code=" + code + ", detailMessage=" + detailMessage + "]";
	}
	
}
