package cn.antke.bct.network.entities;

import com.google.gson.annotations.SerializedName;

/**
 * Created by liuzhichao on 2017/5/4.
 * 商品
 */
public class ProductEntity {

	@SerializedName("goods_id")
	private String goodsId;
	@SerializedName("goods_name")
	private String goodsName;
	@SerializedName("pic_url")
	private String picUrl;
	@SerializedName("price")
	private String sellingPrice;
	@SerializedName("integral")
	private String sellingIntegral;
	@SerializedName("brand_name")
	private String storeName;
	@SerializedName("areaName")
	private String address;

	private boolean isChecked;
	private boolean isEditMode;

	public ProductEntity(String name, String picUrl, String integral) {
		this.goodsName = name;
		this.picUrl = picUrl;
		this.sellingIntegral = integral;
	}

	public ProductEntity(String goodsName, String picUrl, String sellingPrice, String sellingIntegral, String address) {
		this.goodsName = goodsName;
		this.picUrl = picUrl;
		this.sellingPrice = sellingPrice;
		this.sellingIntegral = sellingIntegral;
		this.address = address;
	}

	public String getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	public String getSellingPrice() {
		return sellingPrice;
	}

	public void setSellingPrice(String sellingPrice) {
		this.sellingPrice = sellingPrice;
	}

	public String getSellingIntegral() {
		return sellingIntegral;
	}

	public void setSellingIntegral(String sellingIntegral) {
		this.sellingIntegral = sellingIntegral;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public boolean isChecked() {
		return isChecked;
	}

	public void setChecked(boolean checked) {
		isChecked = checked;
	}

	public boolean isEditMode() {
		return isEditMode;
	}

	public void setEditMode(boolean editMode) {
		isEditMode = editMode;
	}
}
