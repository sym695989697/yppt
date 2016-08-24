package com.ctfo.yppt.baseservice.card.cons;

/**
 * 
 * 
 * IC模块常量类.
 * 
 * <pre>
 * 修改日期        修改人    修改原因
 * 2015年1月22日    dxs    新建
 * </pre>
 */
public class ICCardCons {

    /**
     * 呼叫中心待审核
     * */
    public static final String APPLY_AUDIT_STATE_CALL_CENTER_YET = "00";
    /**
     * 呼叫中心审核中
     * */
    public static final String APPLY_AUDIT_STATE_CALL_CENTER_ING = "01";
    /**
     * 呼叫中心审核通过
     * */
    public static final String APPLY_AUDIT_STATE_CALL_CENTER_PASS = "02";

    /**
     * 卡务待审核
     * */
    public static final String APPLY_AUDIT_STATE_CARD_BUSINESS_YET = "03";
    /**
     * 卡务审核中
     * */
    public static final String APPLY_AUDIT_STATE_CARD_BUSINESS_ING = "04";
    /**
     * 卡务审核通过
     * */
    public static final String APPLY_AUDIT_STATE_CARD_BUSINESS_PASS = "05";

    /**
     * 行政待审核
     * */
    public static final String APPLY_AUDIT_STATE_ADMINISTRATIVE_YET = "06";
    /**
     * 行政审核中
     * */
    public static final String APPLY_AUDIT_STATE_ADMINISTRATIVE_ING = "07";
    /**
     * 行政审核通过
     * */
    public static final String APPLY_AUDIT_STATE_ADMINISTRATIVE_PASS = "08";

    /**
     * 所有审核通过(与最后一步相同，现在为“行政审核通过”)
     * */
    public static final String APPLY_AUDIT_STATE_ALL_PASS = "08";

    /**
     * 审核不通过
     * */
    public static final String APPLY_AUDIT_STATE_NOT_PASS = "10";
    
    
    /**
     * 操作成功
     * */
    public static final String OPER_SUCCESS = "success";
    /**
     * 操作失败
     * */
    public static final String OPER_FAIL = "fail";
    
    /**
     * 车辆默认颜色（黄色）
     * */
    public static final String VEHICLE_COLOR_DEFAULT="1";
    
    /**
     * 数据来源-门户
     * */
    public static final String DATA_SOURCE_WEB="0";
    /**
     * 数据来源-手机端
     * */
    public static final String DATA_SOURCE_MOBILE="1";
    /**
     * 数据来源-管理端
     * */
    public static final String DATA_SOURCE_APP="2";
    
    /**
     * IC申请类型-开卡
     * */
    public static final String IC_CARD_APPLY_TYPE_OPEN="01";
    
    /**
     * 旺金币结算状态-未结算
     * */
    public static final String CURRECTY_STATUS_NO="0";
    /**
     * 旺金币结算状态-已结算
     * */
    public static final String CURRECTY_STATUS_YES="1";
    
    
    /**
     * 空卡导入进度session key
     * */
    public static final String BLANK_CARD_IMPORT_PROCESS_KEY="blank_card_import_process_key";
    
    /**
     * 批量开卡导入进度session key
     * */
    public static final String OPEN_PATCH_CARD_PROCESS_KEY="open_patch_card_process_key";

    /**
     * 人工返利导入session key
     * */
    public static final String REBATE_HISTORY_IMPORT_PROCESS_KEY="rebate_history_import_process_key";
    /**
     * 空卡导入结果信息session key
     * */
    public static final String BLANK_CARD_IMPORT_MESSAGE_KEY="blank_card_import_message_key";
    
    /**
     * 批量开卡导入结果信息session key
     * */
    public static final String OPEN_PATCH_CARD_MESSAGE_KEY="open_patch_card_message_key";
    
    /**
     * 人工返利导入结果信息session key
     * */
    public static final String REBATE_HISTORY_IMPORT_MESSAGE_KEY="rebate_history_import_message_key";
    
    /**
     * 对账导入结果信息session key
     * */
    public static final String RECONCILIATION_SY_MESSAGE_KEY="reconciliation_sy_import_message_key";
    
    /**
     * 对账导入进度session key
     * */
    public static final String RECONCILIATION_SY_PROCESS_KEY="reconciliation_sy_import_process_key";
    
    /**
     * 对账导入结果信息session key
     * */
    public static final String RECONCILIATION_SH_MESSAGE_KEY="reconciliation_sh_import_message_key";
    
    /**
     * 对账导入进度session key
     * */
    public static final String RECONCILIATION_SH_PROCESS_KEY="reconciliation_sh_import_process_key";
    public static final String SEPERATOR="_";
    public static final String CARD_TYPE_CODE="IC-CARD-TYPE";

}
