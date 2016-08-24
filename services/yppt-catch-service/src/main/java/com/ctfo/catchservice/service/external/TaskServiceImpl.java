package com.ctfo.catchservice.service.external;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ctfo.catchservice.bean.JResult;
import com.ctfo.catchservice.exception.CatchException;
import com.ctfo.catchservice.interfaces.external.ITaskService;
import com.ctfo.catchservice.interfaces.internal.ICatchInternalService;

/**
 * 定时任务接口实现
 * 
 * @author jichao
 */
@Service(value="taskService")
public class TaskServiceImpl implements ITaskService {

	@Resource
	ICatchInternalService catchInternalService;
	
	@Override
	public JResult executeTask(String taskType) throws CatchException {
		catchInternalService.timimgTaskCatchData();
		return null;
	}

}
