package com.project.service;


import com.project.pojo.Peripheral_services;

public interface IPeripheral_servicesService {
	
	/**
	 * 保存服务
	 * @param ser
	 * @return
	 */
	public Peripheral_services saveServices(Peripheral_services ser) throws Exception;
	
	/**
	 * 修改服务
	 * @param ser
	 * @return
	 * @throws Exception
	 */
	public Peripheral_services updateServices(Peripheral_services ser) throws Exception;
	
	/**
	 * 根据编号获得服务
	 * @param id
	 * @return
	 */
	public Peripheral_services getServices(int id);
	
}
