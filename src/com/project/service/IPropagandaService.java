package com.project.service;

import java.util.List;

import com.project.orm.basedao.PageFinder;
import com.project.pojo.Advertise;
import com.project.pojo.FriendlyLink;
import com.project.pojo.Notice;
import com.project.pojo.PlatformInfo;

/**
 * 宣传  Service
 * @author daybreak
 *
 */
public interface IPropagandaService {
	/**
	 * 删除广告信息
	 * @param ad_id
	 */
	public void delAdvertise(int ad_id)  throws Exception ;
	/**
	 * 更新广告信息
	 * @param advertise
	 */
	public void updateAdvertise(Advertise advertise);
	/**
	 * 保存广告信息
	 * @param advertise
	 */
	public void saveAdvertise(Advertise advertise)  throws Exception ;
	/**
	 * 得到分页信息
	 * @param pageNumber
	 * @param pagesize
	 * @return
	 */
	public PageFinder<Advertise> getAdvertisePageBean(Advertise o,int pageNumber,int pagesize);
	/**
	 * 根据ID得到广告信息
	 * @param ad_id
	 * @return
	 */
	public Advertise getAdvertiseInfo(int ad_id);
	/**
	 * 得到所有广告信息
	 * @return
	 */
	public List<Advertise> getAllAdvertises();
	/**
	 * 根据type得到广告信息
	 * @param ad_type
	 * @return
	 */
	public List<Advertise> getAdvertiseByType(int ad_type);
	/**
	 * 网站公告分页
	 * @param pageNumber
	 * @param i
	 * @return
	 */
	public PageFinder<Notice> getNoticeList(int pageNumber, int i);
	
	/**
	 * 得到全部公告
	 * @return
	 */
	public List<Notice> getNoticeLists(int city_show_id,int province_id);
	
	/**
	 * 得到网站公告详情
	 * @param id
	 * @return
	 */
	public Notice getNoticeById(int id);
	/**
	 * 保存网站公告信息
	 * @param notice
	 * @return
	 */
	public boolean saveNotice(Notice notice);

	/**
	 * 得到友情链接列表
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	public PageFinder<FriendlyLink> getFriendlyLinkList(int pageNumber,int pageSize);
	/**
	 * 得到所有友情链接
	 * @return
	 */
	public List<FriendlyLink> getAllFriendlyLinks();
	/**
	 * 根据ID得到友情链接
	 * @param id
	 * @return
	 */
	public FriendlyLink getFriendlyLinkById(int id);
	/**
	 * 保存友情链接
	 * @param friendlyLink
	 * @return
	 */
	public boolean updateFriendlyLink(FriendlyLink friendlyLink);
	/**
	 * 添加友情链接
	 * @param team
	 * @return
	 */
	public FriendlyLink saveFriendlyLink(FriendlyLink link) throws Exception;
	/**
	 * 得到公司信息
	 * @return
	 */
	public PlatformInfo getPlatformInfo();
	/**
	 * 修改公司介绍信息|关于我们
	 * @param companyInfo
	 * @return
	 */
	public boolean updatePlatformInfoForCompany_info(PlatformInfo pi );
	
	/**
	 * 修改公司介绍信息|联系我们
	 * @param companyInfo
	 * @return
	 */
	public boolean updatePlatformInfoForContact_us(PlatformInfo pi );
	
	/**
	 * 修改公司介绍信息|协议
	 * @param pi
	 * @return
	 */
	public boolean updatePlatformInfoAgreement(PlatformInfo pi );
	
	/**
	 * 修改等级说明
	 * @param pi
	 * @return
	 */
	public boolean updatePlatformInfoLevel_explain(PlatformInfo pi );
	
	/**
	 * 获取移动端首页的广告banner图
	 * @return
	 */
	public List<Advertise> getAdlistOnMobile();
	
	/**
	 * 这是为mobile查询平台公告列表，所以没有查询content字段
	 * @param pageNumber
	 * @param pageSize
	 * @return
	 */
	public PageFinder<Notice> getNoticeListForMobile(int pageNumber, int pageSize);
	/**
	 * 删除友情链接
	 * @param id
	 */
	public void delFriendlink(int id)  throws Exception ;
	
	/**
	 * 删除网站公告
	 * @param id
	 */
	public void delNotice(int id)  throws Exception ;
	
	/**
	 * 编辑网站公告信息
	 * @param notice
	 */
	public void updateNotice(Notice notice);
	
	/**
	 * 根据链接类型获取数据
	 * @param linkType		链接类型 1 媒体报道，2 合作媒体
	 * @return
	 * add by dashan	2015-05-14
	 */
	List<FriendlyLink> getFriendLinksInTypes(String linkType);
	
	/***
	 * 根据文章类型获得数据
	 * @param pageNumber
	 * @param pageSize
	 * @param noticeType		类型 1 网站公告 2 新闻报道		
	 * add by dashan		2015-05-15
	 * @return
	 */
	PageFinder<Notice> getNoticeListWithNoticeType(int pageNumber, int pageSize,
			String noticeType);
	
	
	
}
