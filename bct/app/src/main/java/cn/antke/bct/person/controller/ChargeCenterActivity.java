package cn.antke.bct.person.controller;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.common.viewinject.annotation.ViewInject;

import cn.antke.bct.R;
import cn.antke.bct.base.ToolBarActivity;
import cn.antke.bct.login.utils.UserCenter;
import cn.antke.bct.utils.ViewInjectUtils;

/**
 * Created by zhaoweiwei on 2017/5/15.
 * 充值中心
 */

public class ChargeCenterActivity extends ToolBarActivity implements View.OnClickListener {
	@ViewInject(R.id.charge_center_share_integral)
	private TextView shareIntegral;
	@ViewInject(R.id.charge_center_useable_integral)
	private TextView useableIntegral;
	@ViewInject(R.id.charge_center_usernumber)
	private TextView userNumber;
	@ViewInject(R.id.charge_center_realname)
	private TextView realName;
	@ViewInject(R.id.charge_center_phone)
	private TextView userPhone;
	@ViewInject(R.id.charge_center_money)
	private TextView amountMoney;
	@ViewInject(R.id.charge_center_wx)
	private TextView chargeByWx;
	@ViewInject(R.id.charge_center_zfb)
	private TextView chargeByZfb;
	@ViewInject(R.id.charge_center_pay)
	private TextView chargePay;

	private String chargeType;
	private String payType;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_charge_center);
		ViewInjectUtils.inject(this);
		setLeftTitle(getString(R.string.charge_center));
		userNumber.setText(UserCenter.getUserCode(this));
		realName.setText(UserCenter.getUserName(this));
		userPhone.setText(UserCenter.getPhone(this));
		shareIntegral.setOnClickListener(this);
		useableIntegral.setOnClickListener(this);
		chargeByWx.setOnClickListener(this);
		chargeByZfb.setOnClickListener(this);
		chargePay.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.charge_center_share_integral:
				shareIntegral.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.language_area_selected, 0);
				useableIntegral.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.language_area_normal, 0);
				chargeType = "1";
				break;
			case R.id.charge_center_useable_integral:
				useableIntegral.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.language_area_selected, 0);
				shareIntegral.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.language_area_normal, 0);
				chargeType = "2";
				break;
			case R.id.charge_center_wx:
				chargeByWx.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.language_area_selected, 0);
				chargeByZfb.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.language_area_normal, 0);
				payType = "1";
				break;
			case R.id.charge_center_zfb:
				chargeByZfb.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.language_area_selected, 0);
				chargeByWx.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.language_area_normal, 0);
				payType = "2";
				break;
			case R.id.charge_center_pay:
				break;
		}
	}
}
