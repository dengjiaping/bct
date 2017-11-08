package cn.antke.bct.network;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import cn.antke.bct.network.entities.AddressPageEntity;
import cn.antke.bct.network.entities.AreaEntity;
import cn.antke.bct.network.entities.CategoryMainEntity;
import cn.antke.bct.network.entities.DrawEntity;
import cn.antke.bct.network.entities.DrawQueryEntity;
import cn.antke.bct.network.entities.Entity;
import cn.antke.bct.network.entities.HomeEntity;
import cn.antke.bct.network.entities.OrderListPageEntity;
import cn.antke.bct.network.entities.PagesEntity;
import cn.antke.bct.network.entities.PersonCenterEntity;
import cn.antke.bct.network.entities.PlateDetailEntity;
import cn.antke.bct.network.entities.ProductEntity;
import cn.antke.bct.network.entities.RecommenderEntity;
import cn.antke.bct.network.entities.StoreDetailEntity;
import cn.antke.bct.network.entities.UserEntity;
import cn.antke.bct.network.json.GsonObjectDeserializer;

/**
 * Created by jacktian on 15/8/30.
 * json解析
 */
public class Parsers {

	private static Gson gson = GsonObjectDeserializer.produceGson();

	/**
	 * @return 所有请求的公共部分，业务层的返回码和返回提示
	 */
	public static Entity getResult(String data) {		Entity result = gson.fromJson(data, new TypeToken<Entity>() {
		}.getType());
		if (result == null) {
			result = new Entity();
		}
		return result;
	}

