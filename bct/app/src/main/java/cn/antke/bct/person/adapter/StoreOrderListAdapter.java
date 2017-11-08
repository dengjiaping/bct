package cn.antke.bct.person.adapter;

import android.content.Context;
import android.view.View;

import com.common.adapter.BaseAdapterNew;

import java.util.List;

import cn.antke.bct.R;
import cn.antke.bct.network.entities.StoreOrderEntity;

/**
 * Created by zhaoweiwei on 2017/5/14.
 */

public class StoreOrderListAdapter extends BaseAdapterNew<StoreOrderEntity> {
	public StoreOrderListAdapter(Context context, List<StoreOrderEntity> mDatas) {
		super(context, mDatas);
	}

	@Override
	protected int getResourceId(int resId) {
		return R.layout.item_store_order;
	}

	@Override
	protected void setViewData(View convertView, int position) {

	}
}
