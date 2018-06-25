package com.project.dao.impl;

import org.springframework.stereotype.Repository;

import com.project.dao.IRedBagRecordDao;
import com.project.orm.basedao.HibernateEntityDao;
import com.project.pojo.Red_bag_record;

@Repository
public class RedBagRecordDaoImpl extends HibernateEntityDao<Red_bag_record> implements IRedBagRecordDao {

}
