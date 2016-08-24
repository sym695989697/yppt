package com.ctfo.chpt.action.iccard.trade;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ctfo.base.action.base.BaseControler;
import com.ctfo.chpt.service.iccard.trade.ICMainCardTradeInfoServiceImpl;
import com.ctfo.yppt.baseservice.trade.beans.ICCardMainTradeInfo;
@Controller
@Scope(value=ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@RequestMapping(value="mainCardTrade")
public class MainCardTradeController extends BaseControler{
	public MainCardTradeController(){
		model=new ICCardMainTradeInfo();
		service=new ICMainCardTradeInfoServiceImpl();
	}
}
