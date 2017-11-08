package cn.antke.bct.login.controller;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;

import com.common.network.FProtocol;
import com.github.mikephil.charting.formatter.IFillFormatter;

import java.util.IdentityHashMap;

import cn.antke.bct.R;
import cn.antke.bct.base.ToolBarActivity;
import cn.antke.bct.main.controller.MainActivity;
import cn.antke.bct.network.Constants;
import cn.antke.bct.person.controller.PersonInfoActivity;
import cn.antke.bct.widget.PasswordInputView;

import static cn.antke.bct.common.CommonConstant.EXTRA_FROM;
import static cn.antke.bct.common.CommonConstant.FROM_REGISTER;
import static cn.antke.bct.common.CommonConstant.REQUEST_ACT_ONE;
import static cn.antke.bct.common.CommonConstant.REQUEST_NET_ONE;

/**
 * Created by zhaoweiwei on 2017/5/4.
 * 设置交易密码
 */

public class SetPassWordActivity extends ToolBarActivity {

	private int from;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_set_password);
		final PasswordInputView passwordInputView = (PasswordInputView) findViewById(R.id.set_password_input);
		from = getIntent().getIntExtra(EXTRA_FROM,REQUEST_ACT_ONE);
		if (REQUEST_ACT_ONE == from) {//重置积分交易密码
			setLeftTitle(getString(R.string.integral_trading_pwd_reset));
		} else {//注册流程
			setLeftTitle(getString(R.string.setpassword_title));
			setRightText(getString(R.string.jump));
		}

		rightText.setOnClickListener(v -> startActivity(new Intent(SetPassWordActivity.this, MainActivity.class)));

		passwordInputView.addTextChangedListener(new TextWatcher() {
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {

			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {

			}

			@Override
			public void afterTextChanged(Editable s) {
				String pwd = passwordInputView.getText().toString();
				if (6 == pwd.length()) {
					showProgressDialog();
					IdentityHashMap<String, String> params = new IdentityHashMap<>();
					params.put("integralPassword", pwd);
					requestHttpData(Constants.Urls.URL_POST_SET_PASSWORD, REQUEST_NET_ONE, FProtocol.HttpMethod.POST, params);
				}
			}
		});
	}

	@Override
	public void success(int requestCode, String data) {
		super.success(requestCode, data);
		closeProgressDialog();
		if (REQUEST_ACT_ONE == from) {
			setResult(RESULT_OK);
		} else {
			startActivity(new Intent(SetPassWordActivity.this, PersonInfoActivity.class).putExtra(EXTRA_FROM, FROM_REGISTER));
		}
		finish();
	}
}
