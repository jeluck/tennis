package com.project.dao.impl;


import com.project.dao.ITennis_circlesDao;
import com.project.orm.basedao.HibernateEntityDao;
import com.project.pojo.Tennis_circles;

import org.springframework.stereotype.Repository;

@Repository
public class Tennis_circlesDaoImpl extends HibernateEntityDao<Tennis_circles> implements ITennis_circlesDao {

}
