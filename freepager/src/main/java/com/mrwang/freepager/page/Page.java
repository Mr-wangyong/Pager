package com.mrwang.freepager.page;

import android.content.Context;
import android.view.View;

/**
 * User: chengwangyong(chengwangyong@blinnnk.com)
 * Date: 2017/8/2
 * Time: 上午10:43
 */
public abstract class Page implements IPage {
  protected View contentView;
  protected Context context;

  public Page(Context context) {
    this.context = context;
  }

  @Override
  public View getView() {
    contentView = inflateView();
    return contentView;
  }

  protected abstract View inflateView();

}
