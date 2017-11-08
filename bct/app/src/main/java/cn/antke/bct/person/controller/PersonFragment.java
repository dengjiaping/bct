package cn.antke.bct.person.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.common.network.FProtocol;
import com.common.utils.StringUtil;
import com.common.viewinject.annotation.ViewInject;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.IdentityHashMap;

import cn.antke.bct.R;
import cn.antke.bct.base.BaseFragment;
import cn.antke.bct.login.controller.LoginActivity;
import cn.antke.bct.login.utils.UserCenter;
import cn.antke.bct.network.Constants;
import cn.antke.bct.network.Parsers;
import cn.antke.bct.network.entities.PersonCenterEntity;
import cn.antke.bct.utils.DialogUtils;
import cn.antke.bct.utils.ImageUtils;
import cn.antke.bct.utils.ViewInjectUtils;

import static cn.antke.bct.common.CommonConstant.EXTRA_FROM;
import static cn.antke.bct.common.CommonConstant.EXTRA_TYPE;
import static cn.antke.bct.common.CommonConstant.FROM_PERSON;
import static cn.antke.bct.common.CommonConstant.ORDERSTATE_ALL;
import static cn.antke.bct.common.CommonConstant.ORDERSTATE_DELIVED;
import static cn.antke.bct.common.CommonConstant.ORDERSTATE_DELIVING;
import static cn.antke.bct.common.CommonConstant.ORDERSTATE_FINISHED;
import static cn.antke.bct.common.CommonConstant.ORDERSTATE_PAYING;
import static cn.antke.bct.common.CommonConstant.ORDERSTATE_REFUND;
import static cn.antke.bct.common.CommonConstant.REQUEST_NET_ONE;

/**
 * Created by liuzhichao on 2017/4/27.
 * 个人中心
 */

public class PersonFragment extends BaseFragment implements View.OnClickListener {
	@ViewInject(R.id.person_setting)
	private ImageView setting;
	@ViewInject(R.id.person_avatar)
	private SimpleDraweeView avatar;
	@ViewInject(R.id.person_username)
	private TextView username;
	@ViewInject(R.id.person_useable_integral_ll)
	private LinearLayout useableIntegralLl;
	@ViewInject(R.id.person_useable_integral)
	private TextView useableIntegral;
	@ViewInject(R.id.person_good_friend_ll)
	private LinearLayout goodFriendLl;
	@ViewInject(R.id.person_good_friend)
	private TextView goodFriend;
	@ViewInject(R.id.person_all_order)
	private RelativeLayout allOrder;
	@ViewInject(R.id.person_order_paying)
	private FrameLayout orderShop;
	@ViewInject(R.id.person_order_deliving)
	private FrameLayout orderCharge;
	@ViewInject(R.id.person_order_delivred)
	private FrameLayout orderGive;
	@ViewInject(R.id.person_order_finished)
	private FrameLayout integralTransfer;
	@ViewInject(R.id.person_order_refund)
	private FrameLayout tradingHall;
	@ViewInject(R.id.person_personal_store)
	private TextView personalStore;
	@ViewInject(R.id.person_voucher_center)
	private TextView voucherCenter;
	@ViewInject(R.id.person_my_message)
	private RelativeLayout myMessage;
	@ViewInject(R.id.person_my_message_num)
	private TextView myMessageNum;
	@ViewInject(R.id.person_share_member)
	private TextView shareMember;
	@ViewInject(R.id.person_consumer_service_bind)
	private TextView counsumerServiceBind;
	@ViewInject(R.id.person_consumer_service)
	private TextView counsumerService;
	@ViewInject(R.id.person_transfer_integral)
	private TextView transferIntegral;
	@ViewInject(R.id.person_transaction_password)
	private TextView transferPassword;
	@ViewInject(R.id.person_bond_exchange)
	private TextView bondExchange;
	@ViewInject(R.id.person_lottery_inquiry)
	private TextView lotteryInquiry;

