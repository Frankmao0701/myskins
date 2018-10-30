package com.mykins.linkin.injection;

import com.mykins.linkin.app.MyApp;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;

/**
 * Created by jerry on 2017/12/8.
 */
@Singleton
@Component(modules = {
        AndroidInjectionModule.class,
        MyKinsServiceModule.class,
        AppModule.class,
        DatabaseModule.class
})
public interface AppComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        Builder app(MyApp app);

        AppComponent build();
    }

    void inject(MyApp target);
}
