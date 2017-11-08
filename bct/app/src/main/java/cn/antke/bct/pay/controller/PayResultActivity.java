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
 * 支付结果页
 */
public class PayResultActivity extends ToolBarActivity implements View.OnClickListener {

	@ViewInject(R.id.tv_pay_result)
	private TextView tvPayResult;
	@ViewInject(R.id.tv_pay_result_integral)
	private TextView tvPayResultIntegral;
	@ViewInject(R.id.tv_pay_result_no)
	private TextView tvPayResultNo;
	@ViewInject(R.id.tv_pay_result_name)
	private TextView tvPayResultName;
	@ViewInject(R.id.tv_pay_result_way)
	private TextView tvPayResultWay;
	@ViewInject(R.id.tv_pay_result_lbtn)
	private View tvPayResultLbtn;
	@ViewInject(R.id.tv_pay_result_rbtn)
	private View tvPayResultRbtn;

	public static void startPayResultActivity(Context context) {
		Intent intent = new Intent(context, PayResultActivity.class);
		context.startActivity(intent);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_pay_result);
		ViewInjectUtils.inject(this);
		initView();
	}

	private void initView() {
		setLeftTitle(getString(R.string.pay_result));

		tvPayResultLbtn.setOnClickListener(this);
		tvPayResultRbtn.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.tv_pay_result_lbtn:
				break;
			case R.id.tv_pay_result_rbtn:
				break;
		}
	}
}
