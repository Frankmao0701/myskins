package com.mykins.linkin.repostory;

import com.mykins.linkin.component.AppSchedulers;
import com.mykins.linkin.repostory.database.UserDao;
import com.mykins.linkin.repostory.database.UserDb;

import javax.inject.Inject;

/**
 * Created by yjn on 2017/12/9.
 */

public final class UserRepo {
    UserDb db;
    UserDao userDao;
    AppSchedulers schedulers;

    @Inject
    public UserRepo(UserDb db, UserDao userDao, AppSchedulers schedulers) {
        this.db = db;
        this.userDao = userDao;
        this.schedulers = schedulers;
    }

}
