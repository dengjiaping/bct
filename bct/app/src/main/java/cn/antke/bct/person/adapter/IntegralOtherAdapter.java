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

public class IntegralOtherAdapter extends BaseAdapterNew<IntegralEntity> {

	public IntegralOtherAdapter(Context context, List<IntegralEntity> mDatas) {
		super(context, mDatas);
	}

	@Override
	protected int getResourceId(int resId) {
		return R.layout.item_integral_other;
	}

	@Override
	protected void setViewData(View convertView, int position) {
		IntegralEntity entity = getItem(position);
		TextView orderNumber = ViewHolder.get(convertView, R.id.integral_other_order_number);
		TextView integral = ViewHolder.get(convertView, R.id.integral_other_integral);
		TextView goodsName = ViewHolder.get(convertView, R.id.integral_other_goods_name);
		TextView time = ViewHolder.get(convertView, R.id.integral_other_time);

	}
}
