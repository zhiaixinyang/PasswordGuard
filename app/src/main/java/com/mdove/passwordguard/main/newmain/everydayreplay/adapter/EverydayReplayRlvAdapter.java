package com.mdove.passwordguard.main.newmain.everydayreplay.adapter;

import android.content.Context;
import android.graphics.PorterDuff;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.mdove.passwordguard.R;
import com.mdove.passwordguard.calendar.model.BaseCalendarModel;
import com.mdove.passwordguard.calendar.model.CalendarTopModel;
import com.mdove.passwordguard.calendar.model.ItemCalendarHandler;
import com.mdove.passwordguard.calendar.presenter.CalendarPresenter;
import com.mdove.passwordguard.databinding.ItemCalendarDailyPlanRlvBinding;
import com.mdove.passwordguard.databinding.ItemEverydayReplayRlvBinding;
import com.mdove.passwordguard.main.model.DailyPlanModel;
import com.mdove.passwordguard.main.model.vm.DailyPlanModelVM;
import com.mdove.passwordguard.main.newmain.everydayreplay.model.EverydayReplayRlvModelVM;
import com.mdove.passwordguard.main.newmain.everydayreplay.model.handler.ItemEverydayReplayRlvHandler;
import com.mdove.passwordguard.main.newmain.everydayreplay.presenter.EtEverydayReplayPresenter;
import com.mdove.passwordguard.utils.InflateUtils;

import java.util.List;

/**
 * Created by MDove on 2018/5/9.
 */

public class EverydayReplayRlvAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    private List<BaseCalendarModel> mData;
    private static final int TYPE_TOP = 0;
    private static final int TYPE_NORMAL = 1;
    private EtEverydayReplayPresenter mPresenter;

    public EverydayReplayRlvAdapter(Context context, EtEverydayReplayPresenter presenter) {
        mContext = context;
        mPresenter = presenter;
    }

    public void setData(List<BaseCalendarModel> data) {
        mData = data;
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        BaseCalendarModel model = mData.get(position);
//        if (model instanceof CalendarTopModel){
//            return TYPE_TOP;
//        }else
        if (model instanceof DailyPlanModel) {
            return TYPE_NORMAL;
        }
        return super.getItemViewType(position);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case TYPE_TOP: {
                return new TopViewHolder(InflateUtils.inflate(parent, R.layout.item_calendar_top));
            }
            case TYPE_NORMAL: {
                return new ViewHolder((ItemEverydayReplayRlvBinding) InflateUtils.bindingInflate(parent,
                        R.layout.item_everyday_replay_rlv));
            }
            default: {
                return null;
            }
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        BaseCalendarModel model = mData.get(position);
        if (model instanceof DailyPlanModel) {
            ((ViewHolder) holder).bind((DailyPlanModel) model, position, mPresenter);
        }
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ItemEverydayReplayRlvBinding mBinding;

        public ViewHolder(ItemEverydayReplayRlvBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        public void bind(final DailyPlanModel dailyPlanModel, int position, final EtEverydayReplayPresenter presenter) {
            mBinding.setViewModel(new EverydayReplayRlvModelVM(dailyPlanModel, position));
            mBinding.setActionHandler(new ItemEverydayReplayRlvHandler(mPresenter));

            mBinding.ivGet.setColorFilter(ContextCompat.getColor(mContext, R.color.gray), PorterDuff.Mode.SRC_ATOP);
            mBinding.tvGet.setTextColor(ContextCompat.getColor(mContext, R.color.gray));
            mBinding.ivNormal.setColorFilter(ContextCompat.getColor(mContext, R.color.gray), PorterDuff.Mode.SRC_ATOP);
            mBinding.tvNormal.setTextColor(ContextCompat.getColor(mContext, R.color.gray));
            mBinding.ivLost.setColorFilter(ContextCompat.getColor(mContext, R.color.gray), PorterDuff.Mode.SRC_ATOP);
            mBinding.tvLost.setTextColor(ContextCompat.getColor(mContext, R.color.gray));
            mBinding.tvTime.setTextColor(ContextCompat.getColor(mContext, R.color.gray));

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

    public class TopViewHolder extends RecyclerView.ViewHolder {

        public TopViewHolder(View itemView) {
            super(itemView);
        }
    }

    public void notifyPosition(int position) {
        notifyItemChanged(position);
    }

    public void notifyDelete(int position) {
        mData.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, mData.size());
    }
}
