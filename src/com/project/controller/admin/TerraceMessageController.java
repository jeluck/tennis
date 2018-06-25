package com.project.controller.admin;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.project.auth.UserRightAuth;
import com.project.common.Constants;
import com.project.common.Constants.MES_CLOUD_TYPE;
import com.project.controller.BaseController;
import com.project.pojo.Manager;
import com.project.pojo.PlaygroundManager;
import com.project.pojo.TerraceMessage;
import com.project.pojo.Weuser;
import com.project.service.ITerraceMessageService;
import com.project.util.CommonUtil;
import com.sun.media.sound.InvalidFormatException;


@Controller("adminterraceMessageController")
@RequestMapping(value="/admin")
public class TerraceMessageController extends BaseController {
	
	@Resource
	private ITerraceMessageService terraceMessageService;
	
	/**
	 * 去增加页
	 * @param request
	 * @param map
	 * @return
	 */
	@UserRightAuth(menuCode = "terraceMessage")
	@RequestMapping(value = "toaddterraceMessage")
	public String toAddterraceMessage(HttpServletRequest request, ModelMap map){
		return "admin/terraceMessage/terraceMessage_add";
	}
	
	/**
	 * 添加操作
	 * @param request
	 * @param map
	 * @param a
	 * @return
	 */
	@UserRightAuth(menuCode = "terraceMessage")
	@RequestMapping(value = "addterraceMessage")
	public String addterraceMessage(HttpServletRequest request, ModelMap map,TerraceMessage t){
		
		try {
			terraceMessageService.saveTerraceMessage(t);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(t.getMes_cloud_type()==2){
			return "redirect:terraceMessage_list.do?note=note";
		}
		return "redirect:terraceMessage_list.do";
	}
	
	/**
	 * 添加操作(后台用户批量导入数据的)
	 * @param request
	 * @param map
	 * @param a
	 * @return
	 */
	@UserRightAuth(menuCode = "terraceMessage")
	@RequestMapping(value = "fileaddterraceMessage")
	public String fileaddterraceMessage(HttpServletRequest request, ModelMap map,TerraceMessage t,@RequestParam MultipartFile file){
		
		InputStream inputStream=null;
		List<String> strin=new ArrayList<String>();
		try {  
			inputStream=file.getInputStream();
            String cellStr = null;  
  
            Workbook wb = null;
			try {
				wb = WorkbookFactory.create(inputStream);
			} catch (org.apache.poi.openxml4j.exceptions.InvalidFormatException e) {
				e.printStackTrace();
			}  
  
            Sheet sheet = wb.getSheetAt(0);// 取得第一个sheets  
  
            //从第四行开始读取数据  
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {  
  
                Row row = sheet.getRow(i); // 获取行(row)对象  
                System.out.println(row);
                if (row == null) {  
                    // row为空的话,不处理  
                    continue;  
                }  
  
                for (int j = 0; j < row.getLastCellNum(); j++) {  
  
                    Cell cell = row.getCell(j); // 获得单元格(cell)对象  
  
                    // 转换接收的单元格  
                    cellStr = ConvertCellStr(cell, cellStr);  
  
                    strin.add(cellStr);
                }   
            }
            
           
  
        } catch (InvalidFormatException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        } finally {  
            if (inputStream != null) {  
                try {  
                	inputStream.close();  
                } catch (IOException e) {  
                    e.printStackTrace();  
                }  
            } else {  
                
            }  
        }  
		
		StringBuffer sb=new StringBuffer();
		
		for(String s:strin){
			sb.append(s+",");
		}
		
		try {
			t.setUser_data(sb.toString());
			terraceMessageService.saveTerraceMessage(t);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "redirect:terraceMessage_list.do";
	}
	
	/**
	 * 列表
	 * @param pageNumber
	 * @param request
	 * @param map
	 * @param o
	 * @return
	 */
	@UserRightAuth(menuCode = "terraceMessage")
	@RequestMapping(value = "terraceMessage_list")
	public String terraceMessage_list(@RequestParam(defaultValue="1")int pageNumber,HttpServletRequest request, ModelMap map,TerraceMessage t) {
		CommonUtil.printHTTP(request);
		String note = request.getParameter("note");
		System.err.println(note);
		if(CommonUtil.NotEmpty(note)){
			map.put("note", request.getParameter("note"));
			t.setMes_cloud_type(MES_CLOUD_TYPE.PHONEMES.getStatus());
		}
		map.put("data_page",terraceMessageService.getPageFindeTerraceMessage(t, pageNumber, Constants.BACKGROUND_PAGESIZE));
		
		
		return "admin/terraceMessage/terraceMessage_list";
	}
	
	
	/**
	 * 去修改页
	 * @param request
	 * @param map
	 * @return
	 */
	@UserRightAuth(menuCode = "terraceMessage")
	@RequestMapping(value = "toedit_terraceMessage")
	public String toEditterraceMessage(HttpServletRequest request, ModelMap map,int oid){
		PlaygroundManager k = getPlaygroundManager(request);
		if(k!=null ){
			map.put("k", k);
		}
		Manager manager = getManager(request);
		if(manager!=null ){
			map.put("m", manager);
		}
		String note = request.getParameter("note");
		System.err.println(note);
		if(CommonUtil.NotEmpty(note)){
			map.put("note", request.getParameter("note"));
		}
		String check = request.getParameter("check");
		map.put("check", check);
		map.put("o", terraceMessageService.getById(oid));
		
		return "admin/terraceMessage/terraceMessage_edit";
	}
	
	/**
	 * 去修改页
	 * @param request
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "manager_terraceMessage")
	public String managerterraceMessage(HttpServletRequest request, ModelMap map,int oid){
		String check = request.getParameter("check");
		map.put("check", check);
		map.put("o", terraceMessageService.getById(oid));
		
		return "admin/terraceMessage/managerterraceMessage_edit";
	}
	
	/**
	 * 修改
	 * @param request
	 * @param map
	 * @return
	 */
	@UserRightAuth(menuCode = "terraceMessage")
	@RequestMapping(value = "edit_terraceMessage")
	public String editterraceMessage(HttpServletRequest request, ModelMap map,TerraceMessage t){
		try {
			if("del".equals(request.getParameter("del"))){
				TerraceMessage tm= terraceMessageService.getById(Integer.valueOf(request.getParameter("oid")));
				tm.setDelstatus(1);
				terraceMessageService.updateTerraceMessage(tm);
			}else{
				terraceMessageService.updateTerraceMessage(t);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:terraceMessage_list.do";
	}
	
	/**
	 * 后台用户批量导入数据的修改
	 * @param request
	 * @param map
	 * @return
	 */
	@UserRightAuth(menuCode = "terraceMessage")
	@RequestMapping(value = "dedit_terraceMessage")
	public String deditterraceMessage(HttpServletRequest request, ModelMap map,TerraceMessage t,@RequestParam MultipartFile file){
		InputStream inputStream=null;
		List<String> strin=new ArrayList<String>();
		try {  
			inputStream=file.getInputStream();
            String cellStr = null;  
  
            Workbook wb = null;
			try {
				wb = WorkbookFactory.create(inputStream);
			} catch (org.apache.poi.openxml4j.exceptions.InvalidFormatException e) {
				e.printStackTrace();
			}  
  
            Sheet sheet = wb.getSheetAt(0);// 取得第一个sheets  
  
            //从第四行开始读取数据  
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {  
  
                Row row = sheet.getRow(i); // 获取行(row)对象  
                System.out.println(row);
                if (row == null) {  
                    // row为空的话,不处理  
                    continue;  
                }  
  
                for (int j = 0; j < row.getLastCellNum(); j++) {  
  
                    Cell cell = row.getCell(j); // 获得单元格(cell)对象  
  
                    // 转换接收的单元格  
                    cellStr = ConvertCellStr(cell, cellStr);  
  
                    strin.add(cellStr);
                }   
            }
            
           
  
        } catch (InvalidFormatException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        } finally {  
            if (inputStream != null) {  
                try {  
                	inputStream.close();  
                } catch (IOException e) {  
                    e.printStackTrace();  
                }  
            } else {  
                
            }  
        }  
		
		StringBuffer sb=new StringBuffer();
		
		for(String s:strin){
			sb.append(s+",");
		}
		
		try {
			if("del".equals(request.getParameter("del"))){
				TerraceMessage tm= terraceMessageService.getById(Integer.valueOf(request.getParameter("oid")));
				tm.setDelstatus(1);
				terraceMessageService.updateTerraceMessage(tm);
			}else{
				t.setUser_data(sb.toString());
				terraceMessageService.updateTerraceMessage(t);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:terraceMessage_list.do";
	}
	
    
    /** 
     * 把单元格内的类型转换至String类型 
     */  
    private String ConvertCellStr(Cell cell, String cellStr) {  
  
        switch (cell.getCellType()) {  
  
        case Cell.CELL_TYPE_STRING:  
            // 读取String  
            cellStr = cell.getStringCellValue().toString();  
            break;  
  
        case Cell.CELL_TYPE_BOOLEAN:  
            // 得到Boolean对象的方法  
            cellStr = String.valueOf(cell.getBooleanCellValue());  
            break;  
  
        case Cell.CELL_TYPE_NUMERIC:  
  
            // 先看是否是日期格式  
            if (DateUtil.isCellDateFormatted(cell)) {  
  
                // 读取日期格式  
                cellStr = cell.getDateCellValue().toString();  
  
            } else {  
  
                // 读取数字  
                cellStr = String.valueOf((long)cell.getNumericCellValue());  
            }  
            break;  
  
        case Cell.CELL_TYPE_FORMULA:  
            // 读取公式  
            cellStr = cell.getCellFormula().toString();  
            break;  
        }  
        return cellStr;  
    }
	
	/**
	 * 删除数据
	 * @param request
	 * @param map
	 * @return
	 */
	@UserRightAuth(menuCode = "terraceMessage")
	@RequestMapping(value = "del_terraceMessage")
	public String deleteterraceMessage(HttpServletRequest request, ModelMap map,Integer oid){
		try {
			terraceMessageService.deleteTerraceMessage(oid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:terraceMessage_list.do";
	}
}
