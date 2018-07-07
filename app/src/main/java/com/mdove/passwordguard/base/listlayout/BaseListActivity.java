package com.mdove.passwordguard.base.listlayout;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.mdove.passwordguard.R;
import com.mdove.passwordguard.base.listlayout.annotation.TitleLayout;
import com.mdove.passwordguard.base.listlayout.model.BaseModelVM;
import com.mdove.passwordguard.databinding.ActivityBaseListBinding;
import com.mdove.passwordguard.utils.InflateUtils;
import com.mdove.passwordguard.utils.StatusBarUtils;

import java.util.List;

public abstract class BaseListActivity extends AppCompatActivity {
    private ActivityBaseListBinding mBinding;
    private BaseListAdapter<BaseModelVM> mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_base_list);
        StatusBarUtils.setColorDiff(this, ContextCompat.getColor(this, R.color.gray_new_home));
        initTitleLayout();
        initRlv();

        onCreateOver();
    }

    protected abstract void onCreateOver();

    private void initRlv() {
        if (useCustomLayoutManager() != null) {
            mBinding.rlvContent.setLayoutManager(useCustomLayoutManager());
        } else {
            mBinding.rlvContent.setLayoutManager(new LinearLayoutManager(this));
        }

        mAdapter = useCustomAdapter();
        if (mAdapter != null) {
            mBinding.rlvContent.setAdapter(mAdapter);
        } else {
            mAdapter = new BaseListAdapter<>(this);
            mBinding.rlvContent.setAdapter(mAdapter);
        }
    }

    private void initTitleLayout() {
        Class realActivity = this.getClass();
        TitleLayout titleLayout = (TitleLayout) realActivity.getAnnotation(TitleLayout.class);
        if (titleLayout != null) {
            mBinding.layoutTitle.addView(InflateUtils.inflate(mBinding.layoutTitle, titleLayout.titleLayout()));
        }
    }

    public BaseListAdapter useCustomAdapter() {
        return null;
    }

    public RecyclerView.LayoutManager useCustomLayoutManager() {
        return null;
    }

    public void setData(List<BaseModelVM> data) {
        if (mAdapter != null) {
            mAdapter.setData(data);
        }
    }

}
