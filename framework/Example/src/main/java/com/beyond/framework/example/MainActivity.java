package com.beyond.framework.example;

import android.os.Bundle;

import com.beyond.framework.activity.AbsActivity;

public class MainActivity extends AbsActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
  }
}
