package com.mdove.passwordguard.home.todayreview.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mdove.passwordguard.R;
import com.mdove.passwordguard.databinding.FragmentCustomReviewBinding;

/**
 * Created by MDove on 2018/6/30.
 */

public class CustomReViewFragment extends Fragment {
    private FragmentCustomReviewBinding mBinding;

    public static CustomReViewFragment newInstance() {
        Bundle args = new Bundle();

        CustomReViewFragment fragment = new CustomReViewFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_custom_review, container, false);
        return mBinding.getRoot();
    }
}
