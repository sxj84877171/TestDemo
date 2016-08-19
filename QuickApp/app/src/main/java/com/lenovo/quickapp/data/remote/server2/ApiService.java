package com.lenovo.quickapp.data.remote.server2;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by elvissun on 8/18/2016.
 */
public interface ApiService {

    @GET("login")
    public Observable<?> login(@Query("username") String username, @Query("password") String password);

}
