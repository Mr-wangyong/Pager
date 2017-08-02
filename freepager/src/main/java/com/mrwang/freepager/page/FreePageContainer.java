package com.mrwang.freepager.page;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.widget.FrameLayout;


/**
 * User: chengwangyong(chengwangyong@blinnnk.com)
 * Date: 2017/8/1
 * Time: 下午5:41
 */
public class FreePageContainer extends FrameLayout implements WorkHandler.WorkMessageProxy {

  public Handler surfaceHandler;
  private FreePage freePage;
  private final int INIT = 0x00;
  private final int DESTROY = 0x10;
  private final int ADD_VIEW = 0x11;
  private final int RESUME = 0x12;
  private WorkHandler workHandlerThread;

  public FreePageContainer(Context context) {
    this(context, null);
  }

  public FreePageContainer(Context context,AttributeSet attrs) {
    this(context, attrs, 0);
  }

  public FreePageContainer(Context context,AttributeSet attrs,int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    init();
  }

  private void init() {
    workHandlerThread = new WorkHandler();
    surfaceHandler = workHandlerThread.getHandler();
    workHandlerThread.addMessageProxy(this);
    surfaceHandler.sendEmptyMessage(INIT);
  }

  @Override
  public void handleMessage(Message msg) {
    if (msg != null) {
      switch (msg.what) {
        case INIT:
          if (freePage == null) {
            freePage = new FreePage(getContext());
          }
        case DESTROY:
          freePage.destroy();
          break;
        case ADD_VIEW:
          IPage page = (IPage) msg.obj;
          freePage.show(page.getView());
          page.start();
          break;
        case RESUME:
          freePage.initView();
          break;
      }
    }
  }


  public void destroyPage() {
    surfaceHandler.sendEmptyMessage(DESTROY);
  }

  public void destroy() {
    workHandlerThread.quit();
  }


  public void addPage(IPage myPage) {
    Message msg = Message.obtain();
    msg.what = ADD_VIEW;
    msg.obj = myPage;
    surfaceHandler.sendMessage(msg);
  }

  public void resumePage() {
    surfaceHandler.sendEmptyMessage(RESUME);
  }


}
