package com.project.controller.admin;

import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

import com.project.auth.UserRightAuth;
import com.project.common.Constants;
import com.project.common.Constants.O_STATUS;
import com.project.controller.BaseController;
import com.project.orm.basedao.PageFinder;
import com.project.pojo.BankCard;
import com.project.pojo.Manager;
import com.project.pojo.WithdrawOrder;
import com.project.service.IBankService;
import com.project.service.IMoneyService;
import com.project.util.CommonUtil;
/**
 * 资金管理控制器
 * @author daybreak
 *
 */
@Controller("moneyController")
@RequestMapping("/admin")
public class MoneyController extends BaseController {
	
	@Resource
	private IMoneyService moneyService;
	@Resource
	private IBankService bankService;
	
	 /**
	 * 得到用户银行卡内容
	 * @param map
	 * @return
	 */
	@UserRightAuth(menuCode="bank_card_view")
	@RequestMapping(value = "user_card_list", method = RequestMethod.GET)
	public String user_card_list(HttpServletRequest request,BankCard card,@RequestParam(defaultValue="1")int pageNumber,ModelMap map){
		map.put("bankcard_page", bankService.getAllBandCardByPage(pageNumber, Constants.BACKGROUND_PAGESIZE,card));
		return "admin/money_management/user_card_list";
	}

	@RequestMapping(value = "withdwar_view")
	public String withdwar_view(HttpServletRequest request,BankCard card,@RequestParam(defaultValue="1")int pageNumber,ModelMap map){
		return "admin/money_management/withdwar_view";
	}
	
	//得到卡片信息
	@UserRightAuth(menuCode="bank_card_view")	
	@RequestMapping(value = "get_card_info", method = RequestMethod.GET)
	@ResponseBody
	public Object get_card_info(int card_id){
		return bankService.getCardInfoById_cascade(card_id);
	}
	
	//改变卡片状态
	@UserRightAuth(menuCode="bank_card_view")
	@RequestMapping(value = "update_status", method = RequestMethod.POST)
	public String update_status(@RequestParam(defaultValue="1")int pageNumber,BankCard card,RedirectAttributesModelMap map){
		BankCard bc = bankService.getCardInfoById(card.getId());
		bc.setCard_status(O_STATUS.PASS_FOR_CHECK.getStatus());
		bc.setCard_remark(card.getCard_remark());
		bankService.updateBankCardInfo(bc);
		map.put("pageNumber", pageNumber);
		return "redirect:user_card_list.do";
	}
	
	//申请中的提现列表
	@UserRightAuth(menuCode="withdwar_view")
	@RequestMapping(value = "withdraw_list", method = RequestMethod.GET)
	@ResponseBody
	public Object withdraw_shenqing(HttpServletRequest request,@RequestParam(defaultValue="1")int pageNumber,@RequestParam(defaultValue="1")int status){
		return moneyService.getWithdrawByStatus(pageNumber,Constants.BACKGROUND_PAGESIZE,status,request);
	}
	
	//提现列表
	@UserRightAuth(menuCode="withdwar_view")
	@RequestMapping(value = "withdraw_tran", method = RequestMethod.GET)
	public String withdraw_tran(HttpServletRequest request,ModelMap map,@RequestParam(defaultValue="1")int pageNumber,@RequestParam(defaultValue="1")int status){
		PageFinder<WithdrawOrder> list =moneyService.getWithdrawByStatus(pageNumber,Constants.BACKGROUND_PAGESIZE,status,request); 
		map.put("tx_tran_page",list);
		String path = "";
		switch (status) {
		case 1://申请
			path = "admin/money_management/transferapplication_withdwar_list";
			break;
		case 2://转账中
			path = "admin/money_management/transfering_withdwar_list";
			break;
		case 3://转账成功
			path = "admin/money_management/transuccess_withdwar_list";
			break;
		case 4://转账失败
			path = "admin/money_management/fail_withdwar_list";
			break;
		}
		return path;
	}
	
