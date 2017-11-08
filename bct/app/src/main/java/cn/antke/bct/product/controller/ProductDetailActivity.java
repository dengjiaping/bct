package cn.antke.bct.product.controller;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;
import com.common.viewinject.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

import cn.antke.bct.R;
import cn.antke.bct.base.BaseActivity;
import cn.antke.bct.common.CommonConstant;
import cn.antke.bct.network.entities.BannerEntity;
import cn.antke.bct.utils.ImageUtils;
import cn.antke.bct.utils.ViewInjectUtils;

/**
 * Created by liuzhichao on 2017/5/17.
 * 商品详情
 */
public class ProductDetailActivity extends BaseActivity implements View.OnClickListener {

	@ViewInject(R.id.iv_product_detail_back)
	private View ivProductDetailBack;
	@ViewInject(R.id.iv_product_detail_car)
	private View ivProductDetailCar;
	@ViewInject(R.id.cb_product_detail_banner)
	private ConvenientBanner cbProductDetailBanner;
	@ViewInject(R.id.tv_product_detail_name)
	private TextView tvProductDetailName;
	@ViewInject(R.id.tv_product_detail_desc)
	private TextView tvProductDetailDesc;
	@ViewInject(R.id.tv_product_detail_price)
	private TextView tvProductDetailPrice;
	@ViewInject(R.id.tv_product_detail_postage)
	private TextView tvProductDetailPostage;
	@ViewInject(R.id.tv_product_detail_num)
	private TextView tvProductDetailNum;
	@ViewInject(R.id.tv_product_detail_city)
	private TextView tvProductDetailCity;
	@ViewInject(R.id.iv_product_store)
	private View ivProductStore;
	@ViewInject(R.id.tv_product_add_car)
	private View tvProductAddCar;
	@ViewInject(R.id.tv_product_buy)
	private View tvProductBuy;

	private String id;

	public static void startProductDetailActivity(Context context, String id) {
		Intent intent = new Intent(context, ProductDetailActivity.class);
		intent.putExtra(CommonConstant.EXTRA_ID, id);
		context.startActivity(intent);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_product_detail);
		ViewInjectUtils.inject(this);
		initView();
		loadData();
	}

	private void initView() {
		cbProductDetailBanner.setPageIndicator(new int[]{R.drawable.dot_dark, R.drawable.dot_light})
				.setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL);

		ivProductDetailBack.setOnClickListener(this);
		ivProductDetailCar.setOnClickListener(this);
		ivProductStore.setOnClickListener(this);
		tvProductAddCar.setOnClickListener(this);
		tvProductBuy.setOnClickListener(this);
	}

	private void loadData() {
		//准备测试数据
		tvProductDetailName.setText("商品名称");
		tvProductDetailDesc.setText("商品的描述商品的描述商品的描述商品的描述商品的描述");
		tvProductDetailPrice.setText("1836积分");
		tvProductDetailPostage.setText("邮费：10");
		tvProductDetailNum.setText("销量1384");
		tvProductDetailCity.setText("北京");

		List<BannerEntity> bannerEntities = new ArrayList<>();
		bannerEntities.add(new BannerEntity("http://image.antke.cn/36652188-34bb-4993-9c0d-84646bfc05dd"));
		bannerEntities.add(new BannerEntity("http://image.antke.cn/2852ab0b-4a74-4ba1-97b9-4258a2700fec"));
		bannerEntities.add(new BannerEntity("http://image.antke.cn/35798f47-d56c-4f94-bea9-bba772a6e4ae"));
		cbProductDetailBanner.setPages(new CBViewHolderCreator<ImageHolder>() {

			@Override
			public ImageHolder createHolder() {
				return new ImageHolder();
			}
		}, bannerEntities);
	}

	@Override
	public void success(int requestCode, String data) {
		super.success(requestCode, data);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.iv_product_detail_back:
				finish();
				break;
			case R.id.iv_product_detail_car:
				ShopCarActivity.startShopCarActivity(this);
				break;
			case R.id.iv_product_store:
				StoreDetailActivity.startStoreDetailActivity(this, id);
				break;
			case R.id.tv_product_add_car:
				break;
			case R.id.tv_product_buy:
				ConfirmOrderActivity.startConfirmOrderActivity(this);
				break;
		}
	}

	private class ImageHolder implements Holder<BannerEntity> {

		private ImageView sdvBannerPic;

		@Override
		public View createView(Context context) {
			View view = LayoutInflater.from(context).inflate(R.layout.item_banner, null);
			sdvBannerPic = (ImageView) view.findViewById(R.id.sdv_banner_pic);
			return view;
		}

		@Override
		public void UpdateUI(Context context, int position, BannerEntity data) {
			ImageUtils.setSmallImg(sdvBannerPic, data.getImgUrl());
		}
	}
}
