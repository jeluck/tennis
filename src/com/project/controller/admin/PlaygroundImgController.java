package com.project.controller.admin;

import java.io.IOException;
import java.util.Iterator;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.project.auth.UserRightAuth;
import com.project.common.Settings;
import com.project.controller.BaseController;
import com.project.pojo.Playground;
import com.project.pojo.PlaygroundImg;
import com.project.service.IPlaygroundImgService;
import com.project.service.IPlaygroundService;

@Controller
@RequestMapping(value = "/img")
public class PlaygroundImgController extends BaseController {
	
	@Resource
	private IPlaygroundImgService playgroundImgService;
	@Resource
	private IPlaygroundService playgroundService;
	/**
	 * 场馆添加图片
	 * @param request
	 * @param response
	 * @throws Exception 
	 */
	@UserRightAuth(menuCode = "playground")
	@RequestMapping(value = "addimg")
	@ResponseBody
	public void uploadFile(HttpServletRequest request,HttpServletResponse response) throws Exception {  
		int pdId = Integer.valueOf(request.getParameter("pdId"));
		 String image_name = "banner" + System.currentTimeMillis()
					+ ".jpg";
		request.setCharacterEncoding("utf-8");
		//创建一个通用的多部分解析器  
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());  
        //判断 request 是否有文件上传,即多部分请求  
        if(multipartResolver.isMultipart(request)){  
            //转换成多部分request    
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest)request;  
            //取得request中的所有文件名  
            Iterator<String> iter = multiRequest.getFileNames();  
            while(iter.hasNext()){  
                //取得上传文件  
                MultipartFile file = multiRequest.getFile(iter.next());  
                if(file != null){  
                    //取得当前上传文件的文件名称  
                    String myFileName = file.getOriginalFilename();
                    System.out.println(myFileName);
                   
                    PlaygroundImg o = new PlaygroundImg();
                    o.setPdId(pdId);
                    o.setImg(uploadFile(file,request,Settings.IMAGE_SAVE_FILE_NAME + "/banner/" + image_name,""));
                    try {
						playgroundImgService.savePlaygroundImg(o);
					} catch (Exception e) {
						e.printStackTrace();
					}
                }  
            }
        }
    }  
}
