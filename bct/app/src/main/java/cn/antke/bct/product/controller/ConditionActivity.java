package cn.antke.bct.product.controller;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.common.viewinject.annotation.ViewInject;

import cn.antke.bct.R;
import cn.antke.bct.base.BaseActivity;
import cn.antke.bct.common.CommonConstant;
import cn.antke.bct.utils.ViewInjectUtils;

/**
 * Created by liuzhichao on 2017/6/2.
 * 规格尺码颜色
 */
public class ConditionActivity extends BaseActivity implements View.OnClickListener {

	@ViewInject(R.id.tv_condition_result)
	private View tvConditionResult;
	private int parentPosition;
	private int position;

	public static void startConditionActivity (Activity context, int parentPosition, int position) {
		Intent intent = new Intent(context, ConditionActivity.class);
		intent.putExtra("parentPosition", parentPosition);
		intent.putExtra("position", position);
		context.startActivityForResult(intent, CommonConstant.REQUEST_ACT_ONE);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_condition);
		ViewInjectUtils.inject(this);
		initView();
		loadData();
	}

	private void initView() {
		parentPosition = getIntent().getIntExtra("parentPosition", -1);
		position = getIntent().getIntExtra("position", -1);
		tvConditionResult.setOnClickListener(this);
	}

	private void loadData() {

	}

	@Override
	public void success(int requestCode, String data) {
		super.success(requestCode, data);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.tv_condition_result:
				Intent intent = new Intent();
				intent.putExtra("condition", "35mm*60mm");
				intent.putExtra("parentPosition", parentPosition);
				intent.putExtra("position", position);
				setResult(RESULT_OK, intent);
				finish();
				break;
		}
	}
}
