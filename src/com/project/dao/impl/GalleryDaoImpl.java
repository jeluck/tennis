package com.project.dao.impl;

import org.springframework.stereotype.Repository;

import com.project.dao.IGalleryDao;
import com.project.orm.basedao.HibernateEntityDao;
import com.project.pojo.Gallery;

@Repository
public class GalleryDaoImpl extends HibernateEntityDao<Gallery> implements IGalleryDao  {

}
