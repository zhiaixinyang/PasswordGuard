package com.mdove.passwordguard.base.listlayout;

import android.app.Activity;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.mdove.passwordguard.BR;
import com.mdove.passwordguard.base.listlayout.annotation.CustomViewHolderLayout;
import com.mdove.passwordguard.base.listlayout.customviewholder.CustomViewHolderModel;
import com.mdove.passwordguard.base.listlayout.customviewholder.ICustomViewHolder;
import com.mdove.passwordguard.base.listlayout.model.BaseModelVM;
import com.mdove.passwordguard.utils.InflateUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BaseListAdapter<T> extends RecyclerView.Adapter<BaseListAdapter.BaseDBListViewHolder> {
    private List<T> mData;
    private Activity mActivity;
    private Map<Integer, CustomViewHolderModel> mCustomViewHolderMap;

    public BaseListAdapter(Activity activity) {
        mActivity = activity;
    }

    @Override
    public int getItemViewType(int position) {
        Class activity = mActivity.getClass();
        if (activity.isAssignableFrom(ICustomViewHolder.class)) {
            mCustomViewHolderMap = new HashMap<>();
            try {
                Method bindCustomViewHolderLayout = activity.getMethod("bindCustomViewHolderLayout", BaseModelVM.class);
                CustomViewHolderModel model = (CustomViewHolderModel) bindCustomViewHolderLayout.invoke(activity, mData.get(position));
                mCustomViewHolderMap.put(model.mViewType, model);
                return model.mViewType;
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }

        return super.getItemViewType(position);
    }

    @Override
    public BaseDBListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mCustomViewHolderMap != null) {
            for (Map.Entry<Integer, CustomViewHolderModel> entry : mCustomViewHolderMap.entrySet()) {
                if (viewType == entry.getKey()) {
                    int layoutId = entry.getValue().mCustomViewHolderLayoutId;
                    return new BaseCustomDBListViewHolder(InflateUtils.bindingInflate(parent, layoutId), entry.getValue());
                }
            }
            return null;
        } else {
            BaseDBListViewHolder viewHolder = useCustomViewHolder();
            if (viewHolder == null) {
                CustomViewHolderLayout customViewHolderLayout = mActivity.getClass().getAnnotation(CustomViewHolderLayout.class);
                if (customViewHolderLayout != null && customViewHolderLayout.customViewHolderLayout() != -1) {
                    ViewDataBinding viewDataBinding = InflateUtils.bindingInflate(parent, customViewHolderLayout.customViewHolderLayout());
                    return new BaseDBListViewHolder(viewDataBinding);
                } else {
                    return null;
                }
            } else {
                return viewHolder;
            }
        }
    }

    @Override
    public void onBindViewHolder(BaseListAdapter.BaseDBListViewHolder holder, int position) {
        if (holder instanceof BaseListAdapter.BaseCustomDBListViewHolder) {

        } else {
            holder.bind(mData.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    public BaseDBListViewHolder useCustomViewHolder() {
        return null;
    }


    public class BaseDBListViewHolder extends RecyclerView.ViewHolder {
        private ViewDataBinding mBinding;

        public BaseDBListViewHolder(ViewDataBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }

        public void bind(T modelVM) {
            mBinding.setVariable(BR.vm, modelVM);
        }

    }

    public class BaseCustomDBListViewHolder extends BaseDBListViewHolder {
        private ViewDataBinding mBinding;
        private CustomViewHolderModel mModel;

        public BaseCustomDBListViewHolder(ViewDataBinding binding, CustomViewHolderModel model) {
            super(binding);
            mBinding = binding;
            mModel = model;
        }

        public void bind(T modelVM) {
            mBinding.setVariable(BR.vm, modelVM);
        }

    }

    public void setData(List<T> data) {
        mData = data;
        notifyDataSetChanged();
    }
}
