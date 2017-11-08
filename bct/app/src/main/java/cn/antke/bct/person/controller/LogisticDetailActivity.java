package cn.antke.bct.person.controller;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.common.viewinject.annotation.ViewInject;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

import cn.antke.bct.R;
import cn.antke.bct.base.ToolBarActivity;
import cn.antke.bct.network.entities.LogisticEntity;
import cn.antke.bct.person.adapter.LogisticDetailAdapter;
import cn.antke.bct.utils.ViewInjectUtils;

/**
 * Created by zhaoweiwei on 2017/5/23.
 * 物流详情
 */

public class LogisticDetailActivity extends ToolBarActivity {
	@ViewInject(R.id.logistic_pic)
	private SimpleDraweeView logisticPic;
	@ViewInject(R.id.logistic_state)
	private TextView logisticState;
	@ViewInject(R.id.logistic_company)
	private TextView logisticCompany;
	@ViewInject(R.id.logistic_number)
	private TextView logisticNumber;
	@ViewInject(R.id.logistic_phone)
	private TextView logisticPhone;
	@ViewInject(R.id.logistic_list)
	private ListView logisticList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_logistic_detail);
		ViewInjectUtils.inject(this);
		setLeftTitle(getString(R.string.person_order_logistic_detail));
		LogisticDetailAdapter adapter = new LogisticDetailAdapter(this,new ArrayList<>());
		logisticList.setAdapter(adapter);
	}
}
