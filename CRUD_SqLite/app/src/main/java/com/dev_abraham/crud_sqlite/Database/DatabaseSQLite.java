package com.dev_abraham.crud_sqlite.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.dev_abraham.crud_sqlite.Moderls.ModelStudent;

public class DatabaseSQLite {

    public static MySQLiteOpenHelper dbHelper;
    public static SQLiteDatabase db;
    public static Context context;

    public static SQLiteDatabase getDataBase(Context context){

         if(db==null){
             dbHelper = new MySQLiteOpenHelper(context);
             db = dbHelper.getWritableDatabase();
             Log.i("TAG","BD creada");
         }
         return db;
     }

     public static  Cursor getAllStudents (Context context){
         return   DatabaseSQLite.getDataBase(context).query(
                 "students",  // Nombre de la tabla
                 null,  // Lista de Columnas a consultar
                 null,  // Columnas para la cláusula WHERE
                 null,  // Valores a comparar con las columnas del WHERE
                 null,  // Agrupar con GROUP BY
                 null,  // Condición HAVING para GROUP BY
                 null  // Cláusula ORDER BY
         );
     }

     public static void createStudent (ModelStudent student){

         ContentValues cv = new ContentValues();
         cv.put("name", student.getName());
         cv.put("phone",student.getPhone());
         cv.put("email", student.getEmail());
         db.insert("students", null, cv);

         Log.i("TAG","Se inserto estudiante");
     }

     public static  int getIdStudent (ModelStudent student){

         String[] columns = new String []{ "_id"};
         String  where = "name=? and email=? and phone=?";
         String[] wherevalues = new String []{ student.getName(),student.getEmail(),student.getPhone()};
         Cursor c =  DatabaseSQLite.getDataBase(context).query(
                 "students",  // Nombre de la tabla
                 columns,  // Lista de Columnas a consultar
                 where,  // Columnas para la cláusula WHERE
                 wherevalues,  // Valores a comparar con las columnas del WHERE
                 null,  // Agrupar con GROUP BY
                 null,  // Condición HAVING para GROUP BY
                 null  // Cláusula ORDER BY
         );
         while(c.moveToNext()){
             return c.getInt(c.getColumnIndex("_id"));
         }
         return 0;
     }

     public static boolean updateStudent (ModelStudent oldstudent,ModelStudent newStudent){
        int id = getIdStudent(oldstudent);
        if(id==0){
            return false;
        }else{
            ContentValues  cv = new ContentValues();
            cv.put("email",newStudent.getEmail());
            cv.put("name",newStudent.getName());
            cv.put("phone",newStudent.getPhone());
            String[] args = new String []{ id+""};
            db.update("students", cv, "_id=?", args);
            Log.i("TAG","Se actualizo estudiante"+id);
            return true;
        }

     }

     public static boolean deleteStudent (ModelStudent student){
         int id = getIdStudent(student);
         if(id==0){
             return false;
         }else{
             String[] args = new String []{ id+""};
             db.delete("students", "_id=?", args);
             Log.i("TAG","Se elimino estudiante"+id);
             return true;
         }

     }


        //    DatabaseSQLite.getDataBase(getContext()).delete("students", "name=?", args);




}
