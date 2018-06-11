package com.mdove.passwordguard.main.newmain.dailytask.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.mdove.passwordguard.R;
import com.mdove.passwordguard.base.listener.OnChangeDataSizeListener;
import com.mdove.passwordguard.databinding.ItemMainTimerDailyTaskBinding;
import com.mdove.passwordguard.databinding.ItemNewMainDailyTaskBinding;
import com.mdove.passwordguard.main.newmain.dailytask.model.BaseMainSelfTaskModel;
import com.mdove.passwordguard.main.newmain.dailytask.model.MainSelfTaskHandler;
import com.mdove.passwordguard.main.newmain.dailytask.model.MainSelfTaskModel;
import com.mdove.passwordguard.main.newmain.dailytask.model.MainSelfTaskModelVM;
import com.mdove.passwordguard.main.newmain.dailytask.model.MainSelfTaskTimerHandler;
import com.mdove.passwordguard.main.newmain.dailytask.model.MainSelfTaskTimerModel;
import com.mdove.passwordguard.main.newmain.dailytask.model.MainSelfTaskTimerModelVM;
import com.mdove.passwordguard.main.newmain.dailytask.presenter.MainSelfTaskPresenter;
import com.mdove.passwordguard.main.newmain.dailytask.util.LabelConstant;
import com.mdove.passwordguard.task.model.SelfTaskModel;
import com.mdove.passwordguard.task.utils.SelfTaskPriorityHelper;
import com.mdove.passwordguard.utils.InflateUtils;

import java.util.List;

/**
 * Created by MDove on 2018/5/27.
 */

