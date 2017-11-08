package cn.antke.bct.person.controller;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.common.network.FProtocol;
import com.common.viewinject.annotation.ViewInject;
import com.facebook.drawee.view.SimpleDraweeView;
import com.google.zxing.BarcodeFormat;

import java.util.IdentityHashMap;

import cn.antke.bct.R;
import cn.antke.bct.base.ToolBarActivity;
import cn.antke.bct.login.utils.UserCenter;
import cn.antke.bct.network.Constants;
import cn.antke.bct.network.Parsers;
import cn.antke.bct.utils.CommonShareUtil;
import cn.antke.bct.utils.ImageUtils;
import cn.antke.bct.utils.QRCodeUtils;
import cn.antke.bct.utils.ViewInjectUtils;

import static cn.antke.bct.common.CommonConstant.REQUEST_NET_THREE;

/**
 * Created by zhaoweiwei on 2017/5/6.
 * 我的二维码
 */

public class MyQrCodeActivity extends ToolBarActivity {

	@ViewInject(R.id.myqrcode_avatar)
	private SimpleDraweeView avatar;
	@ViewInject(R.id.myqrcode_usercode)
	private TextView userCode;
	@ViewInject(R.id.myqrcode_username)
	private TextView userName;
	@ViewInject(R.id.myqrcode_qrcode)
	private SimpleDraweeView qrQrCode;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_person_myqrcode);
		ViewInjectUtils.inject(this);
		setLeftTitle(getString(R.string.personinfo_qrcode));
		setRightTitle(R.drawable.qrcode_share);
		mBtnTitleRight.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				CommonShareUtil.share(MyQrCodeActivity.this,"","","","");
			}
		});

		ImageUtils.setSmallImg(avatar, UserCenter.getHeadPic(this));
		userCode.setText(getString(R.string.register_usernumber_text_add,UserCenter.getUserCode(this)));
		userName.setText(UserCenter.getUserName(this));
		getQrcode();
	}

	private void getQrcode() {
		showProgressDialog();
		requestHttpData(Constants.Urls.URL_POST_GET_QRCODE, REQUEST_NET_THREE, FProtocol.HttpMethod.POST, new IdentityHashMap<>());
	}

	@Override
	public void success(int requestCode, String data) {
		super.success(requestCode, data);
		closeProgressDialog();
		String qrCode = Parsers.getQrcode(data);

		int qrWidth = qrQrCode.getLayoutParams().width;
		int qrHeight = qrQrCode.getLayoutParams().height;
		//二维码
		try{
			QRCodeUtils.setQvImageView(qrQrCode, qrCode, BarcodeFormat.QR_CODE, qrWidth, qrHeight);
		}catch (OutOfMemoryError e){
			System.gc();
			QRCodeUtils.setQvImageView(qrQrCode, qrCode, BarcodeFormat.QR_CODE, qrWidth, qrHeight);
		}
	}
}
