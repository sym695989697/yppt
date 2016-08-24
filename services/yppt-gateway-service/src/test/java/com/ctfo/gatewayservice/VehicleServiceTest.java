package com.ctfo.gatewayservice;

import org.junit.BeforeClass;
import org.junit.Test;

import com.caucho.hessian.client.HessianProxyFactory;
import com.ctfo.gatewayservice.interfaces.service.IVehicleService;
import com.vims.external.bean.VehicleExternalBean;

public class VehicleServiceTest {
	protected static IVehicleService iVehicleService;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		try {
			String url = "http://localhost:8082/gatewayService/hessian-remote/com.ctfo.gatewayservice.interfaces.service.IVehicleService";
			com.caucho.hessian.client.HessianProxyFactory factory = new HessianProxyFactory();
			iVehicleService = (IVehicleService) factory.create(
					IVehicleService.class, url);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// // 指的是车后系统
	// private String userCode = "1400";

	@Test
	public void addVehicle() {
		try {
			String vid = iVehicleService.addVehicle("山14", "1");
			iVehicleService.getVehicleById(vid);
			System.out.println(vid);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void getVehicleById() {
		try {
			VehicleExternalBean ext = iVehicleService.getVehicleById("37f2cb70-83d8-4b7e-b2c5-dc599563e820");
			System.out.println(ext);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
