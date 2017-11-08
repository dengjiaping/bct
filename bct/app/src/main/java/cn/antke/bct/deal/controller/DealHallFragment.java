package cn.antke.bct.deal.controller;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.common.viewinject.annotation.ViewInject;
import com.common.widget.RefreshRecyclerView;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.utils.Utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import cn.antke.bct.R;
import cn.antke.bct.base.BaseFragment;
import cn.antke.bct.deal.adapter.DealRecordAdapter;
import cn.antke.bct.network.entities.DealRecordEntity;
import cn.antke.bct.utils.ViewInjectUtils;

/**
 * Created by liuzhichao on 2017/4/27.
 * 交易大厅
 */
public class DealHallFragment extends BaseFragment implements View.OnClickListener {

	@ViewInject(R.id.left_button)
	private View leftButton;
	@ViewInject(R.id.toolbar_title)
	private TextView toolbarTitle;
	@ViewInject(R.id.rigth_text)
	private TextView rigthText;
	@ViewInject(R.id.tv_deal_name)
	private TextView tvDealName;
	@ViewInject(R.id.tv_deal_volume)
	private TextView tvDealVolume;
	@ViewInject(R.id.tv_deal_unit)
	private TextView tvDealUnit;
	@ViewInject(R.id.tv_deal_integral)
	private TextView tvDealIntegral;
	@ViewInject(R.id.lc_deal_chart)
	private LineChart lcDealChart;
	@ViewInject(R.id.tv_deal_buy)
	private TextView tvDealBuy;
	@ViewInject(R.id.tv_deal_sale)
	private TextView tvDealSale;
	@ViewInject(R.id.rrv_deal_record)
	private RefreshRecyclerView rrvDealRecord;

