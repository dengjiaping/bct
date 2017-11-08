package cn.antke.bct.draw.controller;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import com.common.network.FProtocol;
import com.common.utils.ToastUtil;
import com.common.widget.RefreshRecyclerView;

import java.util.ArrayList;
import java.util.IdentityHashMap;
import java.util.List;

import cn.antke.bct.R;
import cn.antke.bct.base.ToolBarActivity;
import cn.antke.bct.common.CommonConstant;
import cn.antke.bct.draw.adapter.DrawListAdapter;
import cn.antke.bct.network.Constants;
import cn.antke.bct.network.Parsers;
import cn.antke.bct.network.entities.DrawQueryEntity;
import cn.antke.bct.network.entities.Entity;
import cn.antke.bct.network.entities.PagesEntity;

/**
 * Created by liuzhichao on 2017/5/15.
 * 中奖查询
 */
public class DrawQueryActivity extends ToolBarActivity {

	private RefreshRecyclerView rrvDrawList;
	private DrawListAdapter adapter;

	public static void startDrawQueryActivity(Context context) {
		Intent intent = new Intent(context, DrawQueryActivity.class);
		context.startActivity(intent);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_draw_query);
		initView();
		loadData(false);
	}

	private void initView() {
		setLeftTitle(getString(R.string.draw_list));
		rrvDrawList = (RefreshRecyclerView) findViewById(R.id.rrv_draw_list);
		rrvDrawList.setHasFixedSize(true);
		rrvDrawList.setMode(RefreshRecyclerView.Mode.BOTH);
		rrvDrawList.setLayoutManager(new LinearLayoutManager(this));
		rrvDrawList.setOnRefreshAndLoadMoreListener(new RefreshRecyclerView.OnRefreshAndLoadMoreListener() {
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
		IdentityHashMap<String, String> params = new IdentityHashMap<>();
		params.put(CommonConstant.PAGESIZE, CommonConstant.PAGE_SIZE_10);
		int page = 1;
		int requestCode = CommonConstant.REQUEST_NET_ONE;
		if (isMore) {
			page = adapter.getPage() + 1;
			requestCode = CommonConstant.REQUEST_NET_TWO;
		}
		requestHttpData(Constants.Urls.URL_POST_DRAW_QUERY, requestCode, FProtocol.HttpMethod.POST, params);
		//添加测试数据
		List<DrawQueryEntity> drawQueryEntities = new ArrayList<>();
		drawQueryEntities.add(new DrawQueryEntity("期数", "用户编号", "奖品"));
		drawQueryEntities.add(new DrawQueryEntity("一期", "A888999", "iPhone4"));
		drawQueryEntities.add(new DrawQueryEntity("二期", "A888998", "iPhone5"));
		drawQueryEntities.add(new DrawQueryEntity("三期", "A888997", "iPhone6"));
		drawQueryEntities.add(new DrawQueryEntity("四期", "A888996", "iPhone7"));
	}

	@Override
	public void success(int requestCode, String data) {
		Entity result = Parsers.getResult(data);
		if (CommonConstant.REQUEST_NET_SUCCESS.equals(result.getResultCode())) {
			PagesEntity<DrawQueryEntity> drawQueryPage = Parsers.getDrawQueryPage(data);
			List<DrawQueryEntity> drawQueryEntities = drawQueryPage.getDatas();
			if (drawQueryEntities == null) {
				drawQueryEntities = new ArrayList<>();
			}
			drawQueryEntities.add(0, new DrawQueryEntity(getString(R.string.no), getString(R.string.register_usernumber_text), getString(R.string.prize)));
			adapter = new DrawListAdapter(drawQueryEntities);
			rrvDrawList.setAdapter(adapter);
			if (drawQueryPage.getTotalPage() <= 1) {
				rrvDrawList.setCanAddMore(false);
			}
		} else {
			ToastUtil.shortShow(this, result.getResultMsg());
		}
	}
}
