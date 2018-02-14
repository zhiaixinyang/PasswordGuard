package com.mdove.passwordguard.net;

import android.support.v4.util.Preconditions;

import com.mdove.passwordguard.App;
import com.mdove.passwordguard.model.net.RealUpdate;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by MDove on 2018/2/14.
 */

public class ApiServerImpl {
    private static  <T> Observable<T> wrapper(Observable<T> observable) {
        return observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static Observable<RealUpdate> checkUpdate(String version) {
        return wrapper(App.getApiServer().checkUpdate(version));
    }
}
