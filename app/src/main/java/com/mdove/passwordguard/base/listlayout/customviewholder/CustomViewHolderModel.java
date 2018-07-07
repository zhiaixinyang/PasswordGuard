package com.mdove.passwordguard.base.listlayout.customviewholder;

import com.mdove.passwordguard.base.listlayout.model.BaseModelVM;
import com.mdove.passwordguard.home.todayreview.model.CustomReViewModel;

public class CustomViewHolderModel {
    public int mViewType;
    public int mCustomViewHolderLayoutId;
    public BaseModelVM mBaseModelVM;

    public CustomViewHolderModel(int viewType, int customViewHolderLayoutId, BaseModelVM baseModelVM) {
        mViewType = viewType;
        mCustomViewHolderLayoutId = customViewHolderLayoutId;
        mBaseModelVM = baseModelVM;
    }
}
