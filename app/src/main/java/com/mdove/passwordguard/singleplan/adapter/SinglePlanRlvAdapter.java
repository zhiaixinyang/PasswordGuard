package com.mdove.passwordguard.singleplan.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.mdove.passwordguard.R;
import com.mdove.passwordguard.databinding.ItemEtSinglePlanBinding;
import com.mdove.passwordguard.databinding.ItemSinglePlanRlvBinding;
import com.mdove.passwordguard.singleplan.model.EtSinglePlanModelVM;
import com.mdove.passwordguard.singleplan.model.SinglePlanModel;
import com.mdove.passwordguard.utils.InflateUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MDove on 2018/6/25.
 */

public class SinglePlanRlvAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<EtSinglePlanModelVM> mData;
    private Context mContext;

    public SinglePlanRlvAdapter(Context context) {
        mContext = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder((ItemSinglePlanRlvBinding) InflateUtils.bindingInflate(parent, R.layout.item_single_plan_rlv));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
//        ((ViewHolder) holder).bind(mData.get(position));
    }

    @Override
    public int getItemCount() {
        return mData != null ? mData.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ItemSinglePlanRlvBinding mBinding;

        public ViewHolder(ItemSinglePlanRlvBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        public void bind(SinglePlanModel model) {
            String[] arr = model.mSinglePlan.split("#");
//            mBinding.setVm(vm);
        }
    }

    public void setData(List<EtSinglePlanModelVM> data) {
        mData = data;
        notifyDataSetChanged();
    }

    public void addSinglePlan(String singlePlanTips, String singlePlan) {
        if (mData == null) {
            mData = new ArrayList<>();
        }
        mData.add(new EtSinglePlanModelVM(singlePlanTips, singlePlan));
        notifyDataSetChanged();
    }
}
