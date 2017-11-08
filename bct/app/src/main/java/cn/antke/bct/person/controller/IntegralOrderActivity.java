package cn.antke.bct.person.controller;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.common.viewinject.annotation.ViewInject;
import com.common.widget.FootLoadingListView;

import cn.antke.bct.R;
import cn.antke.bct.base.ToolBarActivity;
import cn.antke.bct.utils.ViewInjectUtils;

import static cn.antke.bct.common.CommonConstant.EXTRA_TYPE;
import static cn.antke.bct.common.CommonConstant.TYPE_0;
import static cn.antke.bct.common.CommonConstant.TYPE_1;
import static cn.antke.bct.common.CommonConstant.TYPE_2;
import static cn.antke.bct.common.CommonConstant.TYPE_3;
import static cn.antke.bct.common.CommonConstant.TYPE_4;

/**
 * Created by zhaoweiwei on 2017/5/26.
 * 积分订单页
 */

public class IntegralOrderActivity extends ToolBarActivity {
	@ViewInject(R.id.integral_name)
	private TextView integralName;
	@ViewInject(R.id.integral_account)
	private TextView integralAccount;
	@ViewInject(R.id.integral_list)
	private FootLoadingListView integralList;
	private String title;

	public static void startIntegralOrderActivity(Context context, int type) {
		Intent intent = new Intent(context, IntegralOrderActivity.class);
		intent.putExtra(EXTRA_TYPE, type);
		context.startActivity(intent);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_integral_order);
		ViewInjectUtils.inject(this);
		hideTitleLine();
		int type = getIntent().getIntExtra(EXTRA_TYPE, TYPE_0);
		getTitle(type);
		setLeftTitle(title);
		integralName.setText(title);
	}

	private void getTitle(int type) {
		switch (type) {
			case TYPE_0:
				title = getString(R.string.person_useable_integral);
				break;
			case TYPE_1:
				title = getString(R.string.integral_shop);
				break;
			case TYPE_2:
				title = getString(R.string.integral_adide);
				break;
			case TYPE_3:
				title = getString(R.string.integral_multi_function);
				break;
			case TYPE_4:
				title = getString(R.string.integral_share);
				break;
		}
	}
}
