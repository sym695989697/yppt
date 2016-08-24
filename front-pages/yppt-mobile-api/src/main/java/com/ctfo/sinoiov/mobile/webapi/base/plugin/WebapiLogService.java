package com.ctfo.sinoiov.mobile.webapi.base.plugin;

public interface WebapiLogService {

	public Page<WebApiServiceLog> findList(String servCode,String statusType,int page,int pageSize);
}
