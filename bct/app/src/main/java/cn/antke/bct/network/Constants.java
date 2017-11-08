package cn.antke.bct.network;

/**
 * Created by jacktian on 15/8/19.
 * 接口地址
 */
public class Constants {

	public static class Urls {
		// 测试环境域名
//		public static String URL_BASE_DOMAIN = "http://101.200.133.167:8091";
		public static String URL_BASE_DOMAIN = "http://192.168.1.83:8081";
		// 正式环境域名
//		private static String URL_BASE_DOMAIN = "https://mobilecardc.wjika.com";

		//首页
		public static String URL_POST_HOME = URL_BASE_DOMAIN + "/home/show";
		//地区
		public static String URL_POST_AREA = URL_BASE_DOMAIN + "/site/list";
		//抽奖列表
		public static String URL_POST_DRAW_LIST = URL_BASE_DOMAIN + "/prize/list";
		//抽奖详情
		public static String URL_POST_DRAW_DETAIL = URL_BASE_DOMAIN + "/prize/detail";
		//抽奖
		public static String URL_POST_DRAW_JOIN = URL_BASE_DOMAIN + "/prize/prize";
		//中奖查询
		public static String URL_POST_DRAW_QUERY = URL_BASE_DOMAIN + "/prize/winningList";
		//板块列表
		public static String URL_POST_PLATE_LIST = URL_BASE_DOMAIN + "/home/categoryChannelList";
		//首页-分类-商品列表
		public static String URL_POST_PRODUCT_LIST = URL_BASE_DOMAIN + "/home/goodsChannelList";
		//板块-商品列表
		public static String URL_POST_PLATE_PRODUCT_LIST = URL_BASE_DOMAIN + "/goodsList";
		//店铺详情
		public static String URL_POST_STORE_DETAIL = URL_BASE_DOMAIN + "/storeInfo";
		//注册
		public static String URL_POST_USER_REGISTER = URL_BASE_DOMAIN + "/user/saveUser";
		//登录
		public static String URL_POST_USER_LOGIN = URL_BASE_DOMAIN + "/login";
		//获取用户编号
		public static String URL_POST_USER_USER_CODE = URL_BASE_DOMAIN + "/usercode";
		//获取推荐人信息
		public static String URL_POST_USER_RECOMMEND_CODE = URL_BASE_DOMAIN + "/recommender";
		//获取验证码
		public static String URL_POST_SMS_CODE = URL_BASE_DOMAIN + "/identifyCode";
		//绑定用户信息
		public static String URL_POST_BIND_INFO = URL_BASE_DOMAIN + "/user/binding";
		//忘记密码重置
		public static String URL_POST_RESET_PASSWORD = URL_BASE_DOMAIN + "/user/resetpassword";
		//分类
		public static String URL_POST_CATEGORY = URL_BASE_DOMAIN + "/categrayList";
		//个人中心
		public static String URL_POST_PERSON_CENTER = URL_BASE_DOMAIN + "/user/center";
		//收货地址列表
		public static String URL_POST_ADDRESS_LIST = URL_BASE_DOMAIN + "/receivingList";
		//添加更新地址
		public static String URL_POST_ADD_EDIT_ADDRESS = URL_BASE_DOMAIN + "/userreceiving/saveorupdate";
		//设置默认地址
		public static String URL_POST_ADDRESS_SETDEFAULT = URL_BASE_DOMAIN + "/userreceiving/setdefault";
		//提交个人信息
		public static String URL_POST_PERSONINFO_COMMIT = URL_BASE_DOMAIN + "/setUserCenter";
		//获取个人二维码
		public static String URL_POST_GET_QRCODE = URL_BASE_DOMAIN + "/user/getqrcode";
		//设置积分交易密码
		public static String URL_POST_SET_PASSWORD = URL_BASE_DOMAIN + "/user/setintegralpassword";
		//订单列表
		public static String URL_POST_ORDER_LIST = URL_BASE_DOMAIN + "/orderinfo/orderlist";
		//退款申请
		public static String URL_POST_ORDER_CHANGE = URL_BASE_DOMAIN + "/orderinfo/orderchange";
	}
}
