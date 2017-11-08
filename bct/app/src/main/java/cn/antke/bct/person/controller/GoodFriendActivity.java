package cn.antke.bct.person.controller;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.common.viewinject.annotation.ViewInject;

import cn.antke.bct.R;
import cn.antke.bct.base.ToolBarActivity;
import cn.antke.bct.utils.CommonShareUtil;
import cn.antke.bct.utils.ViewInjectUtils;
import cn.antke.bct.widget.SideBar;

/**
 * Created by zhaoweiwei on 2017/5/21.
 * 好友
 */

public class GoodFriendActivity extends ToolBarActivity implements View.OnClickListener {
	@ViewInject(R.id.good_friend_phone)
	private TextView goodFriendPhone;
	@ViewInject(R.id.good_friend_wx)
	private TextView goodFriendWx;
	@ViewInject(R.id.share_member_list)
	private ListView goodFriendList;
	@ViewInject(R.id.share_member_sidebar)
	private SideBar goodFriendSidebar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_good_friend);
		setLeftTitle(getString(R.string.person_good_friend));
		ViewInjectUtils.inject(this);
		goodFriendPhone.setOnClickListener(this);
		goodFriendWx.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.good_friend_phone:
				sendSMS("");
				break;
			case R.id.good_friend_wx:
				CommonShareUtil.shareToWechat(this,"","","","");
				break;
		}
	}

	private void sendSMS(String smsBody) {
		//"smsto:xxx" xxx是可以指定联系人的
		Uri smsToUri = Uri.parse("smsto:");
		Intent intent = new Intent(Intent.ACTION_SENDTO, smsToUri);
		//"sms_body"必须一样，smsbody是发送短信内容content
		intent.putExtra("sms_body", smsBody);
		startActivity(intent);

	}
}
