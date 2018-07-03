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
import android.view.View;
import android.widget.TextView;

import com.mdove.passwordguard.R;
import com.mdove.passwordguard.base.BaseActivity;
import com.mdove.passwordguard.databinding.ActivityRichEditorBinding;
import com.mdove.passwordguard.home.richeditor.adapter.RichEditorBtnAdapter;
import com.mdove.passwordguard.home.richeditor.model.RichEditorBtnModel;
import com.mdove.passwordguard.home.richeditor.presenter.RichEditorPresenter;
import com.mdove.passwordguard.home.richeditor.presenter.contract.RichEditorContract;
import com.mdove.passwordguard.ui.richeditor.RichEditor;
import com.mdove.passwordguard.utils.StatusBarUtils;

import java.util.List;

public class RichEditorActivity extends BaseActivity implements RichEditorContract.MvpView {
    public static String html;
    private ActivityRichEditorBinding mBinding;
    private RichEditor mEditor;
    private RichEditorBtnAdapter mAdapter;
    private RichEditorPresenter mPresenter;


    public static void start(Context context) {
        Intent start = new Intent(context, RichEditorActivity.class);
        if (!(context instanceof Activity)) {
            start.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        }
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

        mEditor = mBinding.editor;

        mEditor.setEditorHeight(200);
        mEditor.setEditorFontSize(16);
        mEditor.setEditorFontColor(Color.RED);
        mEditor.setEditorBackgroundColor(ContextCompat.getColor(this, R.color.gray_new_home));
//        mEditor.setBackgroundColor(Color.BLUE);
        //mEditor.setBackgroundResource(R.drawable.bg);
        mEditor.setPadding(10, 10, 10, 10);
        //mEditor.setBackground("https://raw.githubusercontent.com/wasabeef/art/master/chip.jpg");
        mEditor.setPlaceholder("添加内容...");
        //mEditor.setInputEnabled(false);
        mPresenter = new RichEditorPresenter();
        mPresenter.subscribe(this);

        mAdapter = new RichEditorBtnAdapter(this, mPresenter);
        mBinding.rlvBtn.setLayoutManager(new GridLayoutManager(this, 5));
        mBinding.rlvBtn.setAdapter(mAdapter);
        mPresenter.initRichEditorBtn();

        initListener();
    }

    private void initListener() {
        mEditor.setOnTextChangeListener(new RichEditor.OnTextChangeListener() {
            @Override
            public void onTextChange(String text) {
                html = text;
                mBinding.content.setText(html);
            }
        });

        mBinding.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEditor.setHtml(html);
            }
        });
    }

    @Override
    public void initRichEditorBtn(List<RichEditorBtnModel> data) {
        mAdapter.setData(data);
    }

    @Override
    public void onClickRichEditorBtn(int modelType, int updatePosition) {
        mAdapter.notifyItemChanged(updatePosition);
        switch (modelType) {
            case RichEditorBtnModel.MODEL_TYPE_UNDO: {
                mEditor.undo();
                break;
            }
            case RichEditorBtnModel.MODEL_TYPE_REDO: {
                mEditor.redo();
                break;
            }
            case RichEditorBtnModel.MODEL_TYPE_BOLD: {
                mEditor.setBold();
                break;
            }
            case RichEditorBtnModel.MODEL_TYPE_ITALIC: {
                mEditor.setItalic();
                break;
            }
            case RichEditorBtnModel.MODEL_TYPE_SUBSCRIPT: {
                mEditor.setSubscript();
                break;
            }
            case RichEditorBtnModel.MODEL_TYPE_SUPERSCRIPT: {
                mEditor.setSuperscript();
                break;
            }
            case RichEditorBtnModel.MODEL_TYPE_STRIKETHROUGH: {
                mEditor.setStrikeThrough();
                break;
            }
            case RichEditorBtnModel.MODEL_TYPE_H1: {
                mEditor.setHeading(1);
                break;
            }
            case RichEditorBtnModel.MODEL_TYPE_H2: {
                mEditor.setHeading(2);
                break;
            }
            case RichEditorBtnModel.MODEL_TYPE_H3: {
                mEditor.setHeading(3);
                break;
            }
            case RichEditorBtnModel.MODEL_TYPE_H4: {
                mEditor.setHeading(4);
                break;
            }
            case RichEditorBtnModel.MODEL_TYPE_H5: {
                mEditor.setHeading(5);
                break;
            }
            case RichEditorBtnModel.MODEL_TYPE_H6: {
                mEditor.setHeading(6);
                break;
            }
            case RichEditorBtnModel.MODEL_TYPE_TXT_COLOR: {

                break;
            }
            case RichEditorBtnModel.MODEL_TYPE_BG_COLOR: {
                break;
            }
            case RichEditorBtnModel.MODEL_TYPE_INDENT: {
                mEditor.setIndent();
                break;
            }
            case RichEditorBtnModel.MODEL_TYPE_OUTDENT: {
                mEditor.setOutdent();
                break;
            }
            case RichEditorBtnModel.MODEL_TYPE_JUSTIFY_LEFT: {
                mEditor.setAlignLeft();
                break;
            }
            case RichEditorBtnModel.MODEL_TYPE_JUSTIFY_CENTER: {
                mEditor.setAlignCenter();
                break;
            }
            case RichEditorBtnModel.MODEL_TYPE_JUSTIFY_RIGHT: {
                mEditor.setAlignRight();
                break;
            }
            case RichEditorBtnModel.MODEL_TYPE_BULLETS: {
                mEditor.setBullets();
                break;
            }
            case RichEditorBtnModel.MODEL_TYPE_NUMBERST: {
                mEditor.setNumbers();
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