	/**
	 * @return 首页
	 */
	public static HomeEntity getHomeEntity(String data) {
		HomeEntity homeEntity = null;
		try {
			JSONObject jsonObject = new JSONObject(data);
			homeEntity = gson.fromJson(jsonObject.optString("val"), new TypeToken<HomeEntity>(){}.getType());
			if (homeEntity != null) {
				homeEntity.setResultCode(jsonObject.optString("rspCode"));
				homeEntity.setResultMsg(jsonObject.optString("rspMsg"));
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return homeEntity;
	}

	/**
	 * @return 抽奖列表分页数据
	 */
	public static PagesEntity<DrawEntity> getDrawPage(String data) {
		PagesEntity<DrawEntity> drawPages = new PagesEntity<>();
		try {
			JSONObject jsonObject = new JSONObject(data);
			drawPages.setResultCode(jsonObject.optString("rspCode"));
			drawPages.setResultMsg(jsonObject.optString("rspMsg"));
			jsonObject = new JSONObject(jsonObject.optString("val"));
			List<DrawEntity> drawEntities = gson.fromJson(jsonObject.optString("prize"), new TypeToken<List<DrawEntity>>(){}.getType());
			drawPages.setDatas(drawEntities);
			drawPages.setTotalPage(jsonObject.optInt("totalPage"));
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return drawPages;
	}

	/**
	 * @return 抽奖详情
	 */
	public static DrawEntity getDraw(String data) {
		DrawEntity drawEntity = null;
		try {
			JSONObject jsonObject = new JSONObject(data);
			drawEntity = gson.fromJson(jsonObject.optString("val"), DrawEntity.class);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return drawEntity;
	}

	/**
	 * @return 中奖查询
	 */
	public static PagesEntity<DrawQueryEntity> getDrawQueryPage(String data) {
		PagesEntity<DrawQueryEntity> drawQueryPages = new PagesEntity<>();
		try {
			JSONObject jsonObject = new JSONObject(data);
			jsonObject = new JSONObject(jsonObject.optString("val"));
			List<DrawQueryEntity> drawQueryEntities = gson.fromJson(jsonObject.optString("prize"), new TypeToken<List<DrawQueryEntity>>(){}.getType());
			drawQueryPages.setDatas(drawQueryEntities);
			drawQueryPages.setTotalPage(jsonObject.optInt("totalPage"));
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return drawQueryPages;
	}

	/**
	 * @return 板块列表
	 */
	public static List<PlateDetailEntity> getPlateDetailList(String data) {
		List<PlateDetailEntity> plateDetailEntities = null;
		try {
			JSONObject jsonObject = new JSONObject(data);
			plateDetailEntities = gson.fromJson(jsonObject.optString("val"), new TypeToken<List<PlateDetailEntity>>(){}.getType());
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return plateDetailEntities;
	}

	/**
	 * @return 店铺详情
	 */
	public static StoreDetailEntity getStoreDetail(String data) {
		return gson.fromJson(data, StoreDetailEntity.class);
	}

	/**
	 * @return 首页-分类-商品列表
	 */
	public static PagesEntity<ProductEntity> getProductPage(String data) {
		PagesEntity<ProductEntity> productPages = new PagesEntity<>();
		try {
			JSONObject jsonObject = new JSONObject(data);
			String val = jsonObject.optString("goods_list");
			List<ProductEntity> productEntities = gson.fromJson(val, new TypeToken<List<ProductEntity>>(){}.getType());
			productPages.setDatas(productEntities);
			productPages.setTotalPage(jsonObject.optInt("total_page"));
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return productPages;
	}

	/**
	 * @return 获取地区列表
	 */
	public static List<AreaEntity> getAreaList(String data) {
		List<AreaEntity> areaEntities = null;
		try {
			JSONObject jsonObject = new JSONObject(data);
			areaEntities = gson.fromJson(jsonObject.optString("val"), new TypeToken<List<AreaEntity>>(){}.getType());
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return areaEntities;
	}

	/**
	 * 获取token
	 **/
	public static UserEntity getUserInfo(String data) {
		return gson.fromJson(data, new TypeToken<UserEntity>() {}.getType());
	}

	/**
	 * 获取用户编码
	 **/
	public static String getUserCode(String data) {
		String userCode = null;
		if (data != null) {
			try {
				JSONObject jsonObject = new JSONObject(data);
				userCode = jsonObject.getString("userCode");
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		return userCode;
	}

	/**
	 * 获取推荐人信息
	 **/

	public static RecommenderEntity getRecommender(String data){
		return gson.fromJson(data, new TypeToken<RecommenderEntity>() {}.getType());
	}

	/**
	 * 分类
	 **/

	public static List<CategoryMainEntity> getCategory(String data){
		List<CategoryMainEntity> entities = null;
		try {
			JSONObject jsonObject = new JSONObject(data);
			entities = gson.fromJson(jsonObject.optString("category_list"), new TypeToken<List<CategoryMainEntity>>() {}.getType());
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return entities;
	}

	/**
	 * 个人中心
	 **/

	public static PersonCenterEntity getOrderNum(String data){
		PersonCenterEntity entity = null;
		try {
			JSONObject jsonObject = new JSONObject(data);
			entity = gson.fromJson(jsonObject.optString("val"), new TypeToken<PersonCenterEntity>() {}.getType());
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return entity;
	}

	/**
	 * 地址列表
	 **/

	public static AddressPageEntity getAddressPage(String data){
		AddressPageEntity entities = null;
		try {
			JSONObject jsonObject = new JSONObject(data);
			entities = gson.fromJson(jsonObject.optString("val"), new TypeToken<AddressPageEntity>() {}.getType());
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return entities;
	}
	/**
	 * 获取二维码
	 **/

	public static String getQrcode(String data){
		String qrCode = null;
		try {
			JSONObject jsonObject = new JSONObject(data);
			qrCode = jsonObject.getJSONObject("val").getString("key");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return qrCode;
	}

	/**
	 * 订单列表
	 **/

	public static OrderListPageEntity getOrders(String data){
		OrderListPageEntity entity = null;
		try {
			JSONObject jsonObject = new JSONObject(data);
			entity = gson.fromJson(jsonObject.optString("val"), new TypeToken<OrderListPageEntity>() {}.getType());
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return entity;
	}
}