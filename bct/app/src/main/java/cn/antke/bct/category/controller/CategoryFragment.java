package cn.antke.bct.category.controller;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.common.network.FProtocol;
import com.common.utils.ToastUtil;
import com.common.viewinject.annotation.ViewInject;
import com.common.widget.RefreshRecyclerView;

import java.util.IdentityHashMap;
import java.util.List;

import cn.antke.bct.R;
import cn.antke.bct.base.BaseFragment;
import cn.antke.bct.category.adapter.CategoryAdapter;
import cn.antke.bct.common.CommonConstant;
import cn.antke.bct.home.adapter.ProductsAdapter;
import cn.antke.bct.network.Constants;
import cn.antke.bct.network.Parsers;
import cn.antke.bct.network.entities.CategoryMainEntity;
import cn.antke.bct.network.entities.PagesEntity;
import cn.antke.bct.network.entities.ProductEntity;
import cn.antke.bct.utils.CommonTools;
import cn.antke.bct.utils.ViewInjectUtils;

import static cn.antke.bct.common.CommonConstant.REQUEST_NET_ONE;
import static cn.antke.bct.common.CommonConstant.REQUEST_NET_THREE;
import static cn.antke.bct.common.CommonConstant.REQUEST_NET_TWO;

/**
 * Created by liuzhichao on 2017/4/27.
 * 分类
 */
public class CategoryFragment extends BaseFragment implements AdapterView.OnItemClickListener, RefreshRecyclerView.OnRefreshAndLoadMoreListener {

	@ViewInject(R.id.categrory_categrory)
	private ListView categrory;
	@ViewInject(R.id.categrory_banner)
	private ConvenientBanner banner;
	@ViewInject(R.id.categrory_goods)
	private RefreshRecyclerView recyclerView;

	private View view;
	private List<CategoryMainEntity> categoryEntities;
	private CategoryAdapter categoryAdapter;
	private String currentCategoryId;
	private ProductsAdapter productAdapter;
	private ProductsAdapter productsAdapter;

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.frag_categrory, null);
		ViewInjectUtils.inject(this, view);
		loadData("", REQUEST_NET_ONE, false);
		initRecycleView();
//		if (view == null) {
//			ViewInjectUtils.inject(this, view);
//			loadData("");
//		}
//		ViewGroup mViewParent = (ViewGroup) view.getParent();
//		if (mViewParent != null) {
//			mViewParent.removeView(view);
//		}
		return view;
	}

	private void initRecycleView() {
		recyclerView.setMode(RefreshRecyclerView.Mode.BOTH);
		recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
		recyclerView.setOnRefreshAndLoadMoreListener(this);
		recyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
			@Override
			public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
				if (parent.getLayoutManager() instanceof GridLayoutManager) {
					GridLayoutManager gridLayoutManager = (GridLayoutManager) parent.getLayoutManager();
					int spanCount = gridLayoutManager.getSpanCount();//设置的列数
					int headerSize = recyclerView.getHeaderSize();//头部数量
					int pos = parent.getChildLayoutPosition(view) - headerSize;//减去头部后的下标位置

					if (pos < 0) {//头部
						return;
					}

					//item左右偏移量(左右间距)，仅对2列有效
					if (pos % 2 == 0) {
						outRect.right = CommonTools.dp2px(getActivity(), 4);
					} else {
						outRect.left = CommonTools.dp2px(getActivity(), 4);
					}
					//item上下偏移量(上下间距)
					if (pos >= spanCount) {
						outRect.top = CommonTools.dp2px(getActivity(), 8);
					}
				}
			}
		});
		recyclerView.setCanAddMore(false);
		recyclerView.setOnRefreshAndLoadMoreListener(this);
	}

	private void loadData(String categoryId, int requestCode, boolean isMore) {
		showProgressDialog();
		IdentityHashMap<String, String> params = new IdentityHashMap<>();
		params.put("category_id", categoryId);
		int page = 1;
		if (isMore) {
			page = productAdapter.getPage() + 1;
		}
		params.put(CommonConstant.PAGENUM, String.valueOf(page));
		params.put(CommonConstant.PAGESIZE, CommonConstant.PAGE_SIZE_10);
		requestHttpData(Constants.Urls.URL_POST_CATEGORY, requestCode, FProtocol.HttpMethod.POST, params);
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		CategoryMainEntity checkEntity = categoryEntities.get(position);
		if (checkEntity.isChecked()) {
			return;
		}
		for (CategoryMainEntity entity : categoryEntities) {
			entity.setChecked(false);
		}
		checkEntity.setChecked(true);
		categoryAdapter.notifyDataSetChanged();
		currentCategoryId = checkEntity.getCategoryId();
		getGoods(false);
	}

	@Override
	public void parseData(int requestCode, String data) {
		super.parseData(requestCode, data);
		recyclerView.resetStatus();
		switch (requestCode) {
			case REQUEST_NET_ONE://默认选择第一个
				List<CategoryMainEntity> entities = Parsers.getCategory(data);
				if (entities.size() > 0) {
					entities.get(0).setChecked(true);
					currentCategoryId = entities.get(0).getCategoryId();
					categoryAdapter = new CategoryAdapter(getActivity(), entities);
					categrory.setAdapter(categoryAdapter);
					categrory.setOnItemClickListener(this);
				}
				getGoods(false);

				break;
			case REQUEST_NET_TWO://选择某一个分类
				setGoods(data);
				break;
			case REQUEST_NET_THREE://加载更多
				PagesEntity<ProductEntity> productPage = Parsers.getProductPage(data);
				if (productPage != null) {
					//添加商品的测试数据
					List<ProductEntity> productEntities = productPage.getDatas();
					productsAdapter.addDatas(productEntities);
					productAdapter.notifyDataSetChanged();
					if (productPage.getTotalPage() <= 1) {
						recyclerView.setCanAddMore(false);
					}
				} else {
					ToastUtil.shortShow(getActivity(), getString(R.string.no_data_now));
				}
				break;
		}
	}

	@NonNull
	private void getGoods(boolean isMore) {
		IdentityHashMap<String, String> params = new IdentityHashMap<>();
		params.put("category_id", currentCategoryId);
		int page = 1;
		int requestCode = REQUEST_NET_TWO;
		if (isMore) {
			page = productAdapter.getPage() + 1;
			requestCode = REQUEST_NET_THREE;
		}

		params.put(CommonConstant.PAGENUM, String.valueOf(page));
		params.put(CommonConstant.PAGESIZE, CommonConstant.PAGE_SIZE_10);
		requestHttpData(Constants.Urls.URL_POST_PLATE_PRODUCT_LIST, requestCode, FProtocol.HttpMethod.POST, params);
	}

	private void setGoods(String data) {
		PagesEntity<ProductEntity> productPage = Parsers.getProductPage(data);
		if (productPage != null) {
			//添加商品的测试数据
			List<ProductEntity> productEntities = productPage.getDatas();
			productsAdapter = new ProductsAdapter(getActivity(), productEntities);
			recyclerView.setAdapter(productsAdapter);
			if (productPage.getTotalPage() <= 1) {
				recyclerView.setCanAddMore(false);
			}
		} else {
			ToastUtil.shortShow(getActivity(), getString(R.string.no_data_now));
		}
	}

	@Override
	public void onRefresh() {
		getGoods(false);
	}

	@Override
	public void onLoadMore() {
		getGoods(true);
	}
}
