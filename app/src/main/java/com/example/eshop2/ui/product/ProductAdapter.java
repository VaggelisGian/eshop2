package com.example.eshop2.ui.product;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;
import com.example.eshop2.model.Product;

import com.example.eshop2.data.local.ProductDao;

import com.example.eshop2.R;
import com.example.eshop2.model.Product;

import java.text.NumberFormat;
import java.util.Objects;

public class ProductAdapter extends ListAdapter<Product, ProductHolder> {

    public ProductAdapter() {
        super(DIFF_CALLBACK);
    }

    private static final DiffUtil.ItemCallback<Product> DIFF_CALLBACK = new DiffUtil.ItemCallback<Product>() {
        @Override
        public boolean areItemsTheSame(@NonNull Product oldItem, @NonNull Product newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Product oldItem, @NonNull Product newItem) {
            return Objects.equals(oldItem.getName(), newItem.getName()) &&
                    Objects.equals(oldItem.getDescription(), newItem.getDescription()) &&
                    oldItem.getPrice() == newItem.getPrice();
        }
    };

    @NonNull
    @Override
    public ProductHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_product, parent, false);
        return new ProductHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductHolder holder, int position) {
        Product currentProduct = getItem(position);
        holder.nameTextView.setText(currentProduct.getName());
        holder.descriptionTextView.setText(currentProduct.getDescription());
        holder.priceTextView.setText(String.valueOf(currentProduct.getPrice()));
        holder.imageView.setImageResource(currentProduct.getImageResId());

        // Load the image using the product ID
        String imageName = "ic_" + currentProduct.getId();
        int imageId = holder.itemView.getContext().getResources()
                .getIdentifier(imageName, "drawable", holder.itemView.getContext().getPackageName());
        holder.imageView.setImageResource(imageId);
    }

    public Product getProductAt(int position) {
        return getItem(position);
    }



    public interface OnItemClickListener {
        void onItemClick(Product product);
    }


}
