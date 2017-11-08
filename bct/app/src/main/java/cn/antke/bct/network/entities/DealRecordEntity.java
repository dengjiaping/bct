package cn.antke.bct.network.entities;

import com.google.gson.annotations.SerializedName;

/**
 * Created by liuzhichao on 2017/5/12.
 * 交易记录
 */
public class DealRecordEntity {

	@SerializedName("consumeNum")
	private String consumeNum;
	@SerializedName("buyNum")
	private String buyNum;
	@SerializedName("date")
	private String date;
	@SerializedName("type")
	private String type;

	public DealRecordEntity(String consumeNum, String buyNum, String date, String type) {
		this.consumeNum = consumeNum;
		this.buyNum = buyNum;
		this.date = date;
		this.type = type;
	}

	public String getConsumeNum() {
		return consumeNum;
	}

	public void setConsumeNum(String consumeNum) {
		this.consumeNum = consumeNum;
	}

	public String getBuyNum() {
		return buyNum;
	}

	public void setBuyNum(String buyNum) {
		this.buyNum = buyNum;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
