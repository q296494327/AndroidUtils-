package com.github.lazylibrary.util;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

/**
 * liujingyuan
 */
public class DensityUtil {

    private static int[] deviceWidthHeight = new int[2];

    public static int[] getDeviceInfo(Context context) {
	if ((deviceWidthHeight[0] == 0) && (deviceWidthHeight[1] == 0)) {
	    DisplayMetrics metrics = new DisplayMetrics();
	    ((Activity) context).getWindowManager().getDefaultDisplay()
		    .getMetrics(metrics);

	    deviceWidthHeight[0] = metrics.widthPixels;
	    deviceWidthHeight[1] = metrics.heightPixels;
	}
	return deviceWidthHeight;
    }

    /**
     * 获取屏幕尺寸
     */
    @SuppressWarnings("deprecation")
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    public static Point getScreenSize(Context context) {
	WindowManager windowManager = (WindowManager) context
		.getSystemService(Context.WINDOW_SERVICE);
	Display display = windowManager.getDefaultDisplay();
	if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB_MR2) {
	    return new Point(display.getWidth(), display.getHeight());
	} else {
	    Point point = new Point();
	    display.getSize(point);
	    return point;
	}
    }

    /**
     * 获取系统dp尺寸密度值
     * 
     * @param context
     *            应用程序上下文
     * @return
     */
    public static float getDensity(Context context) {
	return getDisplayMetrics(context).density;
    }

    /**
     * 获取系统字体sp密度值
     * 
     * @param context
     *            应用程序上下文
     * @return
     */
    public static float getScaledDensity(Context context) {
	return getDisplayMetrics(context).scaledDensity;
    }

    /**
     * dip转换为px大小
     * 
     * @param context
     *            应用程序上下文
     * @param dpValue
     *            dp值
     * @return 转换后的px值
     */
    public static int dp2px(Context context, int dpValue) {
	return (int) (dpValue * getDensity(context) + 0.5f);
    }

    /**
     * px转换为dp值
     * 
     * @param context
     *            应用程序上下文
     * @param pxValue
     *            px值
     * @return 转换后的dp值
     */
    public static int px2dp(Context context, int pxValue) {
	return (int) (pxValue / getDensity(context) + 0.5f);
    }

    /**
     * sp转换为px
     * 
     * @param context
     *            应用程序上下文
     * @param spValue
     *            sp值
     * @return 转换后的px值
     */
    public static int sp2px(Context context, int spValue) {
	return (int) (spValue * getScaledDensity(context) + 0.5f);
    }

    /**
     * px转换为sp
     * 
     * @param context
     *            应用程序上下文
     * @param pxValue
     *            px值
     * @return 转换后的sp值
     */
    public static int px2sp(Context context, int pxValue) {
	return (int) (pxValue / getScaledDensity(context) + 0.5f);
    }

    /**
     * 获取DisplayMetrics对象
     * 
     * @param context
     *            应用程序上下文
     * @return
     */
    public static DisplayMetrics getDisplayMetrics(Context context) {
	WindowManager windowManager = (WindowManager) context
		.getSystemService(Context.WINDOW_SERVICE);
	DisplayMetrics displayMetrics = new DisplayMetrics();
	windowManager.getDefaultDisplay().getMetrics(displayMetrics);
	return displayMetrics;
    }

}
