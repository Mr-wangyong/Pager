package com.mrwang.freepager.page;

import android.content.Context;
import android.os.Message;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import com.mrwang.freepager.SurfaceHandler;

/**
 * User: chengwangyong(chengwangyong@blinnnk.com)
 * Date: 2017/8/1
 * Time: 下午5:41
 */
public class FreePageContainer extends FrameLayout implements SurfaceHandler.OnThreadLooperPrepared {
  public SurfaceHandler surfaceHandler;
  private FreePage freePage;
  private final int DESTROY = 0x10;
  private final int ADD_VIEW = 0x11;
  private final int RESUME = 0x12;

  public FreePageContainer(@NonNull Context context) {
    this(context, null);
  }

  public FreePageContainer(@NonNull Context context, @Nullable AttributeSet attrs) {
    this(context, attrs, 0);
  }

  public FreePageContainer(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    init();
  }

  private void init() {
    surfaceHandler = new SurfaceHandler();
    surfaceHandler.setOnLoopPrepared(this);
  }


  @Override
  public void OnThreadLooperPrepared(Message msg) {
    if (freePage == null) {
      freePage = new FreePage(getContext());
    }
    if (msg != null) {
      switch (msg.what) {
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
    surfaceHandler.sendMessage(DESTROY);
  }

  public void destroy() {
    surfaceHandler.quit();
  }


  public void addPage(IPage myPage) {
    Message msg = Message.obtain();
    msg.what = ADD_VIEW;
    msg.obj = myPage;
    surfaceHandler.sendMessage(msg);
  }

  public void resumePage() {
    surfaceHandler.sendMessage(RESUME);
  }
}
