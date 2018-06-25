package com.project.dao.impl;

import org.springframework.stereotype.Repository;

import com.project.dao.IVideo_classificationDao;
import com.project.orm.basedao.HibernateEntityDao;
import com.project.pojo.Video_classification;

@Repository
public class Video_classificationDaoImpl extends HibernateEntityDao<Video_classification> implements IVideo_classificationDao{

}
