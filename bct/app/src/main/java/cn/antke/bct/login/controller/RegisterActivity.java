package cn.antke.bct.login.controller;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.common.network.FProtocol;
import com.common.utils.StringUtil;
import com.common.utils.ToastUtil;
import com.common.viewinject.annotation.ViewInject;

import java.util.IdentityHashMap;

import cn.antke.bct.R;
import cn.antke.bct.base.ToolBarActivity;
import cn.antke.bct.network.Constants;
import cn.antke.bct.network.Parsers;
import cn.antke.bct.network.entities.RecommenderEntity;
import cn.antke.bct.utils.DialogUtils;
import cn.antke.bct.utils.InputUtil;
import cn.antke.bct.utils.ViewInjectUtils;

import static cn.antke.bct.common.CommonConstant.REQUEST_NET_FOUR;
import static cn.antke.bct.common.CommonConstant.REQUEST_NET_ONE;
import static cn.antke.bct.common.CommonConstant.REQUEST_NET_THREE;
import static cn.antke.bct.common.CommonConstant.REQUEST_NET_TWO;

/**
 * Created by zhaoweiwei on 2017/5/3.
 * 注册
 */

public class RegisterActivity extends ToolBarActivity implements View.OnClickListener {

	@ViewInject(R.id.register_usernumber)
	private TextView userNumber;
	@ViewInject(R.id.register_change_usernumber)
	private TextView changeUserNumber;
	@ViewInject(R.id.register_phone)
	private EditText phoneEt;
	@ViewInject(R.id.register_password)
	private EditText passwordEt;
	@ViewInject(R.id.register_verify_code)
	private EditText verifyCodeEt;
	@ViewInject(R.id.register_get_verify_code)
	private TextView getVerifyCode;
	@ViewInject(R.id.register_recommendnumber)
	private EditText recommednNumberEt;
	@ViewInject(R.id.register_service_protocal_button)
	private CheckBox serviceProBtn;
	@ViewInject(R.id.register_service_protocal)
	private TextView servicePro;
	@ViewInject(R.id.register_confirm)
	private TextView confirm;

	private String phone;
	private String password;
	private String verifyCode;
	private String recommentCode;
	private String userCode;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_register);
		ViewInjectUtils.inject(this);
		initView();
		getUserCode();
	}

	private void initView() {
		setLeftTitle(getString(R.string.login_register));
		changeUserNumber.setOnClickListener(this);
		getVerifyCode.setOnClickListener(this);
		servicePro.setOnClickListener(this);
		confirm.setOnClickListener(this);
		InputUtil.editIsEmpty(confirm, phoneEt, passwordEt, verifyCodeEt, recommednNumberEt);
	}

	@Override
	public void onClick(View v) {
		phone = phoneEt.getText().toString();
		password = passwordEt.getText().toString();
		verifyCode = verifyCodeEt.getText().toString();
		recommentCode = recommednNumberEt.getText().toString();
		switch (v.getId()) {
			case R.id.register_change_usernumber:
				getUserCode();
				break;
			case R.id.register_get_verify_code:
				if (!StringUtil.isEmpty(phone) && phone.startsWith("1") && phone.length() == 11) {
					getVerifyCode();
				} else {
					ToastUtil.shortShow(this, getString(R.string.register_input_phone));
				}
				break;
			case R.id.register_service_protocal:
				// TODO: 2017/5/3 跳转到协议 
				break;
			case R.id.register_confirm:
				getRecommendInfo();
				break;
		}
	}

	@Override
	public void success(int requestCode, String data) {
		super.success(requestCode, data);
		closeProgressDialog();
		switch (requestCode) {
			case REQUEST_NET_ONE:
				userCode = Parsers.getUserCode(data);
				userNumber.setText(userCode);
				break;
			case REQUEST_NET_TWO:
				RecommenderEntity recommenderEntity = Parsers.getRecommender(data);
				String userCode = recommenderEntity.getUserCode();
				String user = recommenderEntity.getUserName();
				String phone = recommenderEntity.getPhone();
				DialogUtils.showRecommendInfoDialog(this, userCode, user, phone, v -> {
					register();
					DialogUtils.closeDialog();
				}, v -> DialogUtils.closeDialog());
				break;
			case REQUEST_NET_THREE:
				startActivity(new Intent(RegisterActivity.this, BindPersonalInfoAcitvity.class));
				finish();
				break;
			case REQUEST_NET_FOUR://获取验证码
				break;
		}
	}

	@Override
	public void mistake(int requestCode, FProtocol.NetDataProtocol.ResponseStatus status, String errorMessage) {
		ToastUtil.shortShow(this, errorMessage);
	}

	private void getUserCode() {
		showProgressDialog();
		IdentityHashMap<String, String> params = new IdentityHashMap<>();
		params.put("token", "");
		requestHttpData(Constants.Urls.URL_POST_USER_USER_CODE, REQUEST_NET_ONE, FProtocol.HttpMethod.POST, params);
	}

	private void getRecommendInfo() {
		showProgressDialog();
		IdentityHashMap<String, String> params = new IdentityHashMap<>();
		params.put("token", "");
		params.put("recommender", recommentCode);
		requestHttpData(Constants.Urls.URL_POST_USER_RECOMMEND_CODE, REQUEST_NET_TWO, FProtocol.HttpMethod.POST, params);
	}

	private void register() {
		showProgressDialog();
		IdentityHashMap<String, String> params = new IdentityHashMap<>();
		params.put("token", "");
		params.put("userCode", "A00001");
//		params.put("userCode", userCode);
		params.put("loginName", phone);
		params.put("password", password);
		params.put("verifiationCode", verifyCode);
		params.put("recommender", recommentCode);
		requestHttpData(Constants.Urls.URL_POST_USER_REGISTER, REQUEST_NET_THREE, FProtocol.HttpMethod.POST, params);
	}

	private void getVerifyCode() {
		showProgressDialog();
		IdentityHashMap<String, String> params = new IdentityHashMap<>();
		params.put("token", "");
		params.put("loginName", phone);
		requestHttpData(Constants.Urls.URL_POST_SMS_CODE, REQUEST_NET_FOUR, FProtocol.HttpMethod.POST, params);
	}
}
