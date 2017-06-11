package framework.beyond.com.framework.activity;

import java.io.Serializable;

import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import example.beyond.com.framework.util.Util;
import example.beyond.com.framework.util.ViewUtil;

public class AbsActivity extends AppCompatActivity {

  public TextView findTv(int id) {
    TextView textView = findView(id);
    return textView;
  }

  public <V extends View> V findView(int id) {
    return ViewUtil.findView(this, id);
  }

  public <V extends View> V findView(View parentView, int id) {
    return ViewUtil.findView(parentView, id);
  }

  public <T extends Serializable> T getSerializableExtra(String name) {
    return Util.getSerializable(getIntent().getExtras(), name);
  }

}
