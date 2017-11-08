package cn.antke.bct.person.controller;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.common.viewinject.annotation.ViewInject;

import cn.antke.bct.R;
import cn.antke.bct.base.ToolBarActivity;
import cn.antke.bct.utils.ViewInjectUtils;

import static cn.antke.bct.common.CommonConstant.TYPE_0;
import static cn.antke.bct.common.CommonConstant.TYPE_1;
import static cn.antke.bct.common.CommonConstant.TYPE_2;
import static cn.antke.bct.common.CommonConstant.TYPE_3;
import static cn.antke.bct.common.CommonConstant.TYPE_4;

/**
 * Created by zhaoweiwei on 2017/5/21.
 * 积分
 */

public class IntegralActivity extends ToolBarActivity implements View.OnClickListener {
	@ViewInject(R.id.integral_useable_ll)
	private LinearLayout integralUseableLl;
	@ViewInject(R.id.integral_useable)
	private TextView integralUseable;
	@ViewInject(R.id.integral_shop_ll)
	private LinearLayout integralShopLl;
	@ViewInject(R.id.integral_shop)
	private TextView integralShop;
	@ViewInject(R.id.integral_aside_ll)
	private LinearLayout integralAsideLl;
	@ViewInject(R.id.integral_aside)
	private TextView integralAside;
	@ViewInject(R.id.integral_multi_function_ll)
	private LinearLayout integralMultiLl;
	@ViewInject(R.id.integral_multi_function)
	private TextView integralMulti;
	@ViewInject(R.id.integral_share_ll)
	private LinearLayout integralShareLl;
	@ViewInject(R.id.integral_share)
	private TextView integralShare;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_integral);
		setLeftTitle(getString(R.string.store_good_integral));
		ViewInjectUtils.inject(this);
		integralUseableLl.setOnClickListener(this);
		integralShopLl.setOnClickListener(this);
		integralAsideLl.setOnClickListener(this);
		integralMultiLl.setOnClickListener(this);
		integralShareLl.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.integral_useable_ll:
				IntegralOrderActivity.startIntegralOrderActivity(this,TYPE_0);
				break;
			case R.id.integral_shop_ll:
				IntegralOrderActivity.startIntegralOrderActivity(this,TYPE_1);
				break;
			case R.id.integral_aside_ll:
				IntegralOrderActivity.startIntegralOrderActivity(this,TYPE_2);
				break;
			case R.id.integral_multi_function_ll:
				IntegralOrderActivity.startIntegralOrderActivity(this,TYPE_3);
				break;
			case R.id.integral_share_ll:
				IntegralOrderActivity.startIntegralOrderActivity(this,TYPE_4);
				break;
		}
	}
}
