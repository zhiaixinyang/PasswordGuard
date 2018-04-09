package com.mdove.passwordguard.main.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mdove.passwordguard.R;
import com.mdove.passwordguard.databinding.FragmentYesterdayPlanBinding;

/**
 * Created by MDove on 2018/4/9.
 */
public class YesterdayPlanFragment extends Fragment{
    private FragmentYesterdayPlanBinding mBinding;

    public static YesterdayPlanFragment newInstance() {
        YesterdayPlanFragment fragment = new YesterdayPlanFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_yesterday_plan, container, false);
        return mBinding.getRoot();
    }
}
