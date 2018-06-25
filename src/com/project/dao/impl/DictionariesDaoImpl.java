package com.project.dao.impl;

import org.springframework.stereotype.Repository;

import com.project.dao.IDictionariesDao;
import com.project.orm.basedao.HibernateEntityDao;
import com.project.pojo.Dictionaries;

@Repository
public class DictionariesDaoImpl extends HibernateEntityDao<Dictionaries> implements IDictionariesDao {

}
