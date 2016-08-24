package com.ctfo.chpt.service.iccard.messagetemplate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import com.ctfo.common.models.DynamicSqlParameter;
import com.ctfo.common.models.PaginationResult;
import com.ctfo.common.models.Rule;
import com.ctfo.common.utils.GlobalMessage;
import com.ctfo.csm.soa.ServiceFactory;
import com.ctfo.csm.utils.BeanCopierUtils;
import com.ctfo.file.boss.IMongoService;
import com.ctfo.yppt.bean.MessageSignature;
import com.ctfo.yppt.bean.MessageTemplate;
import com.google.code.morphia.query.Query;

@Service("MessageTemplateServiceImpl")
public class MessageTemplateServiceImpl  implements IMessageTemplateService{
	
	private static Log logger = LogFactory.getLog(MessageTemplateServiceImpl.class);
	private IMongoService<MessageTemplate> messageTemplateMongoService;
	private IMongoService<MessageSignature> messageSignMongoService;
	
	private MessageTemplateServiceImpl(){
		messageTemplateMongoService = (IMongoService<MessageTemplate>) ServiceFactory.getFactory().getService(IMongoService.class);
		messageSignMongoService = (IMongoService<MessageSignature>) ServiceFactory.getFactory().getService(IMongoService.class);
	}

	@Override
	public String createMessageTemplate(DynamicSqlParameter param) throws Exception {
		// TODO Auto-generated method stub
		logger.debug(">>>>>>>>" + GlobalMessage.REMO_CALL_START);
		
		String id = UUID.randomUUID().toString();
		
		MessageTemplate bean = new MessageTemplate();
		bean.setId(id);
		bean.setTemplateCode(param.getEqual().get("templateCode"));
		bean.setTemplateName(param.getEqual().get("templateName"));
		bean.setContent(param.getEqual().get("content"));
		bean.setState("0");
		bean.setTime(String.valueOf(new Date().getTime()));
		messageTemplateMongoService.save(bean);
		
		logger.info(">>>>>>>>" + GlobalMessage.REMO_CALL_END);
		
		return "1";
	}


	public PaginationResult<?> initMessageTemplateGrid() throws Exception {
		// TODO Auto-generated method stub
		logger.debug(">>>>>>>>" + GlobalMessage.REMO_CALL_START);
		
		PaginationResult result = new PaginationResult();
		
		Query<MessageTemplate> query = messageTemplateMongoService.getQuery(MessageTemplate.class);
		query.order("-time");
		List<MessageTemplate> listMongo = messageTemplateMongoService.query(MessageTemplate.class, query);
		
		result.setData(listMongo);
		
		logger.info(">>>>>>>>" + GlobalMessage.REMO_CALL_END);
		
		return result;
	}

	public String stopMessageTemplate(DynamicSqlParameter param)
			throws Exception {
		// TODO Auto-generated method stub
		logger.debug(">>>>>>>>" + GlobalMessage.REMO_CALL_START);
		
		Query<MessageTemplate> query = messageTemplateMongoService.getQuery(MessageTemplate.class);
		messageTemplateMongoService.convertParamToQuery(query, convertDynamicSqlParameter(param));
		List<MessageTemplate> listMongo = messageTemplateMongoService.query(MessageTemplate.class, query);
		
		if(listMongo != null && listMongo.size() > 0){
			MessageTemplate bean = listMongo.get(0);
			bean.setState("0");
//			bean.setTime(String.valueOf(new Date().getTime()));
			messageTemplateMongoService.update(bean);
		}
		
		logger.info(">>>>>>>>" + GlobalMessage.REMO_CALL_END);
		
		return "1";
	}

	public String startMessageTemplate(DynamicSqlParameter param)
			throws Exception {
		// TODO Auto-generated method stub
		logger.debug(">>>>>>>>" + GlobalMessage.REMO_CALL_START);
		
		Query<MessageTemplate> query = messageTemplateMongoService.getQuery(MessageTemplate.class);
		messageTemplateMongoService.convertParamToQuery(query, convertDynamicSqlParameter(param));
		List<MessageTemplate> listMongo = messageTemplateMongoService.query(MessageTemplate.class, query);
		
		if(listMongo != null && listMongo.size() > 0){
			MessageTemplate bean = listMongo.get(0);
			bean.setState("1");
//			bean.setTime(String.valueOf(new Date().getTime()));
			messageTemplateMongoService.update(bean);
		}
		
		logger.info(">>>>>>>>" + GlobalMessage.REMO_CALL_END);
		
		return "1";
	}

