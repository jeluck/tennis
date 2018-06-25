package com.project.service.impl;

import java.util.List;

import com.project.orm.basedao.PageFinder;
import com.project.pojo.SpaceManager;
import com.project.service.ISpaceManagerService;

import org.springframework.stereotype.Service;

@Service
public class SpaceManagerServiceImpl implements ISpaceManagerService{
    private static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(SpaceManagerServiceImpl.class);

	@Override
	public SpaceManager getSpaceManagerByID(int commodityTypeID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SpaceManager getSpaceManagerByNo(String commodityTypeNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SpaceManager> getSpaceManagerList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PageFinder<SpaceManager> getSpaceManagerList(SpaceManager o,
			int pageNumber, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SpaceManager saveSpaceManager(SpaceManager o) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SpaceManager updateSpaceManager(SpaceManager o) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SpaceManager getSpaceManagerById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removeSpaceManager(SpaceManager o) throws Exception {
		// TODO Auto-generated method stub
		
	}

	

}
