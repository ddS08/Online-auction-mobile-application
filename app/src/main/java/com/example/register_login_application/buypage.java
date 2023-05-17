package com.example.register_login_application;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class buypage extends AppCompatActivity {
    TextView user;
    DBhelper DB;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buypage);
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
        DB = new DBhelper(this,"onlineauction.db",1);
        user.setText("Hello "+username+";\nWelcome to BuyPage") ;
        /*StringBuffer buff;
        buff=DB.buypagelist();*/
        String names[]=DB.buypagelist();
        ListAdapter ls = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,names);
        ListView lv = (ListView) findViewById(R.id.list1);
        lv.setAdapter(ls);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String l1 = String.valueOf(adapterView.getItemAtPosition(i));
                String sellerusername=DB.getsellerusername(l1);
                String productid=DB.productid(l1);
                String price=DB.price(l1);
                String buynowprice = DB.buynowprice(l1);
                String bids = DB.currbid(productid);
                String bidder = DB.bidder(productid);
                Intent k = new Intent(buypage.this,buyitem.class);
                k.putExtra("key", username);
                k.putExtra("name",l1);
                k.putExtra("seller",sellerusername);
                k.putExtra("productid",productid);
                k.putExtra("price",price);
                k.putExtra("buynowprice",buynowprice);
                k.putExtra("bids",bids);
                k.putExtra("bidder",bidder);
                startActivity(k);
            }
        });

    }
}