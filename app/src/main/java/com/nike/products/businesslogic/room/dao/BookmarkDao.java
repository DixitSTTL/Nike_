package com.nike.products.businesslogic.room.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.nike.products.businesslogic.room.entity.ModelCart;
import com.nike.products.businesslogic.room.entity.ModelHome;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;

@Dao
public interface BookmarkDao {
    @Query("SELECT * FROM bookmarkTable")
    Flowable<List<ModelHome>> getAllBookmark();

    @Query("SELECT * FROM bookmarkTable WHERE image=:img")
    Single<ModelHome> getById(int img);

    @Query("DELETE FROM bookmarkTable WHERE image=:img")
    Completable deleteById(int img);


    @Insert
    Completable insertTask(ModelHome bookmarkData);




    @Delete
    Completable deleteTask(ModelHome bookmarkData);


}
