package com.example.register_login_application;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.telephony.TelephonyCallback;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class buyitem extends AppCompatActivity {
    TextView buy_name,buy_productid,buy_currbid,buy_sellerid,buy_bidderid,buy_price,buy_buynowprice;
    EditText yourbid;
    ImageView pro_image;
    DBhelper DB;
    Button sell_bid;
    @SuppressLint({"MissingInflatedId", "WrongViewCast"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buyitem);

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
        String name;
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                name = null;
            } else {
                name = extras.getString("name");
            }
        } else {
            name = (String) savedInstanceState.getSerializable("name");
        }
        String productid;
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                productid = null;
            } else {
                productid = extras.getString("productid");
            }
        } else {
            productid= (String) savedInstanceState.getSerializable("productid");
        }
        String seller;
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                seller = null;
            } else {
                seller = extras.getString("seller");
            }
        } else {
            seller = (String) savedInstanceState.getSerializable("seller");
        }
        String price;
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                price = null;
            } else {
                price = extras.getString("price");
            }
        } else {
            price = (String) savedInstanceState.getSerializable("price");
        }
        String buynowprice;
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                buynowprice = null;
            } else {
                buynowprice = extras.getString("buynowprice");
            }
        } else {
            buynowprice = (String) savedInstanceState.getSerializable("buynowprice");
        }
        String bids;
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                bids = null;
            } else {
                bids = extras.getString("bids");
            }
        } else {
            bids = (String) savedInstanceState.getSerializable("bids");
        }
        String bidder;
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                bidder = null;
            } else {
                bidder = extras.getString("bidder");
            }
        } else {
            bidder = (String) savedInstanceState.getSerializable("bidder");
        }
        buy_name=(TextView)findViewById(R.id.buy_name);
        buy_productid=(TextView)findViewById(R.id.buy_productid);
        buy_sellerid=(TextView)findViewById(R.id.buy_sellerid);
        buy_price=(TextView)findViewById(R.id.buy_price);
        buy_buynowprice=(TextView)findViewById(R.id.buy_buynow);
        buy_currbid=(TextView)findViewById(R.id.buy_currbid);
        buy_bidderid=(TextView)findViewById(R.id.buy_bidder);
        yourbid=(EditText)findViewById(R.id.buy_yourbid);
        buy_name.setText(name);
        buy_productid.setText("product id: "+productid);
        buy_sellerid.setText("seller: "+seller);
        buy_price.setText("Price: "+price);
        buy_buynowprice.setText("Buynow: "+buynowprice);
        buy_currbid.setText("current bid: "+bids);
        if(bidder.equals("null"))
        {
            buy_bidderid.setText("No bidders yet");
        }else
            buy_bidderid.setText("Bidder: "+bidder);
        DB = new DBhelper(this,"onlineauction.db",1);
        pro_image=(ImageView)findViewById(R.id.pro_image);
        pro_image.setImageBitmap(DB.getImage(productid));

        sell_bid=(Button)findViewById(R.id.BID);
        sell_bid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String bid=yourbid.getText().toString();
                int bid1=Integer.parseInt(bid);
                int buynow = Integer.parseInt(buynowprice);
                int currbid = Integer.parseInt(bids);
                if(bid1>=buynow)
                {
                    boolean update = DB.updateuserdata(productid);
                    if (update) {
                        Toast toast1 = Toast.makeText(getApplicationContext(), "Product sold", Toast.LENGTH_SHORT);
                        toast1.show();
                    } else {
                        Toast toast1 = Toast.makeText(getApplicationContext(), "ERROR", Toast.LENGTH_SHORT);
                        toast1.show();
                    }
                }
                else if(currbid < bid1)
                {
                    boolean update = DB.updatesetbids(productid,String.valueOf(bid1),username);
                    if (update) {
                        Toast toast1 = Toast.makeText(getApplicationContext(), "Bid successful", Toast.LENGTH_SHORT);
                        toast1.show();
                    } else {
                        Toast toast1 = Toast.makeText(getApplicationContext(), "ERROR", Toast.LENGTH_SHORT);
                        toast1.show();
                    }
                }
                else
                {
                    Toast toast1 = Toast.makeText(getApplicationContext(), "Invalid Bid \n your bid is less than minimum required" , Toast.LENGTH_SHORT);
                    toast1.show();
                }
            }
        });
        /*int bid = Integer.parseInt(yourbid.getText().toString());
        int currbid=Integer.parseInt(bids);
        int buynow=Integer.parseInt(buynowprice);
        if(bid>=buynow) {
            boolean update = DB.updateuserdata(seller);
            if (update) {
                Toast toast1 = Toast.makeText(getApplicationContext(), "Product sold", Toast.LENGTH_SHORT);
                toast1.show();
            } else {
                Toast toast1 = Toast.makeText(getApplicationContext(), "ERROR", Toast.LENGTH_SHORT);
                toast1.show();
            }
        }*/
    }
}