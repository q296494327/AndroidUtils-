package com.github.lazylibrary.util;

import android.R.anim;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;

/**
 * 图片背景处理工具
 * @author xiemiao
 */
public class DrawableUtils {

    /**
     * 获取矩形背景
     * @param color 矩形背景的颜色
     * @param radius 矩形圆角的半径
     * @return
     */
    public static GradientDrawable getGradientDrawable(int color,int radius) {
	GradientDrawable shape=new GradientDrawable();
	shape.setShape(GradientDrawable.RECTANGLE);//设置形状为矩形
	shape.setCornerRadius(radius);//设置矩形圆角半径
	shape.setColor(color);//设置颜色
	return shape;
    }
    
    /**
     *状态选择器图片
     * @param normal 正常状态
     * @param press 按下状态
     * @return
     */
    public static StateListDrawable getStateListDrawable(Drawable bgNormal,Drawable bgPress) {
	StateListDrawable selector=new StateListDrawable();
	//添加按下状态的背景图片
	selector.addState(new int[] {android.R.attr.state_pressed}, bgPress);
	//添加默认状态的背景图片
	selector.addState(new int[] {}, bgNormal);
	return selector;
    }
    
    /**
     *通过颜色和圆角半径，获取一个圆角矩形的状态选择器图片
     * @param normal 默认颜色
     * @param press 按下颜色
     * @param radius 圆角半径
     * @return
     */
    public static StateListDrawable getStateListDrawable(int normal,int press,int radius) {
	//通过颜色获取对应的默认背景和按下背景
	GradientDrawable bgNormal = getGradientDrawable(normal, radius);
	GradientDrawable bgPress = getGradientDrawable(press, radius);
	//获取状态选择器
	StateListDrawable selector = getStateListDrawable(bgNormal, bgPress);
	return selector;
    }



	 /**
     * drawable图片缩放工具
     * @param drawable
     * @param w
     * @param h
     * @return
     */
    public static Drawable zoomDrawable(Drawable drawable, int w, int h) {
        int width = drawable.getIntrinsicWidth();
        int height = drawable.getIntrinsicHeight();
        Bitmap oldbmp = drawableToBitmap(drawable);
        Matrix matrix = new Matrix();
        float scaleWidth = ((float) w / width);
        float scaleHeight = ((float) h / height);
        matrix.postScale(scaleWidth, scaleHeight);
        Bitmap newbmp = Bitmap.createBitmap(oldbmp, 0, 0, width, height,
                matrix, true);
        return new BitmapDrawable(null, newbmp);
    }

    /**
     * drawable转成bitmap
     * @param drawable
     * @return
     */
    public static Bitmap drawableToBitmap(Drawable drawable) {
        int width = drawable.getIntrinsicWidth();
        int height = drawable.getIntrinsicHeight();
        Bitmap.Config config = drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888
                : Bitmap.Config.RGB_565;
        Bitmap bitmap = Bitmap.createBitmap(width, height, config);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, width, height);
        drawable.draw(canvas);
        return bitmap;
    }
    
    
}
