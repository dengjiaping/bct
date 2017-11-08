package cn.antke.bct.network.entities;

import com.google.gson.annotations.SerializedName;

/**
 * Created by zhaoweiwei on 2017/5/9.
 */

public class UserEntity {
	@SerializedName("user_id")
	private String id;
	@SerializedName("name")
	private String name;
	@SerializedName("user_name")
	private String userName;
	@SerializedName("userCode")
	private String userCode;
	@SerializedName("age")
	private String age;
	@SerializedName("contact")
	private String contact;
	@SerializedName("head_portrait")
	private String avatar;//头像
	@SerializedName("login_name")
	private String loginName;
	@SerializedName("nick_name")
	private String nickName;
	@SerializedName("province")
	private String province;
	@SerializedName("city")
	private String city;
	@SerializedName("district")
	private String district;
	@SerializedName("address")
	private String address;


	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
}
