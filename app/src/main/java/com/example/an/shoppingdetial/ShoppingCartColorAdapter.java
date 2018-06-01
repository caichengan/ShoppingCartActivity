package com.example.an.shoppingdetial;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.RadioButton;


import com.example.an.shoppingcartactivity.R;

import java.util.List;


public class ShoppingCartColorAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private List<String> listColor;
    private Context context;
    private GridView gridView;
    private int listColorSize;


    public ShoppingCartColorAdapter(Context context, List<String> listColor, GridView gridView){
        this.context = context;
        this.listColor = listColor;
        this.gridView = gridView;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        listColorSize = listColor.size();
        return listColor.size();
    }

    @Override
    public String getItem(int i) {
        return listColor.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        ViewHolder holder;
        String color = getItem(i);
        if(convertView == null){
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.item_shoppingcart_attribute,null);
            holder.rbAttribute = (RadioButton) convertView.findViewById(R.id.rb_attribute);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        holder.rbAttribute.setText(color);
        Log.d("hxl","color=="+color);
        holder.rbAttribute.setTag("rbAttribute"+i);

        holder.rbAttribute.setOnClickListener(new MyOnclickListerner(i));

        return convertView;
    }
    class ViewHolder{
        RadioButton rbAttribute;
    }

    class MyOnclickListerner implements View.OnClickListener {
        private int currentPositionColor;
        public MyOnclickListerner(int i) {
            currentPositionColor = i;
        }
        @Override
        public void onClick(View view) {
            RadioButton rbAttribute = (RadioButton) gridView.findViewWithTag("rbAttribute" + currentPositionColor);
                for(int i=0;i<listColorSize;i++){
                    RadioButton rbAttributeAll = (RadioButton) gridView.findViewWithTag("rbAttribute" + i);
                    rbAttributeAll.setChecked(false);
                }
            rbAttribute.setChecked(true);
            sendBrodcastReceiver(rbAttribute.getText().toString());
            //Toast.makeText(context,rbAttribute.getText(),Toast.LENGTH_SHORT).show();
        }
    }
    //发送广播给详情页面记录选中的颜色
    private void sendBrodcastReceiver(String str){
        Intent intent = new Intent(Constant.SHOPPINGCART_COLORADAPTER_SEND_SHOPPINGCART_RECORD_COLOR);
        intent.putExtra("currentPositionColor",str);

        context.sendBroadcast(intent);
    }


}
