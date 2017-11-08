package cn.antke.bct.common;

/**
 * Created by liuzhichao on 2017/5/5.
 * 公共常量
 */
public interface CommonConstant {

	String EXTRA_FROM = "extra_from";
	String EXTRA_ID = "extra_id";
	String EXTRA_URL = "extra_url";
	String EXTRA_PIC_URL = "extra_pic_url";
	String EXTRA_TITLE = "extra_title";
	String EXTRA_TYPE = "extra_type";
	String EXTRA_ENTITY = "extra_entity";
	String REQUEST_NET_SUCCESS = "0";//网络请求成功
	String SEARCH_PRE_KEY_HISTORY = "search_pre_key_history";//搜索历史记录

	String PAGENUM = "pageNum";
	String PAGESIZE = "pageSize";
	String PAGE_SIZE_10 = "10";
	String PAGE_SIZE_20 = "20";

	//语言
	String LANGUAGE_SIMPLIFIED_CHINESE = "zh_CN";
	String LANGUAGE_TRADITIONAL_CHINESE = "zh_HK";
	String LANGUAGE_KOREAN = "ko";
	String LANGUAGE_JAPANESE = "ja";
	String LANGUAGE_RUSSIAN = "ru";
	String LANGUAGE_MALAY_MALAYSIA = "ms_MY";
	String LANGUAGE_ENGLISH = "en";

	//商品列表排序
	String ORDER_FILED_VOLUME = "sale_num";
	String ORDER_FILED_PRICE = "selling_price";
	String ORDER_TYPE_DESC = "desc";
	String ORDER_TYPE_ASC = "asc";

	//请求网络，请求码，必须要情况下可以追加自定义名称
	int REQUEST_NET_ONE = 1;
	int REQUEST_NET_TWO = 2;
	int REQUEST_NET_THREE = 3;
	int REQUEST_NET_FOUR = 4;
	int REQUEST_NET_FIVE = 5;
	int REQUEST_NET_SIX = 6;
	int REQUEST_NET_SEVEN = 7;
	int REQUEST_NET_EIGHT = 8;
	int REQUEST_NET_NINE = 9;
	int REQUEST_NET_TEN = 10;

	//使用startActivityForResult时的请求码
	int REQUEST_ACT_ONE = 1;
	int REQUEST_ACT_TWO = 2;
	int REQUEST_ACT_THREE = 3;
	int REQUEST_ACT_FOUR = 4;
	int REQUEST_ACT_FIVE = 5;

	//商品列表入口
	int FROM_HOME_PRODUCT = 1;
	int FROM_PLATE_PRODUCT = 2;

	//权限配置时
	int REQUEST_CAMERA_PERMISSION_CODE = 1;//相机
	int REQUEST_STORAGE_PERMISSION_CODE = 2;//内存

	//from
	int FROM_REGISTER = 1;
	int FROM_PERSON = 2;

	//上传图片地址
	String FRONT_PATH = "/sdcard/front.jpg";
	String BACK_PATH = "/sdcard/back.jpg";
	String AVATAR_PATH = "/sdcard/back.jpg";

	//编辑地址类型
	int TYPE_ADD = 1;//新增
	int TYPE_EDIT = 2;//编辑

	//订单状态：1，待支付；2，已支付；3，已发货； 4.已完成；5、退款
	int ORDERSTATE_ALL = 0;//全部
	int ORDERSTATE_PAYING = 1;//待支付
	int ORDERSTATE_DELIVING = 2;//待发货
	int ORDERSTATE_DELIVED = 3;//待收货
	int ORDERSTATE_FINISHED = 4;//完成
	int ORDERSTATE_REFUND = 10;//退款

	//积分类型
	int TYPE_0 = 0;
	int TYPE_1 = 1;
	int TYPE_2 = 2;
	int TYPE_3 = 3;
	int TYPE_4 = 4;
	int TYPE_5 = 5;
}
