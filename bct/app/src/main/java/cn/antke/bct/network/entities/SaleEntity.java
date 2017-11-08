package cn.antke.bct.network.entities;

import com.google.gson.annotations.SerializedName;

/**
 * Created by liuzhichao on 2017/5/23.
 * 交易大厅-卖出
 */
public class SaleEntity {

	@SerializedName("id")
	private String id;
	@SerializedName("saleIntegral")
	private String saleIntegral;
	@SerializedName("salePrice")
	private String salePrice;
	@SerializedName("date")
	private String date;

	public SaleEntity(String saleIntegral, String salePrice, String date) {
		this.saleIntegral = saleIntegral;
		this.salePrice = salePrice;
		this.date = date;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSaleIntegral() {
		return saleIntegral;
	}

	public void setSaleIntegral(String saleIntegral) {
		this.saleIntegral = saleIntegral;
	}

	public String getSalePrice() {
		return salePrice;
	}

	public void setSalePrice(String salePrice) {
		this.salePrice = salePrice;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
}
