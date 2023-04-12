package com.example.eshop2.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.eshop2.data.CartRepository;
import com.example.eshop2.model.CartItem;
import com.example.eshop2.data.local.CartDao;

import java.util.List;

public class CartViewModel extends AndroidViewModel {

    private CartRepository mCartRepository;
    private LiveData<List<CartItem>> mAllCartItems;

    private LiveData<Double> mCartTotal;

    public CartViewModel(@NonNull Application application) {
        super(application);
        mCartRepository = new CartRepository(application);
        mAllCartItems = mCartRepository.getAllCartItems();

    }

    public LiveData<Double> getCartTotal() {
        return mCartTotal;
    }

    public void insert(CartItem cartItem) {
        mCartRepository.insert(cartItem);
    }

    public void update(CartItem cartItem) {
        mCartRepository.update(cartItem);
    }

    public void delete(CartItem cartItem) {
        mCartRepository.delete(cartItem);
    }

    public void deleteAllCartItems() {
        mCartRepository.deleteAllItems();
    }

    public LiveData<List<CartItem>> getAllCartItems() {
        return mAllCartItems;
    }



}
