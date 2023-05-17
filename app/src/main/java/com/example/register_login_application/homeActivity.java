package com.example.register_login_application;

import static android.widget.AdapterView.*;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class homeActivity extends AppCompatActivity {
    TextView user;
    String l1[]
            = { "Buy an Item","Profile","Sell an item","Listed/Sold items" };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        user = (TextView)findViewById(R.id.tv_user);

        String username;
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                username = null;
            } else {
                username = extras.getString("key");
            }
        } else {
            username = (String) savedInstanceState.getSerializable("key");
        }
        user.setText("Welcome "+username+";");
        ListAdapter ls = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,l1);
        ListView lv = (ListView) findViewById(R.id.list1);
        lv.setAdapter(ls);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String l1 = String.valueOf(parent.getItemAtPosition(position));
                if(l1.equals("Buy an Item"))
                {
                    Intent i = new Intent(homeActivity.this,buypage.class);
                    i.putExtra("key", username);
                    startActivity(i);
                }
                else if(l1.equals("Profile"))
                {
                    Intent i = new Intent(homeActivity.this,profile.class);
                    i.putExtra("key", username);
                    startActivity(i);
                }
                else if(l1.equals("Sell an item"))
                {
                    Intent i = new Intent(homeActivity.this,sell.class);
                    i.putExtra("key", username);
                    startActivity(i);
                }
                else
                {
                    Intent i = new Intent(homeActivity.this,listeditems.class);
                    i.putExtra("key", username);
                    startActivity(i);
                }
            }
        });
    }
    public void gotomainpage(View v){
        Intent i = new Intent(homeActivity.this,MainActivity.class);
        startActivity(i);
    }
}