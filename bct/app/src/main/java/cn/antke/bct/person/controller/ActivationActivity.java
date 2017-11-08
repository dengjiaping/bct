package cn.antke.bct.person.controller;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cn.antke.bct.R;
import cn.antke.bct.base.ToolBarActivity;
import cn.antke.bct.network.entities.ActivationEntity;
import cn.antke.bct.person.adapter.ActivationAdapter;
import cn.antke.bct.utils.DialogUtils;

/**
 * Created by zhaoweiwei on 2017/5/18.
 * 消费服务中心激活
 */

public class ActivationActivity extends ToolBarActivity {

	private List<ActivationEntity> selectedEntity = new ArrayList<>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_consumer_service_activation);
		setLeftTitle(getString(R.string.activation));
		ListView activationList = (ListView) findViewById(R.id.activation_list);
		TextView activate = (TextView) findViewById(R.id.activation_activate);
		List<ActivationEntity> entities = new ArrayList<>();
		entities.add(new ActivationEntity("贾晓宇", false));
		entities.add(new ActivationEntity("贾晓宇1", false));
		entities.add(new ActivationEntity("贾晓宇2", false));
		entities.add(new ActivationEntity("贾晓宇3", false));
		entities.add(new ActivationEntity("贾晓宇4", false));
		entities.add(new ActivationEntity("贾晓宇5", false));
		entities.add(new ActivationEntity("贾晓宇6", false));
		ActivationAdapter adapter = new ActivationAdapter(this, entities);
		activationList.setAdapter(adapter);

		activate.setOnClickListener(v -> {
			for (ActivationEntity entity : entities) {
				if (entity.isSelected()) {
					selectedEntity.add(entity);
				}
			}
//			String goodsIntegral = context.getString(R.string.product_sell_integral, productEntity.getSellingIntegral());
			SpannableStringBuilder ssb = new SpannableStringBuilder("lsdjfk");
			ssb.setSpan(new ForegroundColorSpan(ContextCompat.getColor(this, R.color.price_color)),  2, 3, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);

			DialogUtils.showTwoBtnDialog(this, "激活信息", "", ssb, new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					DialogUtils.closeDialog();
				}
			}, new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					DialogUtils.closeDialog();
				}
			});
		});
	}
}
