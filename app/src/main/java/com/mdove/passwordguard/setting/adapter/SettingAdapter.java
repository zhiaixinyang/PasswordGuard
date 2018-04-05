package com.mdove.passwordguard.setting.adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.mdove.passwordguard.R;
import com.mdove.passwordguard.databinding.ItemHideMainGroupBinding;
import com.mdove.passwordguard.databinding.ItemHideMainOptionNewBinding;
import com.mdove.passwordguard.databinding.ItemHideMainSearchBinding;
import com.mdove.passwordguard.databinding.ItemHideMainTopBinding;
import com.mdove.passwordguard.main.adapter.GroupRlvAdapter;
import com.mdove.passwordguard.main.adapter.MainAdapter;
import com.mdove.passwordguard.main.adapter.MainOptionAdapter;
import com.mdove.passwordguard.main.model.BaseMainModel;
import com.mdove.passwordguard.main.model.MainGroupModel;
import com.mdove.passwordguard.main.model.MainOptionModel;
import com.mdove.passwordguard.main.model.MainSearchModel;
import com.mdove.passwordguard.main.model.MainTopModel;
import com.mdove.passwordguard.main.model.vm.ItemMainTopVM;
import com.mdove.passwordguard.setting.model.SettingHandler;
import com.mdove.passwordguard.setting.presenter.SettingPresenter;
import com.mdove.passwordguard.utils.InflateUtils;

import java.util.List;

/**
 * Created by MDove on 2018/4/5.
 */

public class SettingAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int TYPE_MAIN_OPTION_NEW = 0;
    private static final int TYPE_MAIN_TOP = 1;
    private static final int TYPE_MAIN_SEARCH = 2;
    private static final int TYPE_MAIN_GROUP = 3;

    private Context mContext;
    private List<BaseMainModel> mData;
    private GroupRlvAdapter mGroupRlvAdapter;
    private SettingPresenter mPresenter;

    public SettingAdapter(Context context, List<BaseMainModel> data, SettingPresenter presenter) {
        mContext = context;
        mData = data;
        mPresenter = presenter;
    }

    @Override
    public int getItemViewType(int position) {
        BaseMainModel model = mData.get(position);
        switch (model.mType) {
            case 0: {
                if (model instanceof MainOptionModel) {
                    return TYPE_MAIN_OPTION_NEW;
                }
            }
            case 1: {
                if (model instanceof MainTopModel) {
                    return TYPE_MAIN_TOP;
                } else if (model instanceof MainSearchModel) {
                    return TYPE_MAIN_SEARCH;
                } else if (model instanceof MainGroupModel) {
                    return TYPE_MAIN_GROUP;
                }
            }
        }
        return super.getItemViewType(position);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case TYPE_MAIN_TOP: {
                return new MainTopViewHolder((ItemHideMainTopBinding) InflateUtils.bindingInflate(parent, R.layout.item_hide_main_top));
            }
            case TYPE_MAIN_SEARCH: {
                return new MainSearchViewHolder((ItemHideMainSearchBinding) InflateUtils.bindingInflate(parent, R.layout.item_hide_main_search));
            }
            case TYPE_MAIN_GROUP: {
                return new MainGroupViewHolder((ItemHideMainGroupBinding) InflateUtils.bindingInflate(parent, R.layout.item_hide_main_group));
            }
            case TYPE_MAIN_OPTION_NEW: {
                return new NewMainOptionViewHolder((ItemHideMainOptionNewBinding) InflateUtils.bindingInflate(parent, R.layout.item_hide_main_option_new));
            }
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        BaseMainModel model = mData.get(position);
        if (holder instanceof MainTopViewHolder) {
            ((MainTopViewHolder) holder).bind(new ItemMainTopVM((MainTopModel) model), mPresenter);
        } else if (holder instanceof MainSearchViewHolder) {
            ((MainSearchViewHolder) holder).bind(mPresenter);
        } else if (holder instanceof MainGroupViewHolder) {
            ((MainGroupViewHolder) holder).bind((MainGroupModel) model, mPresenter);
        } else if (holder instanceof NewMainOptionViewHolder) {
            ((NewMainOptionViewHolder) holder).bind((MainOptionModel) model, mPresenter);
        }
    }

    public void setData(List<BaseMainModel> data) {
        mData = data;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    public class MainTopViewHolder extends RecyclerView.ViewHolder {
        private ItemHideMainTopBinding mBinding;

        public MainTopViewHolder(ItemHideMainTopBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        public void bind(ItemMainTopVM vm, SettingPresenter presenter) {
            mBinding.setViewModel(vm);
            mBinding.setActionHandler(new SettingHandler(presenter));
        }
    }

    public class MainSearchViewHolder extends RecyclerView.ViewHolder {
        private ItemHideMainSearchBinding mBinding;

        public MainSearchViewHolder(ItemHideMainSearchBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        public void bind(SettingPresenter presenter) {
            mBinding.setActionHandler(new SettingHandler(presenter));
        }
    }

    public class NewMainOptionViewHolder extends RecyclerView.ViewHolder {
        private ItemHideMainOptionNewBinding mBinding;
        private MainOptionAdapter mAdapter;

        public NewMainOptionViewHolder(final ItemHideMainOptionNewBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        public void bind(MainOptionModel mainOptionModel,SettingPresenter presenter) {
            GridLayoutManager gridLayoutManager = new GridLayoutManager(mContext, 4);
            if (mAdapter == null) {
                mAdapter = new MainOptionAdapter(mainOptionModel.mData);
                mBinding.rlvOptions.setLayoutManager(gridLayoutManager);
                mBinding.rlvOptions.setAdapter(mAdapter);
            }
            mBinding.setActionHandler(new SettingHandler(presenter));
        }
    }

    public class MainGroupViewHolder extends RecyclerView.ViewHolder {
        private ItemHideMainGroupBinding mBinding;

        public MainGroupViewHolder(ItemHideMainGroupBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        public void bind(MainGroupModel model,SettingPresenter presenter) {
            mBinding.rlvGroup.setLayoutManager(new GridLayoutManager(mContext, 3));

            mGroupRlvAdapter = new GroupRlvAdapter(mContext, model.mData);
            mBinding.rlvGroup.setAdapter(mGroupRlvAdapter);

            mBinding.setActionHandler(new SettingHandler(presenter));
        }
    }

    public void notifyDeleteByPosition(int position){
        mData.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, mData.size());
    }
}
