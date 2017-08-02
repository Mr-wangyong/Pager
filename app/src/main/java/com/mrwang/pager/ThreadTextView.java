package com.mrwang.pager;

import android.content.Context;
import android.graphics.Canvas;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.util.Log;

/**
 * 测试的 view 看看 View 的生命周期都在哪个线程
 * User: chengwangyong(chengwangyong@blinnnk.com)
 * Date: 2017/7/21
 * Time: 下午7:03
 */
public class ThreadTextView extends AppCompatTextView {
  public ThreadTextView(Context context) {
    super(context);
  }

  public ThreadTextView(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  public ThreadTextView(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
  }

  @Override
  protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    Log.i("TAG","Thread onMeasure thread="+Thread.currentThread().getName());
    super.onMeasure(widthMeasureSpec, heightMeasureSpec);
  }

  @Override
  protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
    Log.i("TAG","Thread onLayout thread="+Thread.currentThread().getName());
    super.onLayout(changed, left, top, right, bottom);
  }

  @Override
  protected void onDraw(Canvas canvas) {
    Log.i("TAG","Thread onDraw thread="+Thread.currentThread().getName());
    super.onDraw(canvas);
  }
}
