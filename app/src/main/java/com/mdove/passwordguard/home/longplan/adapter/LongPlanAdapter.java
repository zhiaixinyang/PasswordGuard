package com.mdove.passwordguard.home.longplan.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import com.mdove.passwordguard.R;
import com.mdove.passwordguard.databinding.ItemAddLongPlanBinding;
import com.mdove.passwordguard.databinding.ItemLongPlanBinding;
import com.mdove.passwordguard.home.longplan.EtLongPlanActivity;
import com.mdove.passwordguard.home.longplan.model.AddLongPlanModel;
import com.mdove.passwordguard.home.longplan.model.BaseLongPlanModel;
import com.mdove.passwordguard.home.longplan.model.LongPlanModel;
import com.mdove.passwordguard.home.longplan.model.handler.LongPlanHandler;
import com.mdove.passwordguard.home.longplan.model.vm.LongPlanModelVM;
import com.mdove.passwordguard.home.longplan.presenter.LongPlanPresenter;
import com.mdove.passwordguard.utils.InflateUtils;

import java.util.List;

/**
 * Created by MDove on 2018/6/27.
 */

public class LongPlanAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final static int ITEM_TYPE_ADD_LONG_PLAN = 0;
    private final static int ITEM_TYPE_LONG_PLAN = 1;

    private List<BaseLongPlanModel> mData;
    private Context mContext;
    private LongPlanPresenter mPresenter;

    public LongPlanAdapter(Context context, LongPlanPresenter presenter) {
        mPresenter = presenter;
        mContext = context;
    }

    @Override
    public int getItemViewType(int position) {
        BaseLongPlanModel model = mData.get(position);
        if (model instanceof AddLongPlanModel) {
            return ITEM_TYPE_ADD_LONG_PLAN;
        } else {
            return ITEM_TYPE_LONG_PLAN;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case ITEM_TYPE_ADD_LONG_PLAN: {
                return new AddViewHolder((ItemAddLongPlanBinding) InflateUtils.bindingInflate(parent, R.layout.item_add_long_plan));
            }
            case ITEM_TYPE_LONG_PLAN: {
                return new ViewHolder((ItemLongPlanBinding) InflateUtils.bindingInflate(parent, R.layout.item_long_plan));
            }
            default: {
                return new ViewHolder((ItemLongPlanBinding) InflateUtils.bindingInflate(parent, R.layout.item_long_plan));
            }
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ViewHolder) {
            ((ViewHolder) holder).bind((LongPlanModel) mData.get(position));
        } else if (holder instanceof AddViewHolder) {
            ((AddViewHolder) holder).bind();
        }
    }

    @Override
    public int getItemCount() {
        return mData != null ? mData.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ItemLongPlanBinding mBinding;

        public ViewHolder(ItemLongPlanBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        public void bind(LongPlanModel model) {
            mBinding.setVm(new LongPlanModelVM(model));
            mBinding.setHandler(new LongPlanHandler(mPresenter));
            if (model.mImportant>=60){
                mBinding.tvImportant.setTextColor(ContextCompat.getColor(mContext,R.color.red));
            }
            if (model.mUrgent>=60){
                mBinding.tvUrgent.setTextColor(ContextCompat.getColor(mContext,R.color.red));
            }
        }
    }

    public class AddViewHolder extends RecyclerView.ViewHolder {
        private ItemAddLongPlanBinding mBinding;

        public AddViewHolder(ItemAddLongPlanBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        public void bind() {
            mBinding.layoutAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    EtLongPlanActivity.start(mContext);
                }
            });
        }
    }

    public void setData(List<BaseLongPlanModel> data) {
        mData = data;
        notifyDataSetChanged();
    }

    public void notifyDelete(int position) {
        mData.remove(position);
        notifyItemRemoved(position);
    }
}
