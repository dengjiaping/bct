package cn.antke.bct.deal.controller;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.common.utils.ToastUtil;
import com.common.viewinject.annotation.ViewInject;

import cn.antke.bct.R;
import cn.antke.bct.base.ToolBarActivity;
import cn.antke.bct.utils.DialogUtils;
import cn.antke.bct.utils.ViewInjectUtils;

/**
 * Created by liuzhichao on 2017/5/12.
 * 购买积分
 */
public class BuyIntegralActivity extends ToolBarActivity {

	@ViewInject(R.id.tv_buy_integral_all)
	private TextView tvBuyIntegralAll;
	@ViewInject(R.id.tv_buy_integral_unit)
	private TextView tvBuyIntegralUnit;
	@ViewInject(R.id.tv_buy_integral_price)
	private TextView tvBuyIntegralPrice;
	@ViewInject(R.id.et_buy_integral_num)
	private EditText etBuyIntegralNum;
	@ViewInject(R.id.tv_buy_integral_amount)
	private TextView tvBuyIntegralAmount;
	@ViewInject(R.id.tv_buy_integral_buy)
	private View tvBuyIntegralBuy;

	public static void startBuyIntegralActivity(Context context) {
		Intent intent = new Intent(context, BuyIntegralActivity.class);
		context.startActivity(intent);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_buy_integral);
		ViewInjectUtils.inject(this);
		initView();
	}

	private void initView() {
		setLeftTitle(getString(R.string.purchase));
		tvBuyIntegralBuy.setOnClickListener(v -> {
			String temp = etBuyIntegralNum.getText().toString();
			if (TextUtils.isEmpty(temp)) {
				ToastUtil.shortShow(this, getString(R.string.please_input_buy_integral));
				return;
			}
			int buyNum = Integer.parseInt(temp);
			if (buyNum <= 0) {
				ToastUtil.shortShow(this, getString(R.string.buy_integral_greater_than_zero));
				return;
			}
			DialogUtils.showTwoBtnDialog(this, getString(R.string.pay_hint), getString(R.string.buy_need_pay), null, v1 -> {
				DialogUtils.closeDialog();
				DialogUtils.showPwdInputDialog(this, (v22, editText) -> {
					DialogUtils.closeDialog();
				});
			}, v2 -> DialogUtils.closeDialog());
		});
	}
}
