package com.mdove.passwordguard.base.listlayout;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.mdove.passwordguard.R;
import com.mdove.passwordguard.base.listlayout.annotation.CustomViewHolderLayout;
import com.mdove.passwordguard.base.listlayout.annotation.TitleLayout;
import com.mdove.passwordguard.base.listlayout.model.BaseModelVM;
import com.mdove.passwordguard.base.listlayout.model.TestModelVM;
import com.mdove.passwordguard.utils.DateUtils;

import java.util.ArrayList;
import java.util.List;

@TitleLayout(titleLayout = R.layout.view_base_title)
@CustomViewHolderLayout(customViewHolderLayout = R.layout.item_base_list)
public class TestActivity extends BaseListActivity {

    public static void start(Context context) {
        Intent start = new Intent(context, TestActivity.class);
        if (!(context instanceof Activity)) {
            start.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        context.startActivity(start);
    }

    @Override
    protected void onCreateOver() {
        List<BaseModelVM> data = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            data.add(new TestModelVM(DateUtils.getDateChinese(), "哈哈哈" + i));
        }
        setData(data);
    }
}
