package com.mdove.passwordguard.main.model.event;

/**
 * Created by MDove on 2018/4/22.
 */

public class DailyTaskScrollEvent {
    public int mScrollPosition;
    public int mViewHeight;

    public DailyTaskScrollEvent(int scrollPosition, int viewHeight) {
        mScrollPosition = scrollPosition;
        mViewHeight = viewHeight;
    }
}
