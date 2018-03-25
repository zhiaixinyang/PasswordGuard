package com.mdove.passwordguard.main.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.hwangjr.rxbus.RxBus;
import com.mdove.passwordguard.R;
import com.mdove.passwordguard.base.listener.OnItemDeleteClickListener;
import com.mdove.passwordguard.base.listener.OnItemLongClickListener;
import com.mdove.passwordguard.config.AppConfig;
import com.mdove.passwordguard.dailyself.ItemMainDailySelfVM;
import com.mdove.passwordguard.dailyself.MainDailySelfHandler;
import com.mdove.passwordguard.dailyself.MainDailySelfModel;
import com.mdove.passwordguard.databinding.ItemMainDailyselfBinding;
import com.mdove.passwordguard.databinding.ItemMainGroupBinding;
import com.mdove.passwordguard.databinding.ItemMainOptionBinding;
import com.mdove.passwordguard.databinding.ItemMainOptionNewBinding;
import com.mdove.passwordguard.databinding.ItemMainSearchBinding;
import com.mdove.passwordguard.databinding.ItemMainSelfTaskBinding;
import com.mdove.passwordguard.databinding.ItemMainSelfTaskRlvBinding;
import com.mdove.passwordguard.databinding.ItemMainTopBinding;
import com.mdove.passwordguard.databinding.ItemPasswordNormalBinding;
import com.mdove.passwordguard.main.model.BaseMainModel;
import com.mdove.passwordguard.main.model.MainGroupModel;
import com.mdove.passwordguard.main.model.MainGroupRlvModel;
import com.mdove.passwordguard.main.model.MainOptionModel;
import com.mdove.passwordguard.main.model.MainSearchModel;
import com.mdove.passwordguard.main.model.MainSelfTaskModel;
import com.mdove.passwordguard.main.model.MainTopModel;
import com.mdove.passwordguard.main.model.PasswordModel;
import com.mdove.passwordguard.main.model.event.CheckOrderEvent;
import com.mdove.passwordguard.main.model.handler.MainGroupHandler;
import com.mdove.passwordguard.main.model.handler.MainOptionHandler;
import com.mdove.passwordguard.main.model.handler.MainSearchHandler;
import com.mdove.passwordguard.main.model.vm.ItemMainPasswordVM;
import com.mdove.passwordguard.main.model.vm.ItemMainTopVM;
import com.mdove.passwordguard.main.presenter.MainPresenter;
import com.mdove.passwordguard.ui.guideview.Component;
import com.mdove.passwordguard.ui.guideview.Guide;
import com.mdove.passwordguard.ui.guideview.GuideBuilder;
import com.mdove.passwordguard.ui.guideview.component.CommonComponent;
import com.mdove.passwordguard.ui.guideview.component.SimpleComponent;
import com.mdove.passwordguard.utils.InflateUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MDove on 2018/2/9.
 */

