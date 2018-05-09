package com.mdove.passwordguard.main.newmain.home;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mdove.passwordguard.R;
import com.mdove.passwordguard.databinding.FragmentOptionsBinding;

/**
 * Created by MDove on 2018/5/3.
 */

public class OptionsFragment extends Fragment {
    private FragmentOptionsBinding mBinding;

    public static OptionsFragment newInstance() {

        Bundle args = new Bundle();

        OptionsFragment fragment = new OptionsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_options, container, false);
        return mBinding.getRoot();
    }
}
