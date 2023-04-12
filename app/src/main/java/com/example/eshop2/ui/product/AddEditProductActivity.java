package com.example.eshop2.ui.product;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.example.eshop2.R;
import com.example.eshop2.model.Product;
import com.example.eshop2.viewmodel.ProductViewModel;

import java.util.Objects;

public class AddEditProductActivity extends AppCompatActivity {

    public static final String EXTRA_PRODUCT = "com.example.eshop2.EXTRA_PRODUCT";
    private EditText editTextName;
    private EditText editTextDescription;
    private EditText editTextPrice;
    private EditText editTextQuantity;
    private ProductViewModel productViewModel;
    private boolean editMode = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_product);

        androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        editTextName = findViewById(R.id.edit_text_name);
        editTextDescription = findViewById(R.id.edit_text_description);
        editTextPrice = findViewById(R.id.edit_text_price);
        editTextQuantity = findViewById(R.id.edit_text_quantity);

        productViewModel = new ViewModelProvider(this).get(ProductViewModel.class);

        Intent intent = getIntent();
        if (intent.hasExtra(EXTRA_PRODUCT)) {
            setTitle(R.string.edit_product_title);
            editMode = true;

            Product product = (Product) intent.getSerializableExtra(EXTRA_PRODUCT);
            editTextName.setText(product.getName());
            editTextDescription.setText(product.getDescription());
            editTextPrice.setText(String.valueOf(product.getPrice()));
            editTextQuantity.setText(String.valueOf(product.getQuantity()));
        } else {
            setTitle(R.string.add_product_title);
        }
    }

    private void saveProduct() {
        String name = editTextName.getText().toString().trim();
        String description = editTextDescription.getText().toString().trim();
        String priceString = editTextPrice.getText().toString().trim();
        String quantityString = editTextQuantity.getText().toString().trim();

        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(description) || TextUtils.isEmpty(priceString)
                || TextUtils.isEmpty(quantityString)) {
            Toast.makeText(this, R.string.empty_fields_message, Toast.LENGTH_SHORT).show();
            return;
        }

        double price = Double.parseDouble(priceString);
        int quantity = Integer.parseInt(quantityString);
        Product product = new Product();

        product.setName(name);
        product.setDescription(description);
        product.setPrice(price);
        product.setQuantity(quantity);

        if (editMode) {
            Product oldProduct = (Product) getIntent().getSerializableExtra(EXTRA_PRODUCT);
            product.setId(oldProduct.getId());
            productViewModel.updateProduct(product);
        } else {
            productViewModel.insertProduct(product);
        }

        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.add_edit_product_menu, menu);
        return true;
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            case R.id.action_save_product:
                saveProduct();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
