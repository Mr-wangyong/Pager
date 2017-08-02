package com.mrwang.pager;

import android.animation.ValueAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.mrwang.freepager.ThreadView;

public class MainActivity extends Activity {

  private ValueAnimator valueAnimator;
  private ThreadView threadView;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);


    threadView = new ThreadView(getApplicationContext());
    View inflate = View.inflate(getApplicationContext(), R.layout.thread_view, null);
//    final TextView textView = (TextView) inflate.findViewById(R.id.thread_view);
//    valueAnimator = ValueAnimator.ofFloat(1.0f, 0.0f, 1.0f);
//    valueAnimator.setDuration(50000L);
//    valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//      @Override
//      public void onAnimationUpdate(ValueAnimator animation) {
//        Log.i("TAG", "Thread onAnimationUpdate thread=" + Thread.currentThread().getName());
//        float animatedFraction = (float) animation.getAnimatedValue();
//        textView.setText("缩放 =" + animatedFraction);
//        textView.setScaleX(animatedFraction);
//        textView.setScaleY(animatedFraction);
//      }
//    });
    threadView.show(inflate);
    //valueAnimator.start();

  }

  @Override
  protected void onDestroy() {
    //valueAnimator.cancel();
    threadView.destroy();
    super.onDestroy();
  }
}
