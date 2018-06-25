package com.project.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.project.dao.ITennis_cricles_pariseDao;
import com.project.pojo.Tennis_cricles_parise;
import com.project.service.ITennis_circles_pariseService;

@Service
public class Tennis_circles_pariseServiceImpl implements
		ITennis_circles_pariseService {

	@Resource
	private ITennis_cricles_pariseDao tennis_cricles_pariseDao;
	
	@Override
	public Tennis_cricles_parise saveTennis_cricles_parise (
			Tennis_cricles_parise tcp) throws Exception{
		return tennis_cricles_pariseDao.saveObject(tcp);
	}

	@Override
	public void deleteTennis_cricles_parise(int tcpId) throws Exception {
		tennis_cricles_pariseDao.remove(tennis_cricles_pariseDao.getById(tcpId));
	}

	@Override
	public List<Tennis_cricles_parise> findTennis_cricles_pariseBytcId(int tcId) {
		return tennis_cricles_pariseDao.findBy("tennis_cricles_id", tcId, true);
	}

}
