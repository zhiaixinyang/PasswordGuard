package com.mdove.passwordguard.main.newmain;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mdove.passwordguard.R;
import com.mdove.passwordguard.databinding.FragmentDailyTaskBinding;

/**
 * Created by MDove on 2018/5/3.
 */

public class DailyTaskFragment extends Fragment {
    private FragmentDailyTaskBinding mBinding;

    public static DailyTaskFragment newInstance() {
        Bundle args = new Bundle();

        DailyTaskFragment fragment = new DailyTaskFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_daily_task, container, false);
        return mBinding.getRoot();
    }
}
