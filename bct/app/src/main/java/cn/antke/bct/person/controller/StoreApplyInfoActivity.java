package cn.antke.bct.person.controller;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.common.viewinject.annotation.ViewInject;
import com.facebook.drawee.view.SimpleDraweeView;

import cn.antke.bct.R;
import cn.antke.bct.base.ToolBarActivity;
import cn.antke.bct.utils.ViewInjectUtils;

/**
 * Created by zhaoweiwei on 2017/5/11.
 * 店铺申请信息
 */

public class StoreApplyInfoActivity extends ToolBarActivity implements View.OnClickListener {
	@ViewInject(R.id.store_applyinfo_name)
	private EditText storeNameEt;
	@ViewInject(R.id.store_applyinfo_category_ll)
	private LinearLayout storeCategoryLl;
	@ViewInject(R.id.store_applyinfo_category)
	private TextView storeCategory;
	@ViewInject(R.id.store_applyinfo_phone)
	private EditText storePhoneEt;
	@ViewInject(R.id.store_applyinfo_area)
	private EditText storeAreaEt;
	@ViewInject(R.id.store_applyinfo_address)
	private EditText storeAddressEt;
	@ViewInject(R.id.store_applyinfo_business_license)
	private SimpleDraweeView businessLicense;
	@ViewInject(R.id.store_applyinfo_organization_code)
	private SimpleDraweeView organizaitonCode;
	@ViewInject(R.id.store_applyinfo_account_permit)
	private SimpleDraweeView accountPermit;
	@ViewInject(R.id.store_applyinfo_tax_registration)
	private SimpleDraweeView taxRegistration;
	@ViewInject(R.id.store_applyinfo_idcard_front)
	private SimpleDraweeView idcardFront;
	@ViewInject(R.id.store_applyinfo_idcard_back)
	private SimpleDraweeView idcardBack;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_store_apply_info);
		ViewInjectUtils.inject(this);
		storeCategoryLl.setOnClickListener(this);
		businessLicense.setOnClickListener(this);
		organizaitonCode.setOnClickListener(this);
		accountPermit.setOnClickListener(this);
		taxRegistration.setOnClickListener(this);
		idcardFront.setOnClickListener(this);
		idcardBack.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.store_applyinfo_category_ll://选择分类
				break;
			case R.id.store_applyinfo_business_license://营业执照
				break;
			case R.id.store_applyinfo_organization_code://组织代码证
				break;
			case R.id.store_applyinfo_account_permit://开户许可证
				break;
			case R.id.store_applyinfo_tax_registration://税务登记证
				break;
			case R.id.store_applyinfo_idcard_front://法人身份证正面
				break;
			case R.id.store_applyinfo_idcard_back://法人身份证反面
				break;

		}
	}
}
