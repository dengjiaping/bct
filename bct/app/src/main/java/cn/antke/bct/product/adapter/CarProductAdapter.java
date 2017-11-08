package cn.antke.bct.product.adapter;

import android.app.Activity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.common.adapter.BaseAdapterNew;
import com.common.adapter.ViewHolder;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import cn.antke.bct.R;
import cn.antke.bct.network.entities.ProductEntity;
import cn.antke.bct.product.controller.ConditionActivity;
import cn.antke.bct.utils.ImageUtils;
import cn.antke.bct.widget.AddMinuLayout;

/**
 * Created by liuzhichao on 2017/5/22.
 * 购物车商品
 */
public class CarProductAdapter extends BaseAdapterNew<ProductEntity> {

	private ShopCarAdapter shopCarAdapter;
	private Activity activity;
	private int parentPosition;

	public CarProductAdapter(Activity activity, List<ProductEntity> mDatas, ShopCarAdapter shopCarAdapter, int parentPosition) {
		super(activity, mDatas);
		this.activity = activity;
		this.shopCarAdapter = shopCarAdapter;
		this.parentPosition = parentPosition;
	}

	@Override
	protected int getResourceId(int resId) {
		return R.layout.item_car_product;
	}

	@Override
	protected void setViewData(View convertView, int position) {
		ProductEntity productEntity = getItem(position);
		if (productEntity != null) {
			SimpleDraweeView sdvCarProductPic = ViewHolder.get(convertView, R.id.sdv_car_product_pic);
			ImageUtils.setSmallImg(sdvCarProductPic, productEntity.getPicUrl());

			CheckBox cbCarProductCheck = ViewHolder.get(convertView, R.id.cb_car_product_check);
			View rlCarProductInfo = ViewHolder.get(convertView, R.id.rl_car_product_info);
			View rlCarProductEdit = ViewHolder.get(convertView, R.id.rl_car_product_edit);
			TextView tvCarProductName = ViewHolder.get(convertView, R.id.tv_car_product_name);
			TextView tvCarProductDesc = ViewHolder.get(convertView, R.id.tv_car_product_desc);
			TextView tvCarProductCondition = ViewHolder.get(convertView, R.id.tv_car_product_condition);
			TextView tvCarProductNum = ViewHolder.get(convertView, R.id.tv_car_product_num);
			AddMinuLayout amlCarProductNumChange = ViewHolder.get(convertView, R.id.aml_car_product_num_change);
			TextView tvCarProductChange = ViewHolder.get(convertView, R.id.tv_car_product_change);
			View ivCarProductEdit = ViewHolder.get(convertView, R.id.iv_car_product_edit);

			if (productEntity.isEditMode()) {
				rlCarProductInfo.setVisibility(View.GONE);
				rlCarProductEdit.setVisibility(View.VISIBLE);
			} else {
				rlCarProductInfo.setVisibility(View.VISIBLE);
				rlCarProductEdit.setVisibility(View.GONE);
			}

			cbCarProductCheck.setChecked(productEntity.isChecked());

			tvCarProductName.setText(productEntity.getGoodsName());
			tvCarProductDesc.setText(productEntity.getSellingPrice());
			tvCarProductCondition.setText("规格：" + productEntity.getAddress());
			tvCarProductNum.setText("x" + productEntity.getSellingIntegral());
			cbCarProductCheck.setOnClickListener(v -> {
				productEntity.setChecked(cbCarProductCheck.isChecked());
				shopCarAdapter.notifyDataSetChanged();
			});

			amlCarProductNumChange.setLimitNum(1, 5);
			amlCarProductNumChange.setResult(productEntity.getSellingIntegral());
			tvCarProductChange.setText("规格：" + productEntity.getAddress());
			ivCarProductEdit.setOnClickListener(v -> {
				ConditionActivity.startConditionActivity(activity, parentPosition, position);
			});
		}
	}
}
