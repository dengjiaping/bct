package cn.antke.bct.draw.controller;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;
import com.common.network.FProtocol;
import com.common.utils.ToastUtil;
import com.common.viewinject.annotation.ViewInject;

import java.util.IdentityHashMap;
import java.util.List;

import cn.antke.bct.R;
import cn.antke.bct.base.BaseActivity;
import cn.antke.bct.common.CommonConstant;
import cn.antke.bct.login.controller.LoginActivity;
import cn.antke.bct.login.utils.UserCenter;
import cn.antke.bct.network.Constants;
import cn.antke.bct.network.Parsers;
import cn.antke.bct.network.entities.DrawEntity;
import cn.antke.bct.network.entities.Entity;
import cn.antke.bct.utils.ImageUtils;
import cn.antke.bct.utils.ViewInjectUtils;

/**
 * Created by liuzhichao on 2017/5/16.
 * 抽奖详情
 */
public class DrawDetailActivity extends BaseActivity implements View.OnClickListener {

	@ViewInject(R.id.cb_draw_detail_banner)
	private ConvenientBanner cbDrawDetailBanner;
	@ViewInject(R.id.iv_draw_detail_back)
	private View ivDrawDetailBack;
	@ViewInject(R.id.tv_draw_detail_name)
	private TextView tvDrawDetailName;
	@ViewInject(R.id.tv_draw_detail_price)
	private TextView tvDrawDetailPrice;
	@ViewInject(R.id.tv_draw_detail_num)
	private TextView tvDrawDetailNum;
	@ViewInject(R.id.tv_draw_detail_all_num)
	private TextView tvDrawDetailAllNum;
	@ViewInject(R.id.wv_draw_detail_content)
	private WebView wvDrawDetailContent;
	@ViewInject(R.id.tv_draw_detail_start)
	private View tvDrawDetailStart;

	private String id;
	private DrawEntity draw;

	public static void startDrawDetailActivity(Context context, String id) {
		Intent intent = new Intent(context, DrawDetailActivity.class);
		intent.putExtra(CommonConstant.EXTRA_ID, id);
		context.startActivity(intent);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_draw_detail);
		ViewInjectUtils.inject(this);
		initView();
		loadData();
	}

	private void initView() {
		id = getIntent().getStringExtra(CommonConstant.EXTRA_ID);

		cbDrawDetailBanner.setPageIndicator(new int[]{R.drawable.dot_dark, R.drawable.dot_light})
				.setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL);

		ivDrawDetailBack.setOnClickListener(this);
		tvDrawDetailStart.setOnClickListener(this);
	}

	private void loadData() {
		IdentityHashMap<String, String> params = new IdentityHashMap<>();
		params.put("prizeId", id);
		requestHttpData(Constants.Urls.URL_POST_DRAW_DETAIL, CommonConstant.REQUEST_NET_ONE, FProtocol.HttpMethod.POST, params);
	}

	@Override
	public void success(int requestCode, String data) {
		closeProgressDialog();
		Entity result = Parsers.getResult(data);
		if (CommonConstant.REQUEST_NET_SUCCESS.equals(result.getResultCode())) {
			switch (requestCode) {
				case CommonConstant.REQUEST_NET_ONE:
					draw = Parsers.getDraw(data);
					if (draw != null) {
						DrawEntity drawEntity = draw.getDrawEntity();
						tvDrawDetailName.setText(drawEntity.getName());
						tvDrawDetailPrice.setText(getString(R.string.product_sell_integral, drawEntity.getPrice()));
						tvDrawDetailNum.setText(getString(R.string.draw_people_num, drawEntity.getNum()));
						tvDrawDetailAllNum.setText(getString(R.string.draw_people_all_num, drawEntity.getAllNum()));

						wvDrawDetailContent.loadUrl(draw.getContentUrl());

						List<DrawEntity> picList = draw.getPicList();
						cbDrawDetailBanner.setPages(new CBViewHolderCreator<ImageHolder>() {

							@Override
							public ImageHolder createHolder() {
								return new ImageHolder();
							}
						}, picList);
					}
					break;
				case CommonConstant.REQUEST_NET_TWO:
					break;
			}
		} else {
			ToastUtil.shortShow(this, result.getResultMsg());
		}
	}

	@Override
	public void mistake(int requestCode, FProtocol.NetDataProtocol.ResponseStatus status, String errorMessage) {
		super.mistake(requestCode, status, errorMessage);
		closeProgressDialog();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.iv_draw_detail_back:
				finish();
				break;
			case R.id.tv_draw_detail_start:
				if (UserCenter.isLogin(this)) {
					showProgressDialog();
					IdentityHashMap<String, String> params = new IdentityHashMap<>();
					params.put("prizeId", id);
					requestHttpData(Constants.Urls.URL_POST_DRAW_JOIN, CommonConstant.REQUEST_NET_TWO, FProtocol.HttpMethod.POST, params);
				} else {
					startActivity(new Intent(this, LoginActivity.class));
				}
				break;
		}
	}

	private class ImageHolder implements Holder<DrawEntity> {

		private ImageView sdvBannerPic;

		@Override
		public View createView(Context context) {
			View view = LayoutInflater.from(context).inflate(R.layout.item_banner, null);
			sdvBannerPic = (ImageView) view.findViewById(R.id.sdv_banner_pic);
			return view;
		}

		@Override
		public void UpdateUI(Context context, int position, DrawEntity data) {
			ImageUtils.setSmallImg(sdvBannerPic, data.getPicUrl());
		}
	}
}
