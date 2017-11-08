package cn.antke.bct.person.controller;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.common.viewinject.annotation.ViewInject;

import cn.antke.bct.R;
import cn.antke.bct.base.ToolBarActivity;
import cn.antke.bct.utils.ViewInjectUtils;

/**
 * Created by zhaoweiwei on 2017/5/17.
 * 消费服务中心
 */

public class ConsumerServiceActivity extends ToolBarActivity implements View.OnClickListener {
	@ViewInject(R.id.consumer_service_account_integral)
	private TextView accountIntegral;
	@ViewInject(R.id.consumer_service_charge_integral)
	private TextView integralCharge;
	@ViewInject(R.id.consumer_service_time_start)
	private TextView startTimeTv;
	@ViewInject(R.id.consumer_service_time_end)
	private TextView endTime;
	@ViewInject(R.id.consumer_service_renew)
	private TextView renew;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_consumer_service);
		ViewInjectUtils.inject(this);
		setLeftTitle(getString(R.string.person_consumer_service));
		setRightText(getString(R.string.activation));
		hideTitleLine();
		rightText.setOnClickListener(this);
		integralCharge.setOnClickListener(this);
		renew.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.rigth_text:
				startActivity(new Intent(this, ActivationActivity.class));
				break;
			case R.id.consumer_service_charge_integral:
				startActivity(new Intent(this,RedIntegralChargeActivity.class));
				break;
			case R.id.consumer_service_renew:
				break;
		}
	}
}
