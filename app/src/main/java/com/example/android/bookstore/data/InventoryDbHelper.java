package com.example.android.bookstore.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.android.bookstore.data.InventoryContract.ProductEntry;

public class InventoryDbHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "inventory.db";

    private static final String TEXT_TYPE = " TEXT";
    private static final String INTEGER_TYPE = " INTEGER";
    private static final String COMMA_SEP = ", ";

    private static final String SQL_CREATE_PRODUCTS_TABLE =
            "CREATE TABLE " + ProductEntry.TABLE_NAME + " (" +
                    ProductEntry._ID + INTEGER_TYPE + " PRIMARY KEY AUTOINCREMENT" + COMMA_SEP +
                    ProductEntry.COLUMN_PRODUCT_NAME + TEXT_TYPE + COMMA_SEP +
                    ProductEntry.COLUMN_CATEGORY + INTEGER_TYPE + COMMA_SEP +
                    ProductEntry.COLUMN_PRICE + INTEGER_TYPE + COMMA_SEP +
                    ProductEntry.COLUMN_QUANTITY + INTEGER_TYPE + COMMA_SEP +
                    ProductEntry.COLUMN_SUPPLIER_NAME + TEXT_TYPE + COMMA_SEP +
                    ProductEntry.COLUMN_SUPPLIER_PHONE_NUMBER + TEXT_TYPE + ");";

    public InventoryDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_PRODUCTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        return;
    }
}
