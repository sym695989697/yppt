package com.ctfo.catchservice.service.internal.utils.zsh;

import java.util.ArrayList;
import java.util.List;


public class Test {
	
	public static List list = new ArrayList();

	public static void main(String[] args) {
		Test test = new Test();
		test.addList();
		test.dealOracleInsert("111111");
//		test.dealOracleInsert("222222");
//		test.dealOracleInsert("333333");
	}
	
	
	public void addList(){
		Runnable task = new Runnable() {
			@Override
			public void run() {
				int i = 0 ;
				//.........
				while(true){
					i++;
					Test.list.add(i);
					System.out.println("静态变量存入数据："+list);
					try {
						Thread.sleep(50);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					if(i==104){
						return;
					}
				}
			}
		};
		Thread thread = new Thread(task);
		thread.start();
	}
	
	public void dealOracleInsert(final String dealThread){
		Runnable task = new Runnable() {
			@Override
			public void run() {
				int sleepTime = 0;
				while(true){
					int sleep = 500;
					sleepTime = sleep+sleepTime;
					if(list==null || list.size()==0){
						try {
							Thread.sleep(sleep);
							System.out.println("--"+dealThread+"--停用时间："+sleepTime);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						if(sleepTime>1000){
							System.out.println("1分钟没有数据退出"+sleepTime);
							return;
						}
					}else{
						sleepTime = 0;
						//处理List数据
						int i = list.size();
						List tmpList = list;
						System.out.println("--"+dealThread+"--处理list信息："+tmpList);
						list.removeAll(tmpList);
						
					}
				}
			}
		};
		Thread thread = new Thread(task);
		thread.start();
	}
}
