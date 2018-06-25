package com.project.dao.impl;

import org.springframework.stereotype.Repository;

import com.project.dao.IClubDao;
import com.project.orm.basedao.HibernateEntityDao;
import com.project.pojo.Club;

@Repository
public class ClubDaoImpl extends HibernateEntityDao<Club> implements IClubDao {

}
