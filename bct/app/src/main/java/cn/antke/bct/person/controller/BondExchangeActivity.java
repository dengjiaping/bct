package cn.antke.bct.person.controller;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.common.viewinject.annotation.ViewInject;

import cn.antke.bct.R;
import cn.antke.bct.base.ToolBarActivity;
import cn.antke.bct.login.utils.UserCenter;
import cn.antke.bct.utils.DialogUtils;
import cn.antke.bct.utils.ViewInjectUtils;

/**
 * Created by zhaoweiwei on 2017/5/19.
 * 债券兑换
 */

public class BondExchangeActivity extends ToolBarActivity implements View.OnClickListener {
	@ViewInject(R.id.bond_account)
	private TextView bondAccount;
	@ViewInject(R.id.bond_usercode)
	private TextView bondUserCode;
	@ViewInject(R.id.bond_multi_function)
	private EditText bondMultiFunction;
	@ViewInject(R.id.bond_bond)
	private TextView bondBond;
	@ViewInject(R.id.bond_exchange)
	private TextView bondExchange;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_bond_exchange);
		ViewInjectUtils.inject(this);
		setLeftTitle(getString(R.string.person_bond_exchange));
		setRightText(getString(R.string.bond_recycle));
		hideTitleLine();

		rightText.setOnClickListener(this);
		bondExchange.setOnClickListener(this);
		bondAccount.setText(UserCenter.getUserCode(this));
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.bond_exchange:
				DialogUtils.showTwoBtnDialog(this, getString(R.string.person_bond_exchange),
						getString(R.string.bond_is_exchange),
						null,
						v12 -> {
							// TODO: 2017/5/19 债券兑换
							DialogUtils.closeDialog();
						},
						v1 -> DialogUtils.closeDialog());
				break;
			case R.id.rigth_text:
				startActivity(new Intent(this, BondRecycleActivity.class));
				break;
		}
	}
}
