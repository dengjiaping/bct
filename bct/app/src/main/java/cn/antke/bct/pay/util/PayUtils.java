package cn.antke.bct.pay.util;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Message;

import com.alipay.sdk.app.PayTask;
import com.common.utils.EnCryptionUtils;
import com.common.utils.StringUtil;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;

import cn.antke.bct.common.CommonConfig;
import cn.antke.bct.pay.controller.PayResultActivity;

/**
 * Created by liuzhichao on 2017/3/16.
 * 支付工具类
 */
public final class PayUtils {

	/**
	 * @param prepayId 微信支付凭证，调起微信支付必要参数
	 */
	public static void startWXPay(Context context, String prepayId) {
		if (context == null || StringUtil.isEmpty(prepayId)) {
			return;
		}
		//注册ID
		IWXAPI wxapi = WXAPIFactory.createWXAPI(context, CommonConfig.WECHAT_APP_ID);

		PayReq request = new PayReq();
		request.appId = CommonConfig.WECHAT_APP_ID;
		request.partnerId = CommonConfig.WECHAT_PARTNER_ID;
		//只有这个参数是通过订单获取的
		request.prepayId = prepayId;
		//随机数
		request.nonceStr = EnCryptionUtils.MD5(String.valueOf(new Random().nextInt(10000)));
		request.packageValue = CommonConfig.WECHAT_PACKAGE_VALUE;
		request.timeStamp = String.valueOf(System.currentTimeMillis() / 1000);

		LinkedHashMap<String, String> params = new LinkedHashMap<>();
		params.put("appid", request.appId);
		params.put("noncestr", request.nonceStr);
		params.put("package", request.packageValue);
		params.put("partnerid", request.partnerId);
		params.put("prepayid", request.prepayId);
		params.put("timestamp", request.timeStamp);

		//签名
		request.sign = genPackageSign(params);
		wxapi.sendReq(request);
	}

	/**
	 * 微信支付生成签名
	 */
	private static String genPackageSign(LinkedHashMap<String, String> params) {
		StringBuilder sb = new StringBuilder();
		for (Map.Entry<String, String> entry: params.entrySet()) {
			sb.append(entry.getKey());
			sb.append('=');
			sb.append(entry.getValue());
			sb.append('&');
		}
		//一定要注意这个地方拼的是API密钥不是APPID
		sb.append("key=" + CommonConfig.WECHAT_API_KEY);
		return EnCryptionUtils.MD5(sb.toString()).toUpperCase();
	}

	/**
	 * @param mHandler 支付成功回调消息
	 * @param orderInfo 订单信息
	 */
	public static void startAliPay(Activity activity, Handler mHandler, String orderInfo) {
		if (activity == null || mHandler == null || StringUtil.isEmpty(orderInfo)) {
			return;
		}
		Runnable payRunnable = () -> {
			PayTask alipay = new PayTask(activity);
			Map<String, String> result1 = alipay.payV2(orderInfo, true);

			Message msg = new Message();
			msg.what = 10;
			msg.obj = result1;
			mHandler.sendMessage(msg);
		};
		// 必须异步调用
		Thread payThread = new Thread(payRunnable);
		payThread.start();
	}

	/**
	 * @param context 支付成功去往结果页
	 */
	public static void gotoPaySuccess(Context context) {
		PayResultActivity.startPayResultActivity(context);
	}

