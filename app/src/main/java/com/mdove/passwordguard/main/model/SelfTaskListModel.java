package com.mdove.passwordguard.main.model;


import com.mdove.passwordguard.task.model.SelfTaskModel;

import java.util.List;

/**
 * Created by MDove on 2018/3/25.
 */

public class SelfTaskListModel extends BaseMainModel {
    public List<SelfTaskModel> mData;

    public SelfTaskListModel(List<SelfTaskModel> selfTaskModels) {
        mData = selfTaskModels;
    }
}
