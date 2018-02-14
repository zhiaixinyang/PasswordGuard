package com.mdove.passwordguard.net;

import com.mdove.passwordguard.model.net.RealUpdate;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * @author MDove on 2018/2/14.
 */
public interface ApiServer {
    @FormUrlEncoded
    @POST("checkUpdate")
    Observable<RealUpdate> checkUpdate(@Field("version") String version);
}
