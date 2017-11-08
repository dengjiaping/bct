package cn.antke.bct.person.controller;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.common.viewinject.annotation.ViewInject;
import com.facebook.drawee.view.SimpleDraweeView;

import cn.antke.bct.R;
import cn.antke.bct.base.ToolBarActivity;
import cn.antke.bct.utils.ViewInjectUtils;

/**
 * Created by zhaoweiwei on 2017/5/12.
 * 店铺详情
 */

public class StoreDetailActivity extends ToolBarActivity implements View.OnClickListener {
	@ViewInject(R.id.store_detail_pic)
	private SimpleDraweeView storePic;
	@ViewInject(R.id.store_detail_add)
	private ImageView storeAdd;
	@ViewInject(R.id.store_detail_name)
	private TextView storeName;
	@ViewInject(R.id.store_detail_bulletin)
	private TextView storeBulletin;
	@ViewInject(R.id.store_detail_phone)
	private TextView storePhone;
	@ViewInject(R.id.store_detail_area)
	private TextView storeArea;
	@ViewInject(R.id.store_detail_address)
	private TextView storeAddress;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_store_detail);
		ViewInjectUtils.inject(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.store_detail_add://添加图片
				break;
			case R.id.store_detail_bulletin://店铺公告
				break;
		}
	}
}
