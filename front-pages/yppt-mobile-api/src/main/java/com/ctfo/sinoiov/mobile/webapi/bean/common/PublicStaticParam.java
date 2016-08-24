package com.ctfo.sinoiov.mobile.webapi.bean.common;

import java.math.BigDecimal;

import org.apache.commons.lang3.StringUtils;

/**
 * 
 * 
 * <p>
 * -----------------------------------------------------------------------------
 * <br>
 * 工程名 ： yppt-mobile-webapi
 * <br>
 * 功能：定义公共的静态参数/方法
 * <br>
 * 描述：
 * <br>
 * 授权 : (C) Copyright (c) 2011
 * <br>
 * 公司 : 北京中交车联科技服务有限公司
 * <br>
 * -----------------------------------------------------------------------------
 * <br>
 * 修改历史
 * <br>
 * <table width="432" border="1">
 * <tr><td>版本</td><td>时间</td><td>作者</td><td>改变</td></tr>
 * <tr><td>1.0</td><td>2015-1-30</td><td>JiangXF</td><td>创建</td></tr>
 * </table>
 * <br>
 * <font color="#FF0000">注意: 本内容仅限于[北京中交兴路车联网科技有限公司]内部使用，禁止转发</font>
 * <br>
 * 
 * @version 1.0
 * 
 * @author JiangXF
 * @since JDK1.6
 */
public class PublicStaticParam {
	
	/**
	 * 返回客户端状态
	 */
	public static String RESULT_SUCCESS = "0"; // 成功
	
	public static String RESULT_FAIL = "1"; // 失败
	
	
	// 分页参数
	public class PageValue {
		public final static int page = 1;
		public final static int pageSize = 5;
	}
	
	/**
	 * 用于计算
	 */
	public static int CALCULATE_VALUE = 100;
	

	/**
	 * 数据来源
	 *  0:门户,1:手机客户端;2:后台管理系统
	 */
	public static String DATA_SOURCE = "1";	
	
	/**
	 * IC卡申请状态  
	 * 00：客服专员待审核
	 * 01：客服专员审核中
	 * 02：客服专员审核通过
	 * 03：卡务专员待审核
	 * 04：卡务专员审核中
	 * 05：卡务专员审核通过
	 * 06：行政专员待审核
	 * 07：行政专员审核中
	 * 08：行政专员审核通过
	 * 10：末通过 
	 */
	public static String IC_CARD_APPLY_STATUS = "00";		
	
	/**
	 * IC卡申请类型
	 */
	public static String IC_CARD_APPLY_TYPE = "01";	
	
	/**
	 * 发票类型
	 * 01 普通发票
	 * 02 增值税发票
	 */
	public static String BILL_COMMON_TYPE = "01";	//普通发票
	
	public static String BILL_TAX_TYPE = "02";		//增值税发票
	
	/**
	 * 码表值（发票类型编码）
	 */
	public static String BILL_TYPE = "IC-INVOICE-TYPE";
	
	/**
	 * 开票申请状态
	 * 0:审核中,1:申请成功;2:申请失败,3:处理中
	 */
	public static String BILL_APPLY_STATUS = "1";
	
	/**
	 * IC卡类型
	 * 
	 */
	public static String IC_CARD_TYPE = "IC-CARD-TYPE";
	
	/**
	 * IC卡类型：中石油
	 */
	public static String IC_CARD_TYPE_1 = "CARD-TYPE-01";
	
	/**
	 * IC卡类型：中石华
	 */
	public static String IC_CARD_TYPE_2 = "CARD-TYPE-02";
	
	
	
	/**
	 * 加油交易类型
	 */
	public static String IC_TRADE_STATUS_JY = "IC-GAS";
	/**
	 * 充值交易类型
	 */
	public static String IC_TRADE_STATUS_CZ = "IC-RECHARGE";
	
	/**
	 * 车牌颜色
	 */
	public static String VEHICLE_COLOR = "V-C-COLOR";
	
	/**
	 * 支付方式
	 */
	public static String  PAY_MODE = "IC-PAY-WAY";
	//帐户支付
	public static String ACCOUNT_MODE = "ACCOUNT";
	
	/**
	 * 分换算成元
	 * @param fen
	 * @return
	 */
	public static BigDecimal fen2Yuan(BigDecimal fen) {
		if(fen == null){
			return new BigDecimal(0);
		}
		BigDecimal yuan = fen.divide(new BigDecimal(CALCULATE_VALUE), 2, BigDecimal.ROUND_HALF_UP);
		return yuan;
	}
	
	/**
	 * 元换算成分
	 * @param yuan
	 * @return
	 */
	public static BigDecimal yuan2Fen(BigDecimal yuan) {
		if(yuan == null){
			return new BigDecimal(0);
		}
		BigDecimal fen = yuan.multiply(new BigDecimal(CALCULATE_VALUE)).setScale(0, BigDecimal.ROUND_HALF_UP);
		return fen;
	}
	
	/**
	 * 将省编码转换成6位编码
	 * @param pCode
	 * @return
	 */
	public static String toProvince(String pCode){
		if(StringUtils.isBlank(pCode)){
			return null;
		}
		return pCode.substring(0, 2)+"0000";
	}
	/**
	 * 将市编码转换成6位编码
	 * @param cCode
	 * @return
	 */
	public static String toCity(String cCode){
		if(StringUtils.isBlank(cCode)){
			return null;
		}
		return cCode.substring(0, 4)+"00";
	}
	
	
	
	public static void main(String[] args) {
		String areas[] = "11,13,14".split(",");
		String oftenArea = "";
		for(int i =0;i<areas.length;i++){
			String s =areas[i];
			if(i>0 && i<areas.length){
				oftenArea += ",";
			}
			oftenArea += PublicStaticParam.toProvince(s);
		}
		System.out.println(oftenArea);
	}
}
