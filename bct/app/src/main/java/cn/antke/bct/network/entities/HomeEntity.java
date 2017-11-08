package cn.antke.bct.network.entities;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by liuzhichao on 2017/5/16.
 * 首页实体类
 */
public class HomeEntity extends PagesEntity<ProductEntity> {

	@SerializedName("personallyLike")
	private List<ProductEntity> productEntities;
	@SerializedName("rotationImg")
	private List<BannerEntity> bannerEntities;
	@SerializedName("category")
	private List<PlateEntity> plateEntities;

	public List<ProductEntity> getProductEntities() {
		return productEntities;
	}

	public void setProductEntities(List<ProductEntity> productEntities) {
		this.productEntities = productEntities;
	}

	public List<BannerEntity> getBannerEntities() {
		return bannerEntities;
	}

	public void setBannerEntities(List<BannerEntity> bannerEntities) {
		this.bannerEntities = bannerEntities;
	}

	public List<PlateEntity> getPlateEntities() {
		return plateEntities;
	}

	public void setPlateEntities(List<PlateEntity> plateEntities) {
		this.plateEntities = plateEntities;
	}
}
