package com.example.eshop2.data;

import android.app.Application;
import androidx.lifecycle.LiveData;
import com.example.eshop2.data.local.CartDao;
import com.example.eshop2.data.local.CartDatabase;
import com.example.eshop2.model.CartItem;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CartRepository {
    private CartDao cartDao;
    private LiveData<List<CartItem>> allCartItems;

    public CartRepository(Application application) {
        CartDatabase database = CartDatabase.getInstance(application);
        cartDao = database.cartDao();
        allCartItems = cartDao.getAllItems();
    }
    private static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(4);

    public void insert(CartItem cartItem) {
        CartRepository.databaseWriteExecutor.execute(() -> {
            cartDao.insert(cartItem);
        });
    }

    public void update(CartItem cartItem) {
        CartRepository.databaseWriteExecutor.execute(() -> {
            cartDao.update(cartItem);
        });
    }

    public void delete(CartItem cartItem) {
        CartRepository.databaseWriteExecutor.execute(() -> {
            cartDao.delete(cartItem);
        });
    }

    public void deleteAllItems() {
        CartRepository.databaseWriteExecutor.execute(() -> {
            cartDao.deleteAllItems();
        });
    }

    public LiveData<List<CartItem>> getAllCartItems() {
        return allCartItems;
    }

    public CartItem getItemByProductId(int productId) {
        return cartDao.getItemByProductId(productId);
    }

    public LiveData<Double> getCartTotal() {
        return cartDao.getCartTotal();
    }

}
