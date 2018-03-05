package com.mdove.passwordguard.base;

import android.content.Intent;
import android.databinding.BindingAdapter;
import android.net.Uri;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.mdove.passwordguard.R;
import com.mdove.passwordguard.dailyself.ItemMainDailySelfVM;

/**
 * Created by MDove on 2018/3/5.
 */

public class DataBindingAdapter {
    @BindingAdapter("loadFavorite")
    public static void loadFavorite(ImageView view, final ItemMainDailySelfVM vm) {
        view.setImageResource(vm.mIsFavorite.get() ? R.mipmap.ic_favorite_on : R.mipmap.ic_favorite_off);
    }
}
