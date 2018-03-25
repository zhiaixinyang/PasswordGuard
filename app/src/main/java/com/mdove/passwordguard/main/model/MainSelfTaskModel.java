package com.mdove.passwordguard.main.model;

import com.mdove.passwordguard.task.model.SelfTaskModel;

import java.util.List;

/**
 * Created by MDove on 2018/3/25.
 */

public class MainSelfTaskModel extends BaseMainModel {
    public List<SelfTaskModel> mData;

    public MainSelfTaskModel(List<SelfTaskModel> selfTaskModels) {
        mData = selfTaskModels;
    }
}
