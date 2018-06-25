package com.project.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;

import com.project.common.Settings;
import com.project.pojo.Coach;
import com.project.pojo.Manager;
import com.project.pojo.PlaygroundManager;
import com.project.pojo.PushMsg;
import com.project.pojo.Weuser;
import com.project.util.CommonUtil;
import com.project.util.ExcelsUtil;
import com.project.util.FileUploadUtil;
import com.project.util.ImageDispose;
/**
 * Basic Controller
 * @author daybreak
 *
 */
public class BaseController { 
	
	protected Logger logger = Logger.getLogger(getClass());
	//公司类型
	public static ArrayList<String> companytype_list = null;
	
	//初始化固定数据
	static{
		
		companytype_list  = new ArrayList<String>(10);//公司类型
		companytype_list.add("");
		companytype_list.add("国家机关");
		companytype_list.add("事业单位");
		companytype_list.add("央企");
		companytype_list.add("地方国资委直属企业");
		companytype_list.add("世界500强");
		companytype_list.add("外资企业");
		companytype_list.add("一般上市公司");
		companytype_list.add("一般民营企业");
		companytype_list.add("个体经营者");
	}
	//消息反馈对象
	private static PushMsg pushMsg = new PushMsg();
	
	/**
	 * 得到管理员信息
	 * @param request
	 * @return
	 */
	public Manager getManager(HttpServletRequest request){
		Object object = request.getSession().getAttribute(Settings.MANAGER_SESSION);
		if(object!=null){
			if(object instanceof Manager){
				return (Manager)object;
			}
		}
		return null;
	}
	
	/**
	 * 得到当前用户信息
	 * @param request
	 * @return
	 */
	public Weuser getUser(HttpServletRequest request){
		Object object = request.getSession().getAttribute(Settings.USER_SESSION);
		if(object!=null){
			if(object instanceof Weuser){
				return (Weuser)object;
			}
		}
		return null;
	}
	
	
	/**
	 * 得到当前教练信息
	 * @param request
	 * @return
	 */
	public Coach getCoach(HttpServletRequest request){
		Object object = request.getSession().getAttribute(Settings.COACH_SESSION);
		if(object!=null){
			if(object instanceof Coach){
				return (Coach)object;
			}
		}
		return null;
	}
	
	/**
	 * 得到当前用户信息
	 * @param request
	 * @return
	 */
	public PlaygroundManager getPlaygroundManager(HttpServletRequest request){
		Object object = request.getSession().getAttribute(Settings.PLAYGROUNDMANAGER_SESSION);
		if(object!=null){
			if(object instanceof PlaygroundManager){
				return (PlaygroundManager)object;
			}
		}
		return null;
	}
	
	/**
	 * 得到ROOT路径(资源根路径)
	 * 
	 * @param request
	 * @return
	 */
	public static String getDataFilePath(HttpServletRequest request) {
		File file = new File("/wxhome/data");
		if (!file.exists()) {
			return request.getSession().getServletContext().getRealPath("");
		} else {
			return "/wxhome/data";
		}
	}
	