	private TextView shopDot, chargeDot, giveDot, integralDot, tradingDot;

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.frag_person, null);
		ViewInjectUtils.inject(this, view);
		initDot();
		initClickListener();
		return view;
	}

	@Override
	public void onResume() {
		super.onResume();
		initView();
	}

	private void initView() {
		if (UserCenter.isLogin(getActivity())) {
			ImageUtils.setSmallImg(avatar, UserCenter.getHeadPic(getActivity()));
			username.setText(UserCenter.getNickName(getActivity()));
			loadData();
		} else {
			setNoLoginData();
		}
	}

	private void setNoLoginData() {
		avatar.setImageResource(R.drawable.default_avatar_bg);
		username.setText(getString(R.string.person_click_login));
		setDotText("", shopDot);
		setDotText("", chargeDot);
		setDotText("", giveDot);
		setDotText("", integralDot);
		setDotText("", tradingDot);
		myMessageNum.setVisibility(View.GONE);

		useableIntegral.setText("");
		goodFriend.setText("");
	}

	private void initDot() {
		shopDot = (TextView) orderShop.findViewById(R.id.order_red_dot);
		chargeDot = (TextView) orderCharge.findViewById(R.id.order_red_dot);
		giveDot = (TextView) orderGive.findViewById(R.id.order_red_dot);
		integralDot = (TextView) integralTransfer.findViewById(R.id.order_red_dot);
		tradingDot = (TextView) tradingHall.findViewById(R.id.order_red_dot);
	}

	private void loadData() {
		IdentityHashMap<String, String> params = new IdentityHashMap<>();
		params.put("loginName", "");
		requestHttpData(Constants.Urls.URL_POST_PERSON_CENTER, REQUEST_NET_ONE, FProtocol.HttpMethod.POST, params);
	}

	private void initClickListener() {
		setting.setOnClickListener(this);
		avatar.setOnClickListener(this);
		username.setOnClickListener(this);
		useableIntegralLl.setOnClickListener(this);
		goodFriendLl.setOnClickListener(this);
		allOrder.setOnClickListener(this);
		orderShop.setOnClickListener(this);
		orderCharge.setOnClickListener(this);
		orderGive.setOnClickListener(this);
		integralTransfer.setOnClickListener(this);
		tradingHall.setOnClickListener(this);
		personalStore.setOnClickListener(this);
		voucherCenter.setOnClickListener(this);
		myMessage.setOnClickListener(this);
		shareMember.setOnClickListener(this);
		counsumerServiceBind.setOnClickListener(this);
		counsumerService.setOnClickListener(this);
		transferIntegral.setOnClickListener(this);
		transferPassword.setOnClickListener(this);
		bondExchange.setOnClickListener(this);
		lotteryInquiry.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		if (UserCenter.isLogin(getActivity())) {
			switch (v.getId()) {
				case R.id.person_setting:
					//设置
//					startActivity(new Intent(getActivity(), LoginActivity.class));
					DialogUtils.showTwoBtnDialog(getActivity(), "退出登录", "确定要退出登录", null, new View.OnClickListener() {
						@Override
						public void onClick(View v) {
							UserCenter.cleanLoginInfo(getActivity());
							DialogUtils.closeDialog();
							setNoLoginData();
						}
					}, new View.OnClickListener() {
						@Override
						public void onClick(View v) {
							DialogUtils.closeDialog();
						}
					});
					break;
				case R.id.person_avatar:
				case R.id.person_username:
					//个人信息
					startActivity(new Intent(getActivity(), PersonInfoActivity.class).putExtra(EXTRA_FROM, FROM_PERSON));
					break;
				case R.id.person_useable_integral_ll:
					//可用积分
					startActivity(new Intent(getActivity(), IntegralActivity.class));
					break;
				case R.id.person_good_friend_ll:
					//好友
					startActivity(new Intent(getActivity(), GoodFriendActivity.class));
					break;
				case R.id.person_all_order:
					//全部订单
					startActivity(new Intent(getActivity(), OrderListActivity.class).putExtra(EXTRA_TYPE, ORDERSTATE_ALL));
					break;
				case R.id.person_order_paying:
					//待支付订单
					startActivity(new Intent(getActivity(), OrderListActivity.class).putExtra(EXTRA_TYPE, ORDERSTATE_PAYING));
					break;
				case R.id.person_order_deliving:
					//待发货订单
					startActivity(new Intent(getActivity(), OrderListActivity.class).putExtra(EXTRA_TYPE, ORDERSTATE_DELIVING));
					break;
				case R.id.person_order_delivred:
					//待收货订单
					startActivity(new Intent(getActivity(), OrderListActivity.class).putExtra(EXTRA_TYPE, ORDERSTATE_DELIVED));
					break;
				case R.id.person_order_finished:
					//完成订单
					startActivity(new Intent(getActivity(), OrderListActivity.class).putExtra(EXTRA_TYPE, ORDERSTATE_FINISHED));
					break;
				case R.id.person_order_refund:
					//退款订单
					startActivity(new Intent(getActivity(), OrderListActivity.class).putExtra(EXTRA_TYPE, ORDERSTATE_REFUND));
					break;
				case R.id.person_personal_store:
					//个人店铺
					startActivity(new Intent(getActivity(), StoreMyStoreActivity.class));
					break;
				case R.id.person_voucher_center:
					//充值中心
					startActivity(new Intent(getActivity(), ChargeCenterActivity.class));
					break;
				case R.id.person_my_message:
					//我的消息
					startActivity(new Intent(getActivity(), MyMessageActivity.class));
					break;
				case R.id.person_share_member:
					//分享会员
					startActivity(new Intent(getActivity(), ShareMemberActivity.class));
					break;
				case R.id.person_consumer_service_bind:
					//绑定消费服务中心
					startActivity(new Intent(getActivity(), ConsumeServiceBindActivity.class));
					break;
				case R.id.person_consumer_service:
					//消费服务中心
					startActivity(new Intent(getActivity(), ConsumerServiceActivity.class));
					break;
				case R.id.person_transfer_integral:
					//转积分
					startActivity(new Intent(getActivity(), TransferIntegralActivity.class));
					break;
				case R.id.person_transaction_password:
					//积分交易密码
					startActivity(new Intent(getActivity(), IntegralPwdActivity.class));
					break;
				case R.id.person_bond_exchange:
					//债券兑换
					startActivity(new Intent(getActivity(), BondExchangeActivity.class));
					break;
				case R.id.person_lottery_inquiry:
					//抽奖查询
					startActivity(new Intent(getActivity(), LotteryInquiryActivity.class));
					break;
			}
		} else {
			switch (v.getId()) {
				case R.id.person_setting:
					//设置
					startActivity(new Intent(getActivity(), LoginActivity.class));
					break;
				case R.id.person_avatar:
				case R.id.person_username:
					//个人信息
//					startActivity(new Intent(getActivity(), PersonInfoActivity.class).putExtra(EXTRA_FROM, FROM_PERSON));
//					break;
				case R.id.person_useable_integral_ll:
					//可用积分
//					break;
				case R.id.person_good_friend_ll:
					//好友
//					break;
				case R.id.person_all_order:
					//全部订单
//					break;
				case R.id.person_order_paying:
					//购物订单
//					break;
				case R.id.person_order_deliving:
					//充值订单
//					break;
				case R.id.person_order_delivred:
					//赠送订单
//					break;
				case R.id.person_order_finished:
					//积分互转
//					break;
				case R.id.person_order_refund:
					//交易大厅
//					break;
				case R.id.person_personal_store:
					//个人店铺
//					break;
				case R.id.person_voucher_center:
					//充值中心
//					break;
				case R.id.person_my_message:
					//我的消息
//					break;
				case R.id.person_share_member:
					//分享会员
//					break;
				case R.id.person_consumer_service_bind:
					//绑定消费服务中心
//					break;
				case R.id.person_consumer_service:
					//消费服务中心
//					break;
				case R.id.person_transfer_integral:
					//转积分
//					break;
				case R.id.person_transaction_password:
					//积分交易密码
//					break;
				case R.id.person_bond_exchange:
					//债券兑换
				case R.id.person_lottery_inquiry:
					//抽奖查询
					startActivity(new Intent(getActivity(), LoginActivity.class));
					break;
			}
		}
	}

	@Override
	public void success(int requestCode, String data) {
		super.success(requestCode, data);
		PersonCenterEntity entity = Parsers.getOrderNum(data);
		if (entity != null) {
			String shopNum = entity.getShopOrderNum();
			String chargeNum = entity.getRechargeOrderNum();
			String giveNum = entity.getGivingOrderNum();
			String integralNum = entity.getSwitchNum();
			String tradingNum = entity.getExchangeNum();
			setDotText(shopNum, shopDot);
			setDotText(chargeNum, chargeDot);
			setDotText(giveNum, giveDot);
			setDotText(integralNum, integralDot);
			setDotText(tradingNum, tradingDot);

			String messageNum = entity.getMessageNum();
			if (StringUtil.isEmpty(messageNum) || messageNum.equals("0")) {
				myMessageNum.setVisibility(View.GONE);
			} else if (Integer.parseInt(messageNum) > 99) {
				myMessageNum.setVisibility(View.VISIBLE);
				myMessageNum.setText(getString(R.string.more_than_99));
			} else {
				myMessageNum.setVisibility(View.VISIBLE);
				myMessageNum.setText(messageNum);
			}

			useableIntegral.setText(entity.getUserIntegral());
			goodFriend.setText(entity.getFriendNum());
		}
	}

	private void setDotText(String num, TextView view) {
		if (!StringUtil.isEmpty(num)) {
			if ("0".equals(num) || StringUtil.isEmpty(num)) {
				view.setVisibility(View.GONE);
			} else if (Integer.parseInt(num) > 99) {
				view.setVisibility(View.VISIBLE);
				view.setText(getString(R.string.more_than_99));
			} else {
				view.setVisibility(View.VISIBLE);
				view.setText(num);
			}
		}
	}
}
