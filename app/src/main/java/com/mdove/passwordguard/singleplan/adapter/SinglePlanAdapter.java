package com.mdove.passwordguard.singleplan.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.mdove.passwordguard.R;
import com.mdove.passwordguard.databinding.ItemEtSinglePlanBinding;
import com.mdove.passwordguard.databinding.ItemSinglePlanBinding;
import com.mdove.passwordguard.singleplan.model.EtSinglePlanModelVM;
import com.mdove.passwordguard.singleplan.model.SinglePlanHandler;
import com.mdove.passwordguard.singleplan.model.SinglePlanModel;
import com.mdove.passwordguard.singleplan.model.SinglePlanModelVM;
import com.mdove.passwordguard.singleplan.presenter.SinglePlanPresenter;
import com.mdove.passwordguard.singleplan.utils.ItemSinglePlanBgHelper;
import com.mdove.passwordguard.utils.InflateUtils;

import java.util.List;

/**
 * Created by MDove on 2018/6/24.
 */

public class SinglePlanAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<SinglePlanModel> mData;
    private Context mContext;
    private SinglePlanPresenter mPresenter;

    public SinglePlanAdapter(Context context, SinglePlanPresenter presenter) {
        mPresenter = presenter;
        mContext = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder((ItemSinglePlanBinding) InflateUtils.bindingInflate(parent, R.layout.item_single_plan));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((ViewHolder) holder).bind(mData.get(position));
    }

    @Override
    public int getItemCount() {
        return mData != null ? mData.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ItemSinglePlanBinding mBinding;

        public ViewHolder(ItemSinglePlanBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        public void bind(SinglePlanModel model) {
            mBinding.setVm(new SinglePlanModelVM(model));
            mBinding.setHandler(new SinglePlanHandler(mPresenter));
            mBinding.layoutMain.setBackgroundColor(ItemSinglePlanBgHelper.getBg(model.mImportant, model.mUrgent));
        }
    }

    public void setData(List<SinglePlanModel> data) {
        mData = data;
        notifyDataSetChanged();
    }

    public void notifyDelete(int position) {
        mData.remove(position);
        notifyItemRemoved(position);
    }
}
