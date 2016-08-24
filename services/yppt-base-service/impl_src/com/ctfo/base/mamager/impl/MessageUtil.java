/**
 * 北京中交兴路车联网科技有限公司 2011 版权所有.
 */
package com.ctfo.base.mamager.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ctfo.common.exception.BusinessException;
import com.ctfo.csm.soa.ServiceFactory;
import com.ctfo.file.boss.IMongoService;
import com.ctfo.yppt.bean.MessageSignature;
import com.ctfo.yppt.bean.MessageTemplate;
import com.google.code.morphia.query.Query;

/**
 * 
 * 类说明.
 * 
 * <pre>
 * 修改日期        修改人    修改原因
 * 2015年2月5日    dxs    新建
 * </pre>
 */
public class MessageUtil {
    private static Log logger = LogFactory.getLog(MessageUtil.class);
    
    /**
     * 
     *
     * 根据模板分隔符统计发送条数
     *
     * @param template  模板内容
     * @param ar   分隔符
     * @return  返回短信条数
     *
     * <pre>
     * 修改日期        修改人    修改原因
     * 2015年2月5日    dxs    新建
     * </pre>
     */
    public static int getSubStringCount(String template, String ar){
        int count = 0;
        int num = 0;
        int temp = 0;
        while((template.length() - temp) > 0){
            num = template.indexOf(ar, temp);
            if(num == -1){
                temp = template.length();
            }else{
                count++;
                temp = num + 3;
            }
        }
        return count;
    }
    
    
    /**
     * 
     *
     * 为模板变量赋予实际值
     *
     * @param template  模板
     * @param parameter  模板变量对应的实际值
     * @return  返回模板变量替换后的文本内容
     *
     * <pre>
     * 修改日期        修改人    修改原因
     * 2015年2月5日    dxs    新建
     * </pre>
     */
    public static String putParameterIntoTemplate(String template, List parameter){
        String content = "";
        if(parameter == null || parameter.size() == 0){
            content = template;
        }else{
            if(getSubStringCount(template, "<_>") == parameter.size()){
                for(int i = 0 ; i < parameter.size() ; i++){
                    template = template.replaceFirst("<_>", parameter.get(i).toString());
                }
                content = template;
            }
        }
        return content;
    }
    
    /**
     * 
     * <pre>
     * 短信发送功能，无模板变量，签名需要自己指定。
     * 发送请使用  根据模板编码发送短信 功能
     * </pre>
     * @param mobiles
     * @param content
     * @return
     * @throws BusinessException
     *
     * <pre>
     * 修改日期        修改人    修改原因
     * 2015年2月5日    dxs    新建
     * </pre>
     */
    public static String sendShortMessage(String mobiles, String content) throws BusinessException {
        String code = "success";
        try {
            if(content.length() > 70 && content.indexOf(">_<") >= 0){//短信长度大于70，并且内容包含分隔标示符
                String sign = "";
                if(content.indexOf("【") != -1 && content.indexOf("】") != -1){
                    sign = content.substring(content.indexOf("【"), content.indexOf("】") + 1);
                    content = content.replace(sign, "");
                }
                String contents [] = content.split(">_<");
                for(int i=0;i<contents.length;i++){
                    String partContent = sign + "("+(i+1)+"/"+contents.length+")" + contents[i];
                    ServiceUtil.getMessageService().sendMessage(mobiles, partContent);
                }
            }else{
                ServiceUtil.getMessageService().sendMessage(mobiles, content.replace(">_<", ""));
            }
        } catch (Exception e) {
            code="fail";
            logger.info(String.format("发送短信失败%s", e));
            throw new BusinessException("发送短信失败", e);
        }
        return code;
    }

    /**
     * 
     *
     * 根据模板编码发送短信
     *
     * @param mobiles 手机号,多个手机号使用","分隔
     * @param templateCode  模板编码
     * @param parameter  模板变量值列表
     * @return  返回成功失败标志（success，fail）
     * @throws BusinessException
     *
     * <pre>
     * 修改日期        修改人    修改原因
     * 2015年2月5日    dxs    新建
     * </pre>
     */
    public static String sendShortMessage(String mobiles, String templateCode, List parameter) 
            throws BusinessException {
        String code = "success";
        String content = "";
        try {
            IMongoService<MessageTemplate> messageTemplateMongoService = (IMongoService<MessageTemplate>) ServiceFactory.getFactory().getService(IMongoService.class);
            Query<MessageTemplate> query = messageTemplateMongoService.getQuery(MessageTemplate.class);
            query.and(query.criteria("templateCode").equal(templateCode)).and(query.criteria("state").equal("1"));
            List<MessageTemplate> listMongo = messageTemplateMongoService.query(MessageTemplate.class, query);
            if(listMongo != null && listMongo.size() > 0){
                content = putParameterIntoTemplate(listMongo.get(0).getContent(), parameter);
                if(content.equals("")){
                    logger.info("传递的参数与模板所需的参数不匹配");
                    throw new BusinessException("传递的参数与模板所需的参数不匹配");
                }
            }else{
                logger.info("获取不到对应的短信模板");
                throw new BusinessException("获取不到对应的短信模板");
            }
        } catch (Exception e) {
            logger.info(String.format("获取短信内容失败%s", e));
            throw new BusinessException("获取短信内容失败", e);
        }
        if(content.equals("")){
            code="fail";
            return code;
        }
        try {
            IMongoService<MessageSignature> messageSignatureMongoService = (IMongoService<MessageSignature>) ServiceFactory.getFactory().getService(IMongoService.class);
            Query<MessageSignature> query = messageSignatureMongoService.getQuery(MessageSignature.class);
            query.limit(1);
            List<MessageSignature> listSignatureMongo = messageSignatureMongoService.query(MessageSignature.class, query);
            if(listSignatureMongo != null && listSignatureMongo.size() > 0){
                if(StringUtils.isNotEmpty(listSignatureMongo.get(0).getSign().trim())){
                    content = "【"+listSignatureMongo.get(0).getSign()+"】" + content;    
                }
            }else{
                logger.info("获取不到对应的短信签名");
                throw new BusinessException("获取不到对应的短信签名");
            }
        } catch (Exception e) {
            logger.info("获取不到对应的短信签名");
            throw new BusinessException("获取短信签名失败", e);
        }
        code = sendShortMessage(mobiles, content);
        return code;
    }

}
