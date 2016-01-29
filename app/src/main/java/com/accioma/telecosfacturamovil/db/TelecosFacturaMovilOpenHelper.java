package com.accioma.telecosfacturamovil.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by marcelomora on 1/2/16.
 */
public class TelecosFacturaMovilOpenHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "tfmdb.db";
    private static final int DB_CUR_VER = 1;

    public TelecosFacturaMovilOpenHelper(Context context) {
        super(context, DB_NAME, null, DB_CUR_VER);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
