package cn.antke.bct;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;

import com.common.utils.StringUtil;
import com.facebook.drawee.backends.pipeline.Fresco;

import java.util.List;
import java.util.Locale;

import cn.antke.bct.common.CommonConstant;
import cn.antke.bct.launcher.service.InitializeService;
import cn.antke.bct.utils.ConfigUtils;
import cn.antke.bct.utils.I18nUtil;


/**
 * Created by liuzhichao on 2016/12/12.
 * application
 */
public class MainApplication extends Application {

	public static final String CHANNEL_KEY = "UMENG_CHANNEL";

	@Override
	public void onCreate() {
		super.onCreate();
		InitializeService.start(this);
		//图片加载初始化
		Fresco.initialize(this);
		//需要在应用主进程中初始化的操作
		if (isMainProcess()) {
			configLanguage(this);
		}
	}

	public static void configLanguage(Context context) {
		Locale locale;
		String preLanguage = ConfigUtils.getPreLanguage(context);
		switch (preLanguage) {
			case CommonConstant.LANGUAGE_SIMPLIFIED_CHINESE:
				locale = new Locale("zh", "CN");
				break;
			case CommonConstant.LANGUAGE_TRADITIONAL_CHINESE:
				locale = new Locale("zh", "HK");
				break;
			case CommonConstant.LANGUAGE_KOREAN:
				locale = new Locale(CommonConstant.LANGUAGE_KOREAN);
				break;
			case CommonConstant.LANGUAGE_JAPANESE:
				locale = new Locale(CommonConstant.LANGUAGE_JAPANESE);
				break;
			case CommonConstant.LANGUAGE_RUSSIAN:
				locale = new Locale(CommonConstant.LANGUAGE_RUSSIAN);
				break;
			case CommonConstant.LANGUAGE_MALAY_MALAYSIA:
				locale = new Locale("ms", "MY");
				break;
			case CommonConstant.LANGUAGE_ENGLISH:
				locale = new Locale(CommonConstant.LANGUAGE_ENGLISH);
				break;
			case "sys":
			default:
				locale = Locale.getDefault();
		}
		I18nUtil.setLanguage(context, locale);
	}

	public boolean isMainProcess() {
		ActivityManager am = ((ActivityManager) getSystemService(Context.ACTIVITY_SERVICE));
		List<ActivityManager.RunningAppProcessInfo> processInfos = am.getRunningAppProcesses();
		String mainProcessName = getPackageName();
		int myPid = android.os.Process.myPid();
		for (ActivityManager.RunningAppProcessInfo info : processInfos) {
			if (info.pid == myPid && mainProcessName.equals(info.processName)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 获取application中指定的meta-data
	 * @return 如果没有获取成功(没有对应值或者异常)，则返回值为空
	 */
	public static String getAppMetaData(Context ctx, String key) {
		String resultData = "android";
		if (ctx == null || StringUtil.isEmpty(key)) {
			return resultData;
		}
		try {
			PackageManager packageManager = ctx.getPackageManager();
			if (packageManager != null) {
				ApplicationInfo applicationInfo = packageManager.getApplicationInfo(ctx.getPackageName(), PackageManager.GET_META_DATA);
				if (applicationInfo != null) {
					if (applicationInfo.metaData != null) {
						resultData = applicationInfo.metaData.getString(key) == null ? "android" : applicationInfo.metaData.getString(key);
					}
				}
			}
		} catch (PackageManager.NameNotFoundException e) {
			e.printStackTrace();
		}
		return resultData;
	}
}
