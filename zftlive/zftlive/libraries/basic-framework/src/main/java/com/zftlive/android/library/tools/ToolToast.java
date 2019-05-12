/*
 *     Android基础开发个人积累、沉淀、封装、整理共通
 *     Copyright (c) 2016. 曾繁添 <zftlive@163.com>
 *     Github：https://github.com/zengfantian || http://git.oschina.net/zftlive
 *
 *     Licensed under the Apache License, Version 2.0 (the "License");
 *     you may not use this file except in compliance with the License.
 *     You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 *     Unless required by applicable law or agreed to in writing, software
 *     distributed under the License is distributed on an "AS IS" BASIS,
 *     WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *     See the License for the specific language governing permissions and
 *     limitations under the License.
 */

package com.zftlive.android.library.tools;


import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Handler;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;

import com.zftlive.android.library.MApplication;

/**
 * 自定义Toast控件
 * @author 曾繁添
 * @version 1.0
 */
public class ToolToast {
	
	private static Toast mToast;
	private static Handler mHandler = new Handler();
	private static Runnable r = new Runnable() {
		public void run() {
			mToast.cancel();
		}
	}; 
	
	/**
	 * 弹出较长时间提示信息
	 * @param context 上下文对象
	 * @param msg 要显示的信息
	 */
	public static void showLong(Context context, String msg){
		buildToast(context,msg,Toast.LENGTH_LONG).show();
	}
	
	/**
	 * 弹出较长时间提示信息
	 * @param msg 要显示的信息
	 */
	public static void showLong(String msg){
		buildToast(MApplication.gainContext(),msg,Toast.LENGTH_LONG).show();
	}
	
	/**
	 * 弹出较短时间提示信息
	 * @param context 上下文对象
	 * @param msg 要显示的信息
	 */
	public static void showShort(Context context, String msg){
		buildToast(context,msg,Toast.LENGTH_SHORT).show();
	}
	
	/**
	 * 弹出较短时间提示信息
	 * @param msg 要显示的信息
	 */
	public static void showShort(String msg){
		buildToast(MApplication.gainContext(),msg,Toast.LENGTH_SHORT).show();
	}
	
	/**
	 * 构造Toast
	 * @param context 上下文
	 * @return
	 */
	public static Toast buildToast(Context context,String msg,int duration){
		return buildToast(context,msg,duration,"#D8000000",16);
	}
	

	/**
	 * 构造Toast
	 * @param context 上下文
	 * @param msg 消息
	 * @param duration 显示时间
	 * @param bgColor 背景颜色
	 * @return
	 */
	public static Toast buildToast(Context context,String msg,int duration,String bgColor){
		return buildToast(context,msg,duration,bgColor,16);
	}
	
	
	/**
	 * 构造Toast
	 * @param context 上下文
	 * @param msg	消息
	 * @param duration 显示时间
	 * @param bgColor 背景颜色
	 * @param textSp  文字大小
	 * @return
	 */
	public static Toast buildToast(Context context,String msg,int duration,String bgColor,int textSp){
		return buildToast(context,msg,duration,bgColor,textSp,10);
	}
	
	/**
	 * 构造Toast
	 * @param context 上下文
	 * @param msg	消息
	 * @param duration 显示时间
	 * @param bgColor 背景颜色
	 * @param textSp  文字大小
	 * @param cornerRadius  四边圆角弧度
	 * @return
	 */
	public static Toast buildToast(Context context,String msg,int duration,String bgColor,int textSp,int cornerRadius){
		mHandler.removeCallbacks(r);
		
		if(null == mToast){
			//构建Toast
			mToast = Toast.makeText(context, null, duration);
			mToast.setGravity(Gravity.CENTER, 0, 0);
			//取消toast
			mHandler.postDelayed(r, duration);
		}
		
		//设置Toast文字
		TextView tv = new TextView(context);
		int dpPadding = dipTopx(context,10.0f);
		tv.setPadding(dpPadding, dpPadding, dpPadding, dpPadding);
		tv.setGravity(Gravity.CENTER);
		tv.setText(msg);
		tv.setTextColor(Color.WHITE);
		tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSp);
		
		//Toast文字TextView容器
        LinearLayout mLayout = new LinearLayout(context);
        GradientDrawable shape = new GradientDrawable();
	    shape.setColor(Color.parseColor(bgColor));
	    shape.setCornerRadius(cornerRadius);
	    shape.setStroke(1, Color.parseColor(bgColor));
	    shape.setAlpha(180);
        mLayout.setBackground(shape);
        mLayout.setOrientation(LinearLayout.VERTICAL);
		LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT);
		//设置layout_gravity
		params.gravity = Gravity.CENTER;  
		mLayout.setLayoutParams(params);
	    //设置gravity
		mLayout.setGravity(Gravity.CENTER);
        mLayout.addView(tv);
        
        //将自定义View覆盖Toast的View
        mToast.setView(mLayout);
        
		return mToast;
	}

	private static int dipTopx(Context context, float dipValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dipValue * scale + 0.5f);
	}
}
