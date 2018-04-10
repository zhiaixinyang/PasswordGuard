package com.mdove.passwordguard.main.adapter;

import android.content.Context;
import android.graphics.PorterDuff;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import com.mdove.passwordguard.R;
import com.mdove.passwordguard.base.listener.OnChangeDataSizeListener;
import com.mdove.passwordguard.databinding.ItemMainDailyPlanTodayRlvBinding;
import com.mdove.passwordguard.main.model.DailyPlanModel;
import com.mdove.passwordguard.main.model.vm.DailyPlanModelVM;
import com.mdove.passwordguard.main.presenter.TodayPlanPresenter;
import com.mdove.passwordguard.utils.InflateUtils;

import java.util.List;

/**
 * Created by MDove on 2018/4/9.
 */

public class TodayPlanAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private TodayPlanPresenter mPresenter;
    private Context mContext;
    private List<DailyPlanModel> mData;
    private OnChangeDataSizeListener mListener;

    public TodayPlanAdapter(Context context, TodayPlanPresenter presenter) {
        mContext = context;
        mPresenter = presenter;
        registerAdapterDataObserver(mObserver);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new TodayViewHolder((ItemMainDailyPlanTodayRlvBinding) InflateUtils.bindingInflate(parent, R.layout.item_main_daily_plan_today_rlv));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        DailyPlanModel model = mData.get(position);
        ((TodayViewHolder) holder).bind(model, position, mPresenter);
    }

    public void setData(List<DailyPlanModel> data) {
        mData = data;
        notifyDataSetChanged();
    }

    public void notifyPosition(int position) {
        notifyItemChanged(position);
    }

    @Override
    public int getItemCount() {
        return mData != null ? mData.size() : 0;
    }

    public class TodayViewHolder extends RecyclerView.ViewHolder {
        private ItemMainDailyPlanTodayRlvBinding mBinding;

        public TodayViewHolder(ItemMainDailyPlanTodayRlvBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        public void bind(final DailyPlanModel dailyPlanModel, int position, final TodayPlanPresenter presenter) {
            mBinding.setViewModel(new DailyPlanModelVM(dailyPlanModel, position));
            switch (dailyPlanModel.mStatus) {
                case DailyPlanModel.STATUS_GET: {
                    mBinding.ivGet.setColorFilter(ContextCompat.getColor(mContext, R.color.black), PorterDuff.Mode.SRC_ATOP);
                    mBinding.tvGet.setTextColor(ContextCompat.getColor(mContext, R.color.black));
                    mBinding.ivLost.setColorFilter(ContextCompat.getColor(mContext, R.color.gray), PorterDuff.Mode.SRC_ATOP);
                    mBinding.tvLost.setTextColor(ContextCompat.getColor(mContext, R.color.gray));
                    break;
                }
                case DailyPlanModel.STATUS_LOST: {
                    mBinding.ivGet.setColorFilter(ContextCompat.getColor(mContext, R.color.gray), PorterDuff.Mode.SRC_ATOP);
                    mBinding.tvGet.setTextColor(ContextCompat.getColor(mContext, R.color.gray));
                    mBinding.ivLost.setColorFilter(ContextCompat.getColor(mContext, R.color.black), PorterDuff.Mode.SRC_ATOP);
                    mBinding.tvLost.setTextColor(ContextCompat.getColor(mContext, R.color.black));
                    break;
                }
                default: {
                    mBinding.ivGet.setColorFilter(ContextCompat.getColor(mContext, R.color.gray), PorterDuff.Mode.SRC_ATOP);
                    mBinding.tvGet.setTextColor(ContextCompat.getColor(mContext, R.color.gray));
                    mBinding.ivLost.setColorFilter(ContextCompat.getColor(mContext, R.color.gray), PorterDuff.Mode.SRC_ATOP);
                    mBinding.tvLost.setTextColor(ContextCompat.getColor(mContext, R.color.gray));
                    break;
                }
            }
            mBinding.btnGet.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (dailyPlanModel.mStatus != DailyPlanModel.STATUS_GET) {
                        presenter.updateLostOrGet(dailyPlanModel.mId, true);
                    }
                }
            });
            mBinding.btnLost.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (dailyPlanModel.mStatus != DailyPlanModel.STATUS_LOST) {
                        presenter.updateLostOrGet(dailyPlanModel.mId, false);
                    }
                }
            });
        }
    }

    private RecyclerView.AdapterDataObserver mObserver = new RecyclerView.AdapterDataObserver() {
        @Override
        public void onChanged() {
            onDataChange(mData.size());
        }

        @Override
        public void onItemRangeChanged(int positionStart, int itemCount) {
            onDataChange(mData.size());
        }

        @Override
        public void onItemRangeRemoved(int positionStart, int itemCount) {
            onDataChange(mData.size());
        }
    };

    @Override
    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
        unregisterAdapterDataObserver(mObserver);
    }

    private void onDataChange(int dataSize) {
        if (mListener != null) {
            if (dataSize <= 0) {
                mListener.dataIsEmpty(true);
            } else {
                mListener.dataIsEmpty(false);
            }
        }
    }

    public void setOnChangeDataSizeListener(OnChangeDataSizeListener listener) {
        mListener = listener;
    }
}
