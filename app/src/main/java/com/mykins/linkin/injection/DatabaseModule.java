package com.mykins.linkin.injection;

import android.arch.persistence.room.Room;

import com.mykins.linkin.app.MyApp;
import com.mykins.linkin.repostory.database.UserDao;
import com.mykins.linkin.repostory.database.UserDb;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by yjn on 2017/12/9.
 */

@Module
public class DatabaseModule {

    @Provides
    @Singleton
    public UserDb provideUserDb(MyApp app){
        return Room.databaseBuilder(app, UserDb.class,"users.db").build();
    }

    @Provides
    @Singleton
    public UserDao provideUserDao(UserDb db){
        return db.userDao();
    }
}