	/**
	 * 文件上传并自动分类
	 * @param file
	 * @param request
	 * @param dictoryInfo		新的上传后的目录和文件名
	 * @param olddictoryInfo	旧的上传后的目录和文件名
	 * @return	新的上传后的目录和文件名
	 */
	public String uploadFile(MultipartFile file,HttpServletRequest request, String dictoryInfo,String olddictoryInfo){
		if(file!=null && !file.isEmpty() && file.getSize()>0){
			String path = "";		
			File dictoryinfo = new File(getDataFilePath(request)+File.separatorChar+dictoryInfo);
			File olddictoryinfo = new File(getDataFilePath(request)+File.separatorChar+olddictoryInfo);
			try {
				if(!dictoryinfo.exists()){
					dictoryinfo.mkdirs();//不存在则创建
				}
				if(olddictoryinfo.isFile()) {
					if (olddictoryinfo.exists()) {
						olddictoryinfo.delete();//存在则删除
					}
				}
				file.transferTo(new File(dictoryinfo,path));
				logger.info("文件上传成功："+dictoryinfo+"/"+path);
			} catch (Exception e) {
				throw new RuntimeException("保存文件失败,"+e.getMessage(),e);
			}
			return dictoryInfo;}
		else{
			return null;
		}
	}
	
	
	/**
	 * 图片上传处理
	 * @param file					
	 * @param request
	 * @param photo_mulu			新的上传后的目录
	 * @param imageName				文件名
	 * @param olddictoryInfo		旧的上传后的目录和文件名
	 * @param imageNameStr			是否处理图片,		有值处理,没有值不处理
	 * @return
	 */
	public String handleImage(MultipartFile file,HttpServletRequest request, String photo_mulu,String imageName,String olddictoryInfo,String imageNameStr,String xuanz){
		
//		String srcImageFile = uploadFile(file,request,  photo_mulu+imageName, olddictoryInfo);
//		System.err.println(srcImageFile);
		String srcImageFile = FileUploadUtil.getInstance().saveFileUpload(file, photo_mulu, imageName, getDataFilePath(request));
		System.err.println(getDataFilePath(request)+File.separatorChar+srcImageFile);
		if(CommonUtil.NotEmpty(xuanz)){
			try{
				ImageDispose.rotateImg(getDataFilePath(request)+File.separatorChar+srcImageFile);
			}catch(Exception e){
				logger.error("处理图片出错");
			}
		}
		if(CommonUtil.NotEmpty(imageNameStr)){
			if (Settings.SMALL_IMAGENAMESTR.equals(imageNameStr)) {
				ImageDispose.zipImageFile(getDataFilePath(request)+File.separatorChar+srcImageFile,getDataFilePath(request)+File.separatorChar+photo_mulu,imageNameStr+imageName,200,150,1f);//对上传的图片,处理成小图
			}else if(Settings.MIDDLE_IMAGENAMESTR.equals(imageNameStr)){
//				ImageDispose.middlePhoto(getDataFilePath(request)+File.separatorChar+srcImageFile,getDataFilePath(request)+File.separatorChar+photo_mulu,imageNameStr+imageName);//对上传的图片,处理成中图
				ImageDispose.zipImageFile(getDataFilePath(request)+File.separatorChar+srcImageFile,getDataFilePath(request)+File.separatorChar+photo_mulu,imageNameStr+imageName,485,322,1f);//对上传的图片,处理成中图
			}else if(Settings.BIG_IMAGENAMESTR.equals(imageNameStr)){
//				ImageDispose.bigPhoto(getDataFilePath(request)+File.separatorChar+srcImageFile,getDataFilePath(request)+File.separatorChar+photo_mulu,imageNameStr+imageName);//对上传的图片,处理成大图
				ImageDispose.zipImageFile(getDataFilePath(request)+File.separatorChar+srcImageFile,getDataFilePath(request)+File.separatorChar+photo_mulu,imageNameStr+imageName,1024,680,1f);//对上传的图片,处理成大图
			}
			srcImageFile = photo_mulu+imageNameStr+imageName;
		}
		
		
		return srcImageFile;
	}
	
	
	/**
	 * 判断非空字符串
	 * @param target
	 * @return
	 */
	public static boolean NotEmpty(String target){
		if(target!=null && !target.equals("")){
			return true;
		}
		return false;
	}
	/**
	 * 推送消息到客户端
	 * @param info
	 * @param status
	 * @return
	 */
	public static PushMsg pushmsg(Object info,Boolean status){
		pushMsg.setInfo(info);
		pushMsg.setStatus(status);
		return pushMsg;
	}
	
	/**
	 * 把用户放到session中
	 * @param request
	 * @param w
	 * @return
	 */
	public String setWeuserSession(HttpServletRequest request, Weuser w) {
		HttpSession session = request.getSession();
		session.setAttribute(Settings.USER_SESSION, w);
		session.setMaxInactiveInterval(1 * 60);
		return session.getId();
	}
	
	/**
	 * 把教练放到session中
	 * @param request
	 * @param w
	 * @return
	 */
	public String setCoachSession(HttpServletRequest request, Coach o) {
		HttpSession session = request.getSession();
		session.setAttribute(Settings.COACH_SESSION, o);
		session.setMaxInactiveInterval(1 * 60);
		return session.getId();
	}
	
	/**
	 * 防止页面js,css缓存,获取版本号
	 * @return
	 */
	public String getVersion(){
		return String.valueOf(System.currentTimeMillis());
		//return Settings.PREVENT_PAGE_CACHE_GET_VERSION;
	}
	
	/**
	 * 导出excel
	 * @param request
	 * @param response
	 * @param fileName
	 * @param keys
	 * @param columnNames
	 * @param dataList
	 * */
	public void exportExcel(HttpServletRequest request,HttpServletResponse response,String fileName,String keys[],String columnNames[],List<Map<String, Object>> dataList) throws Exception{
		ByteArrayOutputStream os = new ByteArrayOutputStream();
        try {
        	if(dataList==null)//如果没有传入数据，说明是下载空模板
        		ExcelsUtil.createBlankWorkBook(fileName,columnNames).write(os);
        	else
        		ExcelsUtil.createWorkBook(dataList, keys, columnNames).write(os);
        } catch (IOException e) {
            e.printStackTrace();
        }
        byte[] content = os.toByteArray();
        InputStream is = new ByteArrayInputStream(content);
        // 设置response参数，可以打开下载页面
        response.reset();
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        response.setHeader("Content-Disposition", "attachment;filename="+ new String((fileName + ".xls").getBytes(), "iso-8859-1"));
        ServletOutputStream out = response.getOutputStream();
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        try {
            bis = new BufferedInputStream(is);
            bos = new BufferedOutputStream(out);
            byte[] buff = new byte[2048];
            int bytesRead;
            // Simple read/write loop.
            while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
                bos.write(buff, 0, bytesRead);
            }
        } catch (final IOException e) {
            throw e;
        } finally {
            if (bis != null)
                bis.close();
            if (bos != null)
                bos.close();
        }
	}
}
