package com.mdove.passwordguard.main.adapter;

import android.content.Context;
import android.graphics.PorterDuff;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.mdove.passwordguard.R;
import com.mdove.passwordguard.base.listener.OnChangeDataSizeListener;
import com.mdove.passwordguard.databinding.ItemDailyPlanTodayRlvBinding;
import com.mdove.passwordguard.main.model.DailyPlanModel;
import com.mdove.passwordguard.main.model.handler.ItemDailyPlanHandler;
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
        return new TodayViewHolder((ItemDailyPlanTodayRlvBinding) InflateUtils.bindingInflate(parent, R.layout.item_daily_plan_today_rlv));
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

    public void notifyDelete(int position) {
        mData.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, mData.size());
    }

    @Override
    public int getItemCount() {
        return mData != null ? mData.size() : 0;
    }

    public class TodayViewHolder extends RecyclerView.ViewHolder {
        private ItemDailyPlanTodayRlvBinding mBinding;

        public TodayViewHolder(ItemDailyPlanTodayRlvBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        public void bind(final DailyPlanModel dailyPlanModel, int position, final TodayPlanPresenter presenter) {
            mBinding.setViewModel(new DailyPlanModelVM(dailyPlanModel, position));
            mBinding.setActionHandler(new ItemDailyPlanHandler(mPresenter));

            mBinding.ivGet.setColorFilter(ContextCompat.getColor(mContext, R.color.gray), PorterDuff.Mode.SRC_ATOP);
            mBinding.tvGet.setTextColor(ContextCompat.getColor(mContext, R.color.gray));
            mBinding.ivNormal.setColorFilter(ContextCompat.getColor(mContext, R.color.gray), PorterDuff.Mode.SRC_ATOP);
            mBinding.tvNormal.setTextColor(ContextCompat.getColor(mContext, R.color.gray));
            mBinding.ivLost.setColorFilter(ContextCompat.getColor(mContext, R.color.gray), PorterDuff.Mode.SRC_ATOP);
            mBinding.tvLost.setTextColor(ContextCompat.getColor(mContext, R.color.gray));
            mBinding.tvDay.setTextColor(ContextCompat.getColor(mContext, R.color.gray));
            mBinding.tvWeek.setTextColor(ContextCompat.getColor(mContext, R.color.gray));
            mBinding.tvTime.setTextColor(ContextCompat.getColor(mContext, R.color.gray));
            mBinding.tvYearMonth.setTextColor(ContextCompat.getColor(mContext, R.color.gray));

            switch (dailyPlanModel.mStatus) {
                case DailyPlanModel.STATUS_GET: {
                    mBinding.ivGet.setColorFilter(ContextCompat.getColor(mContext, R.color.black), PorterDuff.Mode.SRC_ATOP);
                    mBinding.tvGet.setTextColor(ContextCompat.getColor(mContext, R.color.black));
                    mBinding.tvDay.setTextColor(ContextCompat.getColor(mContext, R.color.white));
                    mBinding.tvWeek.setTextColor(ContextCompat.getColor(mContext, R.color.white));
                    mBinding.tvYearMonth.setTextColor(ContextCompat.getColor(mContext, R.color.white));
                    mBinding.tvTime.setTextColor(ContextCompat.getColor(mContext, R.color.white));
                    break;
                }
                case DailyPlanModel.STATUS_LOST: {
                    mBinding.ivLost.setColorFilter(ContextCompat.getColor(mContext, R.color.black), PorterDuff.Mode.SRC_ATOP);
                    mBinding.tvLost.setTextColor(ContextCompat.getColor(mContext, R.color.black));
                    mBinding.tvDay.setTextColor(ContextCompat.getColor(mContext, R.color.white));
                    mBinding.tvWeek.setTextColor(ContextCompat.getColor(mContext, R.color.white));
                    mBinding.tvYearMonth.setTextColor(ContextCompat.getColor(mContext, R.color.white));
                    mBinding.tvTime.setTextColor(ContextCompat.getColor(mContext, R.color.white));
                    break;
                }
                case DailyPlanModel.STATUS_NORMAL: {
                    mBinding.ivNormal.setColorFilter(ContextCompat.getColor(mContext, R.color.black), PorterDuff.Mode.SRC_ATOP);
                    mBinding.tvNormal.setTextColor(ContextCompat.getColor(mContext, R.color.black));
                    mBinding.tvDay.setTextColor(ContextCompat.getColor(mContext, R.color.gray));
                    mBinding.tvWeek.setTextColor(ContextCompat.getColor(mContext, R.color.gray));
                    mBinding.tvYearMonth.setTextColor(ContextCompat.getColor(mContext, R.color.gray));
                    mBinding.tvTime.setTextColor(ContextCompat.getColor(mContext, R.color.gray));
                    break;
                }
                default: {
                    break;
                }
            }
            mBinding.btnGet.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (dailyPlanModel.mStatus != DailyPlanModel.STATUS_GET) {
                        presenter.updateLostOrGet(dailyPlanModel.mId, DailyPlanModel.STATUS_GET);
                    }
                }
            });
            mBinding.btnLost.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (dailyPlanModel.mStatus != DailyPlanModel.STATUS_LOST) {
                        presenter.updateLostOrGet(dailyPlanModel.mId, DailyPlanModel.STATUS_LOST);
                    }
                }
            });
            mBinding.btnNormal.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (dailyPlanModel.mStatus != DailyPlanModel.STATUS_NORMAL) {
                        presenter.updateLostOrGet(dailyPlanModel.mId, DailyPlanModel.STATUS_NORMAL);
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
