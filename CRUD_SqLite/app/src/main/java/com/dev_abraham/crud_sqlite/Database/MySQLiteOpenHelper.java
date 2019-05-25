package com.dev_abraham.crud_sqlite.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MySQLiteOpenHelper extends SQLiteOpenHelper {

        private static final String COMMENTS_TABLE_CREATE = "CREATE TABLE students (_id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, phone TEXT,email Text)";
        private static final String DB_NAME = "students.sqlite";
        private static final int DB_VERSION = 1;

        public MySQLiteOpenHelper(Context context) {
            super(context, DB_NAME, null, DB_VERSION);
            //el contexto
            //el nombre de la base de datos
            //un objeto CursorFactory que normalmente no lo necesitaremos (lo pondremos a null)
            //la versión de la base de datos
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(COMMENTS_TABLE_CREATE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }


