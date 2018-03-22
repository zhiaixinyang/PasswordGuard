package com.mdove.passwordguard.ui.guideview.component;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import com.mdove.passwordguard.R;
import com.mdove.passwordguard.ui.guideview.Component;
import com.mdove.passwordguard.utils.ToastHelper;


/**
 * Created by MDove on 18/3/22.
 */
public class SimpleComponent implements Component {

  @Override public View getView(LayoutInflater inflater) {
    LinearLayout ll = (LinearLayout) inflater.inflate(R.layout.layout_layer_main_option, null);
    ll.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View view) {
        ToastHelper.shortToast("您可以点击其他空白区域，退出引导");
      }
    });
    return ll;
  }

  @Override public int getAnchor() {
    return Component.ANCHOR_BOTTOM;
  }

  @Override public int getFitPosition() {
    return Component.FIT_CENTER;
  }

  @Override public int getXOffset() {
    return 0;
  }

  @Override public int getYOffset() {
    return 10;
  }
}
