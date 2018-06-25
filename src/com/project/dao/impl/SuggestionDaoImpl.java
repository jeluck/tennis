package com.project.dao.impl;

import org.springframework.stereotype.Repository;

import com.project.dao.ISuggestionDao;
import com.project.orm.basedao.HibernateEntityDao;
import com.project.pojo.Suggestion;

@Repository
public class SuggestionDaoImpl extends HibernateEntityDao<Suggestion> implements
		ISuggestionDao {
}