public class MainSelfTaskAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private MainSelfTaskPresenter mPresenter;
    private Context mContext;
    private List<BaseMainSelfTaskModel> mData;
    private OnChangeDataSizeListener mListener;

    private static final int TYPE_SELF_TASK_NORMAL = 1;
    private static final int TYPE_SELF_TASK_TIMER = 2;

    public MainSelfTaskAdapter(Context context, MainSelfTaskPresenter presenter) {
        mContext = context;
        mPresenter = presenter;
        registerAdapterDataObserver(mObserver);
    }

    @Override
    public int getItemViewType(int position) {
        BaseMainSelfTaskModel model = mData.get(position);
        if (model instanceof MainSelfTaskTimerModel) {
            return TYPE_SELF_TASK_TIMER;
        } else if (model instanceof SelfTaskModel) {
            return TYPE_SELF_TASK_NORMAL;
        }
        return super.getItemViewType(position);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_SELF_TASK_NORMAL) {
            return new SelfTaskViewHolder((ItemNewMainDailyTaskBinding) InflateUtils.bindingInflate(parent, R.layout.item_new_main_daily_task));
        } else {
            return new TimerViewHolder((ItemMainTimerDailyTaskBinding) InflateUtils.bindingInflate(parent, R.layout.item_main_timer_daily_task));
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        BaseMainSelfTaskModel selfTaskModel = mData.get(position);
        if (selfTaskModel instanceof SelfTaskModel) {
            ((SelfTaskViewHolder) holder).bind((SelfTaskModel) selfTaskModel);
        } else if (selfTaskModel instanceof MainSelfTaskTimerModel) {
            ((TimerViewHolder) holder).bind((MainSelfTaskTimerModel) selfTaskModel);
        }
    }

    public void setData(List<BaseMainSelfTaskModel> data) {
        mData = data;
        notifyDataSetChanged();
    }

    public void notifyPosition(int position) {
        notifyItemChanged(position);
    }

    public void notifyDeleteSelfTask(int position) {
        notifyDelete(position);
    }

    @Override
    public int getItemCount() {
        return mData != null ? mData.size() : 0;
    }

    public class SelfTaskViewHolder extends RecyclerView.ViewHolder {
        private ItemNewMainDailyTaskBinding mBinding;

        public SelfTaskViewHolder(ItemNewMainDailyTaskBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        public void bind(SelfTaskModel selfTaskModel) {
            mBinding.setViewModel(new MainSelfTaskModelVM(selfTaskModel));
            mBinding.setActionHandler(new MainSelfTaskHandler(mPresenter));

            mBinding.ivPriority.setColorFilter(SelfTaskPriorityHelper.getPriorityBtnColor(mContext, selfTaskModel.mPriority), PorterDuff.Mode.SRC_ATOP);
            mBinding.tvPriorityTip.setTextColor(SelfTaskPriorityHelper.getPriorityBtnColor(mContext, selfTaskModel.mPriority));
            mBinding.tvTitle.setTextColor(SelfTaskPriorityHelper.getPriorityTextColor(mContext, selfTaskModel.mPriority));

            switch (selfTaskModel.mPriority) {
                case SelfTaskPriorityHelper.PRIORITY_IS_NORMAL: {
                    mBinding.layoutTop.setBackgroundResource(R.drawable.bg_daily_task_bg_p1);
                    mBinding.tvLabel.setTextColor(ContextCompat.getColor(mContext, R.color.green_300));
                    break;
                }
                case SelfTaskPriorityHelper.PRIORITY_IS_YELLOW: {
                    mBinding.layoutTop.setBackgroundResource(R.drawable.bg_daily_task_bg_p2);
                    mBinding.tvLabel.setTextColor(ContextCompat.getColor(mContext, R.color.self_task_priority_yellow));
                    break;
                }
                case SelfTaskPriorityHelper.PRIORITY_IS_RED: {
                    mBinding.layoutTop.setBackgroundResource(R.drawable.bg_daily_task_bg_p3);
                    mBinding.tvLabel.setTextColor(ContextCompat.getColor(mContext, R.color.self_task_priority_red));
                    break;
                }
            }

            if (!selfTaskModel.mIsSuc) {
                mBinding.tvTitle.setFocusable(true);
                mBinding.tvTitle.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                    @Override
                    public void onFocusChange(View v, boolean hasFocus) {
                        if (hasFocus) {
                            mBinding.btnEdit.setVisibility(View.VISIBLE);
                        } else {
                            mBinding.btnEdit.setVisibility(View.GONE);
                        }
                    }
                });
            } else {
                mBinding.tvTitle.setFocusable(false);
                mBinding.tvTitle.setClickable(false);
                mBinding.btnEdit.setVisibility(View.GONE);
            }

            if (selfTaskModel.mIsSuc) {
                mBinding.tvTitle.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
                mBinding.tvTitle.setTextColor(ContextCompat.getColor(mContext, R.color.gray_light));

                mBinding.layoutBtn.setBackgroundResource(R.drawable.bg_item_self_task_btn_off);
                mBinding.tvBtn.setText("取消");
            } else {
                mBinding.tvTitle.getPaint().setFlags(0);
                mBinding.tvTitle.getPaint().setAntiAlias(true);
                mBinding.tvTitle.setTextColor(SelfTaskPriorityHelper.getPriorityTextColor(mContext, selfTaskModel.mPriority));

                mBinding.layoutBtn.setBackgroundResource(R.drawable.bg_item_self_task_btn_on);
                mBinding.tvBtn.setText("完成");
            }
            if (selfTaskModel.mIsSee) {
                mBinding.ivSee.setImageResource(R.mipmap.ic_self_task_see_on);
                mBinding.tvSeeTip.setTextColor(ContextCompat.getColor(mContext, R.color.commonColor));
            } else {
                mBinding.ivSee.setImageResource(R.mipmap.ic_self_task_see_off);
                mBinding.tvSeeTip.setTextColor(ContextCompat.getColor(mContext, R.color.gray_light));
            }
        }
    }

    public class TimerViewHolder extends RecyclerView.ViewHolder {
        private ItemMainTimerDailyTaskBinding mBinding;

        public TimerViewHolder(ItemMainTimerDailyTaskBinding binging) {
            super(binging.getRoot());
            mBinding = binging;
        }

        public void bind(MainSelfTaskTimerModel model) {
            mBinding.setViewModel(new MainSelfTaskTimerModelVM(model));
            mBinding.setActionHandler(new MainSelfTaskTimerHandler(mPresenter));

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

    public void notifyEventSelfTaskClickSuc(long id, SelfTaskModel postModel) {
        SelfTaskModel exitsModel = null;
        for (BaseMainSelfTaskModel model : mData) {
            if (model instanceof SelfTaskModel) {
                if (((SelfTaskModel) model).mId == id) {
                    exitsModel = ((SelfTaskModel) model);
                }
            }
        }
        if (exitsModel == null) {
            return;
        }
        exitsModel.mIsSuc = postModel.mIsSuc;
        int position = mData.indexOf(exitsModel);
        notifyPosition(position);
    }

    public void notifyEventSelfTaskClickDelete(long id) {
        SelfTaskModel selfTaskModel = null;
        for (BaseMainSelfTaskModel model : mData) {
            if (model instanceof SelfTaskModel) {
                if (((SelfTaskModel) model).mId == id) {
                    selfTaskModel = ((SelfTaskModel) model);
                }
            }
        }
        if (selfTaskModel == null) {
            return;
        }
        int position = mData.indexOf(selfTaskModel);
        notifyDeleteSelfTask(position);
    }


    public void notifyEventSelfTaskClickPriority(long id, int priority) {
        SelfTaskModel selfTaskModel = null;
        for (BaseMainSelfTaskModel model : mData) {
            if (model instanceof SelfTaskModel) {
                if (((SelfTaskModel) model).mId == id) {
                    selfTaskModel = ((SelfTaskModel) model);
                    selfTaskModel.mPriority = priority;
                }
            }
        }
        if (selfTaskModel == null) {
            return;
        }
        int position = mData.indexOf(selfTaskModel);
        notifyPosition(position);
    }

    public void notifyEventSelfTaskClickSee(SelfTaskModel selfTaskModel) {
        int position = -1;
        SelfTaskModel existModel = null;
        for (BaseMainSelfTaskModel model : mData) {
            if (model instanceof SelfTaskModel) {
                if (((SelfTaskModel) model).mId == selfTaskModel.mId) {
                    existModel = ((SelfTaskModel) model);
                    position = mData.indexOf(model);
                }
            }
        }

        if (existModel == null) {
            mData.add(selfTaskModel);
            notifyItemInserted(mData.size());
        } else {
            notifyDelete(position);
        }
    }

    public void notifyDelete(int position) {
        mData.remove(position);
        notifyItemRemoved(position);
    }
}
