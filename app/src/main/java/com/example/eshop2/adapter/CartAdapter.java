package com.example.eshop2.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.eshop2.model.CartItem;

import com.example.eshop2.R;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

    private List<CartItem> cartItems;

    public CartAdapter (){}

    public CartAdapter(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cart_item, parent, false);
        return new CartViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        CartItem currentCartItem = cartItems.get(position);
        holder.productNameTextView.setText(currentCartItem.getName());
        holder.productPriceTextView.setText(String.valueOf(currentCartItem.getPrice()));
        holder.quantityTextView.setText(String.valueOf(currentCartItem.getQuantity()));
        holder.productImageView.setImageResource(currentCartItem.getImageResId());
    }

    @Override
    public int getItemCount() {
        return cartItems.size();
    }

    public class CartViewHolder extends RecyclerView.ViewHolder {
        private ImageView productImageView;
        private TextView productNameTextView;
        private TextView productPriceTextView;
        private TextView quantityTextView;

        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            productImageView = itemView.findViewById(R.id.product_image_view);
            productNameTextView = itemView.findViewById(R.id.product_name_text_view);
            productPriceTextView = itemView.findViewById(R.id.product_price_text_view);
            quantityTextView = itemView.findViewById(R.id.quantity_text_view);
        }
    }
    public void setCartItems(List<CartItem> cartItems) {
        this.cartItems = cartItems;
        notifyDataSetChanged();
    }
}

