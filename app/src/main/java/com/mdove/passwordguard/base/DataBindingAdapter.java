package com.mdove.passwordguard.base;

import android.databinding.BindingAdapter;
import android.databinding.adapters.TextViewBindingAdapter;
import android.support.constraint.ConstraintLayout;
import android.support.v4.content.ContextCompat;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mdove.passwordguard.R;
import com.mdove.passwordguard.home.todayreview.model.BaseTodayReViewModel;
import com.mdove.passwordguard.home.todayreview.model.vm.BaseTodayReViewVM;
import com.mdove.passwordguard.main.model.BaseMainModel;
import com.mdove.passwordguard.main.model.DailyPlanModel;
import com.mdove.passwordguard.main.model.vm.DailyPlanModelVM;
import com.mdove.passwordguard.main.newmain.everydayreplay.model.EverydayReplayRlvModelVM;
import com.mdove.passwordguard.main.newmain.home.model.EverydayReplayModelVM;
import com.mdove.passwordguard.singleplan.model.SinglePlanRlvModelVM;
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
    public static void loadLostAndGetBg(ConstraintLayout view, final DailyPlanModelVM modelVM) {
        switch (modelVM.mStatus.get()) {
            case DailyPlanModel.STATUS_NORMAL: {
                view.setBackgroundResource(R.drawable.bg_normal_white_round);
                break;
            }
            case DailyPlanModel.STATUS_GET: {
//                view.setBackgroundResource(R.drawable.bg_daily_plan_get);
                view.setBackgroundResource(R.drawable.bg_normal_white_round);
                break;
            }
            case DailyPlanModel.STATUS_LOST: {
//                view.setBackgroundResource(R.drawable.bg_daily_plan_lose);
                view.setBackgroundResource(R.drawable.bg_normal_white_round);
                break;
            }
            default: {
//                view.setBackgroundResource(R.drawable.bg_normal_white_round);
                view.setBackgroundResource(R.drawable.bg_normal_white_round);
                break;
            }
        }
    }

    @BindingAdapter({"loadLostAndGetIconBg"})
    public static void loadLostAndGetIconBg(TextView view, final EverydayReplayModelVM modelVM) {
        switch (modelVM.mStatus.get()) {
            case DailyPlanModel.STATUS_NORMAL: {
                view.setBackgroundResource(R.drawable.bg_everyday_replay_normal);
                TextViewBindingAdapter.setText(view, "淡");
                view.setTextColor(ContextCompat.getColor(view.getContext(), R.color.black));
                break;
            }
            case DailyPlanModel.STATUS_GET: {
                view.setBackgroundResource(R.drawable.bg_everyday_replay_get);
                TextViewBindingAdapter.setText(view, "得");
                view.setTextColor(ContextCompat.getColor(view.getContext(), R.color.green_300));
                break;
            }
            case DailyPlanModel.STATUS_LOST: {
                view.setBackgroundResource(R.drawable.bg_everyday_replay_lose);
                TextViewBindingAdapter.setText(view, "失");
                view.setTextColor(ContextCompat.getColor(view.getContext(), R.color.red_300));
                break;
            }
            default: {
                view.setBackgroundResource(R.drawable.bg_everyday_replay_normal);
                break;
            }
        }
    }

    @BindingAdapter({"loadLostAndGetIconBg"})
    public static void loadLostAndGetIconBg(TextView view, final EverydayReplayRlvModelVM modelVM) {
        switch (modelVM.mStatus.get()) {
            case DailyPlanModel.STATUS_NORMAL: {
                view.setBackgroundResource(R.drawable.bg_everyday_replay_normal);
                TextViewBindingAdapter.setText(view, "淡");
                view.setTextColor(ContextCompat.getColor(view.getContext(), R.color.black));
                break;
            }
            case DailyPlanModel.STATUS_GET: {
                view.setBackgroundResource(R.drawable.bg_everyday_replay_get);
                TextViewBindingAdapter.setText(view, "得");
                view.setTextColor(ContextCompat.getColor(view.getContext(), R.color.green_300));
                break;
            }
            case DailyPlanModel.STATUS_LOST: {
                view.setBackgroundResource(R.drawable.bg_everyday_replay_lose);
                TextViewBindingAdapter.setText(view, "失");
                view.setTextColor(ContextCompat.getColor(view.getContext(), R.color.red_300));
                break;
            }
            default: {
                view.setBackgroundResource(R.drawable.bg_everyday_replay_normal);
                break;
            }
        }
    }

    @BindingAdapter("loadLostAndGetBg")
    public static void loadLostAndGetBg(ConstraintLayout view, final EverydayReplayRlvModelVM modelVM) {
        switch (modelVM.mStatus.get()) {
            case DailyPlanModel.STATUS_NORMAL: {
                view.setBackgroundResource(R.drawable.bg_normal_white_round);
                break;
            }
            case DailyPlanModel.STATUS_GET: {
//                view.setBackgroundResource(R.drawable.bg_daily_plan_get);
                view.setBackgroundResource(R.drawable.bg_normal_white_round);
                break;
            }
            case DailyPlanModel.STATUS_LOST: {
//                view.setBackgroundResource(R.drawable.bg_daily_plan_lose);
                view.setBackgroundResource(R.drawable.bg_normal_white_round);
                break;
            }
            default: {
//                view.setBackgroundResource(R.drawable.bg_normal_white_round);
                view.setBackgroundResource(R.drawable.bg_normal_white_round);
                break;
            }
        }
    }

    @BindingAdapter("loadLostAndGetBg")
    public static void loadLostAndGetBg(ConstraintLayout view, final EverydayReplayModelVM modelVM) {
        switch (modelVM.mStatus.get()) {
            case DailyPlanModel.STATUS_NORMAL: {
                view.setBackgroundResource(R.drawable.bg_normal_white_round);
                break;
            }
            case DailyPlanModel.STATUS_GET: {
//                view.setBackgroundResource(R.drawable.bg_daily_plan_get);
                view.setBackgroundResource(R.drawable.bg_normal_white_round);
                break;
            }
            case DailyPlanModel.STATUS_LOST: {
//                view.setBackgroundResource(R.drawable.bg_daily_plan_lose);
                view.setBackgroundResource(R.drawable.bg_normal_white_round);
                break;
            }
            default: {
//                view.setBackgroundResource(R.drawable.bg_normal_white_round);
                view.setBackgroundResource(R.drawable.bg_normal_white_round);
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

    @BindingAdapter("srcBgSinglePlan")
    public static void srcBgSinglePlan(ImageView view, SinglePlanRlvModelVM vm) {
        if (vm.mIsMain.get() == SinglePlanRlvModelVM.BG_IS_MAIN_PLAN) {
            view.setImageResource(R.mipmap.ic_main_plan);
        } else {
            view.setImageResource(R.mipmap.ic_second_plan);
        }
    }

    @BindingAdapter("sucAtTimeBgTv")
    public static void sucAtTimeBgTv(TextView view, BaseTodayReViewVM vm) {
        switch (vm.mSucEnable.get()) {
            case BaseTodayReViewModel.DEFAULT_NOT_SUC: {
                break;
            }
            case BaseTodayReViewModel.DEFAULT_SUC_AT_TIME: {
                view.setText("按时完成");
                view.setBackgroundResource(R.drawable.bg_suc_at_time);
                break;
            }
            case BaseTodayReViewModel.DEFAULT_SUC_NO_AT_TIME_PRE: {
                view.setText("提前完成");
                view.setBackgroundResource(R.drawable.bg_suc_not_at_time_pre);
                break;
            }
            case BaseTodayReViewModel.DEFAULT_SUC_NO_AT_TIME_LAST: {
                view.setText("超时完成");
                view.setBackgroundResource(R.drawable.bg_suc_not_at_time_last);
                break;
            }
            default: {
                break;
            }
        }
    }
}
