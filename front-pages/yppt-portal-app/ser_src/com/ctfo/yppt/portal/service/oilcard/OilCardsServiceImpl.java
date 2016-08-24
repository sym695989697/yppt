package com.ctfo.yppt.portal.service.oilcard;

import java.math.BigDecimal;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.ctfo.base.service.ServiceImpl;
import com.ctfo.common.exception.BusinessException;
import com.ctfo.common.models.DynamicSqlParameter;
import com.ctfo.common.models.Index;
import com.ctfo.common.models.PaginationResult;
import com.ctfo.common.utils.Constants;
import com.ctfo.common.utils.Converter;
import com.ctfo.common.utils.GlobalMessage;
import com.ctfo.csm.annotations.AnnotationName;
import com.ctfo.csm.soa.ServiceFactory;
import com.ctfo.csm.uaa.intf.support.AAException;
import com.ctfo.file.boss.IFileService;
import com.ctfo.yppt.baseservice.card.beans.ICCard;
import com.ctfo.yppt.baseservice.card.beans.ICCardApplyDetail;
import com.ctfo.yppt.baseservice.card.beans.ICCardExampleExtended;
import com.ctfo.yppt.baseservice.card.intf.IICCardApplyManager;
import com.ctfo.yppt.baseservice.card.intf.IICCardManager;
import com.ctfo.yppt.baseservice.system.intf.IICCardStatManager;
import com.ctfo.yppt.baseservice.trade.beans.ICCardTradeInfo;
import com.ctfo.yppt.baseservice.trade.beans.ICCardTradeInfoHistory;
import com.ctfo.yppt.bean.ICCardApplyDetailExtend;
import com.ctfo.yppt.bean.ICCardApplyMetaBean;
import com.ctfo.yppt.portal.view.oilcard.ICCardVo;

/**
 * 油卡相关业务服务类
 * 
 * @author fuxiaohui
 *
 * @datetime 2015-1-20 下午04:42:26
 *
 */
@AnnotationName(name = "油卡业务服务")
@Service("oilCardService")
public class OilCardsServiceImpl extends ServiceImpl implements IOilCardsService {

	private static Log logger = LogFactory.getLog(OilCardsServiceImpl.class);

    private static IICCardManager icCardManager;
    private static IICCardApplyManager icCardApplyManager;
    private static IICCardStatManager icCardStatManager;
    private static IFileService fileService;
    //private static int MONEY_VALUE = 100;
	// 构造方法，将后台对象实例化
	private  OilCardsServiceImpl() {
	}
	static {
		logger.info(">>>>>>>>" + GlobalMessage.REMO_START_OBJECT);
		icCardManager = (IICCardManager) ServiceFactory.getFactory().getService(IICCardManager.class);
		icCardApplyManager = (IICCardApplyManager) ServiceFactory.getFactory().getService(IICCardApplyManager.class);
		icCardStatManager = (IICCardStatManager) ServiceFactory.getFactory().getService(IICCardStatManager.class);
		fileService = (IFileService) ServiceFactory.getFactory().getService(IFileService.class);
		logger.info(">>>>>>>>" + GlobalMessage.REMO_END_OBJECT);
	}
    
    /**
     * 统计油卡消费（加油）记录（实时）
     */
    @Override
    public BigDecimal countOilCardTradeInfo(DynamicSqlParameter requestParam, Index index) throws AAException {
        BigDecimal countOilCardTrade = new BigDecimal(0);
        return countOilCardTrade;
    }
    
    /**
     * 统计油卡消费（加油）记录（历史）
     */
    @Override
    public BigDecimal countOilCardTradeInfoHistory(DynamicSqlParameter requestParam, Index index) throws AAException {
        BigDecimal countOilCardTradeHistory = new BigDecimal(0);
        return countOilCardTradeHistory;
    }
	
    /**
     * 查询油卡消费（加油）记录（实时）
     */
    @Override
    public PaginationResult<ICCardTradeInfo> queryOilCardTradeInfoList(DynamicSqlParameter requestParam, Index index) throws AAException {
        PaginationResult<ICCardTradeInfo> result = new PaginationResult<ICCardTradeInfo>();
        return result;
    }
	
    /**
     * 查询油卡消费（加油）记录（历史）
     */
    @Override
    public PaginationResult<ICCardTradeInfoHistory> queryOilCardTradeInfoHistoryList(DynamicSqlParameter requestParam, Index index) throws AAException {
        PaginationResult<ICCardTradeInfoHistory> result = new PaginationResult<ICCardTradeInfoHistory>();
        return result;
    }
	
