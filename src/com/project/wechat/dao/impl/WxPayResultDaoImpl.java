package com.project.wechat.dao.impl;

import org.springframework.stereotype.Repository;

import com.project.orm.basedao.HibernateEntityDao;
import com.project.wechat.dao.IWxPayResultDao;
import com.project.wechat.model.pojo.WxPayResult;

@Repository
public class WxPayResultDaoImpl extends HibernateEntityDao<WxPayResult>  implements IWxPayResultDao {

	

}
