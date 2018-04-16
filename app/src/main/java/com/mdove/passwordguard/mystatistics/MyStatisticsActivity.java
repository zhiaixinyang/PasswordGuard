package com.mdove.passwordguard.mystatistics;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.mdove.passwordguard.R;
import com.mdove.passwordguard.base.BaseActivity;

/**
 * Created by MDove on 2018/4/16.
 */

public class MyStatisticsActivity extends BaseActivity{
    public static void start(Context context) {
        Intent start = new Intent(context, MyStatisticsActivity.class);
        if (!(context instanceof Activity)) {
            start.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        context.startActivity(start);
    }

    @Override
    protected boolean isNeedCustomLayout() {
        return false;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("我的记录统计");
        setContentView(R.layout.activity_statistics);
    }
}
