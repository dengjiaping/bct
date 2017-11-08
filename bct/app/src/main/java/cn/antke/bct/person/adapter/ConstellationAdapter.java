package cn.antke.bct.person.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.common.adapter.BaseAdapterNew;
import com.common.adapter.ViewHolder;
import com.common.utils.StringUtil;

import java.util.List;

import cn.antke.bct.R;

/**
 * Created by zhaoweiwei on 2017/5/6.
 * 星座
 */

public class ConstellationAdapter extends BaseAdapterNew<String> {
	public ConstellationAdapter(Context context, List<String> mDatas) {
		super(context, mDatas);
	}

	@Override
	protected int getResourceId(int Position) {
		return R.layout.item_person_constellation;
	}

	@Override
	protected void setViewData(View convertView, int position) {
		String constellation = getItem(position);
		TextView textView = ViewHolder.get(convertView,R.id.item_constellation);
		if (!StringUtil.isEmpty(constellation)) {
			textView.setText(constellation);
		}
	}
}
