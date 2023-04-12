package com.example.eshop2.data.local;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.eshop2.model.Product;
import com.example.eshop2.data.local.ProductDao;

@Database(entities = {Product.class}, version = 1, exportSchema = false)
public abstract class EshopDatabase extends RoomDatabase {

    private static EshopDatabase instance;

    public abstract ProductDao productDao();

    public static synchronized EshopDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    EshopDatabase.class, "eshop_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}
