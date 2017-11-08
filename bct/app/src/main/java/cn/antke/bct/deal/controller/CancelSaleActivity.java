package cn.antke.bct.deal.controller;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.common.utils.ToastUtil;
import com.common.widget.RefreshRecyclerView;

import java.util.ArrayList;
import java.util.List;

import cn.antke.bct.R;
import cn.antke.bct.base.ToolBarActivity;
import cn.antke.bct.common.CommonConstant;
import cn.antke.bct.deal.adapter.SaleAdapter;
import cn.antke.bct.network.Parsers;
import cn.antke.bct.network.entities.Entity;
import cn.antke.bct.network.entities.SaleEntity;

/**
 * Created by liuzhichao on 2017/5/23.
 * 撤销卖出
 */
public class CancelSaleActivity extends ToolBarActivity implements View.OnClickListener {

	private RefreshRecyclerView recyclerView;

	public static void startCancelSaleActivity(Context context) {
		Intent intent = new Intent(context, CancelSaleActivity.class);
		context.startActivity(intent);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_cancel_sale);
		initView();
		loadData(false);
	}

	private void initView() {
		setLeftTitle(getString(R.string.cancel_sell));

		recyclerView = (RefreshRecyclerView) findViewById(R.id.rrv_sale_list);
		recyclerView.setHasFixedSize(true);
		recyclerView.setMode(RefreshRecyclerView.Mode.BOTH);
		recyclerView.setLayoutManager(new LinearLayoutManager(this));
		recyclerView.setOnRefreshAndLoadMoreListener(new RefreshRecyclerView.OnRefreshAndLoadMoreListener() {
			@Override
			public void onRefresh() {
				loadData(false);
			}

			@Override
			public void onLoadMore() {
				loadData(true);
			}
		});
	}

	private void loadData(boolean isMore) {
		//添加测试数据
		List<SaleEntity> saleEntities = new ArrayList<>();
		saleEntities.add(new SaleEntity("5000", "1000", "2017-05-23 18:23"));
		saleEntities.add(new SaleEntity("10000", "2500", "2017-05-24 18:23"));
		saleEntities.add(new SaleEntity("20000", "5000", "2017-05-25 18:23"));
		saleEntities.add(new SaleEntity("40000", "10000", "2017-05-26 18:23"));
		SaleAdapter adapter = new SaleAdapter(saleEntities, this);
		recyclerView.setAdapter(adapter);
		recyclerView.setCanAddMore(false);
	}

	@Override
	public void success(int requestCode, String data) {
		Entity result = Parsers.getResult(data);
		if (CommonConstant.REQUEST_NET_SUCCESS.equals(result.getResultCode())) {
		} else {
			ToastUtil.shortShow(this, result.getResultMsg());
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.tv_sale_cancel:
				SaleEntity saleEntity = (SaleEntity) v.getTag();
				if (saleEntity != null) {
					ToastUtil.shortShow(this, saleEntity.getDate());
				}
				break;
		}
	}
}
