package cn.antke.bct.product.adapter;

import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.common.view.ListViewForInner;
import com.common.widget.BaseRecycleAdapter;
import com.common.widget.RecyclerViewHolder;

import java.util.List;

import cn.antke.bct.R;
import cn.antke.bct.network.entities.ProductEntity;
import cn.antke.bct.network.entities.ShopCarEntity;
import cn.antke.bct.product.controller.ShopCarActivity;

/**
 * Created by liuzhichao on 2017/5/22.
 * 购物车adapter
 */
public class ShopCarAdapter extends BaseRecycleAdapter<ShopCarEntity> {

	private ShopCarActivity shopCarActivity;

	public ShopCarAdapter(List<ShopCarEntity> datas, ShopCarActivity shopCarActivity) {
		super(datas);
		this.shopCarActivity = shopCarActivity;
	}

	@Override
	public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		return new RecyclerViewHolder(parent, R.layout.item_shop_car);
	}

	@Override
	public void onBindViewHolder(RecyclerViewHolder holder, int position) {
		ShopCarEntity shopCarEntity = getItemData(position);
		List<ProductEntity> productEntities = shopCarEntity.getProductEntities();

		CheckBox cbCarItemStore = holder.getView(R.id.cb_car_item_store);
		cbCarItemStore.setText(shopCarEntity.getStore());

		TextView tvCarItemEdit = holder.getView(R.id.tv_car_item_edit);
		if (shopCarEntity.isEditMode()) {
			tvCarItemEdit.setText(holder.getContext().getString(R.string.finished));
		} else {
			tvCarItemEdit.setText(holder.getContext().getString(R.string.edit));
		}

		for (ProductEntity productEntity : productEntities) {
			productEntity.setEditMode(shopCarEntity.isEditMode());
		}

		tvCarItemEdit.setOnClickListener(v -> {
			//让Activity也进入编辑模式
			shopCarEntity.setEditMode(!shopCarEntity.isEditMode());
			shopCarActivity.editMode(shopCarEntity.isEditMode());
			if (!shopCarEntity.isEditMode()) {
				//完成，退出编辑模式，保存修改后的数据

			}
			notifyDataSetChanged();
		});

		updateSubtotal(holder, shopCarEntity, productEntities);

		cbCarItemStore.setChecked(shopCarEntity.isChecked());

		if (productEntities.size() > 0) {

			ListViewForInner lvfiCarItemProducts = holder.getView(R.id.lvfi_car_item_products);
			CarProductAdapter adapter = new CarProductAdapter(shopCarActivity, productEntities, this, position);
			lvfiCarItemProducts.setAdapter(adapter);

			cbCarItemStore.setOnClickListener(v -> {
				//设置店铺选中状态
				shopCarEntity.setChecked(cbCarItemStore.isChecked());
				//设置店铺下的商品
				for (ProductEntity productEntity : productEntities) {
					productEntity.setChecked(cbCarItemStore.isChecked());
				}
				updateSubtotal(holder, shopCarEntity, productEntities);
				adapter.notifyDataSetChanged();
			});
		}
	}

	/**
	 * 更新小计统计，根据商品选中数量控制店铺的选中状态，更新总计
	 */
	void updateSubtotal(RecyclerViewHolder holder, ShopCarEntity shopCarEntity, List<ProductEntity> productEntities) {
		int subtotal = 0;
		int checkNum = 0;
		for (ProductEntity productEntity : productEntities) {
			if (productEntity.isChecked()) {
				checkNum++;
				subtotal += (Integer.parseInt(productEntity.getSellingPrice()) * Integer.parseInt(productEntity.getSellingIntegral()));
			}
		}
		if (checkNum == productEntities.size()) {
			shopCarEntity.setChecked(true);
		} else {
			shopCarEntity.setChecked(false);
		}
		holder.setText(R.id.tv_car_item_subtotal, holder.getContext().getString(R.string.subtotal_integral, String.valueOf(subtotal)));
		shopCarActivity.updateCount();
	}
}
