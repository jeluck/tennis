package com.project.dao.impl;


import com.project.dao.ICourseDao;
import com.project.orm.basedao.HibernateEntityDao;
import com.project.pojo.Course;

import org.springframework.stereotype.Repository;

@Repository
public class CourseDaoImpl extends HibernateEntityDao<Course> implements ICourseDao {
}
