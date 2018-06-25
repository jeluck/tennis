package com.project.dao.impl;

import org.springframework.stereotype.Repository;

import com.project.dao.ICoaching_experienceDao;
import com.project.orm.basedao.HibernateEntityDao;
import com.project.pojo.Coaching_experience;

@Repository
public class Coaching_experienceDaoImpl extends HibernateEntityDao<Coaching_experience> implements ICoaching_experienceDao {

}