	//审核通过
	@UserRightAuth(menuCode="withdwar_view")
	@RequestMapping(value = "withdraw_pass", method = RequestMethod.POST)
	@ResponseBody
	public Object withdraw_pass(HttpServletRequest request,int id,int stauts,String remark){
		try {
			return moneyService.updatePassWithdwar(id,stauts,remark);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	//得到提现的详情信息
	@UserRightAuth(menuCode="withdwar_view")
	@RequestMapping(value = "withdraw_info", method = RequestMethod.GET)
	@ResponseBody
	public Object withdraw_info(HttpServletRequest request,int id){
		return moneyService.getWithdrawOrderById(id);
	} 
	
	
	
	/**
	 *导出提现明细单
	 * add by lxc	2015-04-15
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("exportwithdraw_list")
	public String exportwithdraw_list(HttpServletRequest request,@RequestParam(defaultValue="1")int pageNumber,@RequestParam(defaultValue="1")int status,
			HttpServletResponse response)
			throws Exception {
		String fileName =   "提现明细单";
		
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");

		OutputStream outputStream = null;
		try {
			PageFinder<WithdrawOrder> map = moneyService.getWithdrawByStatus(pageNumber,Integer.MAX_VALUE,status,request);
			HSSFWorkbook workbook = new HSSFWorkbook();
			HSSFSheet sheet = workbook.createSheet();
			HSSFRow row = sheet.createRow(0);
			HSSFCell cell = row.createCell(0, HSSFCell.CELL_TYPE_STRING);
			cell.setCellValue("用户名");
			cell = row.createCell(1, HSSFCell.CELL_TYPE_STRING);
			cell.setCellValue("真实姓名");
			cell = row.createCell(2, HSSFCell.CELL_TYPE_STRING);
			cell.setCellValue("提现银行");
			cell = row.createCell(3, HSSFCell.CELL_TYPE_STRING);
			cell.setCellValue("提现账号");
			cell = row.createCell(4, HSSFCell.CELL_TYPE_STRING);
			cell.setCellValue("提现金额");
			cell = row.createCell(5, HSSFCell.CELL_TYPE_STRING);
			cell.setCellValue("支行");
			cell = row.createCell(6, HSSFCell.CELL_TYPE_STRING);
			cell.setCellValue("手续费");
			cell = row.createCell(7, HSSFCell.CELL_TYPE_STRING);
			cell.setCellValue("申请时间");

			
//			{id=37, req_sn=null, order_sn=null, serial_no=B150415507549, uid=210, 
//			bank_code=0105, card_id=8, account_no=436742323234234, account_name=朱元璋, account_idcard=440582198809202376, wd_money=1,
//					withdraw_rate=0, amount_money=null, amount_time=null, wd_status=1, check_uid=0, check_time=null,
//			check_remark=null, submit_time=null, create_time=2015-04-15 16:45:07, update_time=2015-04-15 16:45:07, name=huifu1, real_name=朱元璋, bank_address=null, bank_name=中国建设银行}
			WithdrawOrder m =null;
//			for (int i = 0; i < map.getDataList().size(); i++) {
//				m = map.getDataList().get(i);
//				row = sheet.createRow(i + 1);
//				cell = row.createCell(0, HSSFCell.CELL_TYPE_STRING);
//				cell.setCellValue(String.valueOf(m.get("name")));
//				cell = row.createCell(1, HSSFCell.CELL_TYPE_STRING);
//				cell.setCellValue(String.valueOf(m.get("account_name")));
//				cell = row.createCell(2, HSSFCell.CELL_TYPE_STRING);
//				cell.setCellValue(String.valueOf(m.get("bank_name")));
//				cell = row.createCell(3, HSSFCell.CELL_TYPE_STRING);
//				cell.setCellValue(String.valueOf(m.get("account_no")));
//				cell = row.createCell(4, HSSFCell.CELL_TYPE_STRING);
//				cell.setCellValue(String.valueOf(m.get("wd_money")));
//				cell = row.createCell(5, HSSFCell.CELL_TYPE_STRING);
//				cell.setCellValue(String.valueOf(m.get("bank_address")==null?"":m.get("bank_address")));
//				cell = row.createCell(6, HSSFCell.CELL_TYPE_STRING);
//				cell.setCellValue(String.valueOf(m.get("withdraw_rate")==null?"":m.get("withdraw_rate")));
//				cell = row.createCell(7, HSSFCell.CELL_TYPE_STRING);
//				cell.setCellValue(String.valueOf(m.get("create_time")==null?"":m.get("create_time")));
//			}

			simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd_HHmm");
			fileName = fileName + simpleDateFormat.format(new Date()) + ".xls";

			response.reset();
			response.setContentType("application/x-msdownload ");
			response.setHeader("Content-disposition", "attachment;filename="
					+ new String(fileName.getBytes("GBK"), "ISO8859-1"));
			outputStream = response.getOutputStream();
			workbook.write(outputStream);
		} catch (Exception e) {
		} finally {
			if (outputStream != null) {
				outputStream.flush();
				outputStream.close();
			}
		}
		return null;
	}
	
	
	/***
	 * 线下手动转账后,点击已转账
	 * add by lxc	2015-04-15
	 * @param request
	 * @param id
	 * @param stauts
	 * @param remark
	 * @return
	 */
	@UserRightAuth(menuCode="withdwar_view")
	@RequestMapping(value = "withdraw_hand_movement", method = RequestMethod.POST)
	@ResponseBody
	public Object withdraw_hand_movement(HttpServletRequest request,int id,int stauts,String remark){
		Manager manager = getManager(request);
		try {
			return moneyService.updateHandMovementWithdwar(id,stauts,remark,manager);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
}
