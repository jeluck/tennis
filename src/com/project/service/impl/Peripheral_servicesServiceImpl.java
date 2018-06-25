package com.project.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.project.dao.IPeripheral_servicesDao;
import com.project.pojo.Peripheral_services;
import com.project.service.IPeripheral_servicesService;

@Service
public class Peripheral_servicesServiceImpl implements IPeripheral_servicesService {

	@Resource
	private IPeripheral_servicesDao peripheral_servicesDao;
	


	@Override
	public Peripheral_services saveServices(Peripheral_services ser) throws Exception {
		return peripheral_servicesDao.saveObject(ser);
	}

	@Override
	public Peripheral_services updateServices(Peripheral_services ser)
			throws Exception {
		return peripheral_servicesDao.merge(ser);
	}

	@Override
	public Peripheral_services getServices(int id) {
		return peripheral_servicesDao.getById(id);
	}
	
}
