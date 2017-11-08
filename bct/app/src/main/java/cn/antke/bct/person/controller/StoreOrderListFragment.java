package cn.antke.bct.person.controller;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.common.network.FProtocol;
import com.common.utils.ToastUtil;
import com.common.widget.FootLoadingListView;
import com.common.widget.PullToRefreshBase;

import java.util.ArrayList;
import java.util.IdentityHashMap;
import java.util.List;

import cn.antke.bct.R;
import cn.antke.bct.base.BaseFragment;
import cn.antke.bct.person.adapter.StoreOrderListAdapter;

/**
 * Created by zhaoweiwei on 2016/12/21.
 * 订单列表
 */

public class StoreOrderListFragment extends BaseFragment{

	private int state;
	private FootLoadingListView loadingListView;

	public void setArgs(int state) {
		this.state = state;
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.frag_store_order, null);
		loadingListView = (FootLoadingListView) view.findViewById(R.id.store_order_list);
		loadingListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
			@Override
			public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
				loadData(false);
			}

			@Override
			public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
				loadData(true);
			}
		});
		return view;
	}

	private void loadData(boolean b) {
		StoreOrderListAdapter adapter = new StoreOrderListAdapter(getActivity(),new ArrayList<>());
		loadingListView.setAdapter(adapter);
	}

}
