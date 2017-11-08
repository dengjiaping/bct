package cn.antke.bct.person.controller;

import android.os.Bundle;

import com.common.widget.FootLoadingListView;

import java.util.ArrayList;
import java.util.List;

import cn.antke.bct.R;
import cn.antke.bct.base.ToolBarActivity;
import cn.antke.bct.network.entities.LotteryInquiryEntity;
import cn.antke.bct.person.adapter.LotteryInquiryAdapter;

/**
 * Created by zhaoweiwei on 2017/5/21.
 * 抽奖查询
 */

public class LotteryInquiryActivity extends ToolBarActivity{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_lottery_inquiry);
		setLeftTitle(getString(R.string.person_lottery_inquiry));
		FootLoadingListView lotteryList = (FootLoadingListView) findViewById(R.id.lottery_inquiry_list);
		List<LotteryInquiryEntity> entities = new ArrayList<>();
		LotteryInquiryAdapter adapter = new LotteryInquiryAdapter(this,entities);
		lotteryList.setAdapter(adapter);
	}
}
