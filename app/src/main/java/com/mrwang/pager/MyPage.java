package com.mrwang.pager;

import android.animation.ValueAnimator;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.mrwang.freepager.page.Page;

/**
 * 具体的页面 方便集成到 freePage 里面
 * User: chengwangyong(chengwangyong@blinnnk.com)
 * Date: 2017/8/2
 * Time: 上午10:39
 */
public class MyPage extends Page {

  private ValueAnimator valueAnimator;

  public MyPage(Context context) {
    super(context);
  }


  @Override
  protected View inflateView() {
    return View.inflate(context, R.layout.thread_view, null);
  }

  @Override
  public void start() {
    if (valueAnimator == null) {

      final TextView textView = (TextView) contentView.findViewById(R.id.thread_view);
      valueAnimator = ValueAnimator.ofFloat(0.0f, 1.0f);
      valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
        @Override
        public void onAnimationUpdate(ValueAnimator animation) {
          //设置动画
          Log.i("TAG", "Thread onAnimationUpdate thread=" + Thread.currentThread().getName());
          float animatedFraction = (float) animation.getAnimatedValue();
          textView.setText(String.format("缩放 =%s", animatedFraction));
          textView.setScaleX(animatedFraction);
          textView.setScaleY(animatedFraction);
        }
      });
      valueAnimator.setDuration(2000L);
    }
    valueAnimator.start();
  }
}
