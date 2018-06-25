package com.project.dao.impl;



import com.project.dao.INoticeDao;
import com.project.orm.basedao.HibernateEntityDao;
import com.project.pojo.Notice;


import org.springframework.stereotype.Repository;

@Repository
public class NoticeDaoImpl extends HibernateEntityDao<Notice> implements INoticeDao{
}