	/**
	 * 根据用户ID统计用户油卡数量
	 * @param requestParam
	 * @param index
	 * @return
	 * @throws AAException
	 */
	@Override
	public Integer countOilCardByUserId(String userId) throws AAException {
	    Integer countCard = 0;
	    try {
	        logger.debug(">>>>>>>>" + GlobalMessage.REMO_CALL_START);
	        if(StringUtils.isNotBlank(userId)) {
	            ICCardExampleExtended icCardExample = new ICCardExampleExtended();
	            icCardExample.createCriteria().andUserIdEqualTo(userId);
	            countCard = icCardManager.count(icCardExample);
	        } else {
	            logger.debug(">>>>>>>>账户所属用户不存在或ID为空");
	        }
	        logger.info(">>>>>>>>" + GlobalMessage.REMO_CALL_END);
        } catch (Exception e) {
            logger.error(GlobalMessage.REMO_EXCEPTION, e);
            throw new AAException(GlobalMessage.REMO_EXCEPTION, e);
        }
	    return countCard;
	}
	
	/**
	 * 查询我的油卡列表
	 */
    @Override
    public PaginationResult<ICCardVo> queryCardListPage(DynamicSqlParameter requestParam, String userId) throws AAException {
        PaginationResult<ICCardVo> result = new PaginationResult<ICCardVo>();
        try {
            logger.debug(">>>>>>>>" + GlobalMessage.REMO_CALL_START);
            if (StringUtils.isNotBlank(userId)) {
                //取其中的分页配置、时间参数
                ICCardExampleExtended icCardExample = (ICCardExampleExtended) Converter.paramToExampleExtended(requestParam, new ICCardExampleExtended());
                ICCardExampleExtended icExample = new ICCardExampleExtended();
                icExample.setEndNum(icCardExample.getEndNum());
                icExample.setLimitNum(icCardExample.getLimitNum());
                icExample.setSkipNum(icCardExample.getSkipNum());
                icExample.setOrderByClause(ICCard.fieldOpenCardTime() + " desc");
                //卡号/车牌号/手机号
                if(requestParam.getEqual() != null && StringUtils.isNotBlank(requestParam.getEqual().get("cardNumber"))){
                    ICCardExampleExtended icExample1 = new ICCardExampleExtended();
                    ICCardExampleExtended.Criteria criteria1 = icExample1.createCriteria();
                    criteria1.andUserIdEqualTo(userId);
                    criteria1.andCardNumberLike("%"+URLDecoder.decode(requestParam.getEqual().get("cardNumber"),"UTF-8")+"%");
                    icExample.or(criteria1);
                    
                    ICCardExampleExtended icExample2 = new ICCardExampleExtended();
                    ICCardExampleExtended.Criteria criteria2 = icExample2.createCriteria();
                    criteria2.andUserIdEqualTo(userId);
                    criteria2.andTelephoneNumberLike("%"+URLDecoder.decode(requestParam.getEqual().get("cardNumber"),"UTF-8")+"%");
                    icExample.or(criteria2);
                    
                    ICCardExampleExtended icExample3 = new ICCardExampleExtended();
                    ICCardExampleExtended.Criteria criteria3 = icExample3.createCriteria();
                    criteria3.andUserIdEqualTo(userId);
                    criteria3.andVehicleNoLike("%"+URLDecoder.decode(requestParam.getEqual().get("cardNumber"),"UTF-8")+"%");
                    icExample.or(criteria3);
                } else {
                    icExample.createCriteria().andUserIdEqualTo(userId);
                }
                PaginationResult<ICCard> icCardPaginator = icCardManager.paginate(icExample);
                
                List<ICCard> cardList = new ArrayList<ICCard>();
                List<ICCardVo> vardVoList = new ArrayList<ICCardVo>();
                if(!icCardPaginator.getData().isEmpty()){
                	cardList = icCardPaginator.getData();
                	for(ICCard o : cardList){
                		ICCardVo vo = new ICCardVo();
                		try {
                			PropertyUtils.copyProperties(vo, o);
						} catch (Exception e) {
                             logger.error("copy ICCard to ICCardVo error",e);
						}
                    	Date dt = new Date();
                    	SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM");
                    	String sDateTime = sdf.format(dt); 
                         
                    	//余额
                    	if(o.getBalance()==null){
                    		vo.setBalance(new BigDecimal(0));
                    	}else{
                    		vo.setBalance(o.getBalance().divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP));
                    	}
                    	try {
                    		
                    	   // 累计充值只统计本月
                    	   // 累计消费只统计上月
                          
                    		BigDecimal a[] = icCardStatManager.getICCardLastConsumptionAndRecharge(userId,sDateTime);
                        	if(a[0]!=null){
                        		vo.setNowMonthRecharge(a[0]);
                    	    }else{
                    	    	vo.setNowMonthRecharge(new BigDecimal(0.00));
                    	    }
                        	if(a[1]!=null){
                        		vo.setLastMonthExpense(a[1]);
                        	}else{
                        		vo.setLastMonthExpense(new BigDecimal(0.00));
                        	}
						} catch (Exception e) {
							vo.setNowMonthRecharge(new BigDecimal(0.00));
							vo.setLastMonthExpense(new BigDecimal(0.00));
							logger.error("通过icCardStatManager.getICCardLastConsumptionAndRecharge(查询单卡上月累计充值等失败",e);
						}
						vardVoList.add(vo);
                	}
                	 result.setData(vardVoList);
                }
               
                result.setTotal(icCardPaginator.getTotal());
                result.setStart(requestParam.getPage());
                result.setMessage(GlobalMessage.SUCCESS);
                result.setDataObject(Constants.OPER_SUCCESS);
            } else {
                result.setMessage(GlobalMessage.FAIL);
                result.setDataObject(Constants.OPER_FAIL);
                logger.debug(">>>>>>>>账户所属组织机构ID为空【requestParam.equal.userId=" + userId + "】");
            }
            logger.info(">>>>>>>>" + GlobalMessage.REMO_CALL_END);
        } catch (Exception e) {
            result.setDataObject(Constants.OPER_ERROR);
            result.setMessage(GlobalMessage.FAIL);
            logger.error(GlobalMessage.REMO_EXCEPTION, e);
            throw new AAException(GlobalMessage.REMO_EXCEPTION, e);
        }
        return result;
    }
    
    /**
	 * 查询我的油卡申请列表
	 */
    @Override
    public PaginationResult<ICCardApplyDetailExtend> queryCardListPageApply(DynamicSqlParameter requestParam, String userId) throws AAException {
        PaginationResult<ICCardApplyDetailExtend> result = new PaginationResult<ICCardApplyDetailExtend>();
        try {
        	Map<String, String> map = new HashMap<String, String>();
        	map.put("userId", userId);
        	if(requestParam.getEqual()!=null){
        		if(requestParam.getEqual().get("cardNumber")!=null&&!"null".equals(requestParam.getEqual().get("cardNumber"))){
        			map.put("condition", URLDecoder.decode(requestParam.getEqual().get("cardNumber"),"UTF-8"));
        			//map.put("vehicleNo", URLDecoder.decode(requestParam.getEqual().get("cardNumber"),"UTF-8"));
        		}
        	}
        	int page = requestParam.getPage();
        	int rows = requestParam.getRows();
        	map.put("start", String.valueOf((page-1)*rows+1));
        	map.put("limit", String.valueOf(requestParam.getRows()));
        	try {
        		result = icCardApplyManager.queryProcessCard(map);
			} catch (Exception e) {
                logger.error("调用icCardApplyManager.queryProcessCard查询申请中的卡列表失败",e);
				throw new AAException("调用icCardApplyManager.queryProcessCard查询申请中的卡列表失败",e);
			}
        } catch (Exception e) {
            result.setDataObject(Constants.OPER_ERROR);
            result.setMessage(GlobalMessage.FAIL);
            logger.error(GlobalMessage.REMO_EXCEPTION, e);
            throw new AAException(GlobalMessage.REMO_EXCEPTION, e);
        }
        return result;
    }
	
	/**
	 * 开卡申请
	 */
	@Override
	public String openCardApply(ICCardApplyMetaBean icCardApplyMetaBean, Index index) throws AAException {
		String icCardApplyId = null;
		try {
		    //卡申请
		    icCardApplyMetaBean.getiCCardApply().setUserId(index.getUserId());
		    icCardApplyMetaBean.getiCCardApply().setOpenCardProvince(transArea(icCardApplyMetaBean.getiCCardApply().getOpenCardProvince()));
		    icCardApplyMetaBean.getiCCardApply().setOpenCardCity(transArea(icCardApplyMetaBean.getiCCardApply().getOpenCardCity()));
		    icCardApplyMetaBean.getiCCardApply().setProvince(transArea(icCardApplyMetaBean.getiCCardApply().getProvince()));
			icCardApplyMetaBean.getiCCardApply().setCity(transArea(icCardApplyMetaBean.getiCCardApply().getCity()));
			icCardApplyMetaBean.getiCCardApply().setDistrict(transArea(icCardApplyMetaBean.getiCCardApply().getDistrict()));
			icCardApplyMetaBean.getiCCardApply().setDataSource("0");
			icCardApplyMetaBean.getiCCardApply().setUserName(index.getUserName());
			icCardApplyMetaBean.getiCCardApply().setCardType(icCardApplyMetaBean.getiCCardApply().getCardType());
			icCardApplyMetaBean.getiCCardApply().setUserType(index.getUserType());
			icCardApplyMetaBean.getiCCardApply().setUserRegPhone(index.getMobile());
			icCardApplyMetaBean.getiCCardApply().setCreator(index.getUserId());
			//iCCardApply.setUserCode(index.getStaffCode());
			icCardApplyMetaBean.getiCCardApply().setStatus("0");
			icCardApplyMetaBean.getiCCardApply().setCreateTime(new Date().getTime());
			icCardApplyMetaBean.getiCCardApply().setApplyType("01");
			//卡申请明细
			for(ICCardApplyDetail iCCardApplyDetail : icCardApplyMetaBean.getiCCardApplyDetail()) {
			    iCCardApplyDetail.setCardType(icCardApplyMetaBean.getiCCardApply().getCardType());
			}
			logger.debug(">>>>>>>>" + GlobalMessage.REMO_CALL_START);
			try {
				icCardApplyId = icCardApplyManager.addIcCardApply(icCardApplyMetaBean);
			} catch (Exception e) {
				logger.error("调用后台icCardApplyManager.addIcCardApply失败"+e);
				throw new  Exception("调用后台icCardApplyManager.addIcCardApply失败",e);
			}
			logger.info(">>>>>>>>" + GlobalMessage.REMO_CALL_END);
		} catch (Exception e) {
	        logger.error(GlobalMessage.REMO_EXCEPTION, e);
	        throw new AAException(GlobalMessage.REMO_EXCEPTION, e);
		}
		return icCardApplyId;
	}

    /**
     * 转换省市县/区编码
     * 
     * @param str
     * @return
     */
    private String transArea(String str) {
        String areaCode = str;
        if (StringUtils.isNotBlank(str)) {
            if (str.length() == 2) {
                areaCode = str + "0000";
            } else if (str.length() == 4) {
                areaCode = str + "00";
            }
        }
        return areaCode;
    }

    

    /**
     * 查询办理中的油卡详情
     */
    @Override
    public PaginationResult<?> queryOilCardDetail(String cardApplyDetailId) {
        PaginationResult<ICCardApplyMetaBean> result = new PaginationResult<ICCardApplyMetaBean>();
        try {
            ICCardApplyMetaBean icCCardApplyMetaBean = icCardApplyManager.getIcCardApplyDetail(cardApplyDetailId);
        	List<ICCardApplyDetail> detailList = new ArrayList<ICCardApplyDetail>();
        	if(!icCCardApplyMetaBean.getiCCardApplyDetail().isEmpty()){
        		detailList = icCCardApplyMetaBean.getiCCardApplyDetail();
        		List<ICCardApplyDetail> list = new ArrayList<ICCardApplyDetail>();
        		for(ICCardApplyDetail o : detailList){
        			if(StringUtils.isNotBlank(o.getVehicleLicense())){
        				o.setVehicleLicense(fileService.getFileURL(o.getVehicleLicense(), IFileService.IMAGE_MID));
        				list.add(o);
        			}
        		}
        		icCCardApplyMetaBean.setiCCardApplyDetail(null);
        		icCCardApplyMetaBean.setiCCardApplyDetail(list);
        	}
        	
            result.setDataObject(icCCardApplyMetaBean);
            result.setMessage(GlobalMessage.SUCCESS);
        } catch (BusinessException e) {
            result.setDataObject(Constants.OPER_ERROR);
            result.setMessage(GlobalMessage.FAIL);
            logger.error(GlobalMessage.REMO_EXCEPTION, e);
            throw new AAException(GlobalMessage.REMO_EXCEPTION, e);
        }
        return result;
    }

    /**
     * 更新卡信息
     */
	@Override
	public boolean updateCard(ICCard iccard, Index index) throws AAException {
		
		boolean flag = false;
		ICCard card = new ICCard();
		card.setId(iccard.getId());
		card.setModifinguser(index.getUserId());
		card.setModifiedTime((new Date()).getTime());
		try {
		if(StringUtils.isNotBlank(iccard.getVehicleNo())){
			card.setVehicleNo(URLDecoder.decode(iccard.getVehicleNo(),"UTF-8"));
			//单独接口车牌号
			try {
				icCardManager.updateICCardVehicleById(card.getId(),card.getVehicleNo(),null,index.getUserId());
				flag = true;
			} catch (Exception e) {
				flag = false;
				logger.error("调用icCardManager.updateICCardVehicleById更新车牌号失败",e);
				throw new AAException("调用icCardManager.updateICCardVehicleById更新车牌号失败",e);
			}
		}
		if(StringUtils.isNotBlank(iccard.getTelephoneNumber())){
			card.setTelephoneNumber(iccard.getTelephoneNumber());
			try {
				icCardManager.update(card);
				flag = true;
			} catch (Exception e) {
				flag = false;
				logger.error("调用icCardManager.update更新卡对象失败",e);
				throw new AAException("调用icCardManager.update更新卡对象失败",e);
			}
		}
		} catch (Exception e) {
			flag = false;
			logger.error("更新卡对象失败",e);
			throw new AAException("更新卡对象失败",e);
		}
		
		return flag;
	}
}
