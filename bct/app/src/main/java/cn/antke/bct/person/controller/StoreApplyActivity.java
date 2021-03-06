package cn.antke.bct.person.controller;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.common.viewinject.annotation.ViewInject;

import cn.antke.bct.R;
import cn.antke.bct.base.ToolBarActivity;
import cn.antke.bct.utils.ViewInjectUtils;

/**
 * Created by zhaoweiwei on 2017/5/11.
 * 申请店铺
 */

public class StoreApplyActivity extends ToolBarActivity implements View.OnClickListener{
	@ViewInject(R.id.store_apply_company)
	private TextView storeCompany;
	@ViewInject(R.id.store_apply_business)
	private TextView storeBusiness;
	@ViewInject(R.id.store_apply_personnal)
	private TextView storePersonal;
	@ViewInject(R.id.store_apply_entity)
	private TextView storeEntity;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_store_apply);
		ViewInjectUtils.inject(this);
		setLeftTitle(getString(R.string.store_apply));
		storeCompany.setOnClickListener(this);
		storeBusiness.setOnClickListener(this);
		storePersonal.setOnClickListener(this);
		storeEntity.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.store_apply_company://企业店铺
				break;
			case R.id.store_apply_business://商家店铺
				break;
			case R.id.store_apply_personnal://个人店铺
				break;
			case R.id.store_apply_entity://实体店铺
				break;
		}
	}
}
