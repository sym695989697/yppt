package com.ctfo.sinoiov.mobile.webapi.base.plugin;

import java.io.Serializable;
import java.util.List;

/**
 * 
 *@Title:分页类  
 *@Description:  
 *@Author:fx  
 *@Since:2015-1-20  
 *@Version:1.1.0
 */
public class Page<T> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6348320639954706299L;
	
	private List<T> list;
	
	private long total;

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}
}
