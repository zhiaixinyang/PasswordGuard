package com.mdove.passwordguard.base.listlayout;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.mdove.passwordguard.R;
import com.mdove.passwordguard.base.listlayout.annotation.TitleLayout;

@TitleLayout(titleLayout = R.layout.view_base_title)
public class TestActivity extends BaseListActivity {

    public static void start(Context context) {
        Intent start = new Intent(context, TestActivity.class);
        if (!(context instanceof Activity)) {
            start.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        context.startActivity(start);
    }
}
