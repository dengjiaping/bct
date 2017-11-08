package cn.antke.bct.person.controller;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.common.network.FProtocol;
import com.common.utils.ToastUtil;
import com.common.viewinject.annotation.ViewInject;
import com.common.widget.FootLoadingListView;
import com.common.widget.PullToRefreshBase;

import java.util.IdentityHashMap;
import java.util.List;

import cn.antke.bct.R;
import cn.antke.bct.base.ToolBarActivity;
import cn.antke.bct.login.utils.UserCenter;
import cn.antke.bct.network.Constants;
import cn.antke.bct.network.Parsers;
import cn.antke.bct.network.entities.AddressEntity;
import cn.antke.bct.network.entities.AddressPageEntity;
import cn.antke.bct.person.adapter.AddressAdapter;
import cn.antke.bct.utils.ViewInjectUtils;

import static cn.antke.bct.common.CommonConstant.EXTRA_ENTITY;
import static cn.antke.bct.common.CommonConstant.EXTRA_TYPE;
import static cn.antke.bct.common.CommonConstant.REQUEST_ACT_ONE;
import static cn.antke.bct.common.CommonConstant.REQUEST_ACT_TWO;
import static cn.antke.bct.common.CommonConstant.REQUEST_NET_ONE;
import static cn.antke.bct.common.CommonConstant.REQUEST_NET_THREE;
import static cn.antke.bct.common.CommonConstant.REQUEST_NET_TWO;
import static cn.antke.bct.common.CommonConstant.TYPE_ADD;
import static cn.antke.bct.common.CommonConstant.TYPE_EDIT;

/**
 * Created by zhaoweiwei on 2016/12/25.
 * 地址管理
 */
public class AddressListActivity extends ToolBarActivity implements View.OnClickListener {

	@ViewInject(R.id.address_list)
	private FootLoadingListView addressList;
	@ViewInject(R.id.address_add_new)
	private TextView addressAddNew;

	private AddressAdapter addressAdapter;
	private AddressEntity defaultEntity;
	private List<AddressEntity> addressEntities;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_person_address_list);
		ViewInjectUtils.inject(this);
		initView();
		loadData(false);
	}

	private void initView() {
		setLeftTitle(getString(R.string.person_address));
		addressAddNew.setOnClickListener(this);
		initLoadingView(this);
		setLoadingStatus(LoadingStatus.GONE);
		addressList.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
			@Override
			public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
				loadData(false);
			}

			@Override
			public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
				loadData(true);
			}
		});
		addressList.setOnItemClickListener((parent, view, position, id) -> {
//			Intent intent = getIntent();
//			if (1 == intent.getIntExtra(EXTRA_FROM, 0)) {
//				intent.putExtra("addressEntity", entities.get(position));
//				setResult(RESULT_OK, intent);
//				finish();
//			}
		});
	}

	private void loadData(boolean isMore) {
		IdentityHashMap<String, String> params = new IdentityHashMap<>();
		int request = REQUEST_NET_ONE;
//		int page = 1;
//		if (isMore) {
//			page = addressAdapter.getPage() + 1;
//			request = REQUEST_NET_TWO;
//		}
//		params.put(Constants.PAGENUM, String.valueOf(page));
//		params.put(Constants.PAGESIZE, Constants.PAGE_SIZE_10);
		requestHttpData(Constants.Urls.URL_POST_ADDRESS_LIST, request, FProtocol.HttpMethod.POST, params);
	}

	@Override
	public void onClick(View view) {
		switch (view.getId()) {
			case R.id.address_add_new:
				startActivityForResult(new Intent(this, AddressEditActivity.class).putExtra(EXTRA_TYPE, TYPE_ADD), REQUEST_ACT_ONE);
				break;
			case R.id.address_item_default:
				defaultEntity = (AddressEntity) view.getTag();
				setDefaultAddress();
				break;
			case R.id.address_item_edit:
				AddressEntity editEntity = (AddressEntity) view.getTag();
				Intent editIntent = new Intent(this, AddressEditActivity.class);
				editIntent.putExtra(EXTRA_TYPE, TYPE_EDIT);
				editIntent.putExtra(EXTRA_ENTITY, editEntity);
				startActivityForResult(editIntent, REQUEST_ACT_TWO);
				break;
			case R.id.loading_layout:
				loadData(false);
				break;
		}
	}

	private void setDefaultAddress() {
		showProgressDialog();
		IdentityHashMap<String, String> params = new IdentityHashMap<>();
		params.put("receivingId", defaultEntity.getReciveId());
		requestHttpData(Constants.Urls.URL_POST_ADDRESS_SETDEFAULT, REQUEST_NET_THREE, FProtocol.HttpMethod.POST, params);
	}

	@Override
	public void success(int requestCode, String data) {
		closeProgressDialog();
		setLoadingStatus(LoadingStatus.GONE);
		addressList.setOnRefreshComplete();
		switch (requestCode) {
			case REQUEST_NET_ONE://
				AddressPageEntity addressPageEntity = Parsers.getAddressPage(data);
				addressEntities = addressPageEntity.getAddressEntities();
				if (addressEntities != null && addressEntities.size() > 0) {
					addressAdapter = new AddressAdapter(this, addressEntities, this);
					addressList.setAdapter(addressAdapter);
				}
				break;
			case REQUEST_NET_TWO://加载更多
				break;
			case REQUEST_NET_THREE://设置默认地址
				for (AddressEntity entity : addressEntities) {
					if (defaultEntity.getReciveId().equals(entity.getReciveId())) {
						entity.setDefault(true);
					} else {
						entity.setDefault(false);
					}
				}
				addressAdapter.notifyDataSetChanged();
				break;
		}
	}

	@Override
	public void mistake(int requestCode, FProtocol.NetDataProtocol.ResponseStatus status, String errorMessage) {
		closeProgressDialog();
		ToastUtil.shortShow(this, errorMessage);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (RESULT_OK == resultCode) {
			switch (requestCode) {
				case REQUEST_ACT_ONE://新增地址
				case REQUEST_ACT_TWO://编辑地址
					loadData(false);
					break;
			}
		}
	}

	@Override
	public void finish() {
		Intent intent = getIntent();
		intent.putExtra("defaultAddress", getDefaultAddress());
		setResult(RESULT_OK, intent);
		super.finish();
	}

	private String getDefaultAddress() {
		String address = "";
		if (addressEntities != null && addressEntities.size() > 0) {
			for (AddressEntity entity : addressEntities) {
				if (entity.isDefault()) {
					address = entity.getProvinceName() + entity.getCityName() + entity.getDistrictName() + entity.getAddress();
				}
			}
		}
		UserCenter.setDefaultAddress(this, address);
		return address;
	}
}
