package cn.antke.bct.person.controller;

import android.os.Bundle;

import com.common.viewinject.annotation.ViewInject;
import com.common.widget.FootLoadingListView;

import cn.antke.bct.R;
import cn.antke.bct.base.ToolBarActivity;
import cn.antke.bct.utils.ViewInjectUtils;

/**
 * Created by zhaoweiwei on 2017/5/16.
 * 我的消息
 */

public class MyMessageActivity extends ToolBarActivity {
	@ViewInject(R.id.my_message_list)
	private FootLoadingListView messageList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_my_message);
		ViewInjectUtils.inject(this);
		setLeftTitle(getString(R.string.person_my_message));
	}
}
