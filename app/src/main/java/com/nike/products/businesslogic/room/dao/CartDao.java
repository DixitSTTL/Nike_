package com.nike.products.businesslogic.room.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.nike.products.businesslogic.room.entity.ModelCart;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;

@Dao
public interface CartDao {
    @Query("SELECT * FROM cartTable")
    Flowable<List<ModelCart>> getAllCartProducts();

    @Query("SELECT * FROM cartTable WHERE image=:img")
    Single<ModelCart> getById(int img);

    @Query("DELETE FROM cartTable WHERE image=:img")
    Completable deleteById(int img);


    @Insert
    Completable insertCart(ModelCart modelCart);


    @Delete
    Completable deleteCart(ModelCart modelCart);


    @Update
    Completable updateCart(ModelCart modelCart);


}
