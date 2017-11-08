package cn.antke.bct.home.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.common.utils.StringUtil;
import com.common.widget.BaseRecycleAdapter;
import com.common.widget.RecyclerViewHolder;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import cn.antke.bct.R;
import cn.antke.bct.network.entities.ProductEntity;
import cn.antke.bct.utils.ImageUtils;

/**
 * Created by liuzhichao on 2017/5/4.
 * 商品
 */
public class ProductsAdapter extends BaseRecycleAdapter<ProductEntity> {
	private Context context;

	public ProductsAdapter(Context context, List<ProductEntity> datas) {
		super(datas);
		this.context = context;
	}

	@Override
	public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		return new RecyclerViewHolder(parent, R.layout.item_product);
	}

	@Override
	public void onBindViewHolder(RecyclerViewHolder holder, int position) {
		ProductEntity productEntity = getItemData(position);
		SimpleDraweeView productPic = holder.getView(R.id.sdv_item_product_pic);
		TextView goodsName = holder.getView(R.id.tv_item_product_name);
		TextView integral = holder.getView(R.id.tv_item_product_integral);
		TextView price = holder.getView(R.id.tv_item_product_price);
		RelativeLayout storeLayout = holder.getView(R.id.rl_item_store);
		TextView storeName = holder.getView(R.id.tv_item_store_name);
		TextView address = holder.getView(R.id.tv_item_store_address);

		ImageUtils.setSmallImg(productPic, productEntity.getPicUrl());
		goodsName.setText(productEntity.getGoodsName());

		if (!StringUtil.isEmpty(productEntity.getSellingIntegral())) {
			String goodsIntegral = context.getString(R.string.product_sell_integral, productEntity.getSellingIntegral());
			SpannableStringBuilder ssb = new SpannableStringBuilder(goodsIntegral);
			ssb.setSpan(new ForegroundColorSpan(ContextCompat.getColor(context, R.color.price_color)), goodsIntegral.length() - 2, goodsIntegral.length(), Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
			integral.setText(ssb);
		}

		if (StringUtil.isEmpty(productEntity.getSellingPrice())) {
			price.setVisibility(View.GONE);
		} else {
			price.setVisibility(View.VISIBLE);
			String goodsPrice = context.getString(R.string.product_sell_price, productEntity.getSellingPrice());
			SpannableStringBuilder ssb1 = new SpannableStringBuilder(goodsPrice);
			ssb1.setSpan(new ForegroundColorSpan(ContextCompat.getColor(context, R.color.price_color)), goodsPrice.length() - 1, goodsPrice.length(), Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
			price.setText(ssb1);
		}

		if (StringUtil.isEmpty(productEntity.getStoreName()) && StringUtil.isEmpty(productEntity.getAddress())) {
			storeLayout.setVisibility(View.GONE);
		} else {
			storeLayout.setVisibility(View.VISIBLE);
			storeName.setText(productEntity.getStoreName());
			address.setText(productEntity.getAddress());
		}
	}
}
