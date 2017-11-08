package cn.antke.bct.product.controller;

import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.common.viewinject.annotation.ViewInject;
import com.common.widget.RefreshRecyclerView;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import cn.antke.bct.R;
import cn.antke.bct.base.ToolBarActivity;
import cn.antke.bct.common.CommonConstant;
import cn.antke.bct.network.entities.ProductEntity;
import cn.antke.bct.network.entities.ShopCarEntity;
import cn.antke.bct.product.adapter.ShopCarAdapter;
import cn.antke.bct.utils.CommonTools;
import cn.antke.bct.utils.ViewInjectUtils;

/**
 * Created by liuzhichao on 2017/5/22.
 * 购物车
 */
public class ShopCarActivity extends ToolBarActivity implements View.OnClickListener {

	@ViewInject(R.id.rrv_shop_car_list)
	private RefreshRecyclerView rrvShopCarList;
	@ViewInject(R.id.ll_shop_car_total)
	private View llShopCarTotal;
	@ViewInject(R.id.cb_shop_car_all)
	private CheckBox cbShopCarAll;
	@ViewInject(R.id.tv_shop_car_amount)
	private TextView tvShopCarAmount;
	@ViewInject(R.id.tv_shop_car_postage)
	private TextView tvShopCarPostage;
	@ViewInject(R.id.tv_shop_car_pay)
	private TextView tvShopCarPay;
	@ViewInject(R.id.tv_shop_car_delete)
	private View tvShopCarDelete;

	private List<ShopCarEntity> shopCarEntities;
	private ShopCarAdapter shopCarAdapter;

