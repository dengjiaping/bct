package cn.antke.bct.person.controller;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.common.viewinject.annotation.ViewInject;

import cn.antke.bct.R;
import cn.antke.bct.base.ToolBarActivity;
import cn.antke.bct.utils.ViewInjectUtils;

/**
 * Created by zhaoweiwei on 2017/5/18.
 * 转积分
 */

public class TransferIntegralActivity extends ToolBarActivity {
	@ViewInject(R.id.transfer_integral_usercode)
	private EditText transferUsercode;
	@ViewInject(R.id.transfer_integral_username)
	private TextView transferUsername;
	@ViewInject(R.id.transfer_integral_phone)
	private TextView transferPhone;
	@ViewInject(R.id.transfer_integral_integral)
	private EditText transferIntegral;
	@ViewInject(R.id.transfer_integral_confirm)
	private TextView tansferConfirm;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_transger_integral);
		ViewInjectUtils.inject(this);
		setLeftTitle(getString(R.string.person_transfer_integral));

		tansferConfirm.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

			}
		});
	}
}
