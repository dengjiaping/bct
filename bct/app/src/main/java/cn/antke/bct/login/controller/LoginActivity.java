package cn.antke.bct.login.controller;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.common.network.FProtocol;
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

/**
 * Created by zhaoweiwei on 2017/5/3.
 * 登录
 */

public class LoginActivity extends ToolBarActivity implements View.OnClickListener {

	@ViewInject(R.id.login_username)
	private EditText loginUsername;
	@ViewInject(R.id.login_password)
	private EditText loginPassword;
	@ViewInject(R.id.login_forget)
	private TextView loginForget;
	@ViewInject(R.id.login_confirm)
	private TextView loginConfirm;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_login);
		ViewInjectUtils.inject(this);
		setCenterTitleAndLeftText(getString(R.string.login_text));
		setRightText(getString(R.string.login_register));
		rightText.setOnClickListener(this);
		InputUtil.editIsEmpty(loginConfirm, loginUsername, loginPassword);
		loginForget.setOnClickListener(this);
		loginConfirm.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.rigth_text:
				startActivity(new Intent(this, RegisterActivity.class));
				break;
			case R.id.login_forget:
				startActivity(new Intent(this, ForgetPwdActivity.class));
				break;
			case R.id.login_confirm:
				showProgressDialog();
				String userName = loginUsername.getText().toString();
				String password = loginPassword.getText().toString();
				IdentityHashMap<String, String> params = new IdentityHashMap<>();
				params.put("loginName", userName);
				params.put("password", password);
				requestHttpData(Constants.Urls.URL_POST_USER_LOGIN, REQUEST_NET_ONE, FProtocol.HttpMethod.POST, params);
				break;
		}
	}


	@Override
	protected void parseData(int requestCode, String data) {
		super.parseData(requestCode, data);
		UserEntity userEntity = Parsers.getUserInfo(data);
		UserCenter.savaUserInfo(this, userEntity);
		finish();
	}

	@Override
	public void mistake(int requestCode, FProtocol.NetDataProtocol.ResponseStatus status, String errorMessage) {
		super.mistake(requestCode, status, errorMessage);
		closeProgressDialog();
		ToastUtil.shortShow(this, errorMessage);
	}
}