	public static void startShopCarActivity(Context context) {
		Intent intent = new Intent(context, ShopCarActivity.class);
		context.startActivity(intent);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_shop_car);
		ViewInjectUtils.inject(this);
		initView();
		loadData(false);
	}

	private void initView() {
		setLeftTitle(getString(R.string.shop_car));

		rrvShopCarList.setHasFixedSize(true);
		rrvShopCarList.setMode(RefreshRecyclerView.Mode.BOTH);
		rrvShopCarList.setOnRefreshAndLoadMoreListener(new RefreshRecyclerView.OnRefreshAndLoadMoreListener() {
			@Override
			public void onRefresh() {
				loadData(false);
			}

			@Override
			public void onLoadMore() {
				loadData(true);
			}
		});
		rrvShopCarList.setLayoutManager(new LinearLayoutManager(this));
		rrvShopCarList.addItemDecoration(new RecyclerView.ItemDecoration() {
			@Override
			public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
				int headerSize = rrvShopCarList.getHeaderSize();//头部数量
				int pos = parent.getChildLayoutPosition(view) - headerSize;//减去头部后的下标位置

				if (pos < 0) {//头部
					return;
				}

				//item上下偏移量(上下间距)
				outRect.bottom = CommonTools.dp2px(ShopCarActivity.this, 10);
			}
		});

		cbShopCarAll.setOnClickListener(v -> {
			if (shopCarEntities != null) {
				for (ShopCarEntity shopCarEntity : shopCarEntities) {
					shopCarEntity.setChecked(cbShopCarAll.isChecked());
					for (ProductEntity productEntity : shopCarEntity.getProductEntities()) {
						productEntity.setChecked(cbShopCarAll.isChecked());
					}
				}
				updateCount();
				if (shopCarAdapter != null) {
					shopCarAdapter.notifyDataSetChanged();
				}
			}
		});
		tvShopCarPay.setOnClickListener(this);
		tvShopCarDelete.setOnClickListener(this);
	}

	private void loadData(boolean isMore) {
		tvShopCarAmount.setText("合计：0积分");
		shopCarEntities = new ArrayList<>();
		ShopCarEntity shopCarEntity1 = new ShopCarEntity("店铺一");

		List<ProductEntity> productEntities1 = new ArrayList<>();
		productEntities1.add(new ProductEntity("商品一", "http://onau582bt.bkt.clouddn.com/17463805-56bb-4702-b96e-84acb65bd97e", "300", "1", "北京"));
		productEntities1.add(new ProductEntity("商品二", "http://onau582bt.bkt.clouddn.com/a79aef4d-5a51-464e-8762-085f77f14d0d", "500", "1", "上海"));
		shopCarEntity1.setProductEntities(productEntities1);

		ShopCarEntity shopCarEntity2 = new ShopCarEntity("店铺二");

		List<ProductEntity> productEntities2 = new ArrayList<>();
		productEntities2.add(new ProductEntity("商品三", "http://onau582bt.bkt.clouddn.com/dbf631f2-e12d-46b8-a657-434ff2b6dc19", "300", "2", "深圳"));
		shopCarEntity2.setProductEntities(productEntities2);

		ShopCarEntity shopCarEntity3 = new ShopCarEntity("店铺三");

		List<ProductEntity> productEntities3 = new ArrayList<>();
		productEntities3.add(new ProductEntity("商品四", "http://onau582bt.bkt.clouddn.com/835241fa-36e4-4ce2-839e-a0db259084b9", "300", "1", "广州"));
		productEntities3.add(new ProductEntity("商品四", "http://onau582bt.bkt.clouddn.com/68d428a9-afa0-4669-aedf-7e3102011447", "300", "2", "天津"));
		shopCarEntity3.setProductEntities(productEntities3);

		shopCarEntities.add(shopCarEntity1);
		shopCarEntities.add(shopCarEntity2);
		shopCarEntities.add(shopCarEntity3);

		shopCarAdapter = new ShopCarAdapter(shopCarEntities, this);
		rrvShopCarList.setAdapter(shopCarAdapter);
		rrvShopCarList.setCanAddMore(false);
	}

	@Override
	public void success(int requestCode, String data) {
		super.success(requestCode, data);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.tv_shop_car_pay:
				//支付
				break;
			case R.id.tv_shop_car_delete:
				//删除商品
				if (shopCarEntities != null) {
					//删除选中的店铺
					Iterator<ShopCarEntity> iterator = shopCarEntities.iterator();
					while (iterator.hasNext()) {
						ShopCarEntity shopCarEntity = iterator.next();
						if (shopCarEntity.isChecked()) {
							iterator.remove();
							continue;
						}
						//删除店铺下选中的商品
						Iterator<ProductEntity> productIterator = shopCarEntity.getProductEntities().iterator();
						while (productIterator.hasNext()) {
							ProductEntity productEntity = productIterator.next();
							if (productEntity.isChecked()) {
								productIterator.remove();
							}
						}
					}

					if (shopCarEntities.size() == 0) {
						updateCount();
					}
					if (shopCarAdapter != null) {
						shopCarAdapter.notifyDataSetChanged();
					}
				}
				break;
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (CommonConstant.REQUEST_ACT_ONE == requestCode && RESULT_OK == resultCode) {
			String address = data.getStringExtra("condition");
			int parentPosition = data.getIntExtra("parentPosition", -1);
			int position = data.getIntExtra("position", -1);
			//修改完尺码
			if (parentPosition >= 0 && position >= 0) {
				shopCarEntities.get(parentPosition).getProductEntities().get(position).setAddress(address);
				if (shopCarAdapter != null) {
					shopCarAdapter.notifyDataSetChanged();
				}
			}
		}
	}

	/**
	 * 更新总计，根据店铺选中数量控制全选状态
	 */
	public void updateCount() {
		boolean hasEditMode = false;
		int count = 0;
		int integral = 0;
		if (shopCarEntities != null) {
			int checkNum = 0;
			for (ShopCarEntity shopCarEntity : shopCarEntities) {
				if (shopCarEntity.isEditMode()) {
					hasEditMode = true;
				}
				if (shopCarEntity.isChecked()) {
					checkNum++;
				}
				for (ProductEntity productEntity : shopCarEntity.getProductEntities()) {
					if (productEntity.isChecked()) {
						count++;
						integral += (Integer.parseInt(productEntity.getSellingIntegral()) * Integer.parseInt(productEntity.getSellingPrice()));
					}
				}
			}
			if (shopCarEntities.size() == 0) {
				cbShopCarAll.setChecked(false);
			} else {
				cbShopCarAll.setChecked(checkNum == shopCarEntities.size());
			}
		}
		tvShopCarAmount.setText(getString(R.string.total_integral, String.valueOf(integral)));
		tvShopCarPay.setText(getString(R.string.go_settlement, String.valueOf(count)));
		editMode(hasEditMode);
	}

	/**
	 * 编辑模式处理
	 */
	public void editMode(boolean isEdit) {
		if (isEdit) {
			cbShopCarAll.setVisibility(View.INVISIBLE);
			llShopCarTotal.setVisibility(View.INVISIBLE);
			tvShopCarPay.setVisibility(View.GONE);
			tvShopCarDelete.setVisibility(View.VISIBLE);
		} else {
			cbShopCarAll.setVisibility(View.VISIBLE);
			llShopCarTotal.setVisibility(View.VISIBLE);
			tvShopCarPay.setVisibility(View.VISIBLE);
			tvShopCarDelete.setVisibility(View.GONE);
		}
	}
}
