package cn.antke.bct.product.controller;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.common.viewinject.annotation.ViewInject;
import com.facebook.drawee.view.SimpleDraweeView;

import cn.antke.bct.R;
import cn.antke.bct.base.ToolBarActivity;
import cn.antke.bct.utils.ViewInjectUtils;

/**
 * Created by liuzhichao on 2017/5/19.
 * 确认订单
 */
public class ConfirmOrderActivity extends ToolBarActivity implements View.OnClickListener {

	@ViewInject(R.id.ll_confirm_order_address)
	private View llConfirmOrderAddress;
	@ViewInject(R.id.tv_confirm_order_user_name)
	private TextView tvConfirmOrderUserName;
	@ViewInject(R.id.tv_confirm_order_phone)
	private TextView tvConfirmOrderPhone;
	@ViewInject(R.id.tv_confirm_order_address)
	private TextView tvConfirmOrderAddress;
	@ViewInject(R.id.lvfi_confirm_order_way)
	private TextView lvfiConfirmOrderWay;
	@ViewInject(R.id.tv_confirm_order_store_name)
	private TextView tvConfirmOrderStoreName;
	@ViewInject(R.id.sdv_confirm_order_logo)
	private SimpleDraweeView sdvConfirmOrderLogo;
	@ViewInject(R.id.tv_confirm_order_name)
	private TextView tvConfirmOrderName;
	@ViewInject(R.id.tv_confirm_order_desc)
	private TextView tvConfirmOrderDesc;
	@ViewInject(R.id.tv_confirm_order_condition)
	private TextView tvConfirmOrderCondition;
	@ViewInject(R.id.tv_confirm_order_num)
	private TextView tvConfirmOrderNum;
	@ViewInject(R.id.tv_confirm_order_integral)
	private TextView tvConfirmOrderIntegral;
	@ViewInject(R.id.tv_confirm_order_postage)
	private TextView tvConfirmOrderPostage;
	@ViewInject(R.id.tv_confirm_order_amount)
	private TextView tvConfirmOrderAmount;
	@ViewInject(R.id.tv_confirm_order_postage_amount)
	private TextView tvConfirmOrderPostageAmount;
	@ViewInject(R.id.tv_confirm_order_buy)
	private View tvConfirmOrderBuy;

	public static void startConfirmOrderActivity(Context context) {
		Intent intent = new Intent(context, ConfirmOrderActivity.class);
		context.startActivity(intent);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_confirm_order);
		ViewInjectUtils.inject(this);
		initView();
		loadData();
	}

	private void initView() {
		setLeftTitle(getString(R.string.integral_confirm));
		llConfirmOrderAddress.setOnClickListener(this);
		tvConfirmOrderBuy.setOnClickListener(this);
	}

	private void loadData() {

	}

	@Override
	public void success(int requestCode, String data) {
		super.success(requestCode, data);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.ll_confirm_order_address:
				//选择地址
				break;
			case R.id.tv_confirm_order_buy:
				//结算
				break;
		}
	}
}
