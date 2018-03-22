package com.mdove.passwordguard.ui.guideview.component;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mdove.passwordguard.R;
import com.mdove.passwordguard.ui.guideview.Component;
import com.mdove.passwordguard.utils.ToastHelper;


/**
 * Created by MDove on 18/3/22.
 */
public class CommonComponent implements Component {
    private String mContent;

    public CommonComponent(String content) {
        mContent = content;
    }

    @Override
    public View getView(LayoutInflater inflater) {
        LinearLayout ll = (LinearLayout) inflater.inflate(R.layout.layout_layer_guide_common, null);
        ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ToastHelper.shortToast("您可以点击其他空白区域，进入下一个引导");
            }
        });
        TextView textView = ll.findViewById(R.id.tv_content);
        textView.setText(mContent);
        return ll;
    }

    @Override
    public int getAnchor() {
        return Component.ANCHOR_BOTTOM;
    }

    @Override
    public int getFitPosition() {
        return Component.FIT_CENTER;
    }

    @Override
    public int getXOffset() {
        return 0;
    }

    @Override
    public int getYOffset() {
        return 10;
    }
}
