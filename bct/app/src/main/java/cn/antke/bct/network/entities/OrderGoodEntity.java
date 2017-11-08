package cn.antke.bct.network.entities;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by zhaoweiwei on 2017/5/18.
 */

public class OrderGoodEntity implements Parcelable{
	@SerializedName("goodsPic")
	private String goodsPic;
	@SerializedName("goodsNum")
	private String goodsNum;
	@SerializedName("goodsName")
	private String goodsName;
	@SerializedName("attribute")
	private String attribute;

	protected OrderGoodEntity(Parcel in) {
		goodsPic = in.readString();
		goodsNum = in.readString();
		goodsName = in.readString();
		attribute = in.readString();
	}

	public static final Creator<OrderGoodEntity> CREATOR = new Creator<OrderGoodEntity>() {
		@Override
		public OrderGoodEntity createFromParcel(Parcel in) {
			return new OrderGoodEntity(in);
		}

		@Override
		public OrderGoodEntity[] newArray(int size) {
			return new OrderGoodEntity[size];
		}
	};

	public String getGoodsPic() {
		return goodsPic;
	}

	public void setGoodsPic(String goodsPic) {
		this.goodsPic = goodsPic;
	}

	public String getGoodsNum() {
		return goodsNum;
	}

	public void setGoodsNum(String goodsNum) {
		this.goodsNum = goodsNum;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public String getAttribute() {
		return attribute;
	}

	public void setAttribute(String attribute) {
		this.attribute = attribute;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(goodsPic);
		dest.writeString(goodsNum);
		dest.writeString(goodsName);
		dest.writeString(attribute);
	}
}
