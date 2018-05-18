package com.mdove.passwordguard.main.newmain.options.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.mdove.passwordguard.R;
import com.mdove.passwordguard.databinding.ItemAllMainOptionBinding;
import com.mdove.passwordguard.databinding.ItemMainOthersOptionBinding;
import com.mdove.passwordguard.main.model.MainOptionInfo;
import com.mdove.passwordguard.main.newmain.options.model.MainOptionNewInfo;
import com.mdove.passwordguard.main.newmain.options.model.handler.MainOptionsOthersHandler;
import com.mdove.passwordguard.main.newmain.options.model.vm.MainOptionsOthersVM;
import com.mdove.passwordguard.main.newmain.options.presenter.OptionsPresenter;
import com.mdove.passwordguard.mainoption.adapter.AllMainOptionAdapter;
import com.mdove.passwordguard.mainoption.model.AllMainOptionVM;
import com.mdove.passwordguard.mainoption.model.handler.AllMainOptionHandler;
import com.mdove.passwordguard.mainoption.presenter.AllMainOptionPresenter;
import com.mdove.passwordguard.utils.InflateUtils;

import java.util.List;

/**
 * Created by MDove on 2018/5/18.
 */

public class OptionsOtherAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    private List<MainOptionNewInfo> mData;
    private OptionsPresenter mPresenter;

    public OptionsOtherAdapter(Context context, List<MainOptionNewInfo> data, OptionsPresenter presenter) {
        mContext = context;
        mData = data;
        mPresenter = presenter;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder((ItemMainOthersOptionBinding) InflateUtils.bindingInflate(parent, R.layout.item_main_others_option));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MainOptionNewInfo mainOptionInfo = mData.get(position);
        ((ViewHolder)holder).bind(mainOptionInfo);
    }

    @Override
    public int getItemCount() {
        return mData != null ? mData.size() : 0;
    }

    public void setData(List<MainOptionNewInfo> data) {
        mData = data;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ItemMainOthersOptionBinding mBinding;

        public ViewHolder(ItemMainOthersOptionBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        public void bind(MainOptionNewInfo mainOptionInfo) {
            mBinding.setViewModel(new MainOptionsOthersVM(mainOptionInfo));
            mBinding.setActionHandler(new MainOptionsOthersHandler(mPresenter));
        }
    }
}
