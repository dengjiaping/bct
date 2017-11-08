package cn.antke.bct.person.controller;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;

import cn.antke.bct.R;
import cn.antke.bct.base.ToolBarActivity;

/**
 * Created by zhaoweiwei on 2017/5/11.
 * 开店协议
 */

public class StoreProtocalActivity extends ToolBarActivity implements View.OnClickListener{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_store_protocal);
		setLeftTitle(getString(R.string.store_business));
		WebView webView = (WebView) findViewById(R.id.store_protocal_webview);
		findViewById(R.id.store_protocal_agree).setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()){
			case R.id.store_protocal_agree:
				break;
		}
	}
}
