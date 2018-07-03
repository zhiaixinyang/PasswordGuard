package com.mdove.passwordguard.home.richeditor.model.vm;

import android.databinding.BindingAdapter;
import android.databinding.ObservableField;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.widget.ImageView;
import android.widget.TextView;

import com.mdove.passwordguard.App;
import com.mdove.passwordguard.R;
import com.mdove.passwordguard.home.richeditor.model.RichEditorBtnModel;

public class RichEditorBtnModelVM {
    public ObservableField<String> mTitle = new ObservableField<>();
    public ObservableField<Boolean> isNeedSelect = new ObservableField<>();
    public ObservableField<Boolean> isSelect = new ObservableField<>();
    public ObservableField<Integer> mIcon = new ObservableField<>();
    public ObservableField<Integer> mSelectColor = new ObservableField<>();
    public ObservableField<Integer> mModelType = new ObservableField<>();

    public RichEditorBtnModelVM(RichEditorBtnModel model) {
        mTitle.set(model.mTitle);
        isSelect.set(model.isSelect);
        mIcon.set(model.mIcon);
        mSelectColor.set(model.mSelectColor);
        isNeedSelect.set(model.isNeedSelect);
        mModelType.set(model.mModelType);
    }

    @BindingAdapter("richEditorIc")
    public static void richEditorIc(ImageView iv, RichEditorBtnModelVM vm) {
        iv.setImageResource(vm.mIcon.get());
        if (!vm.isNeedSelect.get()) {
            iv.setColorFilter(Color.BLACK);
            return;
        }
        if (vm.isSelect.get()) {
            iv.setColorFilter(ContextCompat.getColor(App.getAppContext(), R.color.blue_700));
        } else {
            iv.setColorFilter(Color.BLACK);
        }
    }

    @BindingAdapter("richEditorTv")
    public static void richEditorTv(TextView tv, RichEditorBtnModelVM vm) {
        tv.setText(vm.mTitle.get());
        if (!vm.isNeedSelect.get()) {
            tv.setTextColor(Color.BLACK);
            return;
        }
        if (vm.isSelect.get()) {
            tv.setTextColor(ContextCompat.getColor(App.getAppContext(), R.color.blue_700));
        } else {
            tv.setTextColor(Color.BLACK);
        }
    }
}