	public String deleteMessageTemplate(DynamicSqlParameter param)
			throws Exception {
		// TODO Auto-generated method stub
		logger.debug(">>>>>>>>" + GlobalMessage.REMO_CALL_START);
		
		messageTemplateMongoService.delete(MessageTemplate.class, param.getEqual().get("id"));
		
		logger.info(">>>>>>>>" + GlobalMessage.REMO_CALL_END);
		
		return "1";
	}

	public String updateMessageTemplate(DynamicSqlParameter param)
			throws Exception {
		// TODO Auto-generated method stub
		logger.debug(">>>>>>>>" + GlobalMessage.REMO_CALL_START);
		
		String content = param.getEqual().get("content");
		param.getEqual().remove("content");
		Query<MessageTemplate> query = messageTemplateMongoService.getQuery(MessageTemplate.class);
		
		messageTemplateMongoService.convertParamToQuery(query, convertDynamicSqlParameter(param));
		List<MessageTemplate> listMongo = messageTemplateMongoService.query(MessageTemplate.class, query);
		
		if(listMongo != null && listMongo.size() > 0){
			MessageTemplate bean = listMongo.get(0);
			bean.setContent(content);
//			bean.setTime(String.valueOf(new Date().getTime()));
			messageTemplateMongoService.update(bean);
		}
		
		logger.info(">>>>>>>>" + GlobalMessage.REMO_CALL_END);
		
		return "1";
	}

	public String modifySign(DynamicSqlParameter param) throws Exception {
		// TODO Auto-generated method stub
		logger.debug(">>>>>>>>" + GlobalMessage.REMO_CALL_START);
		
		
		Query<MessageSignature> query = messageSignMongoService.getQuery(MessageSignature.class);
		query.limit(1);
		List<MessageSignature> listMongo = messageSignMongoService.query(MessageSignature.class, query);
		if(listMongo==null || listMongo.size()<=0){
		    MessageSignature ms=new MessageSignature();
		    ms.setSign(param.getEqual().get("sign"));
		    messageSignMongoService.save(ms);
		}else{
		    messageSignMongoService.delete(MessageSignature.class, new ObjectId(listMongo.get(0).getId()));
            MessageSignature ms=new MessageSignature();
            ms.setSign(param.getEqual().get("sign"));
            messageSignMongoService.save(ms);
		}
		
		logger.info(">>>>>>>>" + GlobalMessage.REMO_CALL_END);
		
		return "1";
	}

	public PaginationResult<?> querySign() throws Exception {
		// TODO Auto-generated method stub
		logger.debug(">>>>>>>>" + GlobalMessage.REMO_CALL_START);
		
		PaginationResult result = new PaginationResult();
		
		Query<MessageSignature> query = messageSignMongoService.getQuery(MessageSignature.class);
		query.limit(1);
		List<MessageSignature> listMongo = messageSignMongoService.query(MessageSignature.class, query);
		
		result.setData(listMongo);
		
		logger.info(">>>>>>>>" + GlobalMessage.REMO_CALL_END);
		
		return result;
	}
	
	public com.ctfo.csm.local.DynamicSqlParameter convertDynamicSqlParameter(DynamicSqlParameter param){
	    com.ctfo.csm.local.DynamicSqlParameter dsp=new com.ctfo.csm.local.DynamicSqlParameter();
	    dsp.setEndwith(param.getEndwith());
	    dsp.setEqual(param.getEqual());
	    dsp.setInMap(param.getInMap());
	    dsp.setLike(param.getLike());
	    dsp.setNoId(param.getNoId());
	    dsp.setNotequal(param.getNotequal());
	    dsp.setNotInMap(param.getNotInMap());
	    dsp.setOp(param.getOp());
	    dsp.setOrder(param.getOrder());
	    dsp.setPage(param.getPage());
	    dsp.setPagesize(param.getPagesize());
	    dsp.setRows(param.getRows());
	    dsp.setSort(param.getSort());
	    dsp.setStartwith(param.getStartwith());
	    dsp.setUpdateValue(param.getUpdateValue());
	    if(param.getRules()!=null){
	        List<com.ctfo.csm.local.Rule> list=new ArrayList<com.ctfo.csm.local.Rule>();
	        for(Rule rule:param.getRules()){
	            com.ctfo.csm.local.Rule bean=new com.ctfo.csm.local.Rule();
	            bean.setField(rule.getField());
	            bean.setOp(rule.getOp());
	            bean.setType(rule.getType());
	            bean.setValue(rule.getValue());
	            list.add(bean);
	        }
	        dsp.setRules(list);
	    }
	    return dsp;
	    
	}
}
