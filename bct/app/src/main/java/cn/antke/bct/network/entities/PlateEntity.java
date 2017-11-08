package cn.antke.bct.network.entities;

import com.google.gson.annotations.SerializedName;

/**
 * Created by liuzhichao on 2017/5/3.
 * 专区、板块
 */
public class PlateEntity {

	@SerializedName("channelId")
	private String id;
	@SerializedName("channelType")
	private String type;//1抽奖,2商品,3板块
	@SerializedName("channelName")
	private String name;
	@SerializedName("channelPic")
	private String imgUrl;

	public PlateEntity(String name, String imgUrl) {
		this.name = name;
		this.imgUrl = imgUrl;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
}
