package com.example.kamelrechner;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class CamelDBDataSource {
    private static final String LOG_TAG = CamelDBDataSource.class.getSimpleName();

    private SQLiteDatabase database;
    private CamelDBHelper dbHelper;


    public CamelDBDataSource(Context context) {
        Log.d(LOG_TAG, "Unsere DataSource erzeugt jetzt den dbHelper.");
        dbHelper = new CamelDBHelper(context);
    }
}
