package com.ctfo.yppt.baseservice.trade.intf;

import com.ctfo.base.baseservice.IBaseManager;
import com.ctfo.yppt.baseservice.trade.beans.ICCardMainTradeInfo;
import com.ctfo.yppt.baseservice.trade.beans.ICCardMainTradeInfoExampleExtended;
/**
 *  
 *  
 * @author rao yongbing 
 * @Description 主卡交易管理Manager (基本操作父类有实现)
 *              增加调用：add  
 *              删除调用：remove  
 *              修改调用：update 
 *              查询单个对象调用：getById 
 *              分页查询调用：paginate 
 *              查询所有记录调用 ：getList
 * 2015年1月20日
 */
public interface IICCardMainTradeInfoManager extends IBaseManager<ICCardMainTradeInfo, ICCardMainTradeInfoExampleExtended> {

}
