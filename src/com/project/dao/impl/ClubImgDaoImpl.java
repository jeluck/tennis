package com.project.dao.impl;

import org.springframework.stereotype.Repository;

import com.project.dao.IClubImgDao;
import com.project.orm.basedao.HibernateEntityDao;
import com.project.pojo.ClubImg;

@Repository
public class ClubImgDaoImpl  extends HibernateEntityDao<ClubImg> implements IClubImgDao {

}
