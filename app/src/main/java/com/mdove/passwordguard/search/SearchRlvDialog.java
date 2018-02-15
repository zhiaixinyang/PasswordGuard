package com.mdove.passwordguard.search;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;

import com.mdove.passwordguard.R;
import com.mdove.passwordguard.databinding.DialogSearchRlvBinding;
import com.mdove.passwordguard.main.model.BaseMainModel;
import com.mdove.passwordguard.search.adapter.SearchRlvAdapter;
import com.mdove.passwordguard.utils.SystemUtils;

import java.util.List;

/**
 * Created by MDove on 2018/2/15.
 */

public class SearchRlvDialog extends AppCompatDialog {
    private DialogSearchRlvBinding mBinding;
    private Context mContext;
    private SearchRlvAdapter mAdapter;
    private List<BaseMainModel> mData;

    public SearchRlvDialog(Context context, List<BaseMainModel> data) {
        super(context, R.style.UpgradeDialog);
        mContext = context;
        mData = data;

        mBinding = DataBindingUtil.inflate(LayoutInflater.from(mContext), R.layout.dialog_search_rlv,
                null, false);
        setContentView(mBinding.getRoot());
        WindowManager.LayoutParams paramsWindow = getWindow().getAttributes();
        paramsWindow.width = getWindowWidth();
        setCancelable(true);
        setCanceledOnTouchOutside(false);

        initView();
    }

    private void initView() {
        mAdapter = new SearchRlvAdapter();
        mBinding.rlvSearch.setLayoutManager(new LinearLayoutManager(mContext));
        mBinding.rlvSearch.setAdapter(mAdapter);
        mAdapter.setData(mData);
    }

    protected int getWindowWidth() {
        float percent = 0.9f;
        WindowManager wm = this.getWindow().getWindowManager();
        int screenWidth = SystemUtils.getScreenWidth(wm);
        int screenHeight = SystemUtils.getScreenHeight(wm);
        return (int) (screenWidth > screenHeight
                ? screenHeight * percent
                : screenWidth * percent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mBinding.btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }
}
