package com.mrwang.pager;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.mrwang.freepager.GifSurface;
import com.mrwang.freepager.page.FreePage;

public class ThreadActivity extends AppCompatActivity {

  private GifSurface gifSurface;
  private ViewAnim anim;
  private FreePage freePage;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_thread);

    freePage = (FreePage) findViewById(R.id.free_page);

    freePage.start();
    MyPage myPage=new MyPage(getApplicationContext());
    freePage.addPage(myPage);
  }


  @Override
  protected void onPause() {
    freePage.destroyPage();
    super.onPause();
  }

  @Override
  protected void onResume() {
    freePage.resumePage();
    super.onResume();
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    freePage.destroy();
  }
}
