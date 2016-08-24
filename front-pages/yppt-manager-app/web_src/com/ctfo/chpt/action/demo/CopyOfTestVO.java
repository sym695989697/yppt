package com.ctfo.chpt.action.demo;

import java.io.Serializable;

public class CopyOfTestVO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String name;
	private ExampleInnerVO innerVo;
	
	public ExampleInnerVO getInnerVo() {
		return innerVo;
	}
	public void setInnerVo(ExampleInnerVO innerVo) {
		this.innerVo = innerVo;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
