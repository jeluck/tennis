package com.project.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.project.dao.ISchoolDao;
import com.project.orm.basedao.PageFinder;
import com.project.pojo.School;
import com.project.service.ISchoolService;

@Service
public class SchoolServiceImpl implements ISchoolService {

	@Resource
	private ISchoolDao schoolDao;
	
	@Override
	public School seveSchool(School o) throws Exception {
		return schoolDao.saveObject(o);
	}

	@Override
	public PageFinder<School> getPageFindeSchool(School o, int pageNumber,
			int pageSize) {
		return null;
	}

	@Override
	public School getSchoolById(int oid) {
		return schoolDao.getById(oid);
	}

	@Override
	public School mergeSchool(School o) throws Exception {
		return schoolDao.merge(o);
	}

	@Override
	public void deleteSchoolById(int id) throws Exception {
		schoolDao.removeById(id);
	}

	@Override
	public School getSchool() {
		List<School> list = schoolDao.getAll();
		if(list!=null && list.size()>0){
			return list.get(0);
		}
		return null;
	}
	
}
