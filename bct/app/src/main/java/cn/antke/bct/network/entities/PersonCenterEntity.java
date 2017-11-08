package cn.antke.bct.network.entities;

import com.google.gson.annotations.SerializedName;

/**
 * Created by zhaoweiwei on 2017/5/9.
 * 个人中心item数量
 */

public class PersonCenterEntity {
	@SerializedName("dealOrderNum")
	private String exchangeNum;//交易大厅
	@SerializedName("friendNum")
	private String friendNum;//
	@SerializedName("givingOrderNum")
	private String givingOrderNum;//赠送订单
	@SerializedName("messageNum")
	private String messageNum;//未读消息
	@SerializedName("pointSwitchNum")
	private String switchNum;//积分互转
	@SerializedName("rechargeOrderNum")
	private String rechargeOrderNum;//充值订单
	@SerializedName("shopOrderNum")
	private String shopOrderNum;//购物订单
	@SerializedName("usePoint")
	private String userIntegral;//可用积分

	public String getExchangeNum() {
		return exchangeNum;
	}

	public void setExchangeNum(String exchangeNum) {
		this.exchangeNum = exchangeNum;
	}

	public String getFriendNum() {
		return friendNum;
	}

	public void setFriendNum(String friendNum) {
		this.friendNum = friendNum;
	}

	public String getGivingOrderNum() {
		return givingOrderNum;
	}

	public void setGivingOrderNum(String givingOrderNum) {
		this.givingOrderNum = givingOrderNum;
	}

	public String getMessageNum() {
		return messageNum;
	}

	public void setMessageNum(String messageNum) {
		this.messageNum = messageNum;
	}

	public String getSwitchNum() {
		return switchNum;
	}

	public void setSwitchNum(String switchNum) {
		this.switchNum = switchNum;
	}

	public String getRechargeOrderNum() {
		return rechargeOrderNum;
	}

	public void setRechargeOrderNum(String rechargeOrderNum) {
		this.rechargeOrderNum = rechargeOrderNum;
	}

	public String getShopOrderNum() {
		return shopOrderNum;
	}

	public void setShopOrderNum(String shopOrderNum) {
		this.shopOrderNum = shopOrderNum;
	}

	public String getUserIntegral() {
		return userIntegral;
	}

	public void setUserIntegral(String userIntegral) {
		this.userIntegral = userIntegral;
	}
}
