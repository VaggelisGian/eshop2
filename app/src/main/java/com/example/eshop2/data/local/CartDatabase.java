package com.example.eshop2.data.local;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import com.example.eshop2.model.CartItem;

@Database(entities = {CartItem.class}, version = 1, exportSchema = false)
public abstract class CartDatabase extends RoomDatabase {
    private static final String DB_NAME = "cart_database";
    private static volatile CartDatabase instance;

    public static synchronized CartDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                            CartDatabase.class, DB_NAME)
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }

    public abstract CartDao cartDao();

    // Define getDatabase() method to return an instance of CartDatabase
    public static CartDatabase getDatabase(Context context) {
        if (instance == null) {
            synchronized (CartDatabase.class) {
                if (instance == null) {
                    instance = Room.databaseBuilder(context.getApplicationContext(),
                                    CartDatabase.class, DB_NAME)
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return instance;
    }

}
