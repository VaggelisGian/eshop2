package com.example.eshop2.ui.cart;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.eshop2.R;
import com.example.eshop2.adapter.CartAdapter;
import com.example.eshop2.viewmodel.CartViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class CartActivity extends AppCompatActivity {

    private CartViewModel mCartViewModel;

    @SuppressLint("NotifyDataSetChanged")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        RecyclerView recyclerView = findViewById(R.id.recycler_view_cart);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        final CartAdapter adapter = new CartAdapter();
        recyclerView.setAdapter(adapter);

        mCartViewModel = new ViewModelProvider(this).get(CartViewModel.class);
        mCartViewModel.getAllCartItems().observe(this, adapter::setCartItems);

        FloatingActionButton buttonCheckout = findViewById(R.id.button_checkout);
        buttonCheckout.setOnClickListener(v -> {
            mCartViewModel.deleteAllCartItems();
            adapter.notifyDataSetChanged();
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_cart, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_delete_all_cart_items) {
            mCartViewModel.deleteAllCartItems();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
