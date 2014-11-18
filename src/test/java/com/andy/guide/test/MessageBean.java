package com.andy.guide.test;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.thoughtworks.xstream.annotations.XStreamAlias;


public class MessageBean {

	private String result;
	private String resultMsg;
	private ServerInfo[] serverInfo;
	
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getResultMsg() {
		return resultMsg;
	}
	public void setResultMsg(String resultMsg) {
		this.resultMsg = resultMsg;
	}
	public ServerInfo[] getServerInfo() {
		return serverInfo;
	}
	public void setServerInfo(ServerInfo[] serverInfo) {
		this.serverInfo = serverInfo;
	}
	
}//~
