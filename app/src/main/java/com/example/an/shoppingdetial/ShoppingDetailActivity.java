package com.example.an.shoppingdetial;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.an.shoppingcartactivity.R;
import com.example.an.shoppingcartactivity.ShoppingCartActivity;
import com.example.an.shoppingcartactivity.ToastUtil;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

/**
 * Created by an on 2018/6/1.
 */

public class ShoppingDetailActivity extends Activity implements View.OnClickListener {

    Button backBtn;
    //商品详情
            TextView tvCommodity;
            TextView headerRight;
   //菜单按钮
            Button btnRightHead;

   //商品名称
            TextView tvCommodityTitle;
    //商品套装描述
            TextView tvCommodityNumber;
   //商品价格
            TextView tvCommodityMoney;
    //点击跳转选择商品样式界面
            RelativeLayout rlCommodityChange;
   //显示未选已选
            TextView tvCommodityChange;
    //选择商品具体样品
            TextView tvCommodityChangeName;
    //商品描述与评价等信息
            RelativeLayout reCommodityDescription;
            RadioButton rbCommoditySummarize;
   //商品参数
            RadioButton rbCommodityParameter;

   //查看所有评价
            TextView tvAllAppraise;
    //用户头像
            ImageView imgUserHead;
    //用户名字
            TextView tvUserName;
   //评价时间
            TextView tvAppraiseTime;

    //回应评价
            LinearLayout mRespond;

    TextView tvUserPraise;//用户点赞
    //喜欢按钮
            Button btnCommodityLike;
    //购物车
            Button btnCommodityCart;
    //加入购物车
            RelativeLayout reCommodityAddCart;
    //商品滑动查看
            BannerViewPager mBannerViewPager;
    //滑动圆点
            LinearLayout mCommodiyuViewGroup;

   //选择地址
            RelativeLayout reAddressChoose;

