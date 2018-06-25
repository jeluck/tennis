package com.project.dao.impl;


import org.springframework.stereotype.Repository;

import com.project.dao.InviteDao;
import com.project.orm.basedao.HibernateEntityDao;
import com.project.pojo.Invite;

@Repository
public class InviteDaoImpl extends HibernateEntityDao<Invite> implements InviteDao {

}
