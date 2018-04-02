package com.mdove.passwordguard.mainoption.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.mdove.passwordguard.R;
import com.mdove.passwordguard.databinding.ItemAllMainOptionBinding;
import com.mdove.passwordguard.main.model.MainOptionInfo;
import com.mdove.passwordguard.mainoption.model.AllMainOptionVM;
import com.mdove.passwordguard.mainoption.model.handler.AllMainOptionHandler;
import com.mdove.passwordguard.mainoption.presenter.AllMainOptionPresenter;
import com.mdove.passwordguard.utils.InflateUtils;

import java.util.List;

/**
 * Created by MDove on 2018/4/2.
 */

public class AllMainOptionAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    private List<MainOptionInfo> mData;
    private AllMainOptionPresenter mPresenter;

    public AllMainOptionAdapter(Context context, List<MainOptionInfo> data, AllMainOptionPresenter presenter) {
        mContext = context;
        mData = data;
        mPresenter = presenter;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder((ItemAllMainOptionBinding) InflateUtils.bindingInflate(parent, R.layout.item_all_main_option));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MainOptionInfo mainOptionInfo = mData.get(position);
        ((ViewHolder)holder).bind(mainOptionInfo);
    }

    @Override
    public int getItemCount() {
        return mData != null ? mData.size() : 0;
    }

    public void setData(List<MainOptionInfo> data) {
        mData = data;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ItemAllMainOptionBinding mBinding;

        public ViewHolder(ItemAllMainOptionBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        public void bind(MainOptionInfo mainOptionInfo) {
            mBinding.setViewModel(new AllMainOptionVM(mainOptionInfo));
            mBinding.setActionHandler(new AllMainOptionHandler(mPresenter));
        }

    }
}
