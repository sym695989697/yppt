package com.ctfo.catchservice.interfaces.external;

import com.ctfo.catchservice.bean.JResult;
import com.ctfo.catchservice.exception.CatchException;

/**
 * 定时任务接口
 * 
 * @author jichao
 */
public interface ITaskService {
	/**
	 * 执行定时任务接口
	 * @param taskType 定时任务类型
	 */
	public JResult executeTask(String taskType)throws CatchException;
}
