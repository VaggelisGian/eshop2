package com.example.eshop2.ui.product;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.eshop2.R;
import com.example.eshop2.model.Product;
import com.example.eshop2.viewmodel.ProductViewModel;

public class EditProductActivity extends AppCompatActivity {

    public static final String EXTRA_ID =
            "com.example.eshop.EXTRA_ID";
    public static final String EXTRA_NAME =
            "com.example.eshop.EXTRA_NAME";
    public static final String EXTRA_DESCRIPTION =
            "com.example.eshop.EXTRA_DESCRIPTION";
    public static final String EXTRA_PRICE =
            "com.example.eshop.EXTRA_PRICE";

    private EditText editTextName;
    private EditText editTextDescription;
    private EditText editTextPrice;

    private ProductViewModel productViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_product);

        editTextName = findViewById(R.id.edit_text_name);
        editTextDescription = findViewById(R.id.edit_text_description);
        editTextPrice = findViewById(R.id.edit_text_price);

        Button buttonSave = findViewById(R.id.button_save);
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveProduct();
            }
        });

        productViewModel = new ViewModelProvider(this).get(ProductViewModel.class);

        Intent intent = getIntent();
        if (intent.hasExtra(EXTRA_ID)) {
            setTitle("Edit Product");
            editTextName.setText(intent.getStringExtra(EXTRA_NAME));
            editTextDescription.setText(intent.getStringExtra(EXTRA_DESCRIPTION));
            editTextPrice.setText(String.valueOf(intent.getDoubleExtra(EXTRA_PRICE, 0)));
        } else {
            setTitle("Add Product");
        }
    }

    private void saveProduct() {
        String name = editTextName.getText().toString().trim();
        String description = editTextDescription.getText().toString().trim();
        String priceString = editTextPrice.getText().toString().trim();

        if (TextUtils.isEmpty(name)) {
            editTextName.setError("Please enter a name");
            return;
        }

        if (TextUtils.isEmpty(description)) {
            editTextDescription.setError("Please enter a description");
            return;
        }

        if (TextUtils.isEmpty(priceString)) {
            editTextPrice.setError("Please enter a price");
            return;
        }

        double price = Double.parseDouble(priceString);
        Product product = new Product();
        Intent data = new Intent();
        if (getIntent().hasExtra(EXTRA_ID)) {
            int id = getIntent().getIntExtra(EXTRA_ID, -1);
            if (id == -1) {
                Toast.makeText(this, "Product cannot be updated", Toast.LENGTH_SHORT).show();
                return;
            }

            product.setName(name);
            product.setDescription(description);
            product.setPrice(price);
            product.setId(id);
            productViewModel.updateProduct(product);
            Toast.makeText(this, "Product updated", Toast.LENGTH_SHORT).show();
        } else {

            product.setName(name);
            product.setDescription(description);
            product.setPrice(price);
            productViewModel.insertProduct(product);
            Toast.makeText(this, "Product saved", Toast.LENGTH_SHORT).show();
        }

        setResult(RESULT_OK, data);
        finish();
    }
}
