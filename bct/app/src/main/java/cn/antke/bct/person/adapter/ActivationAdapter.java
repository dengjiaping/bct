package cn.antke.bct.person.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.common.adapter.BaseAdapterNew;
import com.common.adapter.ViewHolder;

import java.util.List;

import cn.antke.bct.R;
import cn.antke.bct.network.entities.ActivationEntity;

/**
 * Created by zhaoweiwei on 2017/5/18.
 * 激活
 */

public class ActivationAdapter extends BaseAdapterNew<ActivationEntity> {
	public ActivationAdapter(Context context, List<ActivationEntity> mDatas) {
		super(context, mDatas);
	}

	@Override
	protected int getResourceId(int resId) {
		return R.layout.item_activation;
	}

	@Override
	protected void setViewData(View convertView, int position) {
		ActivationEntity entity = getItem(position);
		TextView textView = ViewHolder.get(convertView, R.id.item_activation);
		if (entity!=null) {
			textView.setText(entity.getName());
			if (entity.isSelected()) {
				textView.setCompoundDrawablesWithIntrinsicBounds(0,0,R.drawable.activation_selected,0);
			} else {
				textView.setCompoundDrawablesWithIntrinsicBounds(0,0,R.drawable.language_area_normal,0);
			}
			textView.setOnClickListener(v -> {
				entity.setSelected(!entity.isSelected());
				notifyDataSetChanged();
			});
		}
	}
}
