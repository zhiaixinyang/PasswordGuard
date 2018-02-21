package com.mdove.passwordguard.main.model;

import java.io.Serializable;

/**
 * Created by MDove on 2018/2/9.
 */

public class BaseMainModel implements Serializable{
    //判断Item类型，0为操作型Item，1为数据型Item
    public int mType;
    public String mTime;
}
