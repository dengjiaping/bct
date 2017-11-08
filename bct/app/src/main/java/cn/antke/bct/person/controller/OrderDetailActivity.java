package cn.antke.bct.person.controller;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.common.network.FProtocol;
import com.common.utils.StringUtil;
import com.common.viewinject.annotation.ViewInject;

import java.util.IdentityHashMap;
import java.util.List;

import cn.antke.bct.R;
import cn.antke.bct.base.ToolBarActivity;
import cn.antke.bct.network.Constants;
import cn.antke.bct.network.entities.OrderEntity;
import cn.antke.bct.network.entities.OrderGoodEntity;
import cn.antke.bct.person.adapter.OrderGoodAdapter;
import cn.antke.bct.utils.ViewInjectUtils;

import static cn.antke.bct.common.CommonConstant.EXTRA_ENTITY;
import static cn.antke.bct.common.CommonConstant.REQUEST_NET_ONE;

/**
 * Created by zhaoweiwei on 2017/5/15.
 * 购物订单详情
 */

public class OrderDetailActivity extends ToolBarActivity implements View.OnClickListener {
	@ViewInject(R.id.order_detail_list)
	private ListView orderList;
	@ViewInject(R.id.order_detail_price)
	private TextView orderPrice;
	@ViewInject(R.id.order_detail_btn1)
	private TextView orderBtn1;
	@ViewInject(R.id.order_detail_btn2)
	private TextView orderBtn2;

	private OrderEntity entity;
	private TextView user;
	private TextView phone;
	private TextView address;
	private TextView store;
	private TextView status;
	private TextView goodPrice;
	private TextView freight;
	private TextView textView1;
	private TextView textView2;
	private TextView textView3;
	private RelativeLayout topRl;
	private View view;
	private View refundView;
	private LinearLayout refundLl;
	private TextView refundReason;
	private TextView refundExplain;

