package com.ctfo.yppt.bean;


import java.io.Serializable;

import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Id;
import com.google.code.morphia.annotations.Transient;

@Entity(value="yppt_message_template", noClassnameStored=true)
public class MessageTemplate implements Serializable {
	/**
	 * 
	 */
	@Transient
	private static final long serialVersionUID = 1477252946529706853L;

	/**
	 * 
	 */
	
	@Id
	private String id;
	
	private String templateCode;//模板编码
	
	private String templateName;//模板名称
	
	private String content;//内容
	
	private String state;//状态
	
	private String time;//创建时间

	public String getTemplateCode() {
		return templateCode;
	}

	public void setTemplateCode(String templateCode) {
		this.templateCode = templateCode;
	}

	public String getTemplateName() {
		return templateName;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}
}
