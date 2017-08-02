package com.mrwang.freepager;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.os.Process;

/**
 * User: chengwangyong(chengwangyong@blinnnk.com)
 * Date: 2017/7/21
 * Time: 下午6:02
 */
public class SurfaceHandler extends HandlerThread {

  private Handler workHandler = null;
  private OnThreadLooperPrepared onLoopPrepared;

  public SurfaceHandler() {
    super("SurfaceHandler", Process.THREAD_PRIORITY_BACKGROUND);
    this.start();
    this.workHandler = new Handler(this.getLooper()) {
      public void handleMessage(Message msg) {
        if (onLoopPrepared != null) {
          onLoopPrepared.OnThreadLooperPrepared(msg);
        }
      }
    };
  }


  public void post(Runnable run) {
    this.workHandler.post(run);
  }

  public void postAtFrontOfQueue(Runnable runnable) {
    this.workHandler.postAtFrontOfQueue(runnable);
  }

  public void postDelayed(Runnable runnable, long delay) {
    this.workHandler.postDelayed(runnable, delay);
  }

  public void postAtTime(Runnable runnable, long time) {
    this.workHandler.postAtTime(runnable, time);
  }


  public Handler getHandler() {
    return this.workHandler;
  }

  public void sendEmpty(int size) {
    this.workHandler.sendEmptyMessageDelayed(0, size > 0 ? 10 : 100);
  }

  public void sendMessage(Message message) {
    this.workHandler.sendMessage(message);
  }

  public void sendMessage(int what) {
    this.workHandler.sendEmptyMessage(what);
  }

  public interface OnThreadLooperPrepared {
    public void OnThreadLooperPrepared(Message what);
  }


  public void setOnLoopPrepared(OnThreadLooperPrepared onLoopPrepared) {
    this.onLoopPrepared = onLoopPrepared;
  }
}
