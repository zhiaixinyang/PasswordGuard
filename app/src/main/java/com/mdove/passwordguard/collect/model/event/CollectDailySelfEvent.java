package com.mdove.passwordguard.collect.model.event;

/**
 * Created by MDove on 2018/3/28.
 */

public class CollectDailySelfEvent {
    public boolean isFavorite;
    public long mId;

    public CollectDailySelfEvent(boolean isFavorite, long id) {
        this.isFavorite = isFavorite;
        mId = id;
    }
}
