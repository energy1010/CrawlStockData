package com.younger.entity;

/**
 * 股票
 * @author Administrator
 *
 */
public class Stock  extends BaseId {
	
	public Stock() {
	}
	
	public Stock(String stockId, String stockName) {
		this.p_id = stockId;
		this.p_name = stockName;
	}

	private String p_id;
	private String p_name;
	private boolean p_ishz;
	
//	private double p_price;
//	private 
	public String getP_id() {
		return p_id;
	}
	public void setP_id(String p_id) {
		this.p_id = p_id;
	}
	public String getP_name() {
		return p_name;
	}
	public void setP_name(String p_name) {
		this.p_name = p_name;
	}
	
	
	public boolean isP_ishz() {
		return p_ishz;
	}

	public void setP_ishz(boolean p_ishz) {
		this.p_ishz = p_ishz;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
//		return super.toString();
		return String.format("%d:%s", p_id, p_name);
	}
	
	@Override
	public String toCSVStr() {
		return p_id+","+p_name;
	}
	
}
