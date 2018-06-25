package com.project.controller.admin;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.baidu.yun.push.exception.PushClientException;
import com.baidu.yun.push.exception.PushServerException;
import com.project.common.Constants.MES_CLOUD_TYPE;
import com.project.common.Settings;
import com.project.controller.BaseController;
import com.project.pojo.PlaygroundManager;
import com.project.pojo.StationMessage;
import com.project.pojo.TerraceMessage;
import com.project.pojo.Weuser;
import com.project.service.IPlaygroundManagerService;
import com.project.service.IStationMessageService;
import com.project.service.ITerraceMessageService;
import com.project.service.IWeuserService;
import com.project.util.BaiduPushForAndroid;
import com.project.util.BaiduPushForIOS;
import com.project.util.CommonUtil;
import com.project.util.SMSUtil;


@Controller("adminstationmessageController")
@RequestMapping(value="/admin")
public class StationMessageController extends BaseController{

	@Resource
	private IStationMessageService stationMessageService;
	@Resource
	private IWeuserService weuserService;
	@Resource
	private ITerraceMessageService terraceMessageService;
	@Resource
	private IPlaygroundManagerService playgroundManagerService; 
	 
	/**
	 * 返回用户接收消息数据
	 * @param request
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "addStationMessage")
	public String AddstationMessage(HttpServletRequest request,Integer oid){
		TerraceMessage t= terraceMessageService.getById(oid);
		
		if(t.getSend_type()==0){
			for(Weuser user: weuserService.getAllUser()){
				StationMessage sm=new StationMessage();
				sm.setContent(t.getContent());
				sm.setTitle(t.getTitle());
				sm.setWeuser_id(user.getId());
				try {
					stationMessageService.saveStationMessage(sm);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
		if(t.getSend_type()==1){
			String[] phone=t.getUser_data().split(",");
			
			for(String p:phone){
				if(weuserService.getUserByPhone(p)!=null){
					StationMessage sm=new StationMessage();
					sm.setContent(t.getContent());
					sm.setTitle(t.getTitle());
					sm.setWeuser_id(weuserService.getUserByPhone(p).getId());
					try {
						stationMessageService.saveStationMessage(sm);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				
				if(playgroundManagerService.getPGM(p)!=null){
					StationMessage sm=new StationMessage();
					sm.setContent(t.getContent());
					sm.setTitle(t.getTitle());
					sm.setWeuser_id(playgroundManagerService.getPGM(p).getId());
					sm.setSend_type(2);
					try {
						stationMessageService.saveStationMessage(sm);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
		
		if(t.getSend_type()==2){
			String[] lev=t.getUser_data().split("-");
			if(lev.length==1){
				for(Weuser user: weuserService.getAllUser()){
					
					//当用户等级不等于空的时候
					if(!"".equals(user.getTennis_level())){
						
						Double d=Double.valueOf(user.getTennis_level().substring(2));
						
						if(d<=Double.valueOf(lev[0])){
							StationMessage sm=new StationMessage();
							sm.setContent(t.getContent());
							sm.setTitle(t.getTitle());
							sm.setWeuser_id(user.getId());
							try {
								stationMessageService.saveStationMessage(sm);
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
						
						
					}
				}
			}
			
			if(lev.length==2){
				for(Weuser user: weuserService.getAllUser()){
					
					//当用户等级不等于空的时候
					if(!"".equals(user.getTennis_level())){
						
						Double d=Double.valueOf(user.getTennis_level().substring(2));
						
						if(d>=Double.valueOf(lev[0]) && d<=Double.valueOf(lev[1])){
							StationMessage sm=new StationMessage();
							sm.setContent(t.getContent());
							sm.setTitle(t.getTitle());
							sm.setWeuser_id(user.getId());
							try {
								stationMessageService.saveStationMessage(sm);
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
						
						
					}
				}
			}
		}
		
		if(t.getSend_type()==3){
			for(Weuser user: weuserService.getAllUserByIs_coach(0)){
				StationMessage sm=new StationMessage();
				sm.setContent(t.getContent());
				sm.setTitle(t.getTitle());
				sm.setWeuser_id(user.getId());
				try {
					stationMessageService.saveStationMessage(sm);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
		if(t.getSend_type()==4){
			for(Weuser user: weuserService.getAllUserByIs_coach(1)){
				StationMessage sm=new StationMessage();
				sm.setContent(t.getContent());
				sm.setTitle(t.getTitle());
				sm.setWeuser_id(user.getId());
				try {
					stationMessageService.saveStationMessage(sm);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
		if(t.getSend_type()==5){
			for(PlaygroundManager pm:playgroundManagerService.getAll()){
				StationMessage sm=new StationMessage();
				sm.setContent(t.getContent());
				sm.setTitle(t.getTitle());
				sm.setWeuser_id(pm.getId());
				sm.setSend_type(2);
				try {
					stationMessageService.saveStationMessage(sm);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
		
		return "redirect:terraceMessage_list.do";
	}
	
	
	/**
	 * 返回用户接收消息数据
	 * @param request
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "dAddStationMessage")
	public String dAddstationMessage(HttpServletRequest request,Integer oid){
 		TerraceMessage t= terraceMessageService.getById(oid);
		
		if(t.getSend_type()==0){
			for(Weuser user: weuserService.getAllUser()){
				
			}
		}
		
		if(t.getSend_type()==1){
			String[] phone=t.getUser_data().split(",");
			
			for(String p:phone){
				logger.info("手动输入 ... 发送到"+p);
				if(weuserService.getUserByPhone(p)!=null){
					SMSUtil.sendSmsMsg(p, t.getContent());
				}
			}
		}
		
		if(t.getSend_type()==2){
			String[] lev=t.getUser_data().split("-");
			logger.info("等级 ... 发送到"+t.getUser_data());
			if(lev.length==1){
				for(Weuser user: weuserService.getAllUser()){
					
					//当用户等级不等于空的时候
					if(!"".equals(user.getTennis_level())){
						
						Double d=Double.valueOf(user.getTennis_level().substring(2));
						
						if(d<Double.valueOf(lev[0])){
							SMSUtil.sendSmsMsg(user.getUphone(), t.getContent());
						}
						
						
					}
				}
			}
			
			if(lev.length==2){
				for(Weuser user: weuserService.getAllUser()){
					
					//当用户等级不等于空的时候
					if(!"".equals(user.getTennis_level())){
						
						Double d=Double.valueOf(user.getTennis_level().substring(2));
						
						if(d>Double.valueOf(lev[0]) && d<Double.valueOf(lev[1])){
							SMSUtil.sendSmsMsg(user.getUphone(), t.getContent());
						}
						
						
					}
				}
			}
		}
		
		if(t.getSend_type()==3){
			for(Weuser user: weuserService.getAllUserByIs_coach(0)){
				SMSUtil.sendSmsMsg(user.getUphone(), t.getContent());
			}
		}
		
		if(t.getSend_type()==4){
			for(Weuser user: weuserService.getAllUserByIs_coach(1)){
				SMSUtil.sendSmsMsg(user.getUphone(), t.getContent());
			}
		}
		
		if(t.getSend_type()==5){
			for(PlaygroundManager pm:playgroundManagerService.getAll()){
				SMSUtil.sendSmsMsg(pm.getMobile(), t.getContent());
			}
		}
		
		if(t.getSend_type()==6){
			String[] s=t.getUser_data().split(",");
			
			for(String st:s){
				if(!"".equals(st)){
					SMSUtil.sendSmsMsg(st, t.getContent());
				}
			}
		}
		
		return "redirect:terraceMessage_list.do?note=note";
	}
	
	/**
	 * 云推送页面
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "push")
	public String cloudPush(HttpServletRequest request,ModelMap map){
		String che = request.getParameter("che");
		map.put("che", che);
		return "admin/cloudPush";
	}
	
	/**
	 * 云推送页面
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "viewpush")
	public String viewcloudPush(HttpServletRequest request,ModelMap map,int oid){
		map.put("o", terraceMessageService.getById(oid));
		return "admin/terraceMessage/viewcloudPush";
	}
	
	/**
	 * 云推送
	 * @param request
	 * @param title
	 * @param content
	 * @param type
	 * @param phoneType			手机类型,3表示安卓 4表示苹果
	 * @param phone				手机号码
	 * @param range				n以下或者n-m区间(只填数字)包含填的数字
	 * @param usertype			推送端口类型,0表示用户端,1表示教练端
	 * @param textData			管理员发送时,所选的条件
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "addpush")
	public  String addpush(HttpServletRequest request,String title,String content,int type,
								String phoneType,String phone,String range,String usertype,String textData) throws Exception{
		Weuser o = new Weuser();
		o.setIs_coach(type);
		o.setUphone(phone);
		List<Weuser> wlist = weuserService.getUserByObj(o);
		
		
		StationMessage s = null;
		for (Weuser weuser : wlist) {
			if(CommonUtil.NotEmpty(range)){  //筛选 等级
				String[] lev = range.split("-");
				if(lev.length==1){  
					if(!"".equals(weuser.getTennis_level())){
						Double d=Double.valueOf(weuser.getTennis_level().substring(2));
						if(d<=Double.valueOf(lev[0])){
							msgInfo(usertype,phoneType,weuser.getUphone(),title,content);
							s = new StationMessage();
							s.setContent(content);
							s.setTitle(title);
							//s.setSummary(title);//不保存摘要
							s.setWeuser_id(weuser.getId());
							
							//3表示安卓 4表示苹果
							if(CommonUtil.NotEmpty(phoneType)){
								if(phoneType.contains("3") && phoneType.contains("4")){
									s.setAppsyetem(Settings.ANDROID+","+Settings.IOS);
								}else if(phoneType.contains("3")){
									s.setAppsyetem(Settings.ANDROID);
								}else if(phoneType.contains("4")){
									s.setAppsyetem(Settings.IOS);
								}
							}else{
								s.setAppsyetem(Settings.ANDROID+","+Settings.IOS);
							}
							
							System.err.println(weuser.getId()+"保存云推送到消息表中");
							stationMessageService.saveStationMessage(s);
						}
					}
				}
				if(lev.length==2){
					if(!"".equals(weuser.getTennis_level())){
						Double d=Double.valueOf(weuser.getTennis_level().substring(2));
						if(d>=Double.valueOf(lev[0]) && d<=Double.valueOf(lev[1])){
							msgInfo(usertype,phoneType,weuser.getUphone(),title,content);
							s = new StationMessage();
							s.setContent(content);
							s.setTitle(title);
							//s.setSummary(title);//不保存摘要
							s.setWeuser_id(weuser.getId());
							//3表示安卓 4表示苹果
							if(CommonUtil.NotEmpty(phoneType)){
								if(phoneType.contains("3") && phoneType.contains("4")){
									s.setAppsyetem(Settings.ANDROID+","+Settings.IOS);
								}else if(phoneType.contains("3")){
									s.setAppsyetem(Settings.ANDROID);
								}else if(phoneType.contains("4")){
									s.setAppsyetem(Settings.IOS);
								}
							}else{
								s.setAppsyetem(Settings.ANDROID+","+Settings.IOS);
							}
							
							System.err.println(weuser.getId()+"保存云推送到消息表中");
							stationMessageService.saveStationMessage(s);
						}
					}
				}
			}else{  
				msgInfo(usertype,phoneType,weuser.getUphone(),title,content);
				s = new StationMessage();
				s.setContent(content);
				s.setTitle(title);
				//s.setSummary(title);//不保存摘要
				s.setWeuser_id(weuser.getId());
				
				//3表示安卓 4表示苹果
				if(CommonUtil.NotEmpty(phoneType)){
					if(phoneType.contains("3") && phoneType.contains("4")){
						s.setAppsyetem(Settings.ANDROID+","+Settings.IOS);
					}else if(phoneType.contains("3")){
						s.setAppsyetem(Settings.ANDROID);
					}else if(phoneType.contains("4")){
						s.setAppsyetem(Settings.IOS);
					}
				}else{
					s.setAppsyetem(Settings.ANDROID+","+Settings.IOS);
				}
				
				
				System.err.println(weuser.getId()+"保存云推送到消息表中");
				stationMessageService.saveStationMessage(s);
			}
		}
		
		//S		发送云推送时,添加数据			edit by lxc	2015-12-14
		TerraceMessage t = new TerraceMessage();		
		t.setContent(content);
		t.setTitle(title);
		t.setSummary(title);
		t.setMes_cloud_type(MES_CLOUD_TYPE.CLOUD.getStatus());
		t.setUser_data(textData);
		terraceMessageService.saveTerraceMessage(t);
		//E
		return "redirect:push.do?che=1";
	}
	
	
	
	public void msgInfo(String usertype,String phoneType,String phone,String title,String content) throws PushClientException, PushServerException{
		if(CommonUtil.NotEmpty(usertype) && CommonUtil.NotEmpty(phoneType)){
			if(usertype.equals("0,1") && phoneType.equals("3")){
				BaiduPushForAndroid.AdroidPushTagMessage(phone+"0",title, content,"0",3); 
				BaiduPushForAndroid.AdroidPushTagMessage(phone+"0",title, content,"1",3); 
			}else if(usertype.equals("0,1") && phoneType.equals("4")){
				BaiduPushForAndroid.AdroidPushTagMessage(phone+"0",title, content,"0",4); 
				BaiduPushForIOS.IOSPushTagMessage(phone+"0",title, content,"1"); 
			}else if(usertype.equals("0") && phoneType.equals("3,4")){
				BaiduPushForAndroid.AdroidPushTagMessage(phone+"0",title, content,"0",3); 
				BaiduPushForIOS.IOSPushTagMessage(phone+"0",title, content,"0"); 
			}else if(usertype.equals("1") && phoneType.equals("3,4")){
				BaiduPushForAndroid.AdroidPushTagMessage(phone+"0",title, content,"1",3); 
				BaiduPushForIOS.IOSPushTagMessage(phone+"0",title, content,"1"); 
			}else if(usertype.equals("0") && phoneType.equals("3")){
				BaiduPushForAndroid.AdroidPushTagMessage(phone+"0",title, content,"0",3);
			}else if(usertype.equals("0") && phoneType.equals("4")){
				BaiduPushForIOS.IOSPushTagMessage(phone+"0",title, content,"0");
			}else if(usertype.equals("1") && phoneType.equals("3")){
				BaiduPushForAndroid.AdroidPushTagMessage(phone+"0",title, content,"1",3);
			}else if(usertype.equals("1") && phoneType.equals("4")){
				BaiduPushForIOS.IOSPushTagMessage(phone+"0",title, content,"1");
			}else{
				BaiduPushForAndroid.AdroidPushTagMessage(phone+"0",title, content,"0",3); 
				BaiduPushForIOS.IOSPushTagMessage(phone+"0",title, content,"0"); 
				BaiduPushForAndroid.AdroidPushTagMessage(phone+"0",title, content,"1",3); 
				BaiduPushForIOS.IOSPushTagMessage(phone+"0",title, content,"1"); 
			}
		}else if(CommonUtil.NotEmpty(usertype) && !CommonUtil.NotEmpty(phoneType)){
			if(usertype.equals("0,1")){
				BaiduPushForAndroid.AdroidPushTagMessage(phone+"0",title, content,"0",3); 
				BaiduPushForIOS.IOSPushTagMessage(phone+"0",title, content,"0"); 
				BaiduPushForAndroid.AdroidPushTagMessage(phone+"0",title, content,"1",3); 
				BaiduPushForIOS.IOSPushTagMessage(phone+"0",title, content,"1"); 
			}else if(usertype.equals("0")){
				BaiduPushForAndroid.AdroidPushTagMessage(phone+"0",title, content,"0",3); 
				BaiduPushForIOS.IOSPushTagMessage(phone+"0",title, content,"0"); 
			}else if(usertype.equals("1")){
				BaiduPushForAndroid.AdroidPushTagMessage(phone+"0",title, content,"1",3); 
				BaiduPushForIOS.IOSPushTagMessage(phone+"0",title, content,"1"); 
			}
		}else if(CommonUtil.NotEmpty(phoneType) && !CommonUtil.NotEmpty(usertype)){
			if(phoneType.equals("3,4")){
				BaiduPushForAndroid.AdroidPushTagMessage(phone+"0",title, content,"0",3); 
				BaiduPushForIOS.IOSPushTagMessage(phone+"0",title, content,"0"); 
				BaiduPushForAndroid.AdroidPushTagMessage(phone+"0",title, content,"1",3); 
				BaiduPushForIOS.IOSPushTagMessage(phone+"0",title, content,"1"); 
			}else if(phoneType.equals("3")){
				BaiduPushForAndroid.AdroidPushTagMessage(phone+"0",title, content,"0",3); 
				BaiduPushForAndroid.AdroidPushTagMessage(phone+"0",title, content,"1",3); 
			}else if(phoneType.equals("4")){
				BaiduPushForIOS.IOSPushTagMessage(phone,title, content,"0"); 
				BaiduPushForIOS.IOSPushTagMessage(phone,title, content,"1"); 
			}
		}else{
			BaiduPushForAndroid.AdroidPushTagMessage(phone+"0",title, content,"0",3); 
			BaiduPushForAndroid.AdroidPushTagMessage(phone+"0",title, content,"0",4); 
			BaiduPushForAndroid.AdroidPushTagMessage(phone+"0",title, content,"1",3); 
			BaiduPushForAndroid.AdroidPushTagMessage(phone+"0",title, content,"1",4); 
		}
	}
}
