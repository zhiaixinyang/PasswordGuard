package com.mdove.passwordguard.deletelist.model.vm;

import android.databinding.ObservableField;

import com.mdove.passwordguard.App;
import com.mdove.passwordguard.R;
import com.mdove.passwordguard.deletelist.model.DeleteTopModel;
import com.mdove.passwordguard.utils.DateUtils;

/**
 * Created by MDove on 2018/2/14.
 */

public class ItemDeleteListTopVM {
    public ObservableField<String> mTimeAll = new ObservableField<>();
    public ObservableField<String> mAllDelete = new ObservableField<>();

    public ItemDeleteListTopVM(DeleteTopModel model) {
        mTimeAll.set(DateUtils.getDateChinese(model.mTime));
        mAllDelete.set(String.format(App.getAppContext().getString(R.string.string_delete_list_top), model.mAllDeleteSize));
    }
}
