package com.mdove.passwordguard.home.richeditor.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.mdove.passwordguard.R;
import com.mdove.passwordguard.databinding.ItemRichEditorBtnBinding;
import com.mdove.passwordguard.home.richeditor.model.RichEditorBtnModel;
import com.mdove.passwordguard.home.richeditor.model.handler.RichEditorBtnHandler;
import com.mdove.passwordguard.home.richeditor.model.vm.RichEditorBtnModelVM;
import com.mdove.passwordguard.home.richeditor.presenter.RichEditorPresenter;
import com.mdove.passwordguard.utils.InflateUtils;

import java.util.List;

public class RichEditorBtnAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<RichEditorBtnModel> mData;
    private Context mContext;
    private RichEditorPresenter mPresenter;

    public RichEditorBtnAdapter(Context context, RichEditorPresenter presenter) {
        mContext = context;
        mPresenter = presenter;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder((ItemRichEditorBtnBinding) InflateUtils.bindingInflate(parent, R.layout.item_rich_editor_btn));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((ViewHolder) holder).binding(mData.get(position));
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ItemRichEditorBtnBinding mBinding;

        public ViewHolder(ItemRichEditorBtnBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        public void binding(RichEditorBtnModel model) {
            mBinding.setVm(new RichEditorBtnModelVM(model));
            mBinding.setHandler(new RichEditorBtnHandler(mPresenter));
        }
    }

    public void setData(List<RichEditorBtnModel> data) {
        mData = data;
        notifyDataSetChanged();
    }
}
