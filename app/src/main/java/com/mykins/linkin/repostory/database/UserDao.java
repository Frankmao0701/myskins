package com.mykins.linkin.repostory.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.mykins.linkin.bean.User;

import io.reactivex.Flowable;

/**
 * Created by yjn on 2017/12/8.
 */

@Dao
public interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(User user);

    @Query("SELECT * FROM users WHERE name = :phone")
    Flowable<User> findByPhone(String phone);
}