public class MainAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<BaseMainModel> mData;
    private Context mContext;
    private Activity mActivity;
    private MainPresenter mPresenter;
    private static final int TYPE_MAIN_OPTION = 0;
    private static final int TYPE_MAIN_TOP = 1;
    private static final int TYPE_MAIN_PASSWORD = 2;
    private static final int TYPE_MAIN_SEARCH = 3;
    private static final int TYPE_MAIN_GROUP = 4;
    private static final int TYPE_MAIN_DAILY_SELF = 5;
    private static final int TYPE_MAIN_OPTION_NEW = 6;
    private static final int TYPE_MAIN_SELF_TASK = 7;
    private int mGroupPosition;
    public static int mPasswordPosition = 0;
    private View mTargetSearch, mTargetGroup, mTargetOption;

    public MainAdapter(Context context, MainPresenter presenter) {
        mContext = context;
        mActivity = (Activity) context;
        mPresenter = presenter;
    }

    public void setData(List<BaseMainModel> data) {
        mData = data;
        notifyDataSetChanged();
    }

    public void addBaseMainModelData(List<BaseMainModel> data) {
        List<BaseMainModel> newData = new ArrayList<>();
        for (int i = 0; i < mPasswordPosition; i++) {
            newData.add(mData.get(i));
        }
        newData.addAll(data);
        mData = newData;
        notifyDataSetChanged();
    }

    public void notifyAddPasswordData(int position) {
        notifyItemChanged(position);
    }

    public void notifyAddDailySelfData(int position) {
        notifyItemChanged(position);
    }

    public void notifyDeletePasswordData(int position) {
        mData.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, mData.size());
    }

    public void notifyAddGroup() {
        notifyItemChanged(mGroupPosition);
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
                } else if (model instanceof PasswordModel) {
                    return TYPE_MAIN_PASSWORD;
                } else if (model instanceof MainSearchModel) {
                    return TYPE_MAIN_SEARCH;
                } else if (model instanceof MainGroupModel) {
                    mGroupPosition = position;
                    return TYPE_MAIN_GROUP;
                } else if (model instanceof MainDailySelfModel) {
                    return TYPE_MAIN_DAILY_SELF;
                } else if (model instanceof MainSelfTaskModel) {
                    return TYPE_MAIN_SELF_TASK;
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
            case TYPE_MAIN_DAILY_SELF: {
                return new MainDailySelfViewHolder((ItemMainDailyselfBinding) InflateUtils.bindingInflate(parent, R.layout.item_main_dailyself));
            }
            case TYPE_MAIN_OPTION_NEW: {
                return new NewMainOptionViewHolder((ItemMainOptionNewBinding) InflateUtils.bindingInflate(parent, R.layout.item_main_option_new));
            }
            case TYPE_MAIN_SELF_TASK: {
                return new MainSelfTaskHolder((ItemMainSelfTaskBinding) InflateUtils.bindingInflate(parent, R.layout.item_main_self_task));
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
            ((MainGroupViewHolder) holder).bind((MainGroupModel) model);
        } else if (holder instanceof MainDailySelfViewHolder) {
            ((MainDailySelfViewHolder) holder).bind((MainDailySelfModel) model, mPresenter, position);
        } else if (holder instanceof NewMainOptionViewHolder) {
            ((NewMainOptionViewHolder) holder).bind((MainOptionModel) model);
        }else if (holder instanceof MainSelfTaskHolder) {
            ((MainSelfTaskHolder) holder).bind((MainSelfTaskModel) model);
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
            mBinding.setViewModel(new ItemMainPasswordVM(model, position));
            mBinding.setPresenter(mPresenter);
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
            mTargetSearch = binding.getRoot();
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

    public class NewMainOptionViewHolder extends RecyclerView.ViewHolder {
        private ItemMainOptionNewBinding mBinding;
        private MainOptionAdapter mAdapter;

        public NewMainOptionViewHolder(final ItemMainOptionNewBinding binding) {
            super(binding.getRoot());
            binding.getRoot().post(new Runnable() {
                @Override
                public void run() {
                    mTargetOption = binding.getRoot();
                }
            });
            mBinding = binding;
        }

        public void bind(MainOptionModel mainOptionModel) {
            GridLayoutManager gridLayoutManager = new GridLayoutManager(mContext, 4);
            if (mAdapter == null) {
                mAdapter = new MainOptionAdapter(mainOptionModel.mData, mPresenter);
                mBinding.rlvOptions.setLayoutManager(gridLayoutManager);
                mBinding.rlvOptions.setAdapter(mAdapter);
                mBinding.rlvOptions.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        showGuideView(mActivity, mBinding.rlvOptions);
                        mBinding.rlvOptions.removeCallbacks(this);
                    }
                }, 100);
            }
        }
    }

    public class MainDailySelfViewHolder extends RecyclerView.ViewHolder {
        private ItemMainDailyselfBinding mBinding;

        public MainDailySelfViewHolder(ItemMainDailyselfBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        public void bind(MainDailySelfModel vm, MainPresenter presenter, int position) {
            mBinding.setViewModel(new ItemMainDailySelfVM(vm, position));
            mBinding.setActionHandler(new MainDailySelfHandler(presenter));
        }
    }

    private GroupRlvAdapter mGroupRlvAdapter;

    public class MainGroupViewHolder extends RecyclerView.ViewHolder {
        private ItemMainGroupBinding mBinding;

        public MainGroupViewHolder(ItemMainGroupBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
            mTargetGroup = mBinding.getRoot();
        }

        public void bind(MainGroupModel model) {
            mBinding.rlvGroup.setLayoutManager(new GridLayoutManager(mContext, 3));

            mGroupRlvAdapter = new GroupRlvAdapter(mContext, model.mData);
            mBinding.rlvGroup.setAdapter(mGroupRlvAdapter);
            mGroupRlvAdapter.setOnCheckListener(new GroupRlvAdapter.OnCheckListener() {
                @Override
                public void onCheck(boolean isCheck, MainGroupRlvModel model) {
                    RxBus.get().post(new CheckOrderEvent(isCheck, model.mGroupInfo, model.mTvGroup));
                }
            });

            mBinding.setActionHandler(new MainGroupHandler(mPresenter));
        }
    }

    private MainSelfTaskAdapter mMainSelfTaskAdapter;

    public class MainSelfTaskHolder extends RecyclerView.ViewHolder {
        private ItemMainSelfTaskBinding mBinding;

        public MainSelfTaskHolder(ItemMainSelfTaskBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        public void bind(MainSelfTaskModel model) {
            mBinding.rlvMainSelfTask.setLayoutManager(new LinearLayoutManager(mContext));

            if (model.mData == null || model.mData.size() == 0) {
                mBinding.tvSee.setVisibility(View.VISIBLE);
            }
            mMainSelfTaskAdapter = new MainSelfTaskAdapter(mContext, mPresenter, model.mData);
            mBinding.rlvMainSelfTask.setAdapter(mMainSelfTaskAdapter);
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

    public void notifyOnlyGroup() {
        mGroupRlvAdapter.notifyDataSetChanged();
    }

    public void showGuideView(Activity activity, View view) {
        if (!AppConfig.isShowGuideView()) {
            return;
        }
        Guide guide;
        GuideBuilder builder = new GuideBuilder();
        builder.setTargetView(view)
                .setAlpha(150)
                .setHighTargetCorner(20)
                .setOverlayTarget(false)
                .setOutsideTouchable(false);
        builder.setOnVisibilityChangedListener(new GuideBuilder.OnVisibilityChangedListener() {
            @Override
            public void onShown() {
            }

            @Override
            public void onDismiss() {
                AppConfig.setShowGuideView(false);
            }
        });

        builder.addComponent(new SimpleComponent());
        guide = builder.createGuide();
        guide.setShouldCheckLocInWindow(true);
        guide.show(activity);
    }


    public void showCommonGuide() {
        //Option引导
        final GuideBuilder optionBuilder = new GuideBuilder();
        optionBuilder.addComponent(new CommonComponent("主操作区，进行记录添加/管理等操作"));
        optionBuilder.setTargetView(mTargetOption)
                .setAlpha(150)
                .setHighTargetCorner(20)
                .setOverlayTarget(false)
                .setOutsideTouchable(false);
        //Guide必须要在GuideBuilder之后初始化
        final Guide optionGuide = optionBuilder.createGuide();
        optionGuide.setShouldCheckLocInWindow(true);

        //Group引导
        final GuideBuilder groupBuilder = new GuideBuilder();
        groupBuilder.addComponent(new CommonComponent("添加分组信息"));
        groupBuilder.setTargetView(mTargetGroup)
                .setAlpha(150)
                .setHighTargetCorner(20)
                .setOverlayTarget(false)
                .setOutsideTouchable(false);
        groupBuilder.setOnVisibilityChangedListener(new GuideBuilder.OnVisibilityChangedListener() {
            @Override
            public void onShown() {
            }

            @Override
            public void onDismiss() {
                optionGuide.show(mActivity);
            }
        });
        final Guide groupGuide = groupBuilder.createGuide();
        groupGuide.setShouldCheckLocInWindow(true);


        //搜索引导
        final GuideBuilder searchBuilder = new GuideBuilder();
        searchBuilder.addComponent(new CommonComponent("快速搜索本地记录信息"));
        searchBuilder.setTargetView(mTargetSearch)
                .setAlpha(150)
                .setHighTargetCorner(20)
                .setOverlayTarget(false)
                .setOutsideTouchable(false);
        searchBuilder.setOnVisibilityChangedListener(new GuideBuilder.OnVisibilityChangedListener() {
            @Override
            public void onShown() {
            }

            @Override
            public void onDismiss() {
                groupGuide.show(mActivity);
            }
        });
        final Guide searchGuide = searchBuilder.createGuide();
        searchGuide.setShouldCheckLocInWindow(true);
        searchGuide.show(mActivity);
    }
}
