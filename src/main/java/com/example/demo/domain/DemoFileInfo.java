/**
 * 
 */
package com.example.demo.domain;

import java.io.Serializable;

/**
 * @author yaong
 *
 */
public class DemoFileInfo implements Serializable {

	private static final long serialVersionUID = 6843315448105684388L;

	private String echoText;
	private String fileName;
	public String getEchoText() {
		return echoText;
	}
	public void setEchoText(String echoText) {
		this.echoText = echoText;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	@Override
	public String toString() {
		return "DemoFileInfo [echoText=" + echoText + ", fileName=" + fileName + "]";
	}
}
