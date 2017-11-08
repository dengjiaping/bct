package cn.antke.bct.person.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.common.adapter.BaseAdapterNew;
import com.common.adapter.ViewHolder;

import java.util.List;

import cn.antke.bct.R;
import cn.antke.bct.network.entities.OrderEntity;
import cn.antke.bct.person.controller.OrderDetailActivity;

/**
 * Created by zhaoweiwei on 2017/5/11.
 * 订单列表
 */

public class OrderListAdapter extends BaseAdapterNew<OrderEntity> {
	private Context context;
	private View.OnClickListener onClickListener;

	public OrderListAdapter(Context context, List<OrderEntity> mDatas, View.OnClickListener onClickListener) {
		super(context, mDatas);
		this.context = context;
		this.onClickListener = onClickListener;
	}

	@Override
	protected int getResourceId(int resId) {
		return R.layout.item_person_order;
	}

	@Override
	protected void setViewData(View convertView, int position) {
		OrderEntity entity = getItem(position);
		TextView storeName = ViewHolder.get(convertView, R.id.item_order_store);
		TextView orderStatus = ViewHolder.get(convertView, R.id.item_order_status);
		ListView goodList = ViewHolder.get(convertView, R.id.item_order_list);
		TextView orderIntegral = ViewHolder.get(convertView, R.id.item_order_integral);
		TextView orderFreight = ViewHolder.get(convertView, R.id.item_order_freight);
		TextView orderBtn1 = ViewHolder.get(convertView, R.id.item_order_btn1);
		TextView orderBtn2 = ViewHolder.get(convertView, R.id.item_order_btn2);

		if (entity != null) {
			storeName.setText(entity.getStoreName());
			orderStatus.setText(entity.getStatusName());

			String orderPrice = context.getString(R.string.person_order_good_price, entity.getOrderTotal());
			SpannableStringBuilder ssb = new SpannableStringBuilder(orderPrice);
			ssb.setSpan(new ForegroundColorSpan(ContextCompat.getColor(context, R.color.price_color)), 5, orderPrice.length(), Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
			orderIntegral.setText(ssb);

			String freightPrice = context.getString(R.string.person_order_freight, entity.getLogistics());
			SpannableStringBuilder ssb1 = new SpannableStringBuilder(freightPrice);
			ssb1.setSpan(new ForegroundColorSpan(ContextCompat.getColor(context, R.color.price_color)), 3, freightPrice.length(), Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
			orderFreight.setText(ssb1);

			OrderGoodAdapter adapter = new OrderGoodAdapter(context, entity.getGoodEntities());
			goodList.setAdapter(adapter);
			goodList.setTag(entity);
			goodList.setOnItemClickListener((parent, view, position1, id) -> OrderDetailActivity.startOrderDetailActivity(context, entity));

			orderBtn1.setTag(entity);
			orderBtn1.setOnClickListener(onClickListener);
			orderBtn2.setTag(entity);
			orderBtn2.setOnClickListener(onClickListener);
			//订单状态：1，待支付；2，待发货；3，已发货； 4.已完成；5，取消订单（待支付状态）6、退款申请；7.退款中；8.已退款；9，拒绝 。
			switch (entity.getStatus()) {
				case "1"://待支付
					orderBtn1.setVisibility(View.VISIBLE);
					orderBtn2.setVisibility(View.VISIBLE);
					orderBtn1.setText(context.getString(R.string.person_order_cancel));
					orderBtn2.setText(context.getString(R.string.person_order_pay));
					break;
				case "2"://待发货
					orderBtn1.setVisibility(View.GONE);
					orderBtn2.setVisibility(View.VISIBLE);
					orderBtn2.setText(context.getString(R.string.person_order_refund));
					break;
				case "3"://待收货
					orderBtn1.setVisibility(View.VISIBLE);
					orderBtn2.setVisibility(View.VISIBLE);
					orderBtn1.setText(context.getString(R.string.person_order_logistic_detail));
					orderBtn2.setText(context.getString(R.string.person_order_get_ensure));
					break;
				case "4"://已完成
					orderBtn1.setVisibility(View.VISIBLE);
					orderBtn2.setVisibility(View.VISIBLE);
					orderBtn1.setText(context.getString(R.string.person_order_logistic_detail));
					orderBtn2.setText(context.getString(R.string.person_order_buy_again));
					break;
				case "5"://取消
					orderBtn1.setVisibility(View.GONE);
					orderBtn2.setVisibility(View.VISIBLE);
					orderBtn2.setText(context.getString(R.string.delete));
					break;
				case "6"://退款申请
					orderBtn1.setVisibility(View.VISIBLE);
					orderBtn2.setVisibility(View.VISIBLE);
					orderBtn1.setText(context.getString(R.string.person_order_refund_detail));
					orderBtn2.setText(context.getString(R.string.person_order_refund_cancel));
					break;
				case "7"://退款中
				case "8"://已退款
				case "9"://拒绝退款
					orderBtn1.setVisibility(View.GONE);
					orderBtn2.setVisibility(View.VISIBLE);
					orderBtn2.setText(context.getString(R.string.person_order_refund_detail));
					break;
			}
		}
	}
}
