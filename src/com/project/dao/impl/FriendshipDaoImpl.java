package com.project.dao.impl;

import org.springframework.stereotype.Repository;

import com.project.dao.IFriendshipDao;
import com.project.orm.basedao.HibernateEntityDao;
import com.project.pojo.Friendship;

@Repository
public class FriendshipDaoImpl  extends HibernateEntityDao<Friendship> implements IFriendshipDao {

}
