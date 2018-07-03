package com.mdove.passwordguard.home.richeditor.model;

public class RichEditorBtnModel {
    public static final int MODEL_TYPE_UNDO = 0;
    public static final int MODEL_TYPE_REDO = 1;
    public static final int MODEL_TYPE_BOLD = 2;
    public static final int MODEL_TYPE_ITALIC = 3;
    public static final int MODEL_TYPE_SUBSCRIPT = 4;
    public static final int MODEL_TYPE_SUPERSCRIPT = 5;
    public static final int MODEL_TYPE_STRIKETHROUGH = 6;
    public static final int MODEL_TYPE_H1 = 7;
    public static final int MODEL_TYPE_H2 = 8;
    public static final int MODEL_TYPE_H3 = 9;
    public static final int MODEL_TYPE_H4 = 10;
    public static final int MODEL_TYPE_H5 = 11;
    public static final int MODEL_TYPE_H6 = 12;
    public static final int MODEL_TYPE_TXT_COLOR = 13;
    public static final int MODEL_TYPE_BG_COLOR = 14;
    public static final int MODEL_TYPE_INDENT = 15;
    public static final int MODEL_TYPE_OUTDENT = 16;
    public static final int MODEL_TYPE_JUSTIFY_LEFT = 17;
    public static final int MODEL_TYPE_JUSTIFY_CENTER = 18;
    public static final int MODEL_TYPE_JUSTIFY_RIGHT = 19;
    public static final int MODEL_TYPE_BULLETS = 20;
    public static final int MODEL_TYPE_NUMBERST = 21;
    public static final int MODEL_TYPE_INSERT_IMAGE = 22;
    public static final int MODEL_TYPE_INSERT_LINK = 23;

    public String mTitle;
    public int mIcon;
    public boolean isSelect;
    public boolean isNeedSelect;
    public int mSelectColor;
    public int mModelType;

    public RichEditorBtnModel(String title, int icon, boolean isSelect, int selectColor, boolean needSelect, int modelType) {
        mTitle = title;
        mIcon = icon;
        this.isSelect = isSelect;
        isNeedSelect = needSelect;
        mSelectColor = selectColor;
        mModelType = modelType;
    }
}
