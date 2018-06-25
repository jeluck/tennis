package com.project.controller.client;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.common.Constants;
import com.project.common.Constants.OperationResult;
import com.project.pojo.BusinessResponse;
import com.project.pojo.LoveCollection;
import com.project.pojo.Weuser;
import com.project.service.ILoveCollectionService;

@Controller("loveCollectionController")
@RequestMapping(value="/")
public class LoveCollectionController {
    private static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(LoveCollectionController.class);
	@Resource
	private ILoveCollectionService loveCollectionService; 
	
	@RequestMapping("saveCollection")
	@ResponseBody
	/**
	 * 
	 * @param request
	 * @param activeId  场馆或教练ID
	 * @param userId	用户ID
	 * @param type		类型1位场馆，2位教练
	 * @return
	 */
	private synchronized Object saveCollection(HttpServletRequest request,Integer activeId,Integer userId,Integer type){
		try {
	    	if(userId==null){
	    		return new BusinessResponse(OperationResult.NOT_LOGIN.getStatus(), OperationResult.NOT_LOGIN.getMsg(),"");
			}
			LoveCollection l=loveCollectionService.getLcByUserAndActive(userId, activeId, type);
			if(l==null){
				l=new LoveCollection();
				l.setWeuser(new Weuser());
				l.getWeuser().setId(userId);
				l.setDataID(String.valueOf(activeId));
				l.setCollectionType(type);
				
				loveCollectionService.saveLoveCollection(l);
//				return "收藏成功";
				logger.info("收藏成功:  数据ID"+activeId+"  collectionType:" +type);
				return new BusinessResponse(Constants.OperationResult.COLLECT_SUCCESS.getStatus(), Constants.OperationResult.COLLECT_SUCCESS.getMsg(), "");
			}else{
				loveCollectionService.deleteLoveCollection(l);
//				return "取消收藏";
				logger.info("取消收藏:  数据ID"+activeId+"  collectionType:" +type);
				return new BusinessResponse(Constants.OperationResult.COLLECT_CANCLE.getStatus(), Constants.OperationResult.COLLECT_CANCLE.getMsg(), "");
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("取消收藏:  数据ID"+activeId+"  collectionType:" +type);
			return "收藏失败";
		}
	}
}
