package com.example.eshop2.ui.product;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.eshop2.R;

public class AddProductActivity extends AppCompatActivity {

    public static final String EXTRA_NAME =
            "com.example.eshop.EXTRA_NAME";
    public static final String EXTRA_DESCRIPTION =
            "com.example.eshop.EXTRA_DESCRIPTION";
    public static final String EXTRA_PRICE =
            "com.example.eshop.EXTRA_PRICE";

    private EditText mNameEditText;
    private EditText mDescriptionEditText;
    private EditText mPriceEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_product);

        mNameEditText = findViewById(R.id.edit_text_name);
        mDescriptionEditText = findViewById(R.id.edit_text_description);
        mPriceEditText = findViewById(R.id.edit_text_price);

        final Button buttonSave = findViewById(R.id.button_save);
        buttonSave.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                saveProduct();
            }
        });
    }

    private void saveProduct() {
        String name = mNameEditText.getText().toString();
        String description = mDescriptionEditText.getText().toString();
        String priceString = mPriceEditText.getText().toString();

        if (TextUtils.isEmpty(name)) {
            mNameEditText.setError("Name is required");
            return;
        }

        if (TextUtils.isEmpty(description)) {
            mDescriptionEditText.setError("Description is required");
            return;
        }

        if (TextUtils.isEmpty(priceString)) {
            mPriceEditText.setError("Price is required");
            return;
        }

        double price = Double.parseDouble(priceString);

        Intent data = new Intent();
        data.putExtra(EXTRA_NAME, name);
        data.putExtra(EXTRA_DESCRIPTION, description);
        data.putExtra(EXTRA_PRICE, price);

        setResult(RESULT_OK, data);
        finish();
    }
}
