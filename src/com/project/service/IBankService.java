package com.project.service;

import java.util.List;

import com.project.orm.basedao.PageFinder;
import com.project.pojo.BankCard;
import com.project.pojo.BankInfo;

public interface IBankService {
	/**
	 * 得到所有银行信息
	 * @return
	 */
	public List<BankInfo> getAllBankInfos();
	/**
	 * 根据UID得到用户所有银行卡信息
	 * @param uid
	 * @return
	 */
	public List<BankCard> getAllBankCardsByUid(int uid);
	/**
	 * 保存用户银行卡信息
	 * @param card
	 * @return
	 */
	public boolean saveBankCardInfo(BankCard card);
	/**
	 * 得到所有用户卡片
	 * @param pageNumber
	 * @param i
	 * @return
	 */
	public PageFinder<BankCard> getAllBandCardByPage(int pageNumber, int i,BankCard o);
	/**
	 * 改变银行卡的状态
	 * @param card_id
	 * @param status
	 * @return
	 */
	public BankCard updateCardStatus(int card_id,int status);
	/**
	 * 根据Id得到银行卡信息
	 * @param card_id
	 * @return
	 */
	public BankCard getCardInfoById(int card_id);
	
	/**
	 * 根据Id得到银行卡信息(带级联,用户和银行)
	 * @param card_id
	 * @return
	 */
	public BankCard getCardInfoById_cascade(int card_id);
	
	/**
	 * 得到用户可用的银行卡 -> 通过审核
	 * @param id
	 * @return
	 */
	public List<BankCard> getAuthBankCardsByUid(int id);
	/**
	 * 修改银行卡信息
	 * @param card
	 * @return
	 */
	public BankCard updateBankCardInfo(BankCard card);
	/**
	 * 删除银行卡信息
	 * @param card_id
	 * @return
	 */
	public boolean delBankCardInfo(int card_id);
	/**
	 * 用户银行卡审核状态
	 * @param card
	 */
	public void updateByCardAuth(BankCard card);
	/**
	 * 根据银行卡卡号得到银行卡对象
	 * @param card_num
	 * @return
	 */
	public BankCard getCardInfoByCard_num(String card_num);
	/**
	 * 得到用户审核的银行卡变更数量
	 * @param status
	 * @return
	 */
	public Integer getBankCardAuthCount(int status);
	
	/**
	 * 根据Id和uid得到银行卡信息
	 * @param card_id
	 * @param uid
	 * @return
	 */
	public BankCard getCardInfoById_Uid(int card_id,int uid);

}