package cn.antke.bct.network.entities;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by liuzhichao on 2017/5/11.
 * 抽奖
 */
public class DrawEntity {

	@SerializedName("h5")
	private String contentUrl;

	@SerializedName("picList")
	private List<DrawEntity> picList;
	@SerializedName("picUrl")
	private String picUrl;

	@SerializedName("prize")
	private DrawEntity drawEntity;
	@SerializedName("prizeId")
	private String id;
	@SerializedName("goodsName")
	private String name;
	@SerializedName("pic")
	private String imgUrl;
	@SerializedName("usingIntegral")
	private String price;
	@SerializedName("buyNum")
	private String num;
	@SerializedName("prizeTimes")
	private String allNum;

	public DrawEntity(String name, String picUrl, String price, String num, String allNum) {
		this.name = name;
		this.picUrl = picUrl;
		this.price = price;
		this.num = num;
		this.allNum = allNum;
	}

	public String getContentUrl() {
		return contentUrl;
	}

	public void setContentUrl(String contentUrl) {
		this.contentUrl = contentUrl;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public List<DrawEntity> getPicList() {
		return picList;
	}

	public void setPicList(List<DrawEntity> picList) {
		this.picList = picList;
	}

	public DrawEntity getDrawEntity() {
		return drawEntity;
	}

	public void setDrawEntity(DrawEntity drawEntity) {
		this.drawEntity = drawEntity;
	}

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

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public String getAllNum() {
		return allNum;
	}

	public void setAllNum(String allNum) {
		this.allNum = allNum;
	}
}
