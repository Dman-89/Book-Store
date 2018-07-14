package com.example.android.bookstore;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.android.bookstore.data.InventoryDbHelper;
import com.example.android.bookstore.data.InventoryContract.ProductEntry;
import com.example.android.bookstore.data.MockDataGenerator;

import java.util.Arrays;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    InventoryDbHelper mDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertDummyData();
                displayDbInfo();
            }
        });

        displayDbInfo();
    }

    private void displayDbInfo() {
        mDbHelper = new InventoryDbHelper(this);
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        String[] projection = {ProductEntry._ID, ProductEntry.COLUMN_PRODUCT_NAME,
                ProductEntry.COLUMN_CATEGORY, ProductEntry.COLUMN_PRICE,
                ProductEntry.COLUMN_QUANTITY, ProductEntry.COLUMN_SUPPLIER_NAME,
                ProductEntry.COLUMN_SUPPLIER_PHONE_NUMBER};
        String selection = null;
        String[] selectionArgs = null;
        String groupBy = null;
        String having = null;
        String orderBy = null;
        Cursor cursor =  db.query(ProductEntry.TABLE_NAME, projection, selection, selectionArgs,
                groupBy, having, orderBy);

        TextView displayView = findViewById(R.id.text_view_test);

        try {
            displayView.setText("Number of rows in products table: " + cursor.getCount()  + "\n\n");
            displayView.append(ProductEntry._ID + " - " +
                    ProductEntry.COLUMN_PRODUCT_NAME + " - " +
                    ProductEntry.COLUMN_CATEGORY + " - " +
                    ProductEntry.COLUMN_PRICE + " - " +
                    ProductEntry.COLUMN_QUANTITY + " - " +
                    ProductEntry.COLUMN_SUPPLIER_NAME + " - " +
                    ProductEntry.COLUMN_SUPPLIER_PHONE_NUMBER + "\n");

            int idColumnIndex = cursor.getColumnIndex(ProductEntry._ID);
            int nameColumnIndex = cursor.getColumnIndex(ProductEntry.COLUMN_PRODUCT_NAME);
            int categoryColumnIndex = cursor.getColumnIndex(ProductEntry.COLUMN_CATEGORY);
            int priceColumnIndex = cursor.getColumnIndex(ProductEntry.COLUMN_PRICE);
            int quantityColumnIndex = cursor.getColumnIndex(ProductEntry.COLUMN_QUANTITY);
            int supplierColumnIndex = cursor.getColumnIndex(ProductEntry.COLUMN_SUPPLIER_NAME);
            int supplierPhoneNumberColumnIndex = cursor.getColumnIndex(ProductEntry.COLUMN_SUPPLIER_PHONE_NUMBER);

            while (cursor.moveToNext()) {
                int currentId = cursor.getInt(idColumnIndex);
                String currentName = cursor.getString(nameColumnIndex);
                int currentCategory = cursor.getInt(categoryColumnIndex);
                int currentPrice = cursor.getInt(priceColumnIndex); //or getString() instead of getInt()
                int currentQuantity = cursor.getInt(quantityColumnIndex);
                String currentSupplier = cursor.getString(supplierColumnIndex);
                String currentSupplierPhoneNumber = cursor.getString(supplierPhoneNumberColumnIndex);

                displayView.append("\n" + currentId + " - " + currentName + " - " +
                        currentCategory + " - " + currentPrice + " - " + currentQuantity + " - " +
                        currentSupplier + " - " + currentSupplierPhoneNumber);
            }

        } finally {
            cursor.close();
        }
    }

    private void insertDummyData() {
        ContentValues values = new ContentValues();
        String product = MockDataGenerator.getRandomProductName();

        int category;
        switch (product.split(" ")[0]) {
            case "book":
                category = 0;
                break;
            case "journal":
                category = 1;
                break;
            case "newspaper":
                category = 2;
                break;
            default:
                String[] cards = {"card", "gift card", "map", "postcard"};
                if(Arrays.asList(cards).contains(product.split(" ")[0])) {
                    category = 3;
                } else {
                    category = 4;
                }
        }

        values.put(ProductEntry.COLUMN_PRODUCT_NAME, product);
        values.put(ProductEntry.COLUMN_CATEGORY, category);
        values.put(ProductEntry.COLUMN_PRICE, new Random().nextInt(1000));
        values.put(ProductEntry.COLUMN_QUANTITY, new Random().nextInt(100));
        values.put(ProductEntry.COLUMN_SUPPLIER_NAME, MockDataGenerator.getRandomFirstName() +
                " " + MockDataGenerator.getRandomLastName());
        values.put(ProductEntry.COLUMN_SUPPLIER_PHONE_NUMBER, MockDataGenerator.getRandomMobile());

        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        long newRowId = db.insert(ProductEntry.TABLE_NAME, null, values);
        Log.i("TEST ", "New Row ID is " + newRowId);

        displayDbInfo();
    }
}
