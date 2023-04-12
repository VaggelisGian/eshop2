package com.example.eshop2.data.local;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.eshop2.model.CartItem;

import java.util.List;

@Dao
public interface CartDao {

    @Insert
    void insert(CartItem cartItem);

    @Update
    void update(CartItem cartItem);

    @Delete
    void delete(CartItem cartItem);

    @Query("DELETE FROM cart_items")
    void deleteAllItems();

    @Query("SELECT * FROM cart_items")
    LiveData<List<CartItem>> getAllItems();

    @Query("SELECT * FROM cart_items WHERE productId=:productId")
    CartItem getItemByProductId(int productId);

    @Query("SELECT SUM(quantity * price) FROM cart_items")
    LiveData<Double> getCartTotal();
}
