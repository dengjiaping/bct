package cn.antke.bct.network.entities;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by liuzhichao on 2017/5/22.
 * 购物车
 */
public class ShopCarEntity {

	@SerializedName("store")
	private String store;
	@SerializedName("product")
	private List<ProductEntity> productEntities;
	private boolean isChecked;
	private boolean isEditMode;

	public ShopCarEntity(String store) {
		this.store = store;
	}

	public String getStore() {
		return store;
	}

	public void setStore(String store) {
		this.store = store;
	}

	public List<ProductEntity> getProductEntities() {
		return productEntities;
	}

	public void setProductEntities(List<ProductEntity> productEntities) {
		this.productEntities = productEntities;
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
