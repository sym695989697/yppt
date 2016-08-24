package com.ctfo.sinoiov.mobile.webapi.base.plugin.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ctfo.file.boss.IMongoService;
import com.ctfo.sinoiov.mobile.webapi.base.plugin.WebapiLogService;
import com.ctfo.sinoiov.mobile.webapi.base.plugin.WebApiServiceLog;
import com.ctfo.sinoiov.mobile.webapi.base.plugin.Page;
import com.google.code.morphia.query.Query;

@Service
public class WebapiLogServiceImpl implements WebapiLogService {
	
	protected static final Log log = LogFactory.getLog(WebapiLogServiceImpl.class);

	@Autowired(required =false)
	private IMongoService mongoService;

	@Override
	public Page<WebApiServiceLog> findList(String servCode, String statusType,int page,int pageSize) {


		Page<WebApiServiceLog> p = new Page<WebApiServiceLog>();
		mongoService.setDatasource("LOGS");
		try {
			Query<WebApiServiceLog> query = mongoService.getQuery(WebApiServiceLog.class);
			
			if(StringUtils.isNotBlank(servCode)){
				query.field("action").contains(servCode);
			}
			if(StringUtils.isNotBlank(statusType)){
				query.field("state").contains(statusType);
			}
			query.order("-handleTime");
			query.offset((page - 1) * pageSize);
			query.limit(pageSize);
			List<WebApiServiceLog> listMongo = mongoService.query(WebApiServiceLog.class, query);
			p.setList(listMongo);
			p.setTotal(mongoService.getCount());
		} catch (Exception e) {
			// TODO: handle exception
			log.error("查询外部应用调用日志发生错误：", e);
		}
		return p;
	}
	
	
}
