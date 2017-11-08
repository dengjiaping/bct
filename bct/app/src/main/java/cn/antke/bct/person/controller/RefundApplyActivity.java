package cn.antke.bct.person.controller;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.AbsListView.LayoutParams;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.common.network.FProtocol;
import com.common.utils.StringUtil;
import com.common.utils.ToastUtil;
import com.common.viewinject.annotation.ViewInject;

import java.util.IdentityHashMap;
import java.util.List;

import cn.antke.bct.R;
import cn.antke.bct.base.ToolBarActivity;
import cn.antke.bct.network.Constants;
import cn.antke.bct.network.entities.OrderEntity;
import cn.antke.bct.network.entities.OrderGoodEntity;
import cn.antke.bct.person.adapter.OrderGoodAdapter;
import cn.antke.bct.utils.CommonTools;
import cn.antke.bct.utils.ViewInjectUtils;

import static cn.antke.bct.common.CommonConstant.EXTRA_ENTITY;
import static cn.antke.bct.common.CommonConstant.ORDERSTATE_DELIVING;
import static cn.antke.bct.common.CommonConstant.REQUEST_ACT_ONE;
import static cn.antke.bct.common.CommonConstant.REQUEST_NET_ONE;

/**
 * Created by zhaoweiwei on 2017/5/23.
 * 申请退款
 */

public class RefundApplyActivity extends ToolBarActivity implements View.OnClickListener {
	@ViewInject(R.id.order_detail_list)
	private ListView orderListView;
	@ViewInject(R.id.order_detail_price)
	private TextView orderPrice;
	@ViewInject(R.id.order_detail_btn1)
	private TextView orderBtn1;
	@ViewInject(R.id.order_detail_btn2)
	private TextView orderBtn2;

	private OrderEntity entity;
	private TextView store;
	private TextView status;
	private TextView goodPrice;
	private TextView freight;
	private TextView refundReason;
	private EditText refundExplain;
	private String reason;

	public static void startRefundApplyActivity(Context context, OrderEntity entity) {
		Intent intent = new Intent(context, RefundApplyActivity.class);
		intent.putExtra(EXTRA_ENTITY, entity);
		context.startActivity(intent);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_order_detail);
		ViewInjectUtils.inject(this);
		setLeftTitle(getString(R.string.person_order_refund_apply));
		initView();
	}

	private void initView() {
		entity = getIntent().getParcelableExtra(EXTRA_ENTITY);
		View topView = getLayoutInflater().inflate(R.layout.act_refund_apply_top, null);
		store = (TextView) topView.findViewById(R.id.order_refund_store);
		status = (TextView) topView.findViewById(R.id.order_refund_status);

		View bottomView = getLayoutInflater().inflate(R.layout.act_refund_apply_bottom, null);
		goodPrice = (TextView) bottomView.findViewById(R.id.order_refund_goods_price);
		freight = (TextView) bottomView.findViewById(R.id.order_refund_freight);
		refundReason = (TextView) bottomView.findViewById(R.id.order_refund_refund_reason);
		refundExplain = (EditText) bottomView.findViewById(R.id.order_refund_refund_explain);

		topView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, CommonTools.dp2px(this, 30)));
		bottomView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
		orderListView.addHeaderView(topView);
		orderListView.addFooterView(bottomView);
		setData();
	}

	private void setData() {
		if (entity != null) {
			List<OrderGoodEntity> goodEntities = entity.getGoodEntities();
			OrderGoodAdapter goodAdapter = new OrderGoodAdapter(this, goodEntities);
			orderListView.setAdapter(goodAdapter);
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

			orderBtn1.setVisibility(View.GONE);
			orderBtn2.setVisibility(View.VISIBLE);
			orderBtn2.setText(getString(R.string.confirm));

			refundReason.setOnClickListener(this);
			orderBtn2.setOnClickListener(this);
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (RESULT_OK == resultCode && REQUEST_ACT_ONE == requestCode) {
			reason = data.getStringExtra(EXTRA_ENTITY);
			refundReason.setText(reason);
		}
	}

	@Override
	public void success(int requestCode, String data) {
		super.success(requestCode, data);
		closeProgressDialog();
		finish();
	}

	@Override
	public void mistake(int requestCode, FProtocol.NetDataProtocol.ResponseStatus status, String errorMessage) {
		super.mistake(requestCode, status, errorMessage);
		closeProgressDialog();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.order_refund_refund_reason:
				startActivityForResult(new Intent(RefundApplyActivity.this, ListDialogActivity.class), REQUEST_ACT_ONE);
				break;
			case R.id.order_detail_btn2:
				String explain = refundExplain.getText().toString();
				if (!StringUtil.isEmpty(reason)) {
					showProgressDialog();
					IdentityHashMap<String, String> params = new IdentityHashMap<>();
					if (!StringUtil.isEmpty(entity.getOrderCode())) {
						params.put("orderCode", entity.getOrderCode());
					} else {
						params.put("orderCode", "");
					}
					params.put("status", String.valueOf(ORDERSTATE_DELIVING));
					params.put("refundDescribe", explain);
					params.put("refundReason", reason);
					requestHttpData(Constants.Urls.URL_POST_ORDER_CHANGE, REQUEST_NET_ONE, FProtocol.HttpMethod.POST, params);
				} else {
					ToastUtil.shortShow(RefundApplyActivity.this, "请选择退款原因");
				}
				break;
		}
	}
}
