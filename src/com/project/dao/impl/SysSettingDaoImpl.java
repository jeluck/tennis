package com.project.dao.impl;


import com.project.dao.ISysSettingDao;
import com.project.orm.basedao.HibernateEntityDao;
import com.project.pojo.SysSetting;

import org.springframework.stereotype.Repository;

@Repository
public class SysSettingDaoImpl extends HibernateEntityDao<SysSetting> implements ISysSettingDao{
}
