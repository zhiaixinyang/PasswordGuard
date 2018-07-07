package com.mdove.passwordguard.home.main.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.mdove.passwordguard.R;
import com.mdove.passwordguard.databinding.ItemAddScheduleShortBinding;
import com.mdove.passwordguard.databinding.ItemScheduleShortBinding;
import com.mdove.passwordguard.home.schedule.EtScheduleActivity;
import com.mdove.passwordguard.home.schedule.model.AddScheduleModel;
import com.mdove.passwordguard.home.schedule.model.BaseScheduleModel;
import com.mdove.passwordguard.home.schedule.model.ScheduleModel;
import com.mdove.passwordguard.home.schedule.model.vm.ScheduleModelVM;
import com.mdove.passwordguard.utils.InflateUtils;

import java.util.List;

/**
 * Created by MDove on 2018/7/7.
 */

public class HomeScheduleAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final static int ITEM_TYPE_ADD_SCHEDULE = 0;
    private final static int ITEM_TYPE_SCHEDULE = 1;

    private List<BaseScheduleModel> mData;
    private Context mContext;

    public HomeScheduleAdapter(Context context) {
        mContext = context;
    }

    @Override
    public int getItemViewType(int position) {
        BaseScheduleModel model = mData.get(position);
        if (model instanceof AddScheduleModel) {
            return ITEM_TYPE_ADD_SCHEDULE;
        } else {
            return ITEM_TYPE_SCHEDULE;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case ITEM_TYPE_ADD_SCHEDULE: {
                return new AddViewHolder((ItemAddScheduleShortBinding) InflateUtils.bindingInflate(parent, R.layout.item_add_schedule_short));
            }
            case ITEM_TYPE_SCHEDULE: {
                return new ViewHolder((ItemScheduleShortBinding) InflateUtils.bindingInflate(parent, R.layout.item_schedule_short));
            }
            default: {
                return new ViewHolder((ItemScheduleShortBinding) InflateUtils.bindingInflate(parent, R.layout.item_schedule_short));
            }
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ViewHolder) {
            ((ViewHolder) holder).bind((ScheduleModel) mData.get(position));
        } else if (holder instanceof AddViewHolder) {
            ((AddViewHolder) holder).bind();
        }
    }

    @Override
    public int getItemCount() {
        return mData != null ? mData.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ItemScheduleShortBinding mBinding;

        public ViewHolder(ItemScheduleShortBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        public void bind(ScheduleModel model) {
            mBinding.setVm(new ScheduleModelVM(model));
        }
    }

    public class AddViewHolder extends RecyclerView.ViewHolder {
        private ItemAddScheduleShortBinding mBinding;

        public AddViewHolder(ItemAddScheduleShortBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        public void bind() {
            mBinding.layoutAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    EtScheduleActivity.start(mContext);
                }
            });
        }
    }

    public void setData(List<BaseScheduleModel> data) {
        mData = data;
        notifyDataSetChanged();
    }

    public void notifyDelete(int position) {
        mData.remove(position);
        notifyItemRemoved(position);
    }
}
