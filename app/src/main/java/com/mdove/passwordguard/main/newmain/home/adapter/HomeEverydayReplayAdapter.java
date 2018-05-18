package com.mdove.passwordguard.main.newmain.home.adapter;

import android.content.Context;
import android.graphics.PorterDuff;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.mdove.passwordguard.R;
import com.mdove.passwordguard.calendar.model.BaseCalendarModel;
import com.mdove.passwordguard.databinding.ItemEverydayReplayEtBinding;
import com.mdove.passwordguard.databinding.ItemEverydayReplayHomeBinding;
import com.mdove.passwordguard.main.model.DailyPlanModel;
import com.mdove.passwordguard.main.newmain.everydayreplay.EtEverydayReplayActivity;
import com.mdove.passwordguard.main.newmain.home.model.EverydayReplayModel;
import com.mdove.passwordguard.main.newmain.home.model.EverydayReplayModelVM;
import com.mdove.passwordguard.main.newmain.home.model.EverydayReplayInitEtModel;
import com.mdove.passwordguard.main.newmain.home.model.handler.ItemEverydayReplayHandler;
import com.mdove.passwordguard.main.newmain.home.presenter.EverydayReplayPresenter;
import com.mdove.passwordguard.utils.InflateUtils;

import java.util.List;

/**
 * Created by MDove on 2018/5/9.
 */

public class HomeEverydayReplayAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    private List<BaseCalendarModel> mData;
    private static final int TYPE_TOP_ET = 0;
    private static final int TYPE_NORMAL = 1;
    private EverydayReplayPresenter mPresenter;

    @Override
    public int getItemViewType(int position) {
        BaseCalendarModel model = mData.get(position);
        if (model instanceof EverydayReplayModel) {
            return TYPE_NORMAL;
        } else if (model instanceof EverydayReplayInitEtModel) {
            return TYPE_TOP_ET;
        }
        return super.getItemViewType(position);
    }

    public HomeEverydayReplayAdapter(Context context, EverydayReplayPresenter presenter) {
        mContext = context;
        mPresenter = presenter;
    }

    public void setData(List<BaseCalendarModel> data) {
        mData = data;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType){
            case TYPE_TOP_ET:{
                return new ViewHolderTopEt((ItemEverydayReplayEtBinding) InflateUtils.bindingInflate(parent,
                        R.layout.item_everyday_replay_et));
            }
            case TYPE_NORMAL:{
                return new ViewHolder((ItemEverydayReplayHomeBinding) InflateUtils.bindingInflate(parent,
                        R.layout.item_everyday_replay_home));
            }
            default:{
                return new ViewHolder((ItemEverydayReplayHomeBinding) InflateUtils.bindingInflate(parent,
                        R.layout.item_everyday_replay_home));
            }
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        BaseCalendarModel model = mData.get(position);
        if (model instanceof EverydayReplayModel) {
            ((ViewHolder) holder).bind((EverydayReplayModel) model, position, mPresenter);
        }else if (model instanceof EverydayReplayInitEtModel){
            ((ViewHolderTopEt) holder).bind();
        }
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    public class ViewHolderTopEt extends RecyclerView.ViewHolder {
        private ItemEverydayReplayEtBinding mBinding;

        public ViewHolderTopEt(ItemEverydayReplayEtBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        public void bind() {
            mBinding.layoutEt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    EtEverydayReplayActivity.start(mContext);
                }
            });
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ItemEverydayReplayHomeBinding mBinding;

        public ViewHolder(ItemEverydayReplayHomeBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        public void bind(final EverydayReplayModel dailyPlanModel, int position, final EverydayReplayPresenter presenter) {
            mBinding.setViewModel(new EverydayReplayModelVM(dailyPlanModel, position));
            mBinding.setActionHandler(new ItemEverydayReplayHandler(mPresenter));

            mBinding.ivGet.setColorFilter(ContextCompat.getColor(mContext, R.color.gray), PorterDuff.Mode.SRC_ATOP);
            mBinding.tvGet.setTextColor(ContextCompat.getColor(mContext, R.color.gray));
            mBinding.ivNormal.setColorFilter(ContextCompat.getColor(mContext, R.color.gray), PorterDuff.Mode.SRC_ATOP);
            mBinding.tvNormal.setTextColor(ContextCompat.getColor(mContext, R.color.gray));
            mBinding.ivLost.setColorFilter(ContextCompat.getColor(mContext, R.color.gray), PorterDuff.Mode.SRC_ATOP);
            mBinding.tvLost.setTextColor(ContextCompat.getColor(mContext, R.color.gray));
            mBinding.tvTime.setTextColor(ContextCompat.getColor(mContext, R.color.gray));

            switch (dailyPlanModel.mStatus) {
                case DailyPlanModel.STATUS_GET: {
                    mBinding.ivGet.setColorFilter(ContextCompat.getColor(mContext, R.color.black), PorterDuff.Mode.SRC_ATOP);
                    mBinding.tvGet.setTextColor(ContextCompat.getColor(mContext, R.color.black));
                    mBinding.tvTime.setTextColor(ContextCompat.getColor(mContext, R.color.white));
                    break;
                }
                case DailyPlanModel.STATUS_LOST: {
                    mBinding.ivLost.setColorFilter(ContextCompat.getColor(mContext, R.color.black), PorterDuff.Mode.SRC_ATOP);
                    mBinding.tvLost.setTextColor(ContextCompat.getColor(mContext, R.color.black));
                    mBinding.tvTime.setTextColor(ContextCompat.getColor(mContext, R.color.white));
                    break;
                }
                case DailyPlanModel.STATUS_NORMAL: {
                    mBinding.ivNormal.setColorFilter(ContextCompat.getColor(mContext, R.color.black), PorterDuff.Mode.SRC_ATOP);
                    mBinding.tvNormal.setTextColor(ContextCompat.getColor(mContext, R.color.black));
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
