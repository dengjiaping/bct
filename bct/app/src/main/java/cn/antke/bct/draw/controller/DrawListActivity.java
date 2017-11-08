package cn.antke.bct.draw.controller;

import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.common.network.FProtocol;
import com.common.utils.ToastUtil;
import com.common.viewinject.annotation.ViewInject;
import com.common.widget.RefreshRecyclerView;

import java.util.ArrayList;
import java.util.IdentityHashMap;
import java.util.List;

import cn.antke.bct.R;
import cn.antke.bct.base.ToolBarActivity;
import cn.antke.bct.common.CommonConstant;
import cn.antke.bct.draw.adapter.DrawAdapter;
import cn.antke.bct.network.Constants;
import cn.antke.bct.network.Parsers;
import cn.antke.bct.network.entities.DrawEntity;
import cn.antke.bct.network.entities.Entity;
import cn.antke.bct.network.entities.PagesEntity;
import cn.antke.bct.utils.CommonTools;
import cn.antke.bct.utils.ViewInjectUtils;

/**
 * Created by liuzhichao on 2017/5/11.
 * 抽奖列表
 */
public class DrawListActivity extends ToolBarActivity implements View.OnClickListener {

	@ViewInject(R.id.rrv_draw_list)
	private RefreshRecyclerView rrvDrawList;

	private DrawAdapter adapter;

	public static void startDrawListActivity(Context context, String title) {
		Intent intent = new Intent(context, DrawListActivity.class);
		intent.putExtra(CommonConstant.EXTRA_TITLE, title);
		context.startActivity(intent);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_draw_list);
		ViewInjectUtils.inject(this);
		initView();
		loadData(false);
	}

	private void initView() {
		String title = getIntent().getStringExtra(CommonConstant.EXTRA_TITLE);
		setLeftTitle(title);
		setRightText(getString(R.string.draw_list));
		rightText.setOnClickListener(this);

		rrvDrawList.setHasFixedSize(true);
		rrvDrawList.setMode(RefreshRecyclerView.Mode.BOTH);
		rrvDrawList.setLayoutManager(new LinearLayoutManager(this));
		rrvDrawList.addItemDecoration(new RecyclerView.ItemDecoration() {
			@Override
			public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
				int headerSize = rrvDrawList.getHeaderSize();//头部数量
				int pos = parent.getChildLayoutPosition(view) - headerSize;//减去头部后的下标位置
				if (pos < 0) {//头部
					return;
				}
				outRect.bottom = CommonTools.dp2px(DrawListActivity.this, 10);
			}
		});
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
		params.put(CommonConstant.PAGENUM, String.valueOf(page));
		requestHttpData(Constants.Urls.URL_POST_DRAW_LIST, requestCode, FProtocol.HttpMethod.POST, params);
		//准备抽奖商品测试数据
		List<DrawEntity> drawEntities = new ArrayList<>();
		drawEntities.add(new DrawEntity("抽奖商品一", "http://onau582bt.bkt.clouddn.com/6505a939-75b0-4c84-b4fd-01ab58d040f2", "300", "32", "10000"));
		drawEntities.add(new DrawEntity("抽奖商品二", "http://onau582bt.bkt.clouddn.com/6daa1975-9f34-4add-ad70-f652cef38ca7", "800", "36", "9000"));
		drawEntities.add(new DrawEntity("抽奖商品三", "http://onau582bt.bkt.clouddn.com/dbf631f2-e12d-46b8-a657-434ff2b6dc19", "1200", "22", "8000"));
		drawEntities.add(new DrawEntity("抽奖商品四", "http://onau582bt.bkt.clouddn.com/a32daac0-f7d2-4ee1-8415-0565abda42df", "5000", "18", "7000"));
		drawEntities.add(new DrawEntity("抽奖商品五", "http://onau582bt.bkt.clouddn.com/d0c155d2-37cd-48b5-adff-4b0858ec6dbd", "10300", "103", "6000"));
		drawEntities.add(new DrawEntity("抽奖商品六", "http://onau582bt.bkt.clouddn.com/fc443542-d2de-4c96-82a1-4b9fe9150827", "30000", "199", "5000"));
	}

	@Override
	public void success(int requestCode, String data) {
		Entity result = Parsers.getResult(data);
		if (CommonConstant.REQUEST_NET_SUCCESS.equals(result.getResultCode())) {
			switch (requestCode) {
				case CommonConstant.REQUEST_NET_ONE:
					PagesEntity<DrawEntity> drawEntityPages = Parsers.getDrawPage(data);
					List<DrawEntity> drawEntities = drawEntityPages.getDatas();
					if (drawEntities != null && drawEntities.size() > 0) {
						adapter = new DrawAdapter(drawEntities, this);
						rrvDrawList.setAdapter(adapter);
					}
					if (drawEntityPages.getTotalPage() <= 1) {
						rrvDrawList.setCanAddMore(false);
					}
					break;
				case CommonConstant.REQUEST_NET_TWO:
					break;
			}
		} else {
			ToastUtil.shortShow(this, result.getResultMsg());
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.rigth_text:
				DrawQueryActivity.startDrawQueryActivity(this);
				break;
			case R.id.ll_item_draw:
				DrawEntity drawEntity = (DrawEntity) v.getTag();
				if (drawEntity != null) {
					DrawDetailActivity.startDrawDetailActivity(this, drawEntity.getId());
				}
				break;
		}
	}
}
