package com.mdove.passwordguard.home.main.model;

import android.databinding.ObservableField;

/**
 * Created by MDove on 2018/6/26.
 */

public class HomeTimeModelVM {
    public ObservableField<String> mYear=new ObservableField<>();
    public ObservableField<String> mMonth=new ObservableField<>();
    public ObservableField<String> mDay=new ObservableField<>();

    public HomeTimeModelVM(String year,String month,String day){
        mYear.set(year);
        mMonth.set(month);
        mDay.set(day);
    }
}
