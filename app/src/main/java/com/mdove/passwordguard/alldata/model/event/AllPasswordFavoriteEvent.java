package com.mdove.passwordguard.alldata.model.event;

/**
 * Created by MDove on 2018/4/12.
 */

public class AllPasswordFavoriteEvent {
    public long mId;
    public boolean mIsFavorite;

    public AllPasswordFavoriteEvent(long id, boolean isFavorite) {
        mIsFavorite = isFavorite;
        mId = id;
    }
}
