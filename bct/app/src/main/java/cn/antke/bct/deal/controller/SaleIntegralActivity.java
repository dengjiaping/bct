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
import cn.antke.bct.utils.ViewInjectUtils;

/**
 * Created by liuzhichao on 2017/5/12.
 * 卖出积分
 */
public class SaleIntegralActivity extends ToolBarActivity implements View.OnClickListener {

	@ViewInject(R.id.tv_sale_integral_available)
	private TextView tvSaleIntegralAvailable;
	@ViewInject(R.id.tv_sale_integral_unit)
	private TextView tvSaleIntegralUnit;
	@ViewInject(R.id.tv_sale_integral_price)
	private TextView tvSaleIntegralPrice;
	@ViewInject(R.id.et_sale_integral_num)
	private EditText etSaleIntegralNum;
	@ViewInject(R.id.et_sale_integral_price)
	private EditText etSaleIntegralPrice;
	@ViewInject(R.id.tv_sale_integral_sell)
	private View tvSaleIntegralSell;

	public static void startSaleIntegralActivity(Context context) {
		Intent intent = new Intent(context, SaleIntegralActivity.class);
		context.startActivity(intent);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_sale_integral);
		ViewInjectUtils.inject(this);
		initView();
	}

	private void initView() {
		setLeftTitle(getString(R.string.dialog_integral_sale));
		setRightText(getString(R.string.cancel_sell));
		rightText.setOnClickListener(this);
		tvSaleIntegralSell.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.rigth_text:
				CancelSaleActivity.startCancelSaleActivity(this);
				break;
			case R.id.tv_sale_integral_sell:
				String sNum = etSaleIntegralNum.getText().toString();
				if (TextUtils.isEmpty(sNum)) {
					ToastUtil.shortShow(this, getString(R.string.please_input_sell_integral));
					return;
				}
				int num = Integer.parseInt(sNum);
				if (num <= 0) {
					ToastUtil.shortShow(this, getString(R.string.sell_integral_greater_than_zero));
					return;
				}
				String sPrice = etSaleIntegralPrice.getText().toString();
				if (TextUtils.isEmpty(sPrice)) {
					ToastUtil.shortShow(this, getString(R.string.please_sell_price));
					return;
				}
				int price = Integer.parseInt(sPrice);
				if (price < 20000) {
					ToastUtil.shortShow(this, getString(R.string.sell_integral_greater_than_price));
					return;
				}
				break;
		}
	}
}
