package com.mykins.linkin.repostory.remote;

import com.mykins.linkin.bean.MyKinsServiceResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by yjn on 2017/12/9.
 */

public interface RegisterService {

    @GET("user/sendRegisterVerifyCode")
    Observable<MyKinsServiceResponse<String>> sendRegisterVerifyCode(
            @Query("email_or_phone") String email_or_phone
    );
}
