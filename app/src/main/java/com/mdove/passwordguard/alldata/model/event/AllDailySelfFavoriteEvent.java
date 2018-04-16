package com.mdove.passwordguard.alldata.model.event;

/**
 * Created by MDove on 2018/4/16.
 */

public class AllDailySelfFavoriteEvent {
    public long mId;
    public boolean mIsFavorite;

    public AllDailySelfFavoriteEvent(long id, boolean isFavorite) {
        mId = id;
        mIsFavorite = isFavorite;
    }
}
