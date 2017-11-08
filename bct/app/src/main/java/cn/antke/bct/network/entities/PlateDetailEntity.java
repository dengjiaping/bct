package cn.antke.bct.network.entities;

import com.google.gson.annotations.SerializedName;

/**
 * Created by liuzhichao on 2017/5/24.
 * 板块详情
 */
public class PlateDetailEntity {

	@SerializedName("categoryId")
	private String id;
	@SerializedName("categoryName")
	private String name;
	@SerializedName("categoryPic")
	private String picUrl;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}
}
