package cn.antke.bct.person.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.common.adapter.BaseAdapterNew;
import com.common.adapter.ViewHolder;

import java.util.List;

import cn.antke.bct.R;
import cn.antke.bct.network.entities.LotteryInquiryEntity;

/**
 * Created by zhaoweiwei on 2017/5/21.
 */

public class LotteryInquiryAdapter extends BaseAdapterNew<LotteryInquiryEntity> {
	public LotteryInquiryAdapter(Context context, List<LotteryInquiryEntity> mDatas) {
		super(context, mDatas);
	}

	@Override
	protected int getResourceId(int resId) {
		return R.layout.item_lottery_inquiry;
	}

	@Override
	protected void setViewData(View convertView, int position) {
		LotteryInquiryEntity entity = getItem(position);
		TextView periods = ViewHolder.get(convertView, R.id.item_lottery_periods);
		TextView time = ViewHolder.get(convertView, R.id.item_lottery_time);
		TextView integral = ViewHolder.get(convertView, R.id.item_lottery_integral);
		TextView result = ViewHolder.get(convertView, R.id.item_lottery_result);

		periods.setText(entity.getPeriods());
		time.setText(entity.getTime());
		integral.setText(entity.getIntegral());
		result.setText(entity.getResult());
	}
}
