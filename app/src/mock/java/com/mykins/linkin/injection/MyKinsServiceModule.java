package com.mykins.linkin.injection;

import android.support.annotation.Nullable;
import android.util.Log;

import com.google.gson.Gson;
import com.mykins.linkin.component.AppSchedulers;
import com.mykins.linkin.BuildConfig;
import com.mykins.linkin.repostory.remote.KinsProfileService;
import com.mykins.linkin.repostory.remote.LoginService;
import com.mykins.linkin.repostory.remote.RegisterService;
import com.mykins.linkin.R;
import com.mykins.linkin.bean.MyKinsServiceResponse;
import com.mykins.linkin.util.ResUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

/**
 * Created by jerry on 2017/12/8.
 */
@Module
public class MyKinsServiceModule {

    @Singleton
    @Provides
    public Retrofit provideRetrofit(final AppSchedulers schedulers) {
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
        httpClientBuilder.connectTimeout(60, TimeUnit.SECONDS);
        final String baseUrl = ResUtils.string(R.string.cfg_api_base_url);
        return new Retrofit.Builder()
                .client(httpClientBuilder.build())
                .addConverterFactory(new Converter.Factory() {
                    Gson gson = new Gson();

                    /**
                     * Returns a {@link Converter} for converting an HTTP response body to {@code type}, or null if
                     * {@code type} cannot be handled by this factory. This is used to create converters for
                     * response types such as {@code SimpleResponse} from a {@code Call<SimpleResponse>}
                     * declaration.
                     *
                     * @param type
                     * @param annotations
                     * @param retrofit
                     */
                    @Nullable
                    @Override
                    public Converter<ResponseBody, MyKinsServiceResponse> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
                        return value -> {
                            String content = value.string();
                            if (BuildConfig.DEBUG) {
                                Log.d("retrofit", content + "");
                            }
                            MyKinsServiceResponse<Type> httpResult = null;
                            //try {
                            httpResult = gson.fromJson(content, MyKinsServiceResponse.class);
                            /*}catch (Exception e){
                                e.printStackTrace();
                            }*/
                            return httpResult;
                        };
                    }
                })
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(schedulers.networkIO()))
                .baseUrl(baseUrl)
                .build();
    }

    @Singleton
    @Provides
    public RegisterService provideRegisterService(Retrofit retrofit) {
        return retrofit.create(RegisterService.class);
    }

    @Singleton
    @Provides
    public LoginService provideLoginService(Retrofit retrofit) {
        return retrofit.create(LoginService.class);
    }

    @Singleton
    @Provides
    public KinsProfileService provideKinsProfileService(Retrofit retrofit) {
        return retrofit.create(KinsProfileService.class);
    }

}
