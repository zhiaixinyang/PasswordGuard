package com.mdove.passwordguard.home.main.model;

public class BaseHomeMessModel {
    public long mTime;
    public Long id;
    public int mIsSuc;//0表示没有点击完成,1表示按时，2表示未完成(提前)，3表示未完成(延迟)
    public int mIsSee;//0表示不在首页展示(暂时没用，历史遗留字段)
}
