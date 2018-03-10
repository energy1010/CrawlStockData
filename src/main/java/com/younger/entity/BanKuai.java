package com.younger.entity;

/**
 * 板块信息
 * @author Administrator
 *
 */
public class BanKuai extends BaseId {

	private String p_bkid;
	
	private String p_bkname;
	
	

	public BanKuai(String p_bkid, String p_bkname) {
		super();
		this.p_bkid = p_bkid;
		this.p_bkname = p_bkname;
	}

	public String getP_bkid() {
		return p_bkid;
	}

	public void setP_bkid(String p_bkid) {
		this.p_bkid = p_bkid;
	}

	public String getP_bkname() {
		return p_bkname;
	}

	public void setP_bkname(String p_bkname) {
		this.p_bkname = p_bkname;
	}
	
	@Override
	public String toCSVStr() {
		return p_bkid+","+p_bkname;
	}
}
