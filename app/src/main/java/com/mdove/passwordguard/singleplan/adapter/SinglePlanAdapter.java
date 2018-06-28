package com.mdove.passwordguard.singleplan.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.mdove.passwordguard.R;
import com.mdove.passwordguard.databinding.ItemSinglePlanBinding;
import com.mdove.passwordguard.greendao.entity.MainTodayPlan;
import com.mdove.passwordguard.greendao.entity.SecondTodayPlan;
import com.mdove.passwordguard.home.ettodayplan.model.BaseTodayPlanModel;
import com.mdove.passwordguard.home.ettodayplan.model.MainTodayPlanModel;
import com.mdove.passwordguard.home.ettodayplan.model.SecondTodayPlanModel;
import com.mdove.passwordguard.singleplan.model.SinglePlanModel;
import com.mdove.passwordguard.singleplan.presenter.SinglePlanPresenter;
import com.mdove.passwordguard.singleplan.utils.ItemSinglePlanBgHelper;
import com.mdove.passwordguard.utils.InflateUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MDove on 2018/6/27.
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
            MainTodayPlan mainTodayPlan = model.mMainTodayPlan;
            List<BaseTodayPlanModel> data = new ArrayList<>();
            data.add(model.mAddTodayPlanModel);

            if (model.mMainTodayPlan != null) {
                data.add(new MainTodayPlanModel(mainTodayPlan));

                for (SecondTodayPlan secondTodayPlan : model.mSecondTodayPlans) {
                    data.add(new SecondTodayPlanModel(secondTodayPlan));
                }
                mBinding.layoutMainCard.setCardBackgroundColor(Color.parseColor(
                        ItemSinglePlanBgHelper.getBg(mainTodayPlan.mImportant, mainTodayPlan.mUrgent)));
            }

            mBinding.rlvSinglePlan.setLayoutManager(new LinearLayoutManager(mContext));
            mBinding.rlvSinglePlan.setAdapter(new RlvTodayPlanAdapter(mContext, data));


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
