package cn.antke.bct.login.utils;

import android.content.Context;

import com.common.utils.PreferencesUtils;
import com.common.utils.StringUtil;

import cn.antke.bct.network.entities.UserEntity;

/**
 * Created by zhaoweiwei on 2017/5/5.
 */

public class UserCenter {
	private static final String USER_ID = "user_id";
	private static final String DEFAULT_ADDRESS = "default_address";
	private static final String AGE = "age";
	private static final String PHONE = "phone";
	private static final String HEAD_PIC = "head_pic";
	private static final String USER_NAME = "user_name";
	private static final String NICK_NAME = "nick_name";
	private static final String USER_CODE = "user_code";

	public static void savaUserInfo(Context context, UserEntity userEntity) {
		PreferencesUtils.putString(context, USER_ID, userEntity.getId());
		PreferencesUtils.putString(context, DEFAULT_ADDRESS, userEntity.getProvince() + userEntity.getCity() + userEntity.getDistrict() + userEntity.getAddress());
		PreferencesUtils.putString(context, AGE, userEntity.getAge());
		PreferencesUtils.putString(context, PHONE, userEntity.getContact());
		PreferencesUtils.putString(context, HEAD_PIC, userEntity.getAvatar());
		PreferencesUtils.putString(context, USER_NAME, userEntity.getName());
		PreferencesUtils.putString(context, NICK_NAME, userEntity.getNickName());
		PreferencesUtils.putString(context, USER_CODE, userEntity.getUserCode());
	}

	public static void cleanLoginInfo(Context context) {
		PreferencesUtils.removeSharedPreferenceByKey(context, USER_ID);
		PreferencesUtils.removeSharedPreferenceByKey(context, DEFAULT_ADDRESS);
		PreferencesUtils.removeSharedPreferenceByKey(context, AGE);
		PreferencesUtils.removeSharedPreferenceByKey(context, PHONE);
		PreferencesUtils.removeSharedPreferenceByKey(context, HEAD_PIC);
		PreferencesUtils.removeSharedPreferenceByKey(context, USER_NAME);
		PreferencesUtils.removeSharedPreferenceByKey(context, NICK_NAME);
		PreferencesUtils.removeSharedPreferenceByKey(context, USER_CODE);
	}

	public static String getUserId(Context context) {
		return PreferencesUtils.getString(context, USER_ID);
	}

	public static void setDefaultAddress(Context context, String address) {
		PreferencesUtils.putString(context, DEFAULT_ADDRESS, address);
	}

	public static String getDefaultAddress(Context context) {
		return PreferencesUtils.getString(context, DEFAULT_ADDRESS);
	}

	public static String getAge(Context context) {
		return PreferencesUtils.getString(context, AGE);
	}

	public static String getPhone(Context context) {
		return PreferencesUtils.getString(context, PHONE);
	}

	public static String getHeadPic(Context context) {
		return PreferencesUtils.getString(context, HEAD_PIC);
	}

	public static String getUserName(Context context) {
		return PreferencesUtils.getString(context, USER_NAME);
	}

	public static String getNickName(Context context) {
		return PreferencesUtils.getString(context, NICK_NAME);
	}

	public static String getUserCode(Context context) {
		return PreferencesUtils.getString(context, USER_CODE);
	}

	public static boolean isLogin(Context context) {
		String token = PreferencesUtils.getString(context, USER_ID);
		return !StringUtil.isEmpty(token);
	}
}