	/**
	 * 支付宝支付业务：入参app_id
	 */
	public static final String APPID = "2017052507344513";
	/** 商户私钥，pkcs8格式 */
	/** 如下私钥，RSA2_PRIVATE 或者 RSA_PRIVATE 只需要填入一个 */
	/** 如果商户两个都设置了，优先使用 RSA2_PRIVATE */
	/** RSA2_PRIVATE 可以保证商户交易在更加安全的环境下进行，建议使用 RSA2_PRIVATE */
	/** 获取 RSA2_PRIVATE，建议使用支付宝提供的公私钥生成工具生成， */
	/**
	 * 工具地址：https://doc.open.alipay.com/docs/doc.htm?treeId=291&articleId=106097&docType=1
	 */
	public static final String RSA2_PRIVATE = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCo934/E+/P9+" +
			"7vpDLbmxmfbFBNg3uUpSWcPnSSEfFyci40JkWK7IHdZ8fsHQ65MgyFnSmnjOBSDwvz+GInYp/fcxyihhM/cNxHBFQAXU5" +
			"6osxnartqDnSuEp40LmQ4q/N3pUonhgK6pXDvJuuSNSFVWEydRS5GUooq4OnBKUKp1HbkOMx7k1VUExZq0rJ0hx1vB5" +
			"ohGOfSe6K37FCW24HoC0Zmtjk72yu+xcwR4xjAYPfCF1AqdqANCO9UX2xzu1OlaONhBJ/v4fdZUa1nYzy9V0lJBOnS077j" +
			"5HPLKaRww/5Qku+6w+3pB5lGk6Hic/k6TuDhlMFux/L7qKPlxTPnAgMBAAECggEACR8jT6Bx59ZIreM9Y6Gh5ntgcm7Qsx" +
			"ufRioQ/Pm/ASCs4d/XDULrV9qMbnC7JMBQ8L2lTAhM6+EoEgmssteeLd/3AccfLuOW4LaZGlPvMqurQUH/0B/rqKIUrFh8zJp" +
			"r0F+5+xw+9XiM+jwtp3z6ybK0oCDl+KHoF5yAwg7KLITXK/eXhjOidAVGEOb2TYxBs8UR7iM61B2Lm6/EMrOoQFoIDZDXn" +
			"SgGoF8Zp0KBYyXDINsd5tzpHkrn2Ee2uFEHt23RWnrmpyQoztQj7C7/Noz35H8pEQ0FIlh8YzcyOul0CtZFWh620wv6WH66" +
			"4iYpvEET2FYIEPlYQVvvwcMuwQKBgQDr4qLn8BZRRBMal1AmHjVmpPb6c7437G/5+dKA2fEOtRftwJITkrdG0FKdB5x701" +
			"skFYX/eD32B1N43lNzUrCIuZemKQ5SKPbAc0rInqq8qH4Jk6u+UiPe0T6QilutX60QrDagJLpNNUbsGdI+3gcSwhZWZWC93" +
			"ELNE9VP8HtJ9wKBgQC3YAdiwDmtHTBP5IMKM7EimYeZzRuFi21ciJoJcfbTlAiXDNLgfL0ppGA5bvxT3sHvjyw/6SdxyTdoD" +
			"G/mBkm9uAsRfkZkyJi7GJvAJdmvAG5F0zpgmP4nB2NEFrkFh8dEKQqIk0iCnmvq7wd5PV4AxwgN7asVu85t7Ccuf7ZpkQKB" +
			"gHd+6Gf3CFdC1SqzXLbytrq49yUJXWOXM2K/XBRKVAPPWb/nvhiMSqxk/HTwViWkfJdbD/bLQxhPvcmNV1kkmqmo21ohkk" +
			"+s0NPNlCTtvDC2aqMPXAGeOu21sqwmayr5PQMlOscF1Bso0jTpH8kZURv/lrFNyiRkYLLjSTyl5s8hAoGAHAZxGoaGSg7L82" +
			"nZHRYJXGBaeQb/zckhTaMS556jOnLFXVB4ahamUlbs3do4dhj1Ssd2282mWZ2G+j/sWhtb6cLW7jV+TKjbrrx0EUm3mydVsx" +
			"Y1Nc4sCYr3gNB4NoAp1CDxmy3nRw8vLxiY686VL0k70eUg4JFzWxlcY57hIgECgYEA17fN4AgMPNinS05fkDjeHsZ4akeyQ" +
			"xH7a4x1N3xtawlxqQdmRRsAZXL+VrrOHru2RhSl+CqYogKBBHAjRep6sVRgVFYUuLHSF+nKlnCMWXd1IKzDOyvE8BoE0S" +
			"9KTGw5srUjDr1QlEbDgseHhfJEeT65ZxVbYza1goeTxQkmfAs=";

	/*public static void payV2(Activity activity, Handler handler) {
		*//**
		 * 这里只是为了方便直接向商户展示支付宝的整个支付流程；所以Demo中加签过程直接放在客户端完成；
		 * 真实App里，privateKey等数据严禁放在客户端，加签过程务必要放在服务端完成；
		 * 防止商户私密数据泄露，造成不必要的资金损失，及面临各种安全风险；
		 * orderInfo的获取必须来自服务端；
		 *//*
		Map<String, String> params = OrderInfoUtil2_0.buildOrderParamMap(APPID);
		String orderParam = OrderInfoUtil2_0.buildOrderParam(params);

		String sign = OrderInfoUtil2_0.getSign(params, RSA2_PRIVATE);
		final String orderInfo = orderParam + "&" + sign;

		Runnable payRunnable = () -> {
			PayTask alipay = new PayTask(activity);
			Map<String, String> result = alipay.payV2(orderInfo, true);
			Log.e("msp", result.toString());

			Message msg = new Message();
			msg.what = 1;
			msg.obj = result;
			handler.sendMessage(msg);
		};

		Thread payThread = new Thread(payRunnable);
		payThread.start();
	}*/
}
