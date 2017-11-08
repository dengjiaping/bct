package cn.antke.bct.network.entities;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by liuzhichao on 2017/5/17.
 * 店铺详情
 */
public class StoreDetailEntity extends Entity {

	@SerializedName("owner_head")
	private String logo;
	@SerializedName("store_pic")
	private String picUrl;
	@SerializedName("owner_name")
	private String name;
	@SerializedName("store_name")
	private String merchant;
	@SerializedName("goods_list")
	private List<ProductEntity> productEntities;
	@SerializedName("totalPage")
	private int totalPage;

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMerchant() {
		return merchant;
	}

	public void setMerchant(String merchant) {
		this.merchant = merchant;
	}

	public List<ProductEntity> getProductEntities() {
		return productEntities;
	}

	public void setProductEntities(List<ProductEntity> productEntities) {
		this.productEntities = productEntities;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
}
