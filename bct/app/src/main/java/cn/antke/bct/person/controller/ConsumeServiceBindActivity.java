package cn.antke.bct.person.controller;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.common.viewinject.annotation.ViewInject;

import cn.antke.bct.R;
import cn.antke.bct.base.ToolBarActivity;
import cn.antke.bct.login.utils.UserCenter;
import cn.antke.bct.utils.ViewInjectUtils;

/**
 * Created by zhaoweiwei on 2017/5/17.
 * 绑定消费服务中心
 */

public class ConsumeServiceBindActivity extends ToolBarActivity {
	@ViewInject(R.id.consumer_service_bind_code)
	private EditText bindCode;
	@ViewInject(R.id.consumer_service_bind_realname)
	private TextView bindName;
	@ViewInject(R.id.consumer_service_bind_phone)
	private TextView bindPhone;
	@ViewInject(R.id.consumer_service_bind_btn)
	private TextView bindBtn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_consume_service_bind);
		ViewInjectUtils.inject(this);
		setLeftTitle(getString(R.string.person_consumer_service_bind));


		bindBtn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

			}
		});
	}
}