	private View view;

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		if (view == null) {
			view = inflater.inflate(R.layout.frag_deal_hall, null);
			ViewInjectUtils.inject(this, view);
			initView();
			initChart();
		}
		ViewGroup mViewParent = (ViewGroup) view.getParent();
		if (mViewParent != null) {
			mViewParent.removeView(view);
		}
		return view;
	}

	private void initView() {
		leftButton.setVisibility(View.GONE);
		toolbarTitle.setText(getString(R.string.main_tab_deal));
		toolbarTitle.setVisibility(View.VISIBLE);
		rigthText.setText(getString(R.string.renew));
		rigthText.setVisibility(View.VISIBLE);

		rrvDealRecord.setHasFixedSize(true);
		rrvDealRecord.setMode(RefreshRecyclerView.Mode.PULL_FROM_END);
		rrvDealRecord.setLayoutManager(new LinearLayoutManager(getActivity()));
		rrvDealRecord.setOnRefreshAndLoadMoreListener(new RefreshRecyclerView.OnRefreshAndLoadMoreListener() {
			@Override
			public void onRefresh() {
			}

			@Override
			public void onLoadMore() {
				loadData();
			}
		});

		rigthText.setOnClickListener(this);
		tvDealBuy.setOnClickListener(this);
		tvDealSale.setOnClickListener(this);
	}

	private void initChart() {
		// no description text
		lcDealChart.getDescription().setEnabled(false);

		List<Entry> datas = new ArrayList<>();
		for (int i = 0; i < 5; i++) {
			float val = (float) (Math.random() * 600) + 3;
			datas.add(new Entry(i, val));
		}
		//折线图
		LineDataSet lineDataSet = new LineDataSet(datas, "Chart");
		//蚂蚁线
//		lineDataSet.enableDashedLine(10f, 5f, 0f);
//		lineDataSet.enableDashedHighlightLine(10f, 5f, 0f);
		//折线颜色
		lineDataSet.setColor(Color.parseColor("#58b4aa"));
		//数据点圆圈颜色
		lineDataSet.setCircleColor(Color.parseColor("#58b4aa"));
		//数据点圆圈大小
		lineDataSet.setCircleRadius(5f);
		//数据点圆圈是否显示空心
		lineDataSet.setDrawCircleHole(true);
		//空心大小
		lineDataSet.setCircleHoleRadius(2f);
		//空心颜色
		lineDataSet.setCircleColorHole(Color.WHITE);
		//线宽，粗细
		lineDataSet.setLineWidth(2f);
		//数据文字大小
		lineDataSet.setValueTextSize(9f);
		//折线模式，CUBIC_BEZIER 弧形、平滑过渡
		lineDataSet.setMode(LineDataSet.Mode.CUBIC_BEZIER);

		//是否填充
		lineDataSet.setDrawFilled(true);
		//填充部分透明度
		lineDataSet.setFillAlpha(30);
		if (Utils.getSDKInt() >= 18) {
			// fill drawable only supported on api level 18 and above
//			Drawable drawable = ContextCompat.getDrawable(this, R.drawable.scan_mask);
//			lineDataSet.setFillDrawable(drawable);
			lineDataSet.setFillColor(Color.parseColor("#58b4aa"));
		} else {
			lineDataSet.setFillColor(Color.parseColor("#58b4aa"));
		}

		LineData lineData = new LineData(lineDataSet);
		lcDealChart.setData(lineData);

		//折线说明，色值、名称
		Legend legend = lcDealChart.getLegend();
		legend.setEnabled(false);

		//X轴
		XAxis xAxis = lcDealChart.getXAxis();
		xAxis.setTextSize(12f);
		xAxis.setTextColor(Color.parseColor("#999999"));
		xAxis.setYOffset(0f);//x轴标值y方向偏移量，默认会向下偏移
		xAxis.setDrawGridLines(false);//区域内分隔线
		xAxis.setDrawAxisLine(true);//轴线
		//设置X轴所在位置
		xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
		xAxis.setGranularity(1f); // one hour
		xAxis.setValueFormatter(new IAxisValueFormatter() {

			private SimpleDateFormat mFormat = new SimpleDateFormat("HH:mm");

			@Override
			public String getFormattedValue(float value, AxisBase axis) {

				long millis = TimeUnit.HOURS.toMillis((long) value);
				return mFormat.format(new Date(millis));
			}
		});

		//左Y轴
		YAxis leftAxis = lcDealChart.getAxisLeft();
		leftAxis.setTextSize(12f);
		leftAxis.setTextColor(Color.parseColor("#999999"));
		leftAxis.setDrawZeroLine(false);//0线是否显示
//		leftAxis.setAxisMaximum(200f);
//		leftAxis.setAxisMinimum(0f);
		leftAxis.setDrawGridLines(true);
		leftAxis.setGranularityEnabled(true);
		leftAxis.setDrawAxisLine(false);
		leftAxis.setGridColor(Color.parseColor("#eeeeee"));
		leftAxis.setPosition(YAxis.YAxisLabelPosition.INSIDE_CHART);

		//不显示右Y轴
		lcDealChart.getAxisRight().setEnabled(false);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		loadData();
	}

	private void loadData() {
		//准备测试数据
		List<DealRecordEntity> dealRecordEntities = new ArrayList<>();
		dealRecordEntities.add(new DealRecordEntity("5000", "1000", "2017-05-15", "买入"));
		dealRecordEntities.add(new DealRecordEntity("6000", "2000", "2017-06-15", "卖出"));
		dealRecordEntities.add(new DealRecordEntity("7000", "3000", "2017-07-15", "卖出"));
		dealRecordEntities.add(new DealRecordEntity("8000", "4000", "2017-08-15", "买入"));
		dealRecordEntities.add(new DealRecordEntity("9000", "5000", "2017-09-15", "买入"));
		dealRecordEntities.add(new DealRecordEntity("10000", "6000", "2017-12-15", "卖出"));
		DealRecordAdapter adapter = new DealRecordAdapter(dealRecordEntities);
		rrvDealRecord.setAdapter(adapter);
		rrvDealRecord.setCanAddMore(false);
	}

	@Override
	public void success(int requestCode, String data) {
		super.success(requestCode, data);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.rigth_text:
				DealRechargeActivity.startDealRechargeActivity(getActivity());
				break;
			case R.id.tv_deal_buy:
				BuyIntegralActivity.startBuyIntegralActivity(getActivity());
				break;
			case R.id.tv_deal_sale:
				SaleIntegralActivity.startSaleIntegralActivity(getActivity());
				break;
		}
	}
}
