package com.example.an.shoppingcartactivity;

import android.content.Context;
import android.widget.Toast;

/**
 * 提醒工具
 * 
 * @author WeiGong
 * 
 */

public class ToastUtil {

	public static void showL(Context context, String info) {
		Toast.makeText(context, info, Toast.LENGTH_LONG).show();
	}

	public static void showS(Context context, String info) {
		Toast.makeText(context, info, Toast.LENGTH_SHORT).show();
	}
}
