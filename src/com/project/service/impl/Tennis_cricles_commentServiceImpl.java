package com.project.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.project.dao.ITennis_cricles_commentDao;
import com.project.pojo.Tennis_cricles_comment;
import com.project.service.ITennis_cricles_commentService;

@Service
public class Tennis_cricles_commentServiceImpl implements
		ITennis_cricles_commentService {

	@Resource
	private ITennis_cricles_commentDao Tennis_cricles_commentDao;
	
	@Override
	public Tennis_cricles_comment saveTennis_cricles_comment(
			Tennis_cricles_comment tcc) throws Exception {
		return Tennis_cricles_commentDao.saveObject(tcc);
	}

	@Override
	public void deleteTennis_cricles_comment(int tccId) throws Exception {
		Tennis_cricles_commentDao.remove(Tennis_cricles_commentDao.getById(tccId));
	}
	
}
