package com.project.dao.impl;

import org.springframework.stereotype.Repository;

import com.project.dao.ICoach_teach_personDao;
import com.project.orm.basedao.HibernateEntityDao;
import com.project.pojo.Coach_teach_person;

@Repository
public class Coach_teach_personDaoImpl extends HibernateEntityDao<Coach_teach_person> implements ICoach_teach_personDao {

}
