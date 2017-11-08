package cn.antke.bct.person.controller;

import android.os.Bundle;
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
 * 债券回收
 */

public class BondRecycleActivity extends ToolBarActivity {
	@ViewInject(R.id.bond_recycle_usercode)
	private TextView userCode;
	@ViewInject(R.id.bond_recycle_bond)
	private EditText bond;
	@ViewInject(R.id.bond_recycle_confirm)
	private TextView bondConfirm;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_bond_recycle);
		ViewInjectUtils.inject(this);
		setLeftTitle(getString(R.string.bond_recycle));
		userCode.setText(UserCenter.getUserCode(this));
		bondConfirm.setOnClickListener(v -> DialogUtils.showTwoBtnDialog(BondRecycleActivity.this,
				getString(R.string.bond_recycle),
				getString(R.string.bond_is_recycle),
				null,
				v12 -> {
					// TODO: 2017/5/19 债权回收
					DialogUtils.closeDialog();
				}, v1 -> DialogUtils.closeDialog()));
	}
}
