package cn.antke.bct.deal.controller;

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
 * Created by liuzhichao on 2017/5/12.
 * 交易大厅续费
 */
public class DealRechargeActivity extends ToolBarActivity implements View.OnClickListener {

	@ViewInject(R.id.ll_recharge_mode)
	private View llRechargeMode;
	@ViewInject(R.id.tv_recharge_price)
	private TextView tvRechargePrice;
	@ViewInject(R.id.tv_recharge_wxpay)
	private View tvRechargeWxpay;
	@ViewInject(R.id.tv_recharge_alipay)
	private View tvRechargeAlipay;
	@ViewInject(R.id.tv_recharge_pay)
	private View tvRechargePay;

	public static void startDealRechargeActivity(Context context) {
		Intent intent = new Intent(context, DealRechargeActivity.class);
		context.startActivity(intent);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_deal_recharge);
		ViewInjectUtils.inject(this);
		initView();
	}

	private void initView() {
		setLeftTitle(getString(R.string.renew));
		tvRechargeWxpay.setSelected(true);

		llRechargeMode.setOnClickListener(this);
		tvRechargeWxpay.setOnClickListener(this);
		tvRechargeAlipay.setOnClickListener(this);
		tvRechargePay.setOnClickListener(this);
	}

	@Override
	public void success(int requestCode, String data) {
		super.success(requestCode, data);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.ll_recharge_mode:
				break;
			case R.id.tv_recharge_wxpay:
				tvRechargeWxpay.setSelected(true);
				tvRechargeAlipay.setSelected(false);
				break;
			case R.id.tv_recharge_alipay:
				tvRechargeWxpay.setSelected(false);
				tvRechargeAlipay.setSelected(true);
				break;
			case R.id.tv_recharge_pay:
				if (tvRechargeWxpay.isSelected()) {
				} else {
				}
				break;
		}
	}
}
