package com.project.dao.impl;

import org.springframework.stereotype.Repository;

import com.project.dao.IClubIndexDao;
import com.project.orm.basedao.HibernateEntityDao;
import com.project.pojo.ClubIndex;

@Repository
public class ClubIndexDaoImpl extends HibernateEntityDao<ClubIndex> implements IClubIndexDao{

}
