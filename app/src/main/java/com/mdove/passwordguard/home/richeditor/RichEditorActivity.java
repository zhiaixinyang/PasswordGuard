package com.mdove.passwordguard.home.richeditor;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.text.TextUtils;
import android.view.View;

import com.hwangjr.rxbus.RxBus;
import com.mdove.passwordguard.App;
import com.mdove.passwordguard.R;
import com.mdove.passwordguard.base.BaseActivity;
import com.mdove.passwordguard.databinding.ActivityRichEditorBinding;
import com.mdove.passwordguard.greendao.entity.CustomReView;
import com.mdove.passwordguard.home.longplan.model.TempLongPlanModel;
import com.mdove.passwordguard.home.richeditor.adapter.RichEditorBtnAdapter;
import com.mdove.passwordguard.home.richeditor.model.RichEditorBtnModel;
import com.mdove.passwordguard.home.richeditor.model.event.RichEditorBtnReturnEvent;
import com.mdove.passwordguard.home.richeditor.presenter.RichEditorPresenter;
import com.mdove.passwordguard.home.richeditor.presenter.contract.RichEditorContract;
import com.mdove.passwordguard.ui.richeditor.RichEditor;
import com.mdove.passwordguard.ui.richeditor.knife.KnifeText;
import com.mdove.passwordguard.utils.DensityUtil;
import com.mdove.passwordguard.utils.StatusBarUtils;
import com.mdove.passwordguard.utils.ToastHelper;

import java.util.Date;
import java.util.List;

public class RichEditorActivity extends BaseActivity implements RichEditorContract.MvpView {
    public static final String EXTRA_TEMP_LONG_PLAN = "extra_temp_long_plan";
    private ActivityRichEditorBinding mBinding;
    private KnifeText knife;
    private RichEditorBtnAdapter mAdapter;
    private RichEditorPresenter mPresenter;
    private TempLongPlanModel mTempModel;

    public static void start(Context context) {
        start(context, null);
    }

    public static void start(Context context, TempLongPlanModel model) {
        Intent start = new Intent(context, RichEditorActivity.class);
        if (!(context instanceof Activity)) {
            start.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        start.putExtra(EXTRA_TEMP_LONG_PLAN, model);
        context.startActivity(start);
    }

    @Override
    protected boolean isNeedCustomLayout() {
        return true;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_rich_editor);
        StatusBarUtils.setColorDiff(this, ContextCompat.getColor(this, R.color.gray_new_home));
        mTempModel = (TempLongPlanModel) getIntent().getSerializableExtra(EXTRA_TEMP_LONG_PLAN);

        knife = mBinding.knife;
        handleTempModel(mTempModel);

        mPresenter = new RichEditorPresenter();
        mPresenter.subscribe(this);

        mAdapter = new RichEditorBtnAdapter(this, mPresenter);
        mBinding.rlvBtn.setLayoutManager(new GridLayoutManager(this, 5));
        mBinding.rlvBtn.setAdapter(mAdapter);
        mPresenter.initRichEditorBtn();

        initListener();
    }

    private void handleTempModel(TempLongPlanModel mTempModel) {
        if (mTempModel == null) {
            return;
        }
    }

    private void initListener() {
        mBinding.btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomReView customReView = new CustomReView();
                customReView.setMContent(knife.toHtml());
                customReView.setMTime(new Date().getTime());
                App.getDaoSession().getCustomReViewDao().insert(customReView);
                finish();
            }
        });
    }

    @Override
    public void initRichEditorBtn(List<RichEditorBtnModel> data) {
        mAdapter.setData(data);
    }

    @Override
    public void onClickReturn() {
//        contentHtml = "#RE#" + contentHtml;
//        RxBus.get().post(new RichEditorBtnReturnEvent(contentHtml));
//        finish();
    }

    @Override
    public void onClickRichEditorBtn(int modelType, int updatePosition) {
        mAdapter.notifyItemChanged(updatePosition);
        switch (modelType) {
            case RichEditorBtnModel.MODEL_TYPE_UNDO: {
                knife.undo();
                break;
            }
            case RichEditorBtnModel.MODEL_TYPE_REDO: {
                knife.redo();
                break;
            }
            case RichEditorBtnModel.MODEL_TYPE_BOLD: {
                knife.bold(!knife.contains(KnifeText.FORMAT_BOLD));
                break;
            }
            case RichEditorBtnModel.MODEL_TYPE_ITALIC: {
                knife.italic(!knife.contains(KnifeText.FORMAT_ITALIC));
                break;
            }
            case RichEditorBtnModel.MODEL_TYPE_SUBSCRIPT: {
                break;
            }
            case RichEditorBtnModel.MODEL_TYPE_SUPERSCRIPT: {
                break;
            }
            case RichEditorBtnModel.MODEL_TYPE_STRIKETHROUGH: {
                knife.strikethrough(!knife.contains(KnifeText.FORMAT_STRIKETHROUGH));
                break;
            }
            case RichEditorBtnModel.MODEL_TYPE_H1: {
                break;
            }
            case RichEditorBtnModel.MODEL_TYPE_H2: {
                break;
            }
            case RichEditorBtnModel.MODEL_TYPE_H3: {
                break;
            }
            case RichEditorBtnModel.MODEL_TYPE_H4: {
                break;
            }
            case RichEditorBtnModel.MODEL_TYPE_H5: {
                break;
            }
            case RichEditorBtnModel.MODEL_TYPE_H6: {
                break;
            }
            case RichEditorBtnModel.MODEL_TYPE_TXT_COLOR: {

                break;
            }
            case RichEditorBtnModel.MODEL_TYPE_BG_COLOR: {
                break;
            }
            case RichEditorBtnModel.MODEL_TYPE_INDENT: {
                break;
            }
            case RichEditorBtnModel.MODEL_TYPE_OUTDENT: {
                break;
            }
            case RichEditorBtnModel.MODEL_TYPE_JUSTIFY_LEFT: {
                break;
            }
            case RichEditorBtnModel.MODEL_TYPE_JUSTIFY_CENTER: {
                break;
            }
            case RichEditorBtnModel.MODEL_TYPE_JUSTIFY_RIGHT: {
                break;
            }
            case RichEditorBtnModel.MODEL_TYPE_BULLETS: {
                knife.bullet(!knife.contains(KnifeText.FORMAT_BULLET));
                break;
            }
            case RichEditorBtnModel.MODEL_TYPE_NUMBERST: {
                break;
            }
            case RichEditorBtnModel.MODEL_TYPE_INSERT_IMAGE: {

                break;
            }
            case RichEditorBtnModel.MODEL_TYPE_INSERT_LINK: {
                break;
            }
        }
    }

    @Override
    public Context getContext() {
        return this;
    }
}
