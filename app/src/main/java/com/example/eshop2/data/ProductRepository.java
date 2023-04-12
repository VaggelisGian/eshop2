package com.example.eshop2.data;
import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.eshop2.data.local.CartDao;
import com.google.firebase.firestore.QueryDocumentSnapshot;


import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.example.eshop2.model.Product;
import com.example.eshop2.data.local.ProductDao;
import com.example.eshop2.data.local.EshopDatabase;

import java.util.ArrayList;
import java.util.List;

public class ProductRepository {

    private static final String TAG = "ProductRepository";

    private ProductDao productDao;
    private FirebaseFirestore firestoreDb;
    private MutableLiveData<List<Product>> allProducts;

    public ProductRepository(Application application) {
        EshopDatabase database = EshopDatabase.getInstance(application);
        productDao = database.productDao();
        allProducts = new MutableLiveData<>();

        firestoreDb = FirebaseFirestore.getInstance();
        firestoreDb.collection("products").addSnapshotListener((value, error) -> {
            if (error != null) {
                Log.w(TAG, "Listen failed.", error);
                return;
            }
            List<Product> products = new ArrayList<>();
            assert value != null;
            for (QueryDocumentSnapshot doc : value) {
                Product product = doc.toObject(Product.class);
                products.add(product);
            }
            allProducts.setValue(products);
        });
    }

    public void insertProduct(Product product) {
        new InsertProductAsyncTask(productDao).execute(product);
        firestoreDb.collection("products").add(product);
    }

    public void updateProduct(Product product) {
        new UpdateProductAsyncTask(productDao).execute(product);
        firestoreDb.collection("products").document(String.valueOf(product.getId())).set(product);
    }

    public void deleteProduct(Product product) {
        new DeleteProductAsyncTask(productDao).execute(product);
        firestoreDb.collection("products").document(String.valueOf(product.getId())).delete();
    }

    public LiveData<List<Product>> getAllProducts() {
        return allProducts;
    }

    private static class InsertProductAsyncTask extends AsyncTask<Product, Void, Void> {
        private ProductDao productDao;

        private InsertProductAsyncTask(ProductDao productDao) {
            super();
            this.productDao = productDao;
        }

    @Override
        protected Void doInBackground(Product... products) {
            productDao.insertProduct(products[0]);
            return null;
        }
    }

    private static class UpdateProductAsyncTask extends AsyncTask<Product, Void, Void> {
        private ProductDao productDao;

        private UpdateProductAsyncTask(ProductDao productDao) {
            super();
            this.productDao = productDao;
        }

        @Override
        protected Void doInBackground(Product... products) {
            productDao.updateProduct(products[0]);
            return null;
        }
    }


    private static class DeleteProductAsyncTask extends AsyncTask<Product, Void, Void> {
        private ProductDao productDao;

        private DeleteProductAsyncTask(ProductDao productDao) {
            super();
            this.productDao = productDao;
        }

        @Override
        protected Void doInBackground(Product... products) {
            productDao.deleteProduct(products[0]);
            return null;
        }
    }
    public static LiveData<Double> getCartTotal(CartDao cartDao) {
        return cartDao.getCartTotal();
    }

}
