package com.mdove.passwordguard.main.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mdove.passwordguard.R;
import com.mdove.passwordguard.base.listener.OnItemDeleteClickListener;
import com.mdove.passwordguard.base.listener.OnItemLongClickListener;
import com.mdove.passwordguard.databinding.ItemMainGroupBinding;
import com.mdove.passwordguard.databinding.ItemMainOptionBinding;
import com.mdove.passwordguard.databinding.ItemMainSearchBinding;
import com.mdove.passwordguard.databinding.ItemMainTopBinding;
import com.mdove.passwordguard.databinding.ItemPasswordNormalBinding;
import com.mdove.passwordguard.main.model.BaseMainModel;
import com.mdove.passwordguard.main.model.MainGroupModel;
import com.mdove.passwordguard.main.model.MainGroupRlvModel;
import com.mdove.passwordguard.main.model.MainSearchModel;
import com.mdove.passwordguard.main.model.MainTopModel;
import com.mdove.passwordguard.main.model.PasswordModel;
import com.mdove.passwordguard.main.model.handler.MainGroupHandler;
import com.mdove.passwordguard.main.model.handler.MainOptionHandler;
import com.mdove.passwordguard.main.model.handler.MainSearchHandler;
import com.mdove.passwordguard.main.model.vm.ItemMainPasswordVM;
import com.mdove.passwordguard.main.model.vm.ItemMainTopVM;
import com.mdove.passwordguard.main.presenter.MainPresenter;
import com.mdove.passwordguard.ui.SwipeMenuLayout;
import com.mdove.passwordguard.utils.InflateUtils;

import java.util.List;

/**
 * Created by MDove on 2018/2/9.
 */

public class MainAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<BaseMainModel> mData;
    private Context mContext;
    private MainPresenter mPresenter;
    private LayoutInflater mInflater;
    private static final int TYPE_MAIN_OPTION = 0;
    private static final int TYPE_MAIN_TOP = 1;
    private static final int TYPE_MAIN_PASSWORD = 2;
    private static final int TYPE_MAIN_SEARCH = 3;
    private static final int TYPE_MAIN_GROUP = 4;

    public MainAdapter(Context context, MainPresenter presenter) {
        mContext = context;
        mInflater = LayoutInflater.from(mContext);
        mPresenter = presenter;
    }

    public void setData(List<BaseMainModel> data) {
        mData = data;
        notifyDataSetChanged();
    }

    public void notifyAddPasswordData(int position) {
        notifyItemChanged(position);
    }

    public void notifyDeletePasswordData(int position) {
        mData.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, mData.size() - position);
    }

    @Override
    public int getItemViewType(int position) {
        BaseMainModel model = mData.get(position);
        switch (model.mType) {
            case 0: {
                return TYPE_MAIN_OPTION;
            }
            case 1: {
                if (model instanceof MainTopModel) {
                    return TYPE_MAIN_TOP;
                } else if (model instanceof PasswordModel) {
                    return TYPE_MAIN_PASSWORD;
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
            case TYPE_MAIN_OPTION: {
                return new MainOptionViewHolder((ItemMainOptionBinding) InflateUtils.bindingInflate(parent, R.layout.item_main_option));
            }
            case TYPE_MAIN_TOP: {
                return new MainTopViewHolder((ItemMainTopBinding) InflateUtils.bindingInflate(parent, R.layout.item_main_top));
            }
            case TYPE_MAIN_PASSWORD: {
                return new PasswordViewHolder((ItemPasswordNormalBinding) InflateUtils.bindingInflate(parent, R.layout.item_password_normal));
            }
            case TYPE_MAIN_SEARCH: {
                return new MainSearchViewHolder((ItemMainSearchBinding) InflateUtils.bindingInflate(parent, R.layout.item_main_search));
            }
            case TYPE_MAIN_GROUP: {
                return new MainGroupViewHolder((ItemMainGroupBinding) InflateUtils.bindingInflate(parent, R.layout.item_main_group));
            }
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        BaseMainModel model = mData.get(position);
        if (holder instanceof MainTopViewHolder) {
            ((MainTopViewHolder) holder).bind(new ItemMainTopVM((MainTopModel) model));
        } else if (holder instanceof PasswordViewHolder) {
            ((PasswordViewHolder) holder).bind((PasswordModel) model, position);
        } else if (holder instanceof MainOptionViewHolder) {
            ((MainOptionViewHolder) holder).bind();
        } else if (holder instanceof MainSearchViewHolder) {
            ((MainSearchViewHolder) holder).bind();
        } else if (holder instanceof MainGroupViewHolder) {
            ((MainGroupViewHolder) holder).bind((MainGroupModel)model);
        }
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    public class MainTopViewHolder extends RecyclerView.ViewHolder {
        private ItemMainTopBinding mBinding;

        public MainTopViewHolder(ItemMainTopBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        public void bind(ItemMainTopVM vm) {
            mBinding.setViewModel(vm);
        }
    }

    public class PasswordViewHolder extends RecyclerView.ViewHolder {
        private ItemPasswordNormalBinding mBinding;

        public PasswordViewHolder(ItemPasswordNormalBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        public void bind(final PasswordModel model, final int position) {
            mBinding.setViewModel(new ItemMainPasswordVM(model));
            mBinding.layoutItem.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    if (mItemListener != null) {
                        mItemListener.onItemLongClick(position, model);
                    }
                    return false;
                }
            });
            mBinding.btnDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mDeleteListener != null) {
                        mDeleteListener.onItemDeleteClick(position, model);
                    }
                    mPresenter.deletePassword(position, model.password);
                    mBinding.layoutMenu.quickClose();
                }
            });
        }
    }

    public class MainSearchViewHolder extends RecyclerView.ViewHolder {
        private ItemMainSearchBinding mBinding;

        public MainSearchViewHolder(ItemMainSearchBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        public void bind() {
            mBinding.setActionHandler(new MainSearchHandler(mPresenter));
        }
    }

    public class MainOptionViewHolder extends RecyclerView.ViewHolder {
        private ItemMainOptionBinding mBinding;

        public MainOptionViewHolder(ItemMainOptionBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        public void bind() {
            mBinding.setActionHandler(new MainOptionHandler(mPresenter));
        }
    }

    public class MainGroupViewHolder extends RecyclerView.ViewHolder {
        private ItemMainGroupBinding mBinding;

        public MainGroupViewHolder(ItemMainGroupBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        public void bind(MainGroupModel model) {
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
            linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            mBinding.rlvGroup.setLayoutManager(linearLayoutManager);
            mBinding.rlvGroup.setAdapter(new GroupRlvAdapter(mContext,model.mData));

            mBinding.setActionHandler(new MainGroupHandler(mPresenter));
        }
    }

    private OnItemLongClickListener<PasswordModel> mItemListener;
    private OnItemDeleteClickListener<PasswordModel> mDeleteListener;

    public void setOnLongClickListener(OnItemLongClickListener<PasswordModel> listener) {
        mItemListener = listener;
    }

    public void setOnDeleteClickListener(OnItemDeleteClickListener<PasswordModel> listener) {
        mDeleteListener = listener;
    }
}
