package cn.antke.bct.person.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.common.adapter.BaseAdapterNew;
import com.common.adapter.ViewHolder;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import cn.antke.bct.R;
import cn.antke.bct.network.entities.MyMessageEntity;

/**
 * Created by zhaoweiwei on 2017/5/16.
 */

public class MyMessageAdapter extends BaseAdapterNew<MyMessageEntity> {
	public MyMessageAdapter(Context context, List<MyMessageEntity> mDatas) {
		super(context, mDatas);
	}

	@Override
	protected int getResourceId(int resId) {
		return R.layout.item_my_message;
	}

	@Override
	protected void setViewData(View convertView, int position) {

		TextView timeTv = ViewHolder.get(convertView,R.id.item_message_time);
		TextView typeTv = ViewHolder.get(convertView,R.id.item_message_type);
		TextView dateTv = ViewHolder.get(convertView,R.id.item_message_date);
		SimpleDraweeView pic = ViewHolder.get(convertView,R.id.item_message_pic);
		TextView pushTv = ViewHolder.get(convertView,R.id.item_message_push_content);
		TextView callTv = ViewHolder.get(convertView,R.id.item_message_call);
		TextView promptTv = ViewHolder.get(convertView,R.id.item_message_prompt);
		TextView detailTv = ViewHolder.get(convertView,R.id.item_message_detail);

	}
}
