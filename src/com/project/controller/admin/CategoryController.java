package com.project.controller.admin;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.project.common.Constants;
import com.project.common.Settings;
import com.project.controller.BaseController;
import com.project.orm.basedao.PageFinder;
import com.project.pojo.Course;
import com.project.pojo.InformationCategoryInfo;
import com.project.service.ICategoryInfoService;
import com.project.util.CommonUtil;

@Controller("adminCategoryWebController")
@RequestMapping(value="/pgm")
public class CategoryController extends BaseController {
    private static org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(CategoryController.class);

    @Resource
    private ICategoryInfoService categoryService;

    /***
     * 添加分类
     * @param request
     * @param map
     * @return
     */
    @RequestMapping(value = "addcategory")
    public String addSystemMessage(MultipartHttpServletRequest request, ModelMap map)
    {
        CommonUtil.printHTTP(request);
        try {
            String categoryname = request.getParameter("categoryname");
            String pagenumber = request.getParameter("pagenumber");
            String categoryInfoid = request.getParameter("categoryInfoid");
            
            InformationCategoryInfo ci = new InformationCategoryInfo();
            
            MultipartFile imgFile = request.getFile("imgurl");
        	if(imgFile!=null && imgFile.getSize()>0){
        		String image_name=System.currentTimeMillis()+".jpg";
        		ci.setImgurl(uploadFile(imgFile,request,"upload/categoryInfo/"+image_name,""));
        	}
            
            try {
            	int id=Integer.parseInt(categoryInfoid);
            	ci.setId(id);
			} catch (Exception e) {
				// TODO: handle exception
			}

            ci.setCategory_name(categoryname);
            ci.setFlag(Constants.NORMAL_FLAG);
            
            categoryService.addCategory(ci);

            logger.info("添加成功");
            return "redirect:pagecategory.do?pagenumber=" + pagenumber;
        }
        catch (NumberFormatException e)
        {
            for(StackTraceElement ste : e.getStackTrace())
            {
                logger.error(ste.toString());
            }
            //TODO 跳转到错误页面
            return "";
        }
        catch (Exception e) {
            for(StackTraceElement ste : e.getStackTrace())
            {
                logger.error(ste.toString());
            }
            e.printStackTrace();
            //TODO 跳转到错误页面
            return "";
        }
    }

    /**
     * 分类列表
     * @param request
     * @param map
     * @return
     */
    @RequestMapping(value = "pagecategory")
    public String pageCategory(HttpServletRequest request, ModelMap map)
    {
        CommonUtil.printHTTP(request);
        int pageNumber;
        int pageSize;

        try {
            if(request.getParameter("pagenumber") == null || "".equals(request.getParameter("pagenumber")))
            {
                pageNumber = 1;
            }
            else
            {
                pageNumber = Integer.parseInt(request.getParameter("pagenumber"));
            }

            if(request.getParameter("pagesize") == null || "".equals(request.getParameter("pagesize")))
            {
                pageSize = 10;
            }
            else {
                pageSize = Integer.parseInt(request.getParameter("pagesize"));
            }

            PageFinder<InformationCategoryInfo> pageFinder = categoryService.getPageCategory(pageNumber, pageSize);

            map.put("data_page", pageFinder);
            map.put("categorylist", categoryService.getAllCategory());//商品一级分类list
            logger.info("查询成功");
            
     	   
            return "admin/category/category_list";
        }
        catch (Exception e) {
            e.printStackTrace();
            for(StackTraceElement ste : e.getStackTrace())
            {
                logger.error(ste.toString());
            }
            map.put("msg", Constants.OperationResult.UNKNOWN_MISTAKE.getMsg());
            //TODO 跳转到错误页面
            return "";
        }
    }
    
    /***
     * 跳到修改页
     * @param request
     * @param map
     * @param id
     * @return
     */
    @RequestMapping("toeditCategory")
    public String toeditCategory(HttpServletRequest request,ModelMap map,int id,@RequestParam(defaultValue="1")int pagenumber){
//    	map.put("categorylist", categoryService.getAllCategory());//商品一级分类list
    	map.put("c", categoryService.getCategoryInfoById(id));
    	return "admin/category/category_edit";
    }
    

    /***
     * 修改分类
     * @param request
     * @param map
     * @param id
     * @return
     */
    @RequestMapping(value = "editCategory")
    public String editCategory(MultipartHttpServletRequest request){
    	 InformationCategoryInfo ci = CommonUtil.SerializableObj(request.getParameterMap(), InformationCategoryInfo.class);
        //保存
        try {
        	MultipartHttpServletRequest mhs = (MultipartHttpServletRequest) request;
        	MultipartFile imgFile1 = mhs.getFile("img");
        	if(imgFile1!=null && imgFile1.getSize()>0){
        		String image_name="banner"+System.currentTimeMillis()+".jpg";
        		ci.setImgurl(uploadFile(imgFile1,request,Settings.IMAGE_SAVE_FILE_NAME+"/banner/"+image_name,""));
        	}else{
        		ci.setImgurl(request.getParameter("imgfile"));
        	}
        	ci.setFlag(Constants.NORMAL_FLAG);
        	categoryService.updateCategory(ci);
        } catch (Exception e) {
        	logger.error("修改分类信息出现错误！错误信息："+e.getMessage());
            e.printStackTrace();
        }
    	
    	return "redirect:pagecategory.do";
    }
    
    

    /**
     * 删除分类
     * @param request
     * @return
     */
    @RequestMapping(value = "deletecategory")
    public String deleteSystemMessage(HttpServletRequest request)
    {
        CommonUtil.printHTTP(request);
            return "";
    }
    
}
