package com.example.an.shoppingdetial;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.example.an.shoppingcartactivity.R;
import com.example.an.shoppingcartactivity.ToastUtil;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by an on 2017/6/15.
 */

/**
 * 选择商品样式
 */
public class ChooseCommodityDialogActivity extends Activity implements View.OnClickListener {



    ImageView commodityImgSmall;
    TextView commodityMoney;
    TextView commodityName;

    LinearLayout attritude;
    GridView gvAttributeColor;
   /* @ViewInject(R.id.color2)
    Button commodityColor2;*/
   Button sureAddShopCart;

    TextView subtractNumber;    //减少数量
    TextView addNumber;        //增加数量
    TextView shopNumber;


    private int commodityNmber;


    private static String TAG="ChooseCommodityDialogActivity";

    private ShoppingCartColorAdapter attributeColorAdapter;
    List<String> listColor;
    ChooseCommodityDialogActivity.InnerBroadcastReceiver receiver;
    private String seledtedSize;
    private String seledtedColor="";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_choose_dialog);
        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);


        initView();

        registerReceiver();
        addData();
        subtractNumber.setOnClickListener(this);
        addNumber.setOnClickListener(this);
        attritude.setOnClickListener(this);
        sureAddShopCart.setOnClickListener(this);
    }

    private void initView() {

         commodityImgSmall=(ImageView)findViewById(R.id.commidity_img_small);
         commodityMoney=(TextView)findViewById(R.id.commodity_money);
         commodityName=(TextView)findViewById(R.id.commodity_name);

         attritude=(LinearLayout)findViewById(R.id.attritude);

         gvAttributeColor=(GridView)findViewById(R.id.gv_attribute_color);
         sureAddShopCart=(Button)findViewById(R.id.add_shop_cart);

         subtractNumber=(TextView)findViewById(R.id.subtract_number);
         addNumber=(TextView)findViewById(R.id.add_number);
         shopNumber=(TextView)findViewById(R.id.shop_number);
    }

    private void addData(){
        listColor = new ArrayList<>();
        listColor.add("大红");
        listColor.add("驼色");
        listColor.add("咖啡");
        listColor.add("紫色");
        listColor.add("紫色");
        listColor.add("紫色");

        if(listColor.size()>0){
            attributeColorAdapter = new ShoppingCartColorAdapter(ChooseCommodityDialogActivity.this,listColor,gvAttributeColor);
            gvAttributeColor.setAdapter(attributeColorAdapter);
        }

    }

    //注册广播接收者
    private void registerReceiver(){
        IntentFilter filter = new IntentFilter();
        //可以添加颜色、尺寸的监听
        filter.addAction(Constant.SHOPPINGCART_COLORADAPTER_SEND_SHOPPINGCART_RECORD_COLOR);
        receiver = new ChooseCommodityDialogActivity.InnerBroadcastReceiver();
        registerReceiver(receiver,filter);
    }

    class InnerBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
           if(Constant.SHOPPINGCART_COLORADAPTER_SEND_SHOPPINGCART_RECORD_COLOR.equals(action)){
                //当前选中的颜色
                seledtedColor = intent.getStringExtra("currentPositionColor");

                ToastUtil.showL(context,seledtedColor.toString());
            }
        }
    }


    //实现onTouchEvent触屏函数但点击屏幕时销毁本Activity
    @Override
    public boolean onTouchEvent(MotionEvent event){
        finish();
        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        unregisterReceiver(receiver);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.subtract_number://减少数量
                if (commodityNmber<=1){
                    return;
                }
                commodityNmber --;
                break;
            case R.id.add_number://增加数量
                commodityNmber ++;
                break;

            case R.id.attritude://

                break;
            case R.id.add_shop_cart:// 确定加入

                makeSureMethod();
                break;
        }

        shopNumber.setText(commodityNmber+"");

    }

    /**
     * 确定
     */
    private void makeSureMethod() {
        String number = shopNumber.getText().toString();
        Intent intent=new Intent();
        intent.putExtra("number",number);
        intent.putExtra("seledtedColor",seledtedColor);
        setResult(11,intent);
        finish();

    }
}
