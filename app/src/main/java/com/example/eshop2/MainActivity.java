package com.example.eshop2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.eshop2.ui.product.AddEditProductActivity;
import com.example.eshop2.ui.product.ProductAdapter;
import com.example.eshop2.ui.product.EditProductActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;


import com.example.eshop2.model.Product;
import com.example.eshop2.viewmodel.ProductViewModel;
import com.google.android.material.navigation.NavigationView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ProductViewModel productViewModel;
    private DrawerLayout drawerLayout;
    private Toolbar toolbar;

    private FloatingActionButton buttonAddProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        }






        java.text.DateFormat dateFormat = android.text.format.DateFormat.getDateFormat(getApplicationContext());

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        final ProductAdapter adapter = new ProductAdapter();
        recyclerView.setAdapter(adapter);

        productViewModel = new ViewModelProvider(this).get(ProductViewModel.class);
        productViewModel.getAllProducts().observe(this, new Observer<List<Product>>() {
            @Override
            public void onChanged(List<Product> products) {
                adapter.submitList(products);
            }
        });

        buttonAddProduct = (FloatingActionButton) findViewById(R.id.button_add_product);
        buttonAddProduct.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.d("MainActivity", "onClick: add_product_option");
                EditProductActivity();
                Intent intent = new Intent(MainActivity.this, com.example.eshop2.ui.product.EditProductActivity.class);
                startActivity(intent);
            }
        });
    }

    public void EditProductActivity()
    {
        Intent intent = new Intent(this , com.example.eshop2.ui.product.EditProductActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.drawer_menu, menu);
        return true;
    }



    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
                NavigationView navigationView = findViewById(R.id.navigation_view);
                drawerLayout.openDrawer(navigationView);
                return true;
            case R.id.profile:
                // Handle Profile click
                return true;
            case R.id.home:
                Intent intent = new Intent(MainActivity.this, MainActivity.class);
                startActivity(intent);
                return true;
            case R.id.suppliers:
                // Handle Suppliers click
                return true;
            case R.id.add_product_option:
                Log.d("MainActivity", "onClick: add_product_option");
                Intent intent1 = new Intent(MainActivity.this, com.example.eshop2.ui.product.EditProductActivity.class);
                startActivity(intent1);
                return true;
            case R.id.drop_product_option:
                // Handle drop product option
                return true;
            case R.id.edit_product_option:
                // Handle edit product option
                return true;
            // Handle other menu items if needed
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
