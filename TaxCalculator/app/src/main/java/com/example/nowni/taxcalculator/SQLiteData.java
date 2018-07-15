package com.example.nowni.taxcalculator;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class SQLiteData extends SQLiteOpenHelper
{

    private String db_path = "";
    private static String db_name = "GST.db";
    private SQLiteDatabase myDatabase;
    private Context context;

    public  SQLiteData(Context context){
        super(context, db_name, null, 1);
        this.context = context;
        this.db_path = context.getDatabasePath(db_name).toString();
        Log.e("Path of Database",db_path);
    }



    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        if(newVersion > oldVersion){
            try{
                copyData();
            }catch (IOException e){
                e.printStackTrace();
            }
        }

    }
    public Cursor fetchAll(String table, String [] columns, String selection, String [] selectionArgs, String groupBy, String having, String orderBy){
        return myDatabase.query("gst",null,null, null, null, null, null);
    }


    public void createDatabase() throws IOException{
        boolean dbExists = checkDataBase();
        if(dbExists){

        }else{
            this.getReadableDatabase();

            try{
                copyData();
            }catch (IOException e){
                throw new Error("Error Copying Database");
            }
        }
    }

    private boolean checkDataBase(){
        SQLiteDatabase db = null;
        try{
            String myPath = db_path;
            Log.e("Databse path :",db_path);
            db = SQLiteDatabase.openDatabase(myPath,null, SQLiteDatabase.OPEN_READONLY);
        }catch(SQLException e){
            Log.e("Error at Check databse",e.toString());
        }

        return db != null? true : false;

    }


    private void  copyData() throws IOException{
        InputStream myInput = context.getAssets().open(db_name);
        Log.e("File opened","true");
        String outFileName = db_path + db_name;
        Log.e("File Name is ",outFileName);
        OutputStream outFile = new FileOutputStream(outFileName);

        byte[] buffer = new byte[10];
        int length;

        while((length = myInput.read(buffer)) > 0){
            outFile.write(buffer, 0, length);
        }
        outFile.flush();
        outFile.close();
        myInput.close();

    }


    public void openDatabase() throws SQLException{
        String myPath = db_path + db_name;
        File outFile = new File(Environment.getDataDirectory(), myPath);
        outFile.setWritable(true);


        Log.e("openDatabase(),Db path",myPath);
        myDatabase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);

    }

    public synchronized void close(){
        if(myDatabase != null)
            myDatabase.close();
        super.close();
    }


}

