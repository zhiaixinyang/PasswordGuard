package com.mdove.passwordguard.home.richeditor.presenter;

import com.mdove.passwordguard.R;
import com.mdove.passwordguard.home.richeditor.model.RichEditorBtnModel;
import com.mdove.passwordguard.home.richeditor.presenter.contract.RichEditorContract;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MDove on 2018/7/3.
 */

public class RichEditorPresenter implements RichEditorContract.Presenter {
    private RichEditorContract.MvpView mView;
    private List<RichEditorBtnModel> mData;

    @Override
    public void subscribe(RichEditorContract.MvpView view) {
        mView = view;
    }

    @Override
    public void unSubscribe() {
    }

    @Override
    public void initRichEditorBtn() {
        mData = new ArrayList<>();
        mData.add(new RichEditorBtnModel("返回", R.mipmap.undo, false, R.color.black, false, RichEditorBtnModel.MODEL_TYPE_UNDO));
        mData.add(new RichEditorBtnModel("撤回", R.mipmap.redo, false, R.color.black, false, RichEditorBtnModel.MODEL_TYPE_REDO));
        mData.add(new RichEditorBtnModel("加粗", R.mipmap.bold, false, R.color.blue_700, true, RichEditorBtnModel.MODEL_TYPE_BOLD));
        mData.add(new RichEditorBtnModel("倾斜", R.mipmap.italic, false, R.color.blue_700, true, RichEditorBtnModel.MODEL_TYPE_ITALIC));
//        mData.add(new RichEditorBtnModel("下标", R.mipmap.subscript, false, R.color.blue_700, true, RichEditorBtnModel.MODEL_TYPE_SUBSCRIPT));
//        mData.add(new RichEditorBtnModel("上标", R.mipmap.superscript, false, R.color.blue_700, true, RichEditorBtnModel.MODEL_TYPE_SUPERSCRIPT));
        mData.add(new RichEditorBtnModel("中划线", R.mipmap.strikethrough, false, R.color.blue_700, true, RichEditorBtnModel.MODEL_TYPE_STRIKETHROUGH));
//        mData.add(new RichEditorBtnModel("标题1", R.mipmap.h1, false, R.color.blue_700, true, RichEditorBtnModel.MODEL_TYPE_H1));
//        mData.add(new RichEditorBtnModel("标题2", R.mipmap.h2, false, R.color.blue_700, true, RichEditorBtnModel.MODEL_TYPE_H2));
//        mData.add(new RichEditorBtnModel("标题3", R.mipmap.h3, false, R.color.blue_700, true, RichEditorBtnModel.MODEL_TYPE_H3));
//        mData.add(new RichEditorBtnModel("标题4", R.mipmap.h4, false, R.color.blue_700, true, RichEditorBtnModel.MODEL_TYPE_H4));
//        mData.add(new RichEditorBtnModel("标题5", R.mipmap.h5, false, R.color.blue_700, true, RichEditorBtnModel.MODEL_TYPE_H5));
//        mData.add(new RichEditorBtnModel("标题6", R.mipmap.h6, false, R.color.blue_700, true, RichEditorBtnModel.MODEL_TYPE_H6));
//        data.add(new RichEditorBtnModel("文本颜色", R.mipmap.txt_color, false, R.color.blue_700, true, RichEditorBtnModel.MODEL_TYPE_TXT_COLOR));
//        data.add(new RichEditorBtnModel("填充背景", R.mipmap.bg_color, false, R.color.blue_700, true, RichEditorBtnModel.MODEL_TYPE_BG_COLOR));
//        mData.add(new RichEditorBtnModel("左缩进", R.mipmap.indent, false, R.color.blue_700, true, RichEditorBtnModel.MODEL_TYPE_INDENT));
//        mData.add(new RichEditorBtnModel("右缩进", R.mipmap.outdent, false, R.color.blue_700, true, RichEditorBtnModel.MODEL_TYPE_OUTDENT));
//        mData.add(new RichEditorBtnModel("左对齐", R.mipmap.justify_left, false, R.color.blue_700, true, RichEditorBtnModel.MODEL_TYPE_JUSTIFY_LEFT));
//        mData.add(new RichEditorBtnModel("居中对齐", R.mipmap.justify_center, false, R.color.blue_700, true, RichEditorBtnModel.MODEL_TYPE_JUSTIFY_CENTER));
//        mData.add(new RichEditorBtnModel("右对齐", R.mipmap.justify_right, false, R.color.blue_700, true, RichEditorBtnModel.MODEL_TYPE_JUSTIFY_RIGHT));
        mData.add(new RichEditorBtnModel("圆点序号", R.mipmap.bullets, false, R.color.blue_700, true, RichEditorBtnModel.MODEL_TYPE_BULLETS));
//        mData.add(new RichEditorBtnModel("数字序号", R.mipmap.numbers, false, R.color.blue_700, true, RichEditorBtnModel.MODEL_TYPE_NUMBERST));
//        data.add(new RichEditorBtnModel("插入图片", R.mipmap.insert_image, false, R.color.blue_700, true, RichEditorBtnModel.MODEL_TYPE_INSERT_IMAGE));
//        data.add(new RichEditorBtnModel("插入链接", R.mipmap.insert_link, false, R.color.blue_700, true, RichEditorBtnModel.MODEL_TYPE_INSERT_LINK));

        mView.initRichEditorBtn(mData);
    }

    @Override
    public void onClickRichEditorBtn(int modelType) {
        int position = -1;
        RichEditorBtnModel updateModel = null;
        for (RichEditorBtnModel model : mData) {
            if (model.mModelType == modelType) {
                position = mData.indexOf(model);
                updateModel = model;
            }
        }
        if (position != -1 && updateModel != null) {
            if (updateModel.isSelect) {
                updateModel.isSelect = false;
            } else {
                updateModel.isSelect = true;
            }
            mView.onClickRichEditorBtn(modelType, position);
        }
    }

    @Override
    public void onClickBack() {
        mView.finish();
    }

    @Override
    public void onClickReturn() {
        mView.onClickReturn();
    }
}
