package com.mdove.passwordguard.home.richeditor;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringDef;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.text.TextUtils;
import android.view.View;

import com.mdove.passwordguard.App;
import com.mdove.passwordguard.R;
import com.mdove.passwordguard.base.BaseActivity;
import com.mdove.passwordguard.databinding.ActivityRichEditorBinding;
import com.mdove.passwordguard.greendao.entity.CustomReView;
import com.mdove.passwordguard.greendao.entity.LongPlan;
import com.mdove.passwordguard.home.longplan.model.TempLongPlanModel;
import com.mdove.passwordguard.home.longplan.model.handler.LongPlanHandler;
import com.mdove.passwordguard.home.richeditor.adapter.RichEditorBtnAdapter;
import com.mdove.passwordguard.home.richeditor.model.RichEditorBtnModel;
import com.mdove.passwordguard.home.richeditor.presenter.RichEditorPresenter;
import com.mdove.passwordguard.home.richeditor.presenter.contract.RichEditorContract;
import com.mdove.passwordguard.ui.richeditor.knife.KnifeText;
import com.mdove.passwordguard.utils.StatusBarUtils;
import com.mdove.passwordguard.utils.ToastHelper;

import java.util.Date;
import java.util.List;

public class RichEditorActivity extends BaseActivity implements RichEditorContract.MvpView {
    public static final String EXTRA_TEMP_LONG_PLAN = "extra_temp_long_plan";
    public static final String INTENT_TYPE_FROM_LONG_PLAN = "intent_type_from_long_plan";
    public static final String INTENT_TYPE_FROM_CUSTOM_REVIEW = "intent_type_from_custom_review";
    public static final String INTENT_TYPE_FROM_KEY = "INTENT_TYPE_FROM_KEY";

    private ActivityRichEditorBinding mBinding;
    private KnifeText mKnife;
    private RichEditorBtnAdapter mAdapter;
    private RichEditorPresenter mPresenter;
    private TempLongPlanModel mTempModel;
    private String mCurIntentType = INTENT_TYPE_FROM_LONG_PLAN;

    public static void start(Context context) {
        start(context, INTENT_TYPE_FROM_LONG_PLAN);
    }

    @StringDef(value = {INTENT_TYPE_FROM_LONG_PLAN, INTENT_TYPE_FROM_CUSTOM_REVIEW})
    public @interface IntentTypeFrom {
    }

    public static void start(Context context, @IntentTypeFrom String type) {
        Intent start = new Intent(context, RichEditorActivity.class);
        if (!(context instanceof Activity)) {
            start.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        start.putExtra(INTENT_TYPE_FROM_KEY, type);
        context.startActivity(start);
    }

    public static void start(Context context, TempLongPlanModel model, @IntentTypeFrom String type) {
        Intent start = new Intent(context, RichEditorActivity.class);
        if (!(context instanceof Activity)) {
            start.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        start.putExtra(INTENT_TYPE_FROM_KEY, type);
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

        mKnife = mBinding.knife;
        handleIntent(getIntent());
        handleTempModel(mTempModel);

        mPresenter = new RichEditorPresenter();
        mPresenter.subscribe(this);

        mAdapter = new RichEditorBtnAdapter(this, mPresenter);
        mBinding.rlvBtn.setLayoutManager(new GridLayoutManager(this, 5));
        mBinding.rlvBtn.setAdapter(mAdapter);
        mPresenter.initRichEditorBtn();

        initListener();
    }

    private void handleIntent(Intent intent) {
        if (intent == null) {
            return;
        }

        switch (intent.getStringExtra(INTENT_TYPE_FROM_KEY)) {
            case INTENT_TYPE_FROM_LONG_PLAN: {
                mCurIntentType = INTENT_TYPE_FROM_LONG_PLAN;
                break;
            }
            case INTENT_TYPE_FROM_CUSTOM_REVIEW: {
                mCurIntentType = INTENT_TYPE_FROM_CUSTOM_REVIEW;
                break;
            }
            default: {
                mCurIntentType = INTENT_TYPE_FROM_LONG_PLAN;
                break;
            }
        }

    }

    private void handleTempModel(TempLongPlanModel mTempModel) {
        if (mTempModel != null) {
            if (!TextUtils.isEmpty(mTempModel.mLongPlan)) {
                mKnife.setText(mTempModel.mLongPlan);
            }
        }
    }

    private void initListener() {
        mBinding.btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (mCurIntentType) {
                    case INTENT_TYPE_FROM_LONG_PLAN: {
                        LongPlan longPlan = new LongPlan();
                        if (mTempModel != null) {
                            longPlan.mEndTime = mTempModel.mEndTime;
                            longPlan.mStartTime = mTempModel.mStartTime;
                            longPlan.mImportant = mTempModel.mImportant;
                            longPlan.mUrgent = mTempModel.mUrgent;
                            longPlan.mLabel = mTempModel.mLabel;
                            longPlan.mLabelId = mTempModel.mLabelId;
                            longPlan.mTips = mTempModel.mTips;
                            longPlan.mTime = new Date().getTime();
                            longPlan.mLongPlan = mKnife.toHtml();
                            longPlan.mIsSee = mTempModel.mIsSee;
                            longPlan.mIsSuc = mTempModel.mIsSuc;
                            mPresenter.insertLongPlan(longPlan);
                            ToastHelper.shortToast(getString(R.string.str_long_plan_insert_suc));
                            break;
                        } else {
                            break;
                        }
                    }
                    case INTENT_TYPE_FROM_CUSTOM_REVIEW: {
                        CustomReView customReView = new CustomReView();
                        customReView.setMContent(mKnife.toHtml());
                        customReView.setMTime(new Date().getTime());
                        mPresenter.insertCustomReView(customReView);
                        ToastHelper.shortToast(getString(R.string.str_custom_review_insert_suc));
                        break;
                    }
                    default: {
                        break;
                    }
                }

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
                mKnife.undo();
                break;
            }
            case RichEditorBtnModel.MODEL_TYPE_REDO: {
                mKnife.redo();
                break;
            }
            case RichEditorBtnModel.MODEL_TYPE_BOLD: {
                mKnife.bold(!mKnife.contains(KnifeText.FORMAT_BOLD));
                break;
            }
            case RichEditorBtnModel.MODEL_TYPE_ITALIC: {
                mKnife.italic(!mKnife.contains(KnifeText.FORMAT_ITALIC));
                break;
            }
            case RichEditorBtnModel.MODEL_TYPE_SUBSCRIPT: {
                break;
            }
            case RichEditorBtnModel.MODEL_TYPE_SUPERSCRIPT: {
                break;
            }
            case RichEditorBtnModel.MODEL_TYPE_STRIKETHROUGH: {
                mKnife.strikethrough(!mKnife.contains(KnifeText.FORMAT_STRIKETHROUGH));
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
                mKnife.bullet(!mKnife.contains(KnifeText.FORMAT_BULLET));
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
