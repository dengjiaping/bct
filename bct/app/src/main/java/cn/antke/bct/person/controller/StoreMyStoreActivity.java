package cn.antke.bct.person.controller;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.common.viewinject.annotation.ViewInject;
import com.facebook.drawee.view.SimpleDraweeView;

import cn.antke.bct.R;
import cn.antke.bct.base.ToolBarActivity;
import cn.antke.bct.utils.ViewInjectUtils;

/**
 * Created by zhaoweiwei on 2017/5/12.
 * 我的店铺
 */

public class StoreMyStoreActivity extends ToolBarActivity implements View.OnClickListener {
	@ViewInject(R.id.store_mystore_icon)
	private SimpleDraweeView myStoreIcon;
	@ViewInject(R.id.store_mystore_name)
	private TextView myStoreName;
	@ViewInject(R.id.store_store_info)
	private TextView storeInfo;
	@ViewInject(R.id.store_order_manager)
	private TextView orderManager;
	@ViewInject(R.id.store_good_manager)
	private TextView goodManager;
	@ViewInject(R.id.store_add_new_good)
	private TextView addNewGood;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_store_mystore);
		ViewInjectUtils.inject(this);
		setLeftTitle(getString(R.string.store_my_store));
		storeInfo.setOnClickListener(this);
		orderManager.setOnClickListener(this);
		goodManager.setOnClickListener(this);
		addNewGood.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.store_store_info://店铺信息
				break;
			case R.id.store_order_manager://订单管理
				break;
			case R.id.store_good_manager://商品管理
				break;
			case R.id.store_add_new_good://添加新商品
				startActivity(new Intent(this,GoodEditActivity.class));
				break;
		}
	}
}
