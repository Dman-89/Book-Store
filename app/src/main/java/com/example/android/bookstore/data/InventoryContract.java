package com.example.android.bookstore.data;

import android.provider.BaseColumns;

public final class InventoryContract {

    private InventoryContract() {};

    public static final class ProductEntry implements BaseColumns {

        public static final String TABLE_NAME = "products";

        public static final String _ID = BaseColumns._ID; // "_id"
        public static final String COLUMN_PRODUCT_NAME = "product_name";
        public static final String COLUMN_CATEGORY = "category";
        public static final String COLUMN_PRICE = "price";
        public static final String COLUMN_QUANTITY = "quantity";
        public static final String COLUMN_SUPPLIER_NAME = "supplier_name";
        public static final String COLUMN_SUPPLIER_PHONE_NUMBER = "supplier_phone_number";

        public static final int CATEGORY_BOOK = 0; // book
        public static final int CATEGORY_JOURNAL = 1;
        public static final int CATEGORY_NEWSPAPER = 2;
        public static final int CATEGORY_CARDS = 3; // postcards, gift cards, maps
        public static final int CATEGORY_WRITING_UTENSILS = 4; //pens, pencils, erasers, notebooks

    }
}
