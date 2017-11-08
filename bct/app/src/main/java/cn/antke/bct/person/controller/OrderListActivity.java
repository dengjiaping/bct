package cn.antke.bct.person.controller;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.common.network.FProtocol;
import com.common.widget.FootLoadingListView;
import com.common.widget.PullToRefreshBase;

import java.util.IdentityHashMap;
import java.util.List;

import cn.antke.bct.R;
import cn.antke.bct.base.ToolBarActivity;
import cn.antke.bct.common.CommonConstant;
import cn.antke.bct.network.Constants;
import cn.antke.bct.network.Parsers;
import cn.antke.bct.network.entities.OrderEntity;
import cn.antke.bct.network.entities.OrderListPageEntity;
import cn.antke.bct.person.adapter.OrderListAdapter;

import static cn.antke.bct.common.CommonConstant.EXTRA_TYPE;
import static cn.antke.bct.common.CommonConstant.ORDERSTATE_ALL;
import static cn.antke.bct.common.CommonConstant.ORDERSTATE_DELIVED;
import static cn.antke.bct.common.CommonConstant.ORDERSTATE_DELIVING;
import static cn.antke.bct.common.CommonConstant.ORDERSTATE_FINISHED;
import static cn.antke.bct.common.CommonConstant.ORDERSTATE_PAYING;
import static cn.antke.bct.common.CommonConstant.ORDERSTATE_REFUND;
import static cn.antke.bct.common.CommonConstant.REQUEST_NET_FOUR;
import static cn.antke.bct.common.CommonConstant.REQUEST_NET_ONE;
import static cn.antke.bct.common.CommonConstant.REQUEST_NET_THREE;
import static cn.antke.bct.common.CommonConstant.REQUEST_NET_TWO;

/**
 * Created by zhaoweiwei on 2017/5/10.
 * 订单列表
 */

public class OrderListActivity extends ToolBarActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

	private int orderState;
	private FootLoadingListView orderListView;
	private List<OrderEntity> entities;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_person_order);
		orderState = getIntent().getIntExtra(EXTRA_TYPE, ORDERSTATE_ALL);
		setLeftTitle(getOrderTitle());

		orderListView = (FootLoadingListView) findViewById(R.id.order_list);
		initLoadingView(this);
		setLoadingStatus(LoadingStatus.GONE);


		orderListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
			@Override
			public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
				loadData(false);
			}

			@Override
			public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
				loadData(true);
			}
		});

		orderListView.setOnItemClickListener(this);
	}

	@Override
	protected void onResume() {
		super.onResume();
		loadData(false);
	}

	private void loadData(boolean isMore) {
		showProgressDialog();
		IdentityHashMap<String, String> params = new IdentityHashMap<>();
		params.put("status", String.valueOf(orderState));
		int page = 1;
		int request = REQUEST_NET_ONE;
		if (isMore) {
			request = REQUEST_NET_TWO;
		}
		params.put(CommonConstant.PAGESIZE, CommonConstant.PAGE_SIZE_10);
		params.put(CommonConstant.PAGENUM, String.valueOf(page));
		requestHttpData(Constants.Urls.URL_POST_ORDER_LIST, request, FProtocol.HttpMethod.POST, params);
	}

	@Override
	public void success(int requestCode, String data) {
		super.success(requestCode, data);
		closeProgressDialog();
		orderListView.setOnRefreshComplete();
		switch (requestCode) {
			case REQUEST_NET_ONE://订单列表
				OrderListPageEntity pageEntity = Parsers.getOrders(data);
				entities = pageEntity.getOrderEntities();
				if (entities != null && entities.size() > 0) {
					OrderListAdapter adapter = new OrderListAdapter(this, entities, this);
					orderListView.setAdapter(adapter);
				}
				break;
			case REQUEST_NET_TWO://加载更多
				break;
			case REQUEST_NET_THREE://取消订单
				loadData(false);
				break;
			case REQUEST_NET_FOUR://删除订单
				loadData(false);
				break;
		}
	}

	@Override
	public void mistake(int requestCode, FProtocol.NetDataProtocol.ResponseStatus status, String errorMessage) {
		super.mistake(requestCode, status, errorMessage);
		closeProgressDialog();
		orderListView.setOnRefreshComplete();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.loading_layout:
				loadData(false);
				break;
			case R.id.item_order_btn1:
				OrderEntity entity1 = (OrderEntity) v.getTag();
				//订单状态：1，待支付；2，待发货；3，已发货； 4.已完成；5，取消订单（待支付状态）6、退款申请；7.退款中；8.已退款；9，拒绝 。
				if (entity1 != null) {
					String status = entity1.getStatus();
					switch (status) {
						case "1"://取消订单
							changeState(entity1,String.valueOf(ORDERSTATE_PAYING));
							break;
						case "3"://查看物流
						case "4"://
							break;
						case "6"://退款详情
							OrderDetailActivity.startOrderDetailActivity(this, entity1);
							break;
					}
				}
				break;
			case R.id.item_order_btn2:
				OrderEntity entity2 = (OrderEntity) v.getTag();
				//订单状态：1，待支付；2，待发货；3，已发货； 4.已完成；5，取消订单（待支付状态）6、退款申请；7.退款中；8.已退款；9，拒绝 。
				if (entity2 != null) {
					String status = entity2.getStatus();
					switch (status) {
						case "1"://付款
							break;
						case "2"://退款
							RefundApplyActivity.startRefundApplyActivity(this, entity2);
							break;
						case "3"://完成(确认收货)
							changeState(entity2,"4");
							break;
						case "4"://再次购买
							break;
						case "5"://删除
							changeState(entity2,"5");
							break;
						case "6"://取消退款
							changeState(entity2,"6");
							break;
						case "7":
						case "8":
						case "9"://退款详情
							OrderDetailActivity.startOrderDetailActivity(this, entity2);
							break;
					}
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
		requestHttpData(Constants.Urls.URL_POST_ORDER_CHANGE, REQUEST_NET_THREE, FProtocol.HttpMethod.POST, params);
	}

	public String getOrderTitle() {
		String orderTitle = "";
		switch (orderState) {
			case ORDERSTATE_ALL:
				orderTitle = getString(R.string.person_all_order);
				break;
			case ORDERSTATE_PAYING:
				orderTitle = getString(R.string.order, getString(R.string.person_order_paying));
				break;
			case ORDERSTATE_DELIVING:
				orderTitle = getString(R.string.order, getString(R.string.person_order_deliving));
				break;
			case ORDERSTATE_DELIVED:
				orderTitle = getString(R.string.order, getString(R.string.person_order_delived));
				break;
			case ORDERSTATE_FINISHED:
				orderTitle = getString(R.string.order, getString(R.string.person_order_finished));
				break;
			case ORDERSTATE_REFUND:
				orderTitle = getString(R.string.order, getString(R.string.person_order_refund));
				break;
		}
		return orderTitle;
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		OrderDetailActivity.startOrderDetailActivity(this, entities.get(position));
	}
}
