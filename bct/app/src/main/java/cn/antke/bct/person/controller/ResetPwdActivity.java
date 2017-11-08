package cn.antke.bct.person.controller;

import android.content.Intent;
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
import cn.antke.bct.login.controller.SetPassWordActivity;
import cn.antke.bct.network.Constants;
import cn.antke.bct.utils.InputUtil;
import cn.antke.bct.utils.ViewInjectUtils;

import static cn.antke.bct.common.CommonConstant.EXTRA_FROM;
import static cn.antke.bct.common.CommonConstant.REQUEST_ACT_ONE;
import static cn.antke.bct.common.CommonConstant.REQUEST_ACT_TWO;
import static cn.antke.bct.common.CommonConstant.REQUEST_NET_ONE;

/**
 * Created by zhaoweiwei on 2017/5/19.
 * 重置支付密码
 */

public class ResetPwdActivity extends ToolBarActivity implements View.OnClickListener{
	@ViewInject(R.id.reset_pwd_phone)
	private EditText resetPhone;
	@ViewInject(R.id.reset_pwd_identify_code)
	private EditText resetIdentifyCode;
	@ViewInject(R.id.reset_get_verify_code)
	private TextView getIdentifyCode;
	@ViewInject(R.id.reset_next)
	private TextView resetNext;

	private String phone;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_integral_pwd_reset);
		ViewInjectUtils.inject(this);
		setLeftTitle(getString(R.string.integral_trading_pwd_reset));
		getIdentifyCode.setOnClickListener(this);
		resetNext.setOnClickListener(this);
		InputUtil.editIsEmpty(resetNext,resetPhone,resetIdentifyCode);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.reset_get_verify_code:
				phone = resetPhone.getText().toString();
				if (!StringUtil.isEmpty(phone) && phone.startsWith("1") && phone.length() == 11) {
					getVerifyCode();
				} else {
					ToastUtil.shortShow(this, getString(R.string.register_input_phone));
				}
				break;
			case R.id.reset_next:
				startActivityForResult(new Intent(this, SetPassWordActivity.class).putExtra(EXTRA_FROM,REQUEST_ACT_ONE),REQUEST_ACT_ONE);
				break;
		}
	}

	private void getVerifyCode() {
		showProgressDialog();
		IdentityHashMap<String, String> params = new IdentityHashMap<>();
		params.put("token", "");
		params.put("loginName", phone);
		requestHttpData(Constants.Urls.URL_POST_SMS_CODE, REQUEST_NET_ONE, FProtocol.HttpMethod.POST, params);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		switch (requestCode) {
			case REQUEST_ACT_ONE:
				break;
			case REQUEST_ACT_TWO:
				break;
		}
	}
}
