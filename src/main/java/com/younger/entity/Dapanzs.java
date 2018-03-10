package com.younger.entity;

import org.json.JSONObject;

/**
 * 大盘指数实体
 * @author Administrator
 *
 */
public class Dapanzs extends BaseId{
	/** YYYYMMdd */
	public String p_time;
	/** 周一 至 周日*/
	public int p_dayOfWeek;
	/** 上证指数*/
	public double p_huzhi;
	/** 深圳指数*/
	public double p_szzhi;
	
	/**上涨家数*/
	public int p_sznum_up;
	/**下跌家数*/
	public int p_sznum_down;
	public int p_sznum_ping;
	
	/**上涨家数*/
	public int p_hunum_up;
	/**下跌家数*/
	public int p_hunum_down;
	public int p_hunum_ping;
	
	
	/** 上证今日开盘价*/
	public double p_huOpen;
	
	public double p_huHigh;
	
	public double p_hulow;
	
	/** 上证成交额 单位亿*/
	public double p_huDeal;
	
	/** 上证指数增量 */
	public double p_huDelta;
	
	public String p_huDeltaRatio;
	
	public double p_huNum;
	
	public double p_szOpen;
	
	public double p_szClose;
	
	public double p_szHigh;
	
	public double p_szLow;
	
	public double p_szDeal;
	
	public double p_szDelta;
	
	public String p_szDeltaRatio;
	
	public double p_szNum;

	public String getP_time() {
		return p_time;
	}

	public void setP_time(String p_time) {
		this.p_time = p_time;
	}

	public int getP_dayOfWeek() {
		return p_dayOfWeek;
	}

	public void setP_dayOfWeek(int p_dayOfWeek) {
		this.p_dayOfWeek = p_dayOfWeek;
	}

	public double getP_huzhi() {
		return p_huzhi;
	}

	public void setP_huzhi(double p_huzhi) {
		this.p_huzhi = p_huzhi;
	}

	public double getP_szzhi() {
		return p_szzhi;
	}

	public void setP_szzhi(double p_szzhi) {
		this.p_szzhi = p_szzhi;
	}

	public double getP_huOpen() {
		return p_huOpen;
	}

	public void setP_huOpen(double p_huOpen) {
		this.p_huOpen = p_huOpen;
	}

	public double getP_huHigh() {
		return p_huHigh;
	}

	public void setP_huHigh(double p_huHigh) {
		this.p_huHigh = p_huHigh;
	}

	public double getP_hulow() {
		return p_hulow;
	}

	public void setP_hulow(double p_hulow) {
		this.p_hulow = p_hulow;
	}

	public double getP_huDeal() {
		return p_huDeal;
	}

	public void setP_huDeal(double p_huDeal) {
		this.p_huDeal = p_huDeal;
	}

	public double getP_huDelta() {
		return p_huDelta;
	}

	public void setP_huDelta(double p_huDelta) {
		this.p_huDelta = p_huDelta;
	}

	public String getP_huDeltaRatio() {
		return p_huDeltaRatio;
	}

	public void setP_huDeltaRatio(String p_huDeltaRatio) {
		this.p_huDeltaRatio = p_huDeltaRatio;
	}

	public double getP_huNum() {
		return p_huNum;
	}

	public void setP_huNum(double p_huNum) {
		this.p_huNum = p_huNum;
	}

	public double getP_szOpen() {
		return p_szOpen;
	}

	public void setP_szOpen(double p_szOpen) {
		this.p_szOpen = p_szOpen;
	}

	public double getP_szClose() {
		return p_szClose;
	}

	public void setP_szClose(double p_szClose) {
		this.p_szClose = p_szClose;
	}

	public double getP_szHigh() {
		return p_szHigh;
	}

	public void setP_szHigh(double p_szHigh) {
		this.p_szHigh = p_szHigh;
	}

	public double getP_szLow() {
		return p_szLow;
	}

	public void setP_szLow(double p_szLow) {
		this.p_szLow = p_szLow;
	}

	public double getP_szDeal() {
		return p_szDeal;
	}

	public void setP_szDeal(double p_szDeal) {
		this.p_szDeal = p_szDeal;
	}

	public double getP_szDelta() {
		return p_szDelta;
	}

	public void setP_szDelta(double p_szDelta) {
		this.p_szDelta = p_szDelta;
	}

	public String getP_szDeltaRatio() {
		return p_szDeltaRatio;
	}

	public void setP_szDeltaRatio(String p_szDeltaRatio) {
		this.p_szDeltaRatio = p_szDeltaRatio;
	}

	public double getP_szNum() {
		return p_szNum;
	}

	public void setP_szNum(double p_szNum) {
		this.p_szNum = p_szNum;
	}

	public int getP_sznum_up() {
		return p_sznum_up;
	}

	public void setP_sznum_up(int p_sznum_up) {
		this.p_sznum_up = p_sznum_up;
	}

	public int getP_sznum_down() {
		return p_sznum_down;
	}

	public void setP_sznum_down(int p_sznum_down) {
		this.p_sznum_down = p_sznum_down;
	}

	public int getP_sznum_ping() {
		return p_sznum_ping;
	}

	public void setP_sznum_ping(int p_sznum_ping) {
		this.p_sznum_ping = p_sznum_ping;
	}

	public int getP_hunum_up() {
		return p_hunum_up;
	}

	public void setP_hunum_up(int p_hunum_up) {
		this.p_hunum_up = p_hunum_up;
	}

	public int getP_hunum_down() {
		return p_hunum_down;
	}

	public void setP_hunum_down(int p_hunum_down) {
		this.p_hunum_down = p_hunum_down;
	}

	public int getP_hunum_ping() {
		return p_hunum_ping;
	}

	public void setP_hunum_ping(int p_hunum_ping) {
		this.p_hunum_ping = p_hunum_ping;
	}
	

}