    //显示是否有存货 TODO
            TextView isInventory;
    private PopupWindow pwMyPopWindow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_detail);
        ViewUtils.inject(this);
        initView();
        tvCommodity.setText("商品详情");
        //btnRightHead.setBackgroundResource(R.drawable.menu_com);

        getNetDatas();
        popuWindowDialog();

    }

    /**
     * 弹出悬浮窗体,显示菜单
     */
    private void popuWindowDialog() {
        LayoutInflater inflater = (LayoutInflater) this.getSystemService(LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.task_detail_popupwindow, null);
        LinearLayout linMessage= (LinearLayout) layout.findViewById(R.id.popuMessage);
        LinearLayout linMain= (LinearLayout) layout.findViewById(R.id.popuMain);
        LinearLayout linShare= (LinearLayout) layout.findViewById(R.id.popuShare);

        linMessage.setOnClickListener(this);
        linMain.setOnClickListener(this);
        linShare.setOnClickListener(this);
        pwMyPopWindow = new PopupWindow(layout);
        pwMyPopWindow.setFocusable(true);
        // 加上这个popupwindow中的ListView才可以接收点击事件
        // 控制popupwindow的宽度和高度自适应
        pwMyPopWindow.setWidth(400);
        pwMyPopWindow.setHeight(300);
        // 触摸popupwindow外部，popupwindow消失。这个要求你的popupwindow要有背景图片才可以成功，如上
        pwMyPopWindow.setBackgroundDrawable(this.getResources().getDrawable(R.drawable.bg_popupwindow));
        // 控制popupwindow点击屏幕其他地方消失
        pwMyPopWindow.setOutsideTouchable(true);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode==11 && requestCode==10){
            ToastUtil.showL(this,"返回数据");
            String number = data.getStringExtra("number");
            String seledtedColor = data.getStringExtra("seledtedColor");
            tvCommodityChange.setText("已选 X "+number+"     "+seledtedColor);
        }
        if (resultCode==14 && requestCode==13){
            // ToastUtil.showL(this,"返回地址");
        }
    }

    /**
     * 从服务器获取某个商品的数据
     */
    private void getNetDatas() {


    }

    private void initView() {
        backBtn=(Button)findViewById(R.id.btn_back);
        headerRight=(TextView)findViewById(R.id.bt_header_right);
                 tvCommodity=(TextView)findViewById(R.id.tv_title);

                 tvCommodityTitle=(TextView)findViewById(R.id.commodity_title);
                 tvCommodityNumber=(TextView)findViewById(R.id.commodity_number);
                 tvCommodityMoney=(TextView)findViewById(R.id.commodity_money);
                 rlCommodityChange=(RelativeLayout)findViewById(R.id.commodity_relative_change);
                 tvCommodityChange=(TextView)findViewById(R.id.commodity_change);
                 tvCommodityChangeName=(TextView)findViewById(R.id.commodity_change_name);
                 reCommodityDescription=(RelativeLayout)findViewById(R.id.commodity_description);
                 rbCommoditySummarize=(RadioButton)findViewById(R.id.commodity_summarize);
                 rbCommodityParameter=(RadioButton)findViewById(R.id.commodity_parameter);

                 tvAllAppraise=(TextView)findViewById(R.id.all_appraise);
                 imgUserHead=(ImageView)findViewById(R.id.user_head);
                 tvUserName=(TextView)findViewById(R.id.user_name);
                 tvAppraiseTime=(TextView)findViewById(R.id.user_time);

                 mRespond=(LinearLayout)findViewById(R.id.respond);
                 tvUserPraise=(TextView)findViewById(R.id.user_praise);
                 btnCommodityLike=(Button)findViewById(R.id.commodity_like);
                 btnCommodityCart=(Button)findViewById(R.id.commodity_cart);
                 reCommodityAddCart=(RelativeLayout)findViewById(R.id.commodity_add_cart);
                 mBannerViewPager=(BannerViewPager)findViewById(R.id.rl_commodity_pager);
                 mCommodiyuViewGroup=(LinearLayout)findViewById(R.id.rl_commodity_viewGroup);

                 reAddressChoose=(RelativeLayout)findViewById(R.id.rl_address_choose);

                 isInventory=(TextView)findViewById(R.id.is_inventory);


        backBtn.setVisibility(View.INVISIBLE);
        headerRight.setVisibility(View.INVISIBLE);
        rlCommodityChange.setOnClickListener(this);
        tvAllAppraise.setOnClickListener(this);
        tvUserPraise.setOnClickListener(this);
        btnCommodityLike.setOnClickListener(this);
        rbCommoditySummarize.setOnClickListener(this);
        rbCommodityParameter.setOnClickListener(this);
        btnCommodityCart.setOnClickListener(this);
        reCommodityAddCart.setOnClickListener(this);
        reAddressChoose.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.commodity_relative_change:   //选择商品样式

                Intent intent1=new Intent(ShoppingDetailActivity.this,ChooseCommodityDialogActivity.class);
                startActivityForResult(intent1,10);
                //startActivity(intent1);

                break;
            case R.id.all_appraise://查看所有评价

                break;
            case R.id.user_praise://用户点赞
                postPraiseNumber();
                break;
            case R.id.commodity_summarize://商品概述
                ToastUtil.showL(ShoppingDetailActivity.this,"---商品概述--");
                break;
            case R.id.commodity_parameter://商品参数
                ToastUtil.showL(ShoppingDetailActivity.this,"---商品参数--");
                break;
            case R.id.commodity_like://喜欢按钮
                btnCommodityLike.setTextColor(getResources().getColor(R.color.purple_button));
                break;
            case R.id.commodity_cart://购物车
                Intent intentCart=new Intent(ShoppingDetailActivity.this,ShoppingCartActivity.class);
                startActivity(intentCart);
                break;
            case R.id.commodity_add_cart://加入购物车
                btnCommodityCart.setTextColor(getResources().getColor(R.color.purple_button));
                addShoppingCartMethod();

                break;
            case R.id.rl_address_choose://选择地址
               /* Intent intentAddress=new Intent(ShoppingDetailActivity.this,ChooseAddressActivity.class);
                startActivityForResult(intentAddress,13);*/
                break;
            case R.id.is_inventory://是否有货
                break;
            case R.id.popuMessage:
                ToastUtil.showL(this,"---消息通知----");
                pwMyPopWindow.dismiss();// 关闭
                break;
            case R.id.popuMain:
                ToastUtil.showL(this,"---首页----");
                pwMyPopWindow.dismiss();// 关闭
                break;
            case R.id.popuShare:
                ToastUtil.showL(this,"---分享----");
                pwMyPopWindow.dismiss();// 关闭
                break;
        }
    }

    /**
     * 用户点赞，提交数据到服务器
     */
    private void postPraiseNumber() {

    }
    /**
     * 加入购物车 ---提交数据到服务器
     */
    private void addShoppingCartMethod() {

        ToastUtil.showL(this,"成功加入购物车");

    }
}
