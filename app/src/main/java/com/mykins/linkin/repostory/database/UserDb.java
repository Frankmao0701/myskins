package com.mykins.linkin.repostory.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.mykins.linkin.bean.User;

/**
 * Created by yjn on 2017/12/8.
 */
@Database(entities = {User.class}, version = 1, exportSchema = false)
public abstract class UserDb extends RoomDatabase {

    public abstract UserDao userDao();

}
