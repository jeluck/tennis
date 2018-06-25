package com.project.controller.client;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.common.Constants;
import com.project.pojo.BusinessResponse;
import com.project.pojo.Comment;
import com.project.pojo.Weuser;
import com.project.service.ICommentService;
import com.project.service.IWeuserService;

@Controller("clientcommentController")
@RequestMapping("/")
public class CommentController {
	@Resource
	private ICommentService commentService;
	@Resource
	private IWeuserService weuserService;
	
	@RequestMapping("tocomment")
	@ResponseBody
	public Object comment(HttpServletRequest request,Integer userId,String activeId,String context,Integer orderType){
		Comment comment=new Comment();
		comment.setActiveID(activeId);
		comment.setWeuser(new Weuser());
		comment.getWeuser().setId(userId);
		comment.setOrderType(orderType);
		comment.setCommentcontent(context);
		
		commentService.saveComment(comment);
		
		comment.setWeuser(weuserService.getUserById(userId));
		return new BusinessResponse(
				Constants.OperationResult.SUCCESS.getStatus(), "成功", comment);
	}
}
