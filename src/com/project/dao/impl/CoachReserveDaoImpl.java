package com.project.dao.impl;

import org.springframework.stereotype.Repository;

import com.project.dao.ICoachReserveDao;
import com.project.orm.basedao.HibernateEntityDao;
import com.project.pojo.CoachReserve;

@Repository
public class CoachReserveDaoImpl extends HibernateEntityDao<CoachReserve> implements ICoachReserveDao{

}