	public static void startOrderDetailActivity(Context context, OrderEntity entity) {
		Intent intent = new Intent(context, OrderDetailActivity.class);
		intent.putExtra(EXTRA_ENTITY, entity);
		context.startActivity(intent);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_order_detail);
		ViewInjectUtils.inject(this);
		setLeftTitle(getString(R.string.person_order_detail));
		initView();
	}

	private void initView() {
		entity = getIntent().getParcelableExtra(EXTRA_ENTITY);
		View topView = getLayoutInflater().inflate(R.layout.act_order_detail_top, null);
		topRl = (RelativeLayout) topView.findViewById(R.id.order_detail_top_rl);
		view = topView.findViewById(R.id.order_detail_top_view);
		user = (TextView) topView.findViewById(R.id.order_detail_consignee);
		phone = (TextView) topView.findViewById(R.id.order_detail_phone);
		address = (TextView) topView.findViewById(R.id.order_detail_address);
		store = (TextView) topView.findViewById(R.id.order_detail_store);
		status = (TextView) topView.findViewById(R.id.order_detail_status);
		View bottomView = getLayoutInflater().inflate(R.layout.act_order_detail_bottom, null);
		goodPrice = (TextView) bottomView.findViewById(R.id.order_detail_goods_price);
		freight = (TextView) bottomView.findViewById(R.id.order_detail_freight);
		refundView = bottomView.findViewById(R.id.order_detail_refund_view);
		refundLl = (LinearLayout) bottomView.findViewById(R.id.order_detail_refund_ll);
		refundReason = (TextView) bottomView.findViewById(R.id.order_detail_refund_reason);
		refundExplain = (TextView) bottomView.findViewById(R.id.order_detail_refund_explain);
		textView1 = (TextView) bottomView.findViewById(R.id.order_detail_text1);
		textView2 = (TextView) bottomView.findViewById(R.id.order_detail_text2);
		textView3 = (TextView) bottomView.findViewById(R.id.order_detail_text3);
		orderList.addHeaderView(topView);
		orderList.addFooterView(bottomView);
		setData();
	}

	private void setData() {
		if (entity != null) {
			List<OrderGoodEntity> goodEntities = entity.getGoodEntities();
			OrderGoodAdapter goodAdapter = new OrderGoodAdapter(this, goodEntities);
			orderList.setAdapter(goodAdapter);
			//订单状态：1，待支付；2，待发货；3，已发货； 4.已完成；5，取消订单（待支付状态）6、退款申请；7.退款中；8.已退款；9，拒绝 。
			switch (entity.getStatus()) {
				case "1":
				case "2":
				case "3":
				case "4":
					topRl.setVisibility(View.VISIBLE);
					view.setVisibility(View.VISIBLE);
					user.setText(getString(R.string.person_address_user, entity.getConsignee()));
					phone.setText(entity.getContacts());
					address.setText(getString(R.string.person_address_address, entity.getAddress()));
					refundLl.setVisibility(View.GONE);
					refundView.setVisibility(View.GONE);
					textView1.setText(getString(R.string.person_orderdetail_order_number, entity.getOrderCode()));
					if (!StringUtil.isEmpty(entity.getCreateDate())) {
						textView2.setText(getString(R.string.person_orderdetail_create_time, entity.getCreateDate()));
					}
					if (!StringUtil.isEmpty(entity.getPayTime())) {
						textView3.setText(getString(R.string.person_orderdetail_pay_time, entity.getPayTime()));
					}
					break;
				case "5":

				case "6":

				case "7":
					refundLl.setVisibility(View.VISIBLE);
					refundView.setVisibility(View.VISIBLE);
					topRl.setVisibility(View.GONE);
					view.setVisibility(View.GONE);
					refundReason.setText(entity.getRefundDescribe());
					refundExplain.setText(entity.getRefundReason());
					textView1.setText(getString(R.string.person_orderdetail_order_number, entity.getOrderCode()));
					if (!StringUtil.isEmpty(entity.getCreateDate())) {
						textView2.setText(getString(R.string.person_orderdetail_refund_apply_time, entity.getCreateDate()));
					}
					break;
				case "8":
					refundLl.setVisibility(View.VISIBLE);
					refundView.setVisibility(View.VISIBLE);
					topRl.setVisibility(View.GONE);
					view.setVisibility(View.GONE);
					refundReason.setText(entity.getRefundDescribe());
					refundExplain.setText(entity.getRefundReason());
					textView1.setText(getString(R.string.person_orderdetail_order_number, entity.getOrderCode()));
					if (!StringUtil.isEmpty(entity.getCreateDate())) {
						textView2.setText(getString(R.string.person_orderdetail_refund_apply_time, entity.getCreateDate()));
					}
					if (!StringUtil.isEmpty(entity.getPayTime())) {
						textView3.setText(getString(R.string.person_orderdetail_refund_time, entity.getPayTime()));
					}
					break;
				case "9":
					refundLl.setVisibility(View.VISIBLE);
					refundView.setVisibility(View.VISIBLE);
					topRl.setVisibility(View.GONE);
					view.setVisibility(View.GONE);
					refundReason.setText(entity.getRefundDescribe());
					refundExplain.setText(entity.getRefundReason());
					textView1.setText(getString(R.string.person_orderdetail_order_number, entity.getOrderCode()));
					if (!StringUtil.isEmpty(entity.getCreateDate())) {
						textView2.setText(getString(R.string.person_orderdetail_refund_apply_time, entity.getCreateDate()));
					}
					if (!StringUtil.isEmpty(entity.getPayTime())) {
						textView3.setText(getString(R.string.person_orderdetail_refused_time, entity.getPayTime()));
					}
					break;
			}

			store.setText(entity.getStoreName());
			status.setText(entity.getStatusName());

			String goodsPrice = getString(R.string.person_order_good_price, entity.getOrderTotal());
			SpannableStringBuilder ssb = new SpannableStringBuilder(goodsPrice);
			ssb.setSpan(new ForegroundColorSpan(ContextCompat.getColor(this, R.color.price_color)), 5, goodsPrice.length(), Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
			goodPrice.setText(ssb);

			if (!StringUtil.isEmpty(entity.getLogistics()) && !"0".equals(entity.getLogistics())) {
				freight.setVisibility(View.VISIBLE);
				String freightPrice = getString(R.string.person_order_freight, entity.getLogistics());
				SpannableStringBuilder ssb1 = new SpannableStringBuilder(freightPrice);
				ssb1.setSpan(new ForegroundColorSpan(ContextCompat.getColor(this, R.color.price_color)), 3, freightPrice.length(), Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
				freight.setText(ssb1);
			} else {
				freight.setVisibility(View.GONE);
			}


			String totalPrice;
			if (!StringUtil.isEmpty(entity.getTotalIntegral()) && !"0".equals(entity.getTotalIntegral())) {
				if (!StringUtil.isEmpty(entity.getTotalMoney()) && !"0".equals(entity.getTotalMoney())) {
					totalPrice = getString(R.string.person_orderdetail_order_price,
							getString(R.string.product_sell_integral, entity.getTotalIntegral()) + getString(R.string.product_sell_price, entity.getTotalMoney()));
				} else {
					totalPrice = getString(R.string.person_orderdetail_order_price, getString(R.string.product_sell_integral, entity.getTotalIntegral()));
				}
			} else {
				if (!StringUtil.isEmpty(entity.getTotalMoney()) && !"0".equals(entity.getTotalMoney())) {
					totalPrice = getString(R.string.person_orderdetail_order_price,
							getString(R.string.product_sell_price2, entity.getTotalMoney()));
				} else {
					totalPrice = getString(R.string.person_orderdetail_order_price, "");
				}
			}
			SpannableStringBuilder ssb2 = new SpannableStringBuilder(totalPrice);
			ssb2.setSpan(new ForegroundColorSpan(ContextCompat.getColor(this, R.color.price_color)), 3, totalPrice.length(), Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
			orderPrice.setText(ssb2);
			setBtn();
		}
	}

	private void setBtn() {
		if (entity != null) {
			//订单状态：1，待支付；2，待发货；3，已发货； 4.已完成；5，取消订单（待支付状态）6、退款申请；7.退款中；8.已退款；9，拒绝 。
			switch (entity.getStatus()) {
				case "1"://待支付
					orderBtn1.setVisibility(View.VISIBLE);
					orderBtn2.setVisibility(View.VISIBLE);
					orderBtn1.setText(getString(R.string.person_order_cancel));
					orderBtn2.setText(getString(R.string.person_order_pay));
					break;
				case "2"://待发货
					orderBtn1.setVisibility(View.GONE);
					orderBtn2.setVisibility(View.VISIBLE);
					orderBtn2.setText(getString(R.string.person_order_refund));
					break;
				case "3"://已发货
					orderBtn1.setVisibility(View.VISIBLE);
					orderBtn2.setVisibility(View.VISIBLE);
					orderBtn1.setText(getString(R.string.person_order_logistic_detail));
					orderBtn2.setText(getString(R.string.person_order_get_ensure));
					break;
				case "4"://已完成
					orderBtn1.setVisibility(View.VISIBLE);
					orderBtn2.setVisibility(View.VISIBLE);
					orderBtn1.setText(getString(R.string.person_order_logistic_detail));
					orderBtn2.setText(getString(R.string.person_order_buy_again));
					break;
				case "5"://取消
					orderBtn1.setVisibility(View.GONE);
					orderBtn2.setVisibility(View.VISIBLE);
					orderBtn2.setText(getString(R.string.delete));
					break;
				case "6"://退款申请
					orderBtn1.setVisibility(View.GONE);
					orderBtn2.setVisibility(View.VISIBLE);
					orderBtn2.setText(getString(R.string.person_order_refund_cancel));
					break;
				case "7"://退款中
				case "8"://已退款
				case "9"://拒绝退款
					orderBtn1.setVisibility(View.GONE);
					orderBtn2.setVisibility(View.GONE);
					break;
			}
			orderBtn1.setOnClickListener(this);
			orderBtn2.setOnClickListener(this);
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.order_detail_btn1:
				switch (entity.getStatus()) {
					case "1"://取消订单
						changeState(entity,"1");
						break;
					case "3"://查看物流
					case "4"://
						break;
				}
				break;
			case R.id.order_detail_btn2:
				switch (entity.getStatus()) {
					case "1"://付款
						break;
					case "2"://退款
						RefundApplyActivity.startRefundApplyActivity(this, entity);
						break;
					case "3"://完成(确认收货)
						changeState(entity,"4");
						break;
					case "4"://再次购买
						break;
					case "5"://删除
						changeState(entity,"5");
						break;
					case "6"://取消退款
						changeState(entity,"6");
						break;
				}
				break;
		}
	}

	private void changeState(OrderEntity entity,String state) {
		showProgressDialog();
		IdentityHashMap<String, String> params = new IdentityHashMap<>();
		params.put("orderCode", entity.getOrderCode());
		params.put("status", state);
		params.put("refundDescribe", "");
		params.put("refundReason", "");
		requestHttpData(Constants.Urls.URL_POST_ORDER_CHANGE, REQUEST_NET_ONE, FProtocol.HttpMethod.POST, params);
	}

	@Override
	public void success(int requestCode, String data) {
		super.success(requestCode, data);
		closeProgressDialog();
	}

	@Override
	public void mistake(int requestCode, FProtocol.NetDataProtocol.ResponseStatus status, String errorMessage) {
		super.mistake(requestCode, status, errorMessage);
		closeProgressDialog();
	}
}
