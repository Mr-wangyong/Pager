package com.mrwang.freepager;

import android.graphics.Canvas;

public interface AnimationInterface {

  void present(Canvas canvas, float deltaTime);
  void update(float deltaTime);
  void clearDrawingAnimation();
  void stopAndClear();
}
