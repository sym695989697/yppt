package com.ctfo.chpt.action.iccard.trade;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.ctfo.base.action.base.BaseControler;
import com.ctfo.chpt.service.iccard.trade.ICCardTradeInfoServiceImpl;
import com.ctfo.yppt.baseservice.trade.beans.ICCardTradeInfo;
@Controller
@Scope(value=ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class TradeController extends BaseControler{
	public TradeController(){
		model=new ICCardTradeInfo();
		service=new ICCardTradeInfoServiceImpl();
	}
}
