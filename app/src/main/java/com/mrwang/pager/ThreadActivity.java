package com.mrwang.pager;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.mrwang.freepager.page.FreePageContainer;

public class ThreadActivity extends AppCompatActivity {

  private FreePageContainer freePage;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_thread);

    freePage = (FreePageContainer) findViewById(R.id.free_page);

    MyPage myPage = new MyPage(getApplicationContext());
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
