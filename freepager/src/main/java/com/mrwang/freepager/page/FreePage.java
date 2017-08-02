package com.mrwang.freepager.page;

import android.content.Context;
import android.graphics.PixelFormat;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;

import static android.content.ContentValues.TAG;

/**
 * User: chengwangyong(chengwangyong@blinnnk.com)
 * Date: 2017/8/2
 * Time: 上午11:15
 */
public class FreePage {
  private static int screenHeightPx;
  private static int screenWidthPx;
  private Context context;
  private WindowManager wManager;
  private FrameLayout phoneView;
  private WindowManager.LayoutParams mParams;
  private boolean attached = false;

  public FreePage(Context context) {
    this.context = context;
    init();
  }

  private void init() {
    phoneView = new FrameLayout(context);
    wManager = (WindowManager) context.getSystemService(
      Context.WINDOW_SERVICE);
    mParams = new WindowManager.LayoutParams();
    mParams.type = WindowManager.LayoutParams.TYPE_SYSTEM_ALERT;// 系统提示window
    mParams.format = PixelFormat.TRANSLUCENT;// 支持透明
    //mParams.format = PixelFormat.RGBA_8888;
    mParams.flags |= WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;// 焦点
    mParams.width = getScreenWidthPx(context);//窗口的宽和高
    mParams.height = getScreenHeightPx(context);
    mParams.x = 0;//窗口位置的偏移量
    mParams.y = 0;
    //mParams.alpha = 0.1f;//窗口的透明度
    initView();


    //一旦起来一个 Windows 后续的所有的触摸事件都被拦截住了
    //跟起来一个 window(dialog phoneWindos) 差不多
    phoneView.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Log.d(TAG, "onClick() called with: v = [" + v + "] name=" + Thread.currentThread().getName());
        destroy();
      }
    });
  }

  public void initView() {
    if (wManager != null && !attached) {
      wManager.addView(phoneView, mParams);//添加窗口
      attached = true;
    }
  }

  public void show(View view) {
    phoneView.addView(view);
  }

  public void show(View view, ViewGroup.LayoutParams params) {
    phoneView.addView(view, params);
  }

  public void updateViewLayout(View view, ViewGroup.LayoutParams params) {
    wManager.updateViewLayout(view, params);
  }

  public void remove(View view) {
    phoneView.removeView(view);//移除窗口
  }

  private static int getScreenHeightPx(Context context) {
    if (screenHeightPx == 0) {
      WindowManager windowManager = (WindowManager) context
        .getSystemService(Context.WINDOW_SERVICE);
      Display defaultDisplay = windowManager.getDefaultDisplay();
      DisplayMetrics metrics = new DisplayMetrics();
      defaultDisplay.getMetrics(metrics);
      screenHeightPx = metrics.heightPixels;
    }
    return screenHeightPx;
  }

  private static int getScreenWidthPx(Context context) {
    if (screenWidthPx == 0) {
      WindowManager windowManager = (WindowManager) context
        .getSystemService(Context.WINDOW_SERVICE);
      Display defaultDisplay = windowManager.getDefaultDisplay();
      DisplayMetrics metrics = new DisplayMetrics();
      defaultDisplay.getMetrics(metrics);
      screenWidthPx = metrics.widthPixels;
    }
    return screenWidthPx;
  }

  //哪个线程创建 就在哪个线程销毁 有线程检测的代码
  public void destroy() {
    Log.d(TAG, "destroy thread=" + Thread.currentThread().getName());
    attached = false;
    wManager.removeViewImmediate(phoneView);
  }
}
