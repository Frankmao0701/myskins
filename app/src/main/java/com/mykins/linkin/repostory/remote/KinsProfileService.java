package com.mykins.linkin.repostory.remote;

import com.google.gson.internal.LinkedTreeMap;
import com.mykins.linkin.bean.MyKinsServiceResponse;

import io.reactivex.Single;
import retrofit2.http.GET;

/**
 * Created by yjn on 2017/12/18.
 */

public interface KinsProfileService {

    @GET("config/getRelationTitleList")
    Single<MyKinsServiceResponse<LinkedTreeMap<Object, Object>>> getRelationTitleList();
}
