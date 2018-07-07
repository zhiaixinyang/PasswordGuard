package com.mdove.passwordguard.home.main.model.vm;

import android.databinding.BindingAdapter;
import android.databinding.ObservableField;

import com.mdove.passwordguard.home.main.model.HomeCustomReViewModel;
import com.mdove.passwordguard.home.todayreview.model.CustomReViewModel;
import com.mdove.passwordguard.ui.richeditor.knife.KnifeText;
import com.mdove.passwordguard.utils.DateUtils;

public class HomeCustomReViewModelVM {
    public ObservableField<String> mContent = new ObservableField<>();
    public ObservableField<String> mTime = new ObservableField<>();

    public HomeCustomReViewModelVM(HomeCustomReViewModel model) {
        mContent.set(model.mContent);
        mTime.set(DateUtils.getDateChinese(model.mTime));
    }

    @BindingAdapter("showHtmlText")
    public static void showHtmlText(KnifeText text, String content) {
        text.fromHtml(content);
    }
}
