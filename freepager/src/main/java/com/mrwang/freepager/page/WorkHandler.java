package com.mrwang.freepager.page;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * User: chengwangyong(chengwangyong@blinnnk.com)
 * Date: 2017/8/2
 * Time: 上午11:35
 */
public class WorkHandler extends HandlerThread {
  private Handler workHandler = null;
  private volatile List<WorkMessageProxy> messageProxyList;

  public WorkHandler() {
    super("WorkHandler", 10);
    this.start();
    this.workHandler = new Handler(this.getLooper()) {
      public void handleMessage(Message msg) {
        if(WorkHandler.this.messageProxyList != null) {
          Iterator var2 = WorkHandler.this.messageProxyList.iterator();

          while(var2.hasNext()) {
            WorkHandler.WorkMessageProxy workMessageProxy = (WorkHandler.WorkMessageProxy)var2.next();
            workMessageProxy.handleMessage(msg);
          }
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

  public void addMessageProxy(WorkHandler.WorkMessageProxy proxy) {
    this.initMessageProxyList();
    this.messageProxyList.add(proxy);
  }

  public void removeMessageProxy(WorkHandler.WorkMessageProxy proxy) {
    this.initMessageProxyList();
    this.messageProxyList.remove(proxy);
  }

  private void initMessageProxyList() {
    if(this.messageProxyList == null) {
      this.messageProxyList = new ArrayList();
    }

  }

  public Handler getHandler() {
    return this.workHandler;
  }

  public interface WorkMessageProxy {
    void handleMessage(Message var1);
  }
}
