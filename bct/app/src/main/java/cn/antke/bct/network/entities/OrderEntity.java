package cn.antke.bct.network.entities;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by zhaoweiwei on 2017/5/11.
 */

public class OrderEntity implements Parcelable {
	@SerializedName("storeName")
	private String storeName;
	@SerializedName("status")
	private String status;
	@SerializedName("orderTotal")
	private String orderTotal;
	@SerializedName("logistics")
	private String logistics;

	@SerializedName("address")
	private String address;
	@SerializedName("consignee")
	private String consignee;
	@SerializedName("contacts")
	private String contacts;
	@SerializedName("createDate")
	private String createDate;
	@SerializedName("payTime")
	private String payTime;
	@SerializedName("orderCode")
	private String orderCode;
	@SerializedName("refundReason")
	private String refundReason;
	@SerializedName("refundDescribe")
	private String refundDescribe;

	@SerializedName("totalMoney")
	private String totalMoney;
	@SerializedName("totalIntegral")
	private String totalIntegral;
	@SerializedName("payType")
	private String payType;


	@SerializedName("detailList")
	private List<OrderGoodEntity> goodEntities;


	protected OrderEntity(Parcel in) {
		storeName = in.readString();
		status = in.readString();
		orderTotal = in.readString();
		logistics = in.readString();
		address = in.readString();
		consignee = in.readString();
		contacts = in.readString();
		createDate = in.readString();
		payTime = in.readString();
		orderCode = in.readString();
		refundReason = in.readString();
		refundDescribe = in.readString();
		totalMoney = in.readString();
		totalIntegral = in.readString();
		payType = in.readString();
		goodEntities = in.createTypedArrayList(OrderGoodEntity.CREATOR);
	}

	public static final Creator<OrderEntity> CREATOR = new Creator<OrderEntity>() {
		@Override
		public OrderEntity createFromParcel(Parcel in) {
			return new OrderEntity(in);
		}

		@Override
		public OrderEntity[] newArray(int size) {
			return new OrderEntity[size];
		}
	};

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getStatus() {
		return status;
	}

	public String getStatusName() {
		//订单状态：1，待支付；2，待发货；3，已发货； 4.已完成；5，取消订单（待支付状态）6、退款申请；7.退款中；8.已退款；9，拒绝 。
		String statusName = "";
		switch (status) {
			case "1":
				statusName = "待支付";
				break;
			case "2":
				statusName = "待发货";
				break;
			case "3":
				statusName = "待收货";
				break;
			case "4":
				statusName = "已完成";
				break;
			case "5":
				statusName = "取消订单";
				break;
			case "6":
				statusName = "退款申请";
				break;
			case "7":
				statusName = "退款中";
				break;
			case "8":
				statusName = "已退款";
				break;
			case "9":
				statusName = "拒绝退款";
				break;
		}
		return statusName;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getOrderTotal() {
		return orderTotal;
	}

	public void setOrderTotal(String orderTotal) {
		this.orderTotal = orderTotal;
	}

	public String getLogistics() {
		return logistics;
	}

	public void setLogistics(String logistics) {
		this.logistics = logistics;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getConsignee() {
		return consignee;
	}

	public void setConsignee(String consignee) {
		this.consignee = consignee;
	}

	public String getContacts() {
		return contacts;
	}

	public void setContacts(String contacts) {
		this.contacts = contacts;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getPayTime() {
		return payTime;
	}

	public void setPayTime(String payTime) {
		this.payTime = payTime;
	}

	public String getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}

	public String getRefundReason() {
		return refundReason;
	}

	public void setRefundReason(String refundReason) {
		this.refundReason = refundReason;
	}

	public String getRefundDescribe() {
		return refundDescribe;
	}

	public void setRefundDescribe(String refundDescribe) {
		this.refundDescribe = refundDescribe;
	}

	public String getTotalMoney() {
		return totalMoney;
	}

	public void setTotalMoney(String totalMoney) {
		this.totalMoney = totalMoney;
	}

	public String getTotalIntegral() {
		return totalIntegral;
	}

	public void setTotalIntegral(String totalIntegral) {
		this.totalIntegral = totalIntegral;
	}

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public List<OrderGoodEntity> getGoodEntities() {
		return goodEntities;
	}

	public void setGoodEntities(List<OrderGoodEntity> goodEntities) {
		this.goodEntities = goodEntities;
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(storeName);
		dest.writeString(status);
		dest.writeString(orderTotal);
		dest.writeString(logistics);
		dest.writeString(address);
		dest.writeString(consignee);
		dest.writeString(contacts);
		dest.writeString(createDate);
		dest.writeString(payTime);
		dest.writeString(orderCode);
		dest.writeString(refundReason);
		dest.writeString(refundDescribe);
		dest.writeString(totalMoney);
		dest.writeString(totalIntegral);
		dest.writeString(payType);
		dest.writeTypedList(goodEntities);
	}
}
