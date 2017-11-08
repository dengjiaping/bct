package cn.antke.bct.person.controller;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import cn.antke.bct.R;
import cn.antke.bct.base.ToolBarActivity;

import static cn.antke.bct.common.CommonConstant.REQUEST_ACT_ONE;

/**
 * Created by zhaoweiwei on 2017/5/19.
 * 积分交易密码
 */

public class IntegralPwdActivity extends ToolBarActivity {

	private TextView setPwd;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_integtal_trading_pwd);
		setLeftTitle(getString(R.string.person_transaction_password));
		setPwd = (TextView) findViewById(R.id.integral_pwd_set);

		setPwd.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO: 2017/5/19 判断是否设置过支付密码
				//设置支付密码成功之后 按钮改为重置支付密码
				startActivityForResult(new Intent(IntegralPwdActivity.this, ResetPwdActivity.class), REQUEST_ACT_ONE);
			}
		});
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (RESULT_OK == resultCode && REQUEST_ACT_ONE == requestCode) {
			setPwd.setText(getString(R.string.integral_trading_pwd_reset));
		}
	}
}
