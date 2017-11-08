package cn.antke.bct.person.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.common.adapter.BaseAdapterNew;
import com.common.adapter.ViewHolder;

import java.util.List;

import cn.antke.bct.R;
import cn.antke.bct.network.entities.IntegralEntity;

/**
 * Created by zhaoweiwei on 2017/5/26.
 */

public class IntegralAsideAdapter extends BaseAdapterNew<IntegralEntity> {
	public IntegralAsideAdapter(Context context, List<IntegralEntity> mDatas) {
		super(context, mDatas);
	}

	@Override
	protected int getResourceId(int resId) {
		return R.layout.item_integral_aside;
	}

	@Override
	protected void setViewData(View convertView, int position) {
		IntegralEntity entity = getItem(position);
		TextView orderNumber = ViewHolder.get(convertView, R.id.integral_aside_order_number);
		TextView returnIntegral = ViewHolder.get(convertView, R.id.integral_aside_consume_return);
		TextView confirmTime = ViewHolder.get(convertView, R.id.integral_aside_confirm_time);
		TextView returnTiem = ViewHolder.get(convertView, R.id.integral_aside_return_time);
	}
}
