package cn.antke.bct.pay.controller;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.common.viewinject.annotation.ViewInject;

import cn.antke.bct.R;
import cn.antke.bct.base.ToolBarActivity;
import cn.antke.bct.utils.ViewInjectUtils;

/**
 * Created by liuzhichao on 2017/5/21.
 * 在线支付
 */
public class OnlinePayActivity extends ToolBarActivity implements View.OnClickListener {

	@ViewInject(R.id.tv_online_wxpay)
	private View tvOnlineWxpay;
	@ViewInject(R.id.tv_online_alipay)
	private View tvOnlineAlipay;
	@ViewInject(R.id.tv_online_postage)
	private TextView tvOnlinePostage;
	@ViewInject(R.id.tv_online_pay)
	private View tvOnlinePay;

	public static void startOnlinePayActivity(Context context) {
		Intent intent = new Intent(context, OnlinePayActivity.class);
		context.startActivity(intent);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_online_pay);
		ViewInjectUtils.inject(this);
		initView();
	}

	private void initView() {
		setLeftTitle(getString(R.string.online_pay));
		tvOnlineWxpay.setOnClickListener(this);
		tvOnlineAlipay.setOnClickListener(this);
		tvOnlinePay.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.tv_online_wxpay:
				break;
			case R.id.tv_online_alipay:
				break;
			case R.id.tv_online_pay:
				break;
		}
	}
}
