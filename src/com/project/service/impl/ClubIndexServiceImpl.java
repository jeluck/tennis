package com.project.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.project.dao.IClubIndexDao;
import com.project.pojo.ClubIndex;
import com.project.service.IClubIndexService;

@Service
public class ClubIndexServiceImpl implements  IClubIndexService {

	@Resource
	private IClubIndexDao clubIndexDao;
	
	@Override
	public ClubIndex saveClubIndex(ClubIndex o) throws Exception {
		return clubIndexDao.saveObject(o);
	}

	@Override
	public ClubIndex getClubIndex() {
		List<ClubIndex> list = clubIndexDao.getAll();
		if(list!=null && list.size()>0){
			return list.get(0);
		}
		return null;
	}

	@Override
	public ClubIndex updateClubIndex(ClubIndex o) throws Exception {
		return clubIndexDao.merge(o);
	}

}
