package com.ctfo.sinoiov.mobile.webapi.base.plugin.impl;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.stereotype.Service;

import com.ctfo.sinoiov.mobile.webapi.base.plugin.IServicePlug;
import com.ctfo.sinoiov.mobile.webapi.base.plugin.WebApiServiceLog;


@Service
public class LogServicePlugImpl implements IServicePlug {
	private List<String> actions;
	private DataSource dataSource;

	public void process(WebApiServiceLog serviceLog) {
		
	}

	public List<String> getActions() {
		return actions;
	}

	public void setActions(List<String> actions) {
		this.actions = actions;
	}

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

}
