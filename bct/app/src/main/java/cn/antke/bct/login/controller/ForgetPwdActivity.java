package cn.antke.bct.login.controller;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.common.network.FProtocol;
import com.common.utils.StringUtil;
import com.common.utils.ToastUtil;
import com.common.viewinject.annotation.ViewInject;

import java.util.IdentityHashMap;

import cn.antke.bct.R;
import cn.antke.bct.base.ToolBarActivity;
import cn.antke.bct.login.utils.UserCenter;
import cn.antke.bct.network.Constants;
import cn.antke.bct.network.Parsers;
import cn.antke.bct.network.entities.UserEntity;
import cn.antke.bct.utils.InputUtil;
import cn.antke.bct.utils.ViewInjectUtils;

import static cn.antke.bct.common.CommonConstant.REQUEST_NET_ONE;
import static cn.antke.bct.common.CommonConstant.REQUEST_NET_TWO;

/**
 * Created by zhaoweiwei on 2017/5/3.
 * 忘记密码
 */

public class ForgetPwdActivity extends ToolBarActivity implements View.OnClickListener {
	@ViewInject(R.id.forget_phone)
	private EditText forgetPhone;
	@ViewInject(R.id.forget_password)
	private EditText newPassword;
	@ViewInject(R.id.forget_verify_code)
	private EditText verifyCodeEt;
	@ViewInject(R.id.forget_get_verify_code)
	private TextView getVerifyCode;
	@ViewInject(R.id.forget_confirm)
	private TextView confirm;

	private String phone;
	private String password;
	private String verifyCode;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_froget_pwd);
		ViewInjectUtils.inject(this);
		setLeftTitle(getString(R.string.forget_password_title));
		getVerifyCode.setOnClickListener(this);
		confirm.setOnClickListener(this);
		InputUtil.editIsEmpty(confirm, forgetPhone, newPassword, verifyCodeEt);
	}

	@Override
	public void onClick(View v) {
		phone = forgetPhone.getText().toString();
		password = newPassword.getText().toString();
		verifyCode = verifyCodeEt.getText().toString();
		switch (v.getId()) {
			case R.id.forget_get_verify_code:
				if (!StringUtil.isEmpty(phone) && phone.startsWith("1") && phone.length() == 11) {
					getVerifyCode();
				} else {
					ToastUtil.shortShow(this, getString(R.string.register_input_phone));
				}
				break;
			case R.id.forget_confirm:
				resetPassword();
				break;
		}
	}

	private void resetPassword() {
		showProgressDialog();
		IdentityHashMap<String, String> params = new IdentityHashMap<>();
		params.put("loginName", phone);
		params.put("password", password);
		params.put("verifiationCode", verifyCode);
		requestHttpData(Constants.Urls.URL_POST_RESET_PASSWORD, REQUEST_NET_TWO, FProtocol.HttpMethod.POST, params);
	}

	private void getVerifyCode() {
		showProgressDialog();
		IdentityHashMap<String, String> params = new IdentityHashMap<>();
		params.put("token", "");
		params.put("loginName", phone);
		requestHttpData(Constants.Urls.URL_POST_SMS_CODE, REQUEST_NET_ONE, FProtocol.HttpMethod.POST, params);
	}

	@Override
	public void success(int requestCode, String data) {
		super.success(requestCode, data);
		closeProgressDialog();
		switch (requestCode) {
			case REQUEST_NET_ONE://获取验证码
				break;
			case REQUEST_NET_TWO://重新设置新密码
				UserEntity userEntity = Parsers.getUserInfo(data);
				UserCenter.savaUserInfo(this, userEntity);
				finish();
				break;
		}
	}
}
