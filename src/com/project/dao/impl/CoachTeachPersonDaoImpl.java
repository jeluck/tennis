package com.project.dao.impl;

import org.springframework.stereotype.Repository;

import com.project.dao.ICoachTeachPersonDao;
import com.project.orm.basedao.HibernateEntityDao;
import com.project.pojo.Coach_teach_person;

@Repository
public class CoachTeachPersonDaoImpl extends HibernateEntityDao<Coach_teach_person> implements ICoachTeachPersonDao {

}
