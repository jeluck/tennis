package com.project.dao.impl;

import org.springframework.stereotype.Repository;

import com.project.dao.IPlaygroundImgDao;
import com.project.orm.basedao.HibernateEntityDao;
import com.project.pojo.PlaygroundImg;

@Repository
public class PlaygroundImgDaoImpl extends HibernateEntityDao<PlaygroundImg> implements IPlaygroundImgDao {

}
