package com.project.service;

import com.project.pojo.Qualification_certificate;

public interface ICertificateService  {
	
	/**
	 * 保存资质证书
	 * @param o
	 * @return
	 */
	public Qualification_certificate saveCertificate(Qualification_certificate o) throws Exception;
	
	/**
	 * 根据编号查询资质证书
	 * @param id
	 * @return
	 */
	public Qualification_certificate getCertificateById(int id);
	
	/**
	 * 修改资质证书
	 * @param o
	 * @return
	 */
	public Qualification_certificate updateCertificate(Qualification_certificate o) throws Exception;
	
	/**
	 * 根据证书类型和所属编号查询资质证书
	 * @param type
	 * @param zheId
	 * @return
	 */
	public Qualification_certificate getCertificateByTypeAndZheId(int type,int zheId);
}
