package cn.antke.bct.person.controller;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.common.viewinject.annotation.ViewInject;

import cn.antke.bct.R;
import cn.antke.bct.base.ToolBarActivity;
import cn.antke.bct.utils.ViewInjectUtils;

/**
 * Created by zhaoweiwei on 2017/5/18.
 * 红积分充值
 */

public class RedIntegralChargeActivity extends ToolBarActivity implements View.OnClickListener {
	@ViewInject(R.id.red_integral_usercode)
	private TextView userCode;
	@ViewInject(R.id.red_integral_money)
	private EditText money;
	@ViewInject(R.id.red_integral_wx)
	private TextView payWx;
	@ViewInject(R.id.red_integral_zfb)
	private TextView payZfb;
	@ViewInject(R.id.red_integral_pay)
	private TextView pay;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_red_integral_charge);
		ViewInjectUtils.inject(this);
		setLeftTitle(getString(R.string.consumer_red_integral_charge));
		pay.setOnClickListener(this);
		payWx.setOnClickListener(this);
		payZfb.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.red_integral_wx:
				payWx.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.language_area_selected, 0);
				payZfb.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.language_area_normal, 0);
				break;
			case R.id.red_integral_zfb:
				payZfb.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.language_area_selected, 0);
				payWx.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.language_area_normal, 0);
				break;
			case R.id.red_integral_pay:
				break;
		}
	}
}
