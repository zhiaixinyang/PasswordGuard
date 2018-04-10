package com.mdove.passwordguard.base;

import android.databinding.BindingAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mdove.passwordguard.R;
import com.mdove.passwordguard.main.model.DailyPlanModel;
import com.mdove.passwordguard.main.model.vm.DailyPlanModelVM;
import com.mdove.passwordguard.task.model.SelfTaskModelVM;

/**
 * Created by MDove on 2018/3/5.
 */

public class DataBindingAdapter {
    @BindingAdapter("loadFavorite")
    public static void loadFavorite(ImageView view, final IFavoriteVM vm) {
        view.setImageResource(vm.isFavorite() ? R.mipmap.ic_favorite_on : R.mipmap.ic_favorite_off);
    }

    @BindingAdapter("loadLostAndGetBg")
    public static void loadLostAndGetBg(RelativeLayout view, final DailyPlanModelVM modelVM) {
        switch (modelVM.mStatus.get()) {
            case DailyPlanModel.STATUS_NORMAL: {
                view.setBackgroundResource(R.drawable.bg_normal_white);
                break;
            }
            case DailyPlanModel.STATUS_GET: {
                view.setBackgroundResource(R.drawable.bg_daily_plan_get);
                break;
            }
            case DailyPlanModel.STATUS_LOST: {
                view.setBackgroundResource(R.drawable.bg_daily_plan_lose);
                break;
            }
        }
    }

    @BindingAdapter("loadHide")
    public static void loadHide(ImageView view, final IHideVM vm) {
        view.setImageResource(vm.isHide() ? R.mipmap.ic_item_hide_off : R.mipmap.ic_item_hide_on);
    }


    @BindingAdapter("isTaskSucTv")
    public static void isTaskSucTv(TextView view, final SelfTaskModelVM vm) {
        if (vm.mIsSuc.get()) {
            view.setText("取消");
        } else {
            view.setText("完成");
        }
    }

    @BindingAdapter("isTaskSucRl")
    public static void isTaskSucRl(RelativeLayout view, final SelfTaskModelVM vm) {
        view.setBackgroundResource(vm.mIsSuc.get() ? R.drawable.bg_item_self_task_btn_on : R.drawable.bg_item_self_task_btn_off);
    }

    @BindingAdapter("mipmapSrc")
    public static void mipmapSrc(ImageView view, int src) {
        view.setImageResource(src);
    }

    @BindingAdapter("srcDrawable")
    public static void srcDrawable(ImageView view, int src) {
        view.setBackgroundResource(src);
    }
}
