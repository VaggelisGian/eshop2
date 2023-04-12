package com.example.eshop2.ui.product;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.eshop2.R;
import com.example.eshop2.model.Product;

import java.text.NumberFormat;

public class ProductHolder extends RecyclerView.ViewHolder {

    public ImageView imageView;
    public TextView nameTextView;
    public TextView descriptionTextView;
    public TextView priceTextView;

    public ProductHolder(View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.product_image_view);
        nameTextView = itemView.findViewById(R.id.product_name_text_view);
        descriptionTextView = itemView.findViewById(R.id.product_description_text_view);
        priceTextView = itemView.findViewById(R.id.product_price_text_view);
    }

    public void bind(Product product) {
        nameTextView.setText(product.getName());
        descriptionTextView.setText(product.getDescription());

        // Format the price to display with 2 decimal places and a currency symbol
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();
        String formattedPrice = currencyFormat.format(product.getPrice());
        nameTextView.setText(formattedPrice);
    }
}
