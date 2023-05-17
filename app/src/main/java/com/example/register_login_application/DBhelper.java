package com.example.register_login_application;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

/*public class DBhelper extends SQLiteOpenHelper {
    public static final String DBNAME = "onlineauction.db";

    public DBhelper(Context context) {
        super(context, "onlineauction.db",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        MyDB.execSQL("create Table users(username TEXT primary key,password TEXT,name TEXT, email TEXT,phno TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {
        MyDB.execSQL("drop Table if exists users");
    }

    public boolean insertData(String username,String password,String name,String email,String phno){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username",username);
        contentValues.put("password",password);
        contentValues.put("name",name);
        contentValues.put("email",email);
        contentValues.put("phno",phno);
        long result = MyDB.insert("users",null,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }
    public Boolean checkUsername(String username){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from users where username=?",new String[]{username});
        if(cursor.getCount()>0)
        {
            return true;
        }
        else
            return false;
    }
    public boolean checkusernamepassword(String username,String password)
    {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from users where username = ? and password = ?", new String[]{username,password});
        if(cursor.getCount()>0)
        {
            return true;
        }
        else
            return false;
    }
}*/
public class DBhelper extends SQLiteOpenHelper{

    Context mcontext;
    String dbName;
    String dbpath;
    public DBhelper(Context context, String name,int version) {
        super(context, name, null, version);
        this.dbName = name;
        this.mcontext=context;
        this.dbpath = "/data/data/" + context.getPackageName() + "/" + "databases/";
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    public void checkDb(){
        SQLiteDatabase checkDB = null;
        try {
            String myPath = dbpath + dbName;
            checkDB = SQLiteDatabase.openDatabase(myPath, null, 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (checkDB != null) {
            Toast.makeText(mcontext,"Database already exists",Toast.LENGTH_SHORT ).show();
        }else
        {
            CopyDatabase();
        }
    }
    public void CopyDatabase(){
        this.getReadableDatabase();
        try{
            InputStream ios = mcontext.getAssets().open(dbName);
            OutputStream os = new FileOutputStream(dbpath+dbName);
            byte[] buffer = new byte[1024];
            int len;
            while((len = ios.read(buffer))>0)
                os.write(buffer, 0, len);
            os.flush();
            ios.close();
            os.close();

        }catch(Exception e)
        {
            e.printStackTrace();
        }
        Log.d("CopyDb","Database copied");

    }
    public void OpenDatabase(){
        String filePath=dbpath + dbName;
        SQLiteDatabase.openDatabase(filePath,null,0);
    }
    public boolean checkusernamepassword(String username,String password)
    {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from users where username = ? and password = ?", new String[]{username,password});
        if(cursor.getCount()>0)
        {
            return true;
        }
        else
            return false;
    }
    public String[] buypagelist()
    {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        String x="1";

        Cursor cursor = MyDB.rawQuery("Select name from product where sold!=?",new String[]{x});

        /*StringBuffer buffer = new StringBuffer();
        while(cursor.moveToNext())
        {
            buffer.append(cursor.getString(0));
        }
        Toast toast2 = Toast.makeText(mcontext.getApplicationContext(), buffer.toString(), Toast.LENGTH_SHORT);
        toast2.show();
        return buffer;*/

        String names[]=new String[cursor.getCount()];
        int i=0;
        while(cursor.moveToNext())
        {
            if(cursor.getString(0).equals("notneeded"))
                continue;
            names[i]=cursor.getString(0);
            i++;
        }
        return names;
    }
    public Boolean checkUsername(String username){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from users where username=?",new String[]{username});
        if(cursor.getCount()>0)
        {
            return true;
        }
        else
            return false;
    }
    public String getsellerusername(String name)
    {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select seller_username from product where name=?",new String[]{name});
        String sellerusername="";
        while(cursor.moveToNext())
        {
            sellerusername=cursor.getString(0);
        }
        return sellerusername;
    }
    public String productid(String name)
    {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select product_id from product where name=?",new String[]{name});
        String sellerusername="";
        while(cursor.moveToNext())
        {
            sellerusername=cursor.getString(0);
        }
        return sellerusername;
    }
    public String price(String name)
    {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select price from product where name=?",new String[]{name});
        String sellerusername="";
        while(cursor.moveToNext())
        {
            sellerusername=cursor.getString(0);
        }
        return sellerusername;
    }
    public String buynowprice(String name)
    {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select buynow_price from product where name=?",new String[]{name});
        String sellerusername="";
        while(cursor.moveToNext())
        {
            sellerusername=cursor.getString(0);
        }
        return sellerusername;
    }
    public String currbid(String name)
    {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select bids from set_bids where product_id=?",new String[]{name});
        String sellerusername="";
        while(cursor.moveToNext())
        {
            sellerusername=cursor.getString(0);
        }
        return sellerusername;
    }
    public String bidder(String name)
    {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select bidder_id from set_bids where product_id=?",new String[]{name});
        String sellerusername="";
        while(cursor.moveToNext())
        {
            sellerusername=cursor.getString(0);
        }
        return sellerusername;
    }



    public boolean insertData(String username,String password,String name,String email,String phno){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username",username);
        contentValues.put("password",password);
        contentValues.put("name",name);
        contentValues.put("email",email);
        contentValues.put("phone",phno);
        long result = MyDB.insert("users",null,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }
    public boolean insertimage(String product_id,String seller,String name,byte[] image,String price,String buynow)
    {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("product_id",product_id);
        contentValues.put("seller_username",seller);
        contentValues.put("sold","0");
        contentValues.put("name",name);
        contentValues.put("image",image);
        contentValues.put("price",price);
        contentValues.put("buynow_price",buynow);
        long result = MyDB.insert("product",null,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }
    public boolean insertintosetbids(String product_id)
    {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("product_id",product_id);
        contentValues.put("bids","0");
        contentValues.put("bidder_id","null");
        contentValues.put("winner_id","null");
        long result = MyDB.insert("set_bids",null,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }
    public Bitmap getImage(String id)
    {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Bitmap bt=null;
        Cursor cursor = MyDB.rawQuery("Select image from product where product_id=?",new String[]{id});
        if(cursor.moveToNext())
        {
            byte[] imag = cursor.getBlob(0);
            bt = BitmapFactory.decodeByteArray(imag,0,imag.length);

        }
        return bt;
    }
    public Boolean updateuserdata(String id)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("product_id",id);
        contentValues.put("sold","1");
        /*contentValues.put("address", address);
        contentValues.put("course", course);*/
        Cursor cursor = DB.rawQuery("Select * from product where product_id = ?", new String[]{id});
        if (cursor.getCount() > 0) {
            long result = DB.update("product", contentValues, "product_id=?", new String[]{id});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }
    public Boolean updatesetbids(String id,String curr,String bidder)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("product_id",id);
        contentValues.put("bids",curr);
        contentValues.put("bidder_id",bidder);
        /*contentValues.put("address", address);
        contentValues.put("course", course);*/
        Cursor cursor = DB.rawQuery("Select * from set_bids where product_id = ?", new String[]{id});
        if (cursor.getCount() > 0) {
            long result = DB.update("set_bids", contentValues, "product_id=?", new String[]{id});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }
    public String name(String name)
    {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select name from users where username=?",new String[]{name});
        String sellerusername="";
        while(cursor.moveToNext())
        {
            sellerusername=cursor.getString(0);
        }
        return sellerusername;
    }
    public String email(String name)
    {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select email from users where username=?",new String[]{name});
        String sellerusername="";
        while(cursor.moveToNext())
        {
            sellerusername=cursor.getString(0);
        }
        return sellerusername;
    }
    public String pass(String name)
    {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select password from users where username=?",new String[]{name});
        String sellerusername="";
        while(cursor.moveToNext())
        {
            sellerusername=cursor.getString(0);
        }
        return sellerusername;
    }
    public String phone(String name)
    {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select phone from users where username=?",new String[]{name});
        String sellerusername="";
        while(cursor.moveToNext())
        {
            sellerusername=cursor.getString(0);
        }
        return sellerusername;
    }
    public boolean updatedata(String name,String username,String pass,String email,String phone)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username",username);
        contentValues.put("name",name);
        contentValues.put("password",pass);
        contentValues.put("email",email);
        contentValues.put("phone",phone);
        /*contentValues.put("address", address);
        contentValues.put("course", course);*/
        Cursor cursor = DB.rawQuery("Select * from users where username = ?", new String[]{username});
        if (cursor.getCount() > 0) {
            long result = DB.update("users", contentValues, "username=?", new String[]{username});
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }
    public String[] solditems(String seller)
    {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        String x="1";

        Cursor cursor = MyDB.rawQuery("Select name from product where sold=? and seller_username=?",new String[]{x,seller});

        /*StringBuffer buffer = new StringBuffer();
        while(cursor.moveToNext())
        {
            buffer.append(cursor.getString(0));
        }
        Toast toast2 = Toast.makeText(mcontext.getApplicationContext(), buffer.toString(), Toast.LENGTH_SHORT);
        toast2.show();
        return buffer;*/

        String names[]=new String[cursor.getCount()];
        int i=0;
        while(cursor.moveToNext())
        {
            if(cursor.getString(0).equals("notneeded"))
                continue;
            names[i]=cursor.getString(0);
            i++;
        }
        return names;
    }
    public String[] unsolditems(String seller)
    {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        String x="1";

        Cursor cursor = MyDB.rawQuery("Select name from product where sold!=? and seller_username=?",new String[]{x,seller});

        /*StringBuffer buffer = new StringBuffer();
        while(cursor.moveToNext())
        {
            buffer.append(cursor.getString(0));
        }
        Toast toast2 = Toast.makeText(mcontext.getApplicationContext(), buffer.toString(), Toast.LENGTH_SHORT);
        toast2.show();
        return buffer;*/

        String names[]=new String[cursor.getCount()];
        int i=0;
        while(cursor.moveToNext())
        {
            if(cursor.getString(0).equals("notneeded"))
                continue;
            names[i]=cursor.getString(0);
            i++;
        }
        return names;
    }


}
