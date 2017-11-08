package cn.antke.bct.main.controller;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.common.utils.LogUtil;
import com.common.utils.ToastUtil;

import cn.antke.bct.R;
import cn.antke.bct.base.BaseTabsDrawerActivity;
import cn.antke.bct.category.controller.CategoryFragment;
import cn.antke.bct.db.CityDBManager;
import cn.antke.bct.deal.controller.DealHallFragment;
import cn.antke.bct.home.controller.HomeFragment;
import cn.antke.bct.person.controller.PersonFragment;
import cn.antke.bct.special.controller.SpecialFragment;
import cn.antke.bct.utils.ExitManager;
import cn.antke.bct.utils.PermissionUtils;
import cn.jpush.android.api.JPushInterface;

public class MainActivity extends BaseTabsDrawerActivity {

	public static final String EXTRA_WHICH_TAB = "extra_which_tab";
	public static final long DIFF_DEFAULT_BACK_TIME = 2000;

	private long mBackTime = -1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		isHasFragment = true;
		LogUtil.e("MainActivity", "registrationID=" + JPushInterface.getRegistrationID(this));
		PermissionUtils.requestPermissions(this, REQUEST_PERMISSION_CODE, Manifest.permission.CAMERA, Manifest.permission.READ_PHONE_STATE, Manifest.permission.READ_EXTERNAL_STORAGE);
		initCityDB();
		getNewActivity();
	}

	private void initCityDB() {
		new Thread(() -> CityDBManager.introducedCityDB(this)).start();
	}

	//获取新的活动
	private void getNewActivity() {
		//添加活动测试数据
		NewActivityActivity.startNewActivityActivity(this, "", "http://onau582bt.bkt.clouddn.com/6505a939-75b0-4c84-b4fd-01ab58d040f2");
	}

	@Override
	public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
		super.onRequestPermissionsResult(requestCode, permissions, grantResults);
		if (requestCode == REQUEST_PERMISSION_CODE) {
			//如果取消了，结果数组将会为0，结果数组数量对应请求权限的个数
			if (grantResults.length < 1) {
				ToastUtil.shortShow(this, getString(R.string.get_permission_failed));
			}
		}
	}

	@Override
	protected void onNewIntent(Intent intent) {
		super.onNewIntent(intent);
		int whichTab = intent.getIntExtra(EXTRA_WHICH_TAB, 0);
		setCurrentTab(whichTab);
	}

	@Override
	protected void addTabs() {
		addTab(initTabView(R.drawable.navigation_ic_home_selector, R.string.main_tab_home), HomeFragment.class, null);
		addTab(initTabView(R.drawable.navigation_ic_category_selector, R.string.main_tab_category), CategoryFragment.class, null);
		addTab(initTabView(R.drawable.navigation_ic_deal_selector, R.string.main_tab_deal), DealHallFragment.class, null);
		addTab(initTabView(R.drawable.navigation_ic_special_selector, R.string.main_tab_special), SpecialFragment.class, null);
		addTab(initTabView(R.drawable.navigation_ic_person_selector, R.string.main_tab_person), PersonFragment.class, null);
	}

	private View initTabView(int tabIcon, int tabText) {
		ViewGroup tab = (ViewGroup) LayoutInflater.from(this).inflate(R.layout.main_tab_item, null);
		ImageView imageView = (ImageView) tab.findViewById(R.id.navigation);
		imageView.setImageResource(tabIcon);

		TextView textView = (TextView) tab.findViewById(R.id.txt_navigation);
		textView.setText(tabText);
		return tab;
	}

	@Override
	public void onTabChanged(String tabId) {
		super.onTabChanged(tabId);
		switch (currentIndex) {
			case 3:
				break;
		}
	}

	@Override
	public void onBackPressed() {
		long nowTime = System.currentTimeMillis();
		long diff = nowTime - mBackTime;
		if (diff >= DIFF_DEFAULT_BACK_TIME) {
			mBackTime = nowTime;
			Toast.makeText(getApplicationContext(), R.string.toast_back_again_exit, Toast.LENGTH_SHORT).show();
		} else {
			ExitManager.instance.exit();
		}
	}
}
