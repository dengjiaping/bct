package cn.antke.bct.person.controller;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.common.viewinject.annotation.ViewInject;

import cn.antke.bct.R;
import cn.antke.bct.base.ToolBarActivity;
import cn.antke.bct.utils.ViewInjectUtils;

/**
 * Created by zhaoweiwei on 2017/5/17.
 * 消费服务中心开通(支付)
 */

public class ConsumerServicePayActivity extends ToolBarActivity implements View.OnClickListener {
	@ViewInject(R.id.consumer_service_choose_area)
	private LinearLayout chooseAreaLl;
	@ViewInject(R.id.consumer_service_area)
	private TextView areaTv;
	@ViewInject(R.id.consumer_service_realname)
	private TextView realNameTv;
	@ViewInject(R.id.consumer_service_phone)
	private TextView phoneTv;
	@ViewInject(R.id.consumer_service_money)
	private TextView moneyTv;
	@ViewInject(R.id.consumer_service_integral)
	private TextView integralTv;
	@ViewInject(R.id.consumer_service_member)
	private TextView memberTv;
	@ViewInject(R.id.consumer_service_wx)
	private TextView payWx;
	@ViewInject(R.id.consumer_service_zfb)
	private TextView payZfb;
	@ViewInject(R.id.consumer_service_pay)
	private TextView pay;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_consumer_service_open);
		ViewInjectUtils.inject(this);

		chooseAreaLl.setOnClickListener(this);
		payWx.setOnClickListener(this);
		payZfb.setOnClickListener(this);
		pay.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.consumer_service_choose_area:
				break;
			case R.id.consumer_service_wx:
				payWx.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.language_area_selected, 0);
				payZfb.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.language_area_normal, 0);
//				payType = "1";
				break;
			case R.id.consumer_service_zfb:
				payZfb.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.language_area_selected, 0);
				payWx.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.language_area_normal, 0);
//				payType = "2";
				break;
			case R.id.consumer_service_pay:
				break;
		}
	}
}
