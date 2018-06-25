package com.project.dao.impl;



import com.project.dao.IFriendlyLinkDao;
import com.project.orm.basedao.HibernateEntityDao;
import com.project.pojo.FriendlyLink;


import org.springframework.stereotype.Repository;

@Repository
public class FriendlyLinkDaoImpl extends HibernateEntityDao<FriendlyLink> implements IFriendlyLinkDao{
}
