package com.example.register_login_application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    DBhelper DBhelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DBhelper = new DBhelper(this,"onlineauction.db",1);
        try{
            DBhelper.checkDb();
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        try{
            DBhelper.OpenDatabase();
        }catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    public void Register(View v)
    {
        Intent i = new Intent(MainActivity.this,register.class);
        startActivity(i);
    }
    public void login(View v)
    {
        Intent i = new Intent(MainActivity.this,login.class);
        startActivity(i);
    }
}