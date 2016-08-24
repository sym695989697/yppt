package com.ctfo.chpt.action.demo;

import java.io.Serializable;

public class ExampleInnerVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String innerName;
	public String getInnerName() {
		return innerName;
	}
	public void setInnerName(String innerName) {
		this.innerName = innerName;
	}

}
