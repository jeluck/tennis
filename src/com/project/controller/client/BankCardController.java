package com.project.controller.client;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.auth.ClientLoginAuth;
import com.project.common.Constants.O_STATUS;
import com.project.controller.BaseController;
import com.project.pojo.BankCard;
import com.project.pojo.Region;
import com.project.pojo.Weuser;
import com.project.service.IBankService;
import com.project.service.IRegionService;
import com.project.service.IWeuserService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.util.List;

@Controller("bankCardWebController")
@RequestMapping(value="/")
public class BankCardController extends BaseController{

    @Resource
    private IBankService bankService;
    @Resource
    private IRegionService regionService;
    @Resource
    private IWeuserService weuserService;
	/**
	 * 个人中心-->银行卡管理
	 * 
	 * */
	@ClientLoginAuth
	@RequestMapping(value = "bank_card.do", method = RequestMethod.GET)
	public String bank_card(HttpServletRequest request, ModelMap map) {
		List<Region> regionlist = regionService.getProvince();
		map.put("provincelist", regionlist);
		map.put("bank_list", bankService.getAllBankInfos());
		//map.put("card_list", bankService.getAllBankCardsByUid(getUser(request).getId()));
		List<Weuser> w = weuserService.getAllUser();
    	map.put("w", w);
		return "test/bank_card";
	}

	/**
	 * 添加银行卡
	 * @param request
	 * @param card
	 * @return
	 */
	@ClientLoginAuth
	@RequestMapping(value = "save_bank_card.do" )
	public String save_bank_card(HttpServletRequest request,BankCard card){
		//card.setAccount_name(getUser(request).getReal_name());
		card.setCard_status(O_STATUS.WAIT_FOR_CHECK.getStatus());//待审核
		bankService.saveBankCardInfo(card);
		return "redirect:bank_card.do";
	}
	//得到银行卡详情信息
	@ClientLoginAuth
	@ResponseBody
	@RequestMapping(value = "get_card_info.do", method = RequestMethod.GET)
	public Object get_card_info(HttpServletRequest request,int card_id){
		return bankService.getCardInfoById(card_id);
	}
	
	//修改银行卡详情信息
	@ClientLoginAuth
	@ResponseBody
	@RequestMapping(value = "update_card_info.do", method = RequestMethod.POST)
	public Object update_card_info(HttpServletRequest request,BankCard card,HttpSession session){
		String phone_verify = request.getParameter("phone_verify");
		if(!NotEmpty(phone_verify)){
			return pushmsg("请输入手机验证码", false);
		}
		Object session_smskey = session.getAttribute("sms_key");
		if(!phone_verify.equals(session_smskey)){
			return pushmsg("手机验证码输入有误，请重新输入", false);
		}
		card.setCard_status(O_STATUS.WAIT_FOR_CHECK.getStatus());//待审核
		if(bankService.updateBankCardInfo(card)!=null){
			return pushmsg("已经提交银行卡变更申请", true);
		}
		return pushmsg("提交失败", false);
	}
	
	//删除银行卡详情信息
	@ClientLoginAuth
	@ResponseBody
	@RequestMapping(value = "del_card_info.do", method = RequestMethod.POST)
	public Object del_card_info(HttpServletRequest request,int card_id,HttpSession session){
		String phone_verify = request.getParameter("phone_verify");
		if(!NotEmpty(phone_verify)){
			return pushmsg("请输入手机验证码", false);
		}
		Object session_smskey = session.getAttribute("sms_key");
		if(!phone_verify.equals(session_smskey)){
			return pushmsg("手机验证码输入有误，请重新输入", false);
		}
		if(bankService.delBankCardInfo(card_id)){
			return pushmsg("已经成功删除该银行卡信息", true);
		}
		return pushmsg("删除失败", false);
	}
	
	/**
	 * 检测银行卡号的唯一性
	 * @param request
	 * @param card_num
	 * @return
	 */
	@ClientLoginAuth
	@ResponseBody
	@RequestMapping(value = "check_card_unique.do", method = RequestMethod.POST)
	public Object check_card_unique(HttpServletRequest request,String card_num){
		if(bankService.getCardInfoByCard_num(card_num)!=null){
			return pushmsg("该银行卡已存在", false);
		}
		return pushmsg("可以添加", true);
	}
	
}
