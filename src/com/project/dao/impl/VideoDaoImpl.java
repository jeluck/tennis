package com.project.dao.impl;

import org.springframework.stereotype.Repository;

import com.project.dao.IVideoDao;
import com.project.orm.basedao.HibernateEntityDao;
import com.project.pojo.Video;

@Repository
public class VideoDaoImpl extends HibernateEntityDao<Video> implements IVideoDao{

}
