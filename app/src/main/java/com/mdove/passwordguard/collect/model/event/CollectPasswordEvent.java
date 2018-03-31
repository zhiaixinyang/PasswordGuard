package com.mdove.passwordguard.collect.model.event;

/**
 * Created by MDove on 2018/3/31.
 */

public class CollectPasswordEvent {
    public boolean isFavorite;
    public long mId;

    public CollectPasswordEvent(boolean isFavorite, long id) {
        this.isFavorite = isFavorite;
        mId = id;
    }
}
