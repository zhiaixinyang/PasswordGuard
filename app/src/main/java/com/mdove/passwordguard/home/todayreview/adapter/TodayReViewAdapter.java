package com.mdove.passwordguard.home.todayreview.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.mdove.passwordguard.R;
import com.mdove.passwordguard.databinding.ItemNewHomeAddPlanBinding;
import com.mdove.passwordguard.databinding.ItemNewHomeMainTodayReviewBinding;
import com.mdove.passwordguard.databinding.ItemNewHomeSecondTodayReviewBinding;
import com.mdove.passwordguard.home.ettodayplan.EtTodayPlanActivity;
import com.mdove.passwordguard.home.todayreview.model.BaseTodayReViewModel;
import com.mdove.passwordguard.home.todayreview.model.MainTodayReViewModel;
import com.mdove.passwordguard.home.todayreview.model.SecondTodayReViewModel;
import com.mdove.passwordguard.home.todayreview.model.handler.TodayReViewHandler;
import com.mdove.passwordguard.home.todayreview.model.vm.MainTodayReViewModelVM;
import com.mdove.passwordguard.home.todayreview.model.vm.SecondTodayReViewModelVM;
import com.mdove.passwordguard.home.todayreview.presenter.TodayReViewPresenter;
import com.mdove.passwordguard.utils.InflateUtils;

import java.util.List;

/**
 * Created by MDove on 2018/6/29.
 */

public class TodayReViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int TYPE_MAIN_TODAY_REVIEW = 0;
    private static final int TYPE_SECOND_TODAY_REVIEW = 1;
    private static final int TYPE_ADD_REVIEW = 2;
    private List<BaseTodayReViewModel> mData;
    private Context mContext;
    private TodayReViewPresenter mPresenter;

    public TodayReViewAdapter(Context context, List<BaseTodayReViewModel> data, TodayReViewPresenter presenter) {
        mContext = context;
        mData = data;
        mPresenter = presenter;
    }

    @Override
    public int getItemViewType(int position) {
        BaseTodayReViewModel model = mData.get(position);
        if (model instanceof MainTodayReViewModel) {
            return TYPE_MAIN_TODAY_REVIEW;
        } else if (model instanceof SecondTodayReViewModel) {
            return TYPE_SECOND_TODAY_REVIEW;
        }
        return TYPE_SECOND_TODAY_REVIEW;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case TYPE_MAIN_TODAY_REVIEW: {
                return new MainTodayReViewViewHolder((ItemNewHomeMainTodayReviewBinding)
                        InflateUtils.bindingInflate(parent, R.layout.item_new_home_main_today_review));
            }
            case TYPE_SECOND_TODAY_REVIEW: {
                return new SecondTodayReViewViewHolder((ItemNewHomeSecondTodayReviewBinding)
                        InflateUtils.bindingInflate(parent, R.layout.item_new_home_second_today_review));
            }
            default: {
                return new MainTodayReViewViewHolder((ItemNewHomeMainTodayReviewBinding)
                        InflateUtils.bindingInflate(parent, R.layout.item_new_home_main_today_review));
            }
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof MainTodayReViewViewHolder) {
            ((MainTodayReViewViewHolder) holder).bind(mData.get(position));
        } else if (holder instanceof SecondTodayReViewViewHolder) {
            ((SecondTodayReViewViewHolder) holder).bind(mData.get(position));
        } else if (holder instanceof AddTodayPlanViewHolder) {
            ((AddTodayPlanViewHolder) holder).bind();
        }
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class MainTodayReViewViewHolder extends RecyclerView.ViewHolder {
        private ItemNewHomeMainTodayReviewBinding mBinding;

        public MainTodayReViewViewHolder(ItemNewHomeMainTodayReviewBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        public void bind(BaseTodayReViewModel model) {
            mBinding.setHandler(new TodayReViewHandler(mPresenter));

            if (model.mIsSuc > 0) {
                mBinding.tvSinglePlan.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
                mBinding.tvSinglePlan.setTextColor(ContextCompat.getColor(mContext, R.color.gray_light));

                mBinding.layoutBtn.setBackgroundResource(R.drawable.bg_item_self_task_btn_off);
                mBinding.tvBtn.setText("取消");
            } else {
                mBinding.tvSinglePlan.getPaint().setFlags(0);
                mBinding.tvSinglePlan.getPaint().setAntiAlias(true);
                mBinding.tvSinglePlan.setTextColor(ContextCompat.getColor(mContext, R.color.black));

                mBinding.layoutBtn.setBackgroundResource(R.drawable.bg_item_self_task_btn_on);
                mBinding.tvBtn.setText("完成");
            }
            mBinding.setVm(new MainTodayReViewModelVM((MainTodayReViewModel) model));
        }
    }

    public class SecondTodayReViewViewHolder extends RecyclerView.ViewHolder {
        private ItemNewHomeSecondTodayReviewBinding mBinding;

        public SecondTodayReViewViewHolder(ItemNewHomeSecondTodayReviewBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        public void bind(BaseTodayReViewModel model) {
            mBinding.setHandler(new TodayReViewHandler(mPresenter));

            if (model.mIsSuc > 0) {
                mBinding.tvSinglePlan.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
                mBinding.tvSinglePlan.setTextColor(ContextCompat.getColor(mContext, R.color.gray_light));

                mBinding.layoutBtn.setBackgroundResource(R.drawable.bg_item_self_task_btn_off);
                mBinding.tvBtn.setText("取消");
            } else {
                mBinding.tvSinglePlan.getPaint().setFlags(0);
                mBinding.tvSinglePlan.getPaint().setAntiAlias(true);
                mBinding.tvSinglePlan.setTextColor(ContextCompat.getColor(mContext, R.color.black));

                mBinding.layoutBtn.setBackgroundResource(R.drawable.bg_item_self_task_btn_on);
                mBinding.tvBtn.setText("完成");
            }
            mBinding.setVm(new SecondTodayReViewModelVM((SecondTodayReViewModel) model));
        }
    }

    public class AddTodayPlanViewHolder extends RecyclerView.ViewHolder {
        private ItemNewHomeAddPlanBinding mBinding;

        public AddTodayPlanViewHolder(ItemNewHomeAddPlanBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        public void bind() {
            mBinding.btnAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    EtTodayPlanActivity.start(mContext);
                }
            });
        }
    }

    public void setData(List<BaseTodayReViewModel> data) {
        mData = data;
        notifyDataSetChanged();
    }

    public void insertData(BaseTodayReViewModel model) {
        mData.add(model);
        notifyItemChanged(mData.size());
    }
}
