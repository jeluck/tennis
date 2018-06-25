package com.project.dao.impl;

import org.springframework.stereotype.Repository;

import com.project.dao.IPeripheral_servicesDao;
import com.project.orm.basedao.HibernateEntityDao;
import com.project.pojo.Peripheral_services;

@Repository
public class Peripheral_servicesDaoImpl extends HibernateEntityDao<Peripheral_services> implements IPeripheral_servicesDao {

}
