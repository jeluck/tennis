package com.project.dao.impl;

import com.project.dao.ICommentDao;
import com.project.orm.basedao.HibernateEntityDao;
import com.project.pojo.Comment;

import org.springframework.stereotype.Repository;

@Repository
public class CommentDaoImpl extends HibernateEntityDao<Comment> implements ICommentDao{
}
