package com.project.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import com.project.common.Constants;
import com.project.common.Settings;
import com.project.common.OrderEnum.LogType;
import com.project.common.OrderEnum.MethodCode;
import com.project.dao.ICoachDao;
import com.project.orm.basedao.CriteriaAdapter;
import com.project.orm.basedao.PageFinder;
import com.project.pojo.Coach;
import com.project.pojo.Orderinfo;
import com.project.pojo.SpaceManager;
import com.project.pojo.Tennis_circles;
import com.project.service.ICoachService;
import com.project.service.IOrderComponent;
import com.project.service.IOrderInfoService;
import com.project.service.IPlaygroundService;
import com.project.service.ISpaceManagerService;
import com.project.service.ISystemConfigService;
import com.project.service.ITennis_circlesService;
import com.project.util.CommonUtil;
import com.sun.org.apache.bcel.internal.generic.ARRAYLENGTH;

import org.hibernate.Criteria;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class Tennis_circlesServiceImpl implements ITennis_circlesService{
    private static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(Tennis_circlesServiceImpl.class);

	@Override
	public List<Tennis_circles> getshoShoppingcartCommodities(int userid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Tennis_circles getTennis_circlesById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Tennis_circles saveTennis_circles(Tennis_circles sc) {
		// TODO Auto-generated method stub
		return null;
	}

	

}
