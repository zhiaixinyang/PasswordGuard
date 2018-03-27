package com.mdove.passwordguard.task;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mdove.passwordguard.R;
import com.mdove.passwordguard.databinding.FragmentAllSelfTaskBinding;

/**
 * Created by MDove on 2018/3/27.
 */

public class DeleteSelfTaskFragment extends Fragment {
    private FragmentAllSelfTaskBinding mBinding;

    public static DeleteSelfTaskFragment newInstance() {
        DeleteSelfTaskFragment fragment = new DeleteSelfTaskFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_delete_self_task, container, false);

        return mBinding.getRoot();
    }
}
