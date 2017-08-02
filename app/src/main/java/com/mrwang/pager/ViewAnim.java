package com.mrwang.pager;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.mrwang.freepager.AnimationInterface;
import com.mrwang.freepager.ThreadView;

/**
 * User: chengwangyong(chengwangyong@blinnnk.com)
 * Date: 2017/7/21
 * Time: 下午1:44
 */
public class ViewAnim implements AnimationInterface {


  private Context context;
  private boolean isDraw = false;
  private  ThreadView threadView;
  private ValueAnimator valueAnimator;

  public ViewAnim(Context context) {
    this.context = context;
  }

  @Override
  public void present(Canvas canvas, float deltaTime) {
    if (!isDraw) {
      isDraw = true;
      threadView = new ThreadView(context);
      // Toast.makeText(context, "你猜我能不能弹出来～～", Toast.LENGTH_LONG).show();// Toast初始化的时候会new
      Log.i("TAG", "Thread present thread=" + Thread.currentThread().getName());
      View inflate = View.inflate(context, R.layout.thread_view, null);
      final TextView textView = (TextView) inflate.findViewById(R.id.thread_view);
      valueAnimator = ValueAnimator.ofFloat(1.0f, 0.0f, 1.0f);
      valueAnimator.setDuration(50000L);
      valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
        @Override
        public void onAnimationUpdate(ValueAnimator animation) {
          Log.i("TAG", "Thread onAnimationUpdate thread=" + Thread.currentThread().getName());
          float animatedFraction = (float) animation.getAnimatedValue();
          textView.setText("缩放 =" + animatedFraction);
          textView.setScaleX(animatedFraction);
          textView.setScaleY(animatedFraction);
        }
      });
      threadView.show(inflate);
      valueAnimator.start();

    }
  }

  @Override
  public void update(float deltaTime) {

  }

  @Override
  public void clearDrawingAnimation() {

  }

  @Override
  public void stopAndClear() {
    destroy();
  }


  public void destroy() {
    valueAnimator.cancel();
    threadView.destroy();
  }
}
