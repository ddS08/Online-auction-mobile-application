package com.example.register_login_application;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class sell extends AppCompatActivity {
    ImageView img;
    FloatingActionButton fab;
    DBhelper DB;
    byte[] image;
    EditText name,id,price,buynow;
    String username;
    private static final int PICK_IMAGE=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sell);
        img = findViewById(R.id.coverimage);
        fab = findViewById(R.id.floatingActionButton);
        name =(EditText)findViewById(R.id.name_id2);
        id = (EditText)findViewById(R.id.pro_id);
        price =(EditText)findViewById(R.id.price);
        buynow =(EditText)findViewById(R.id.buynow);
        Button btn = (Button)findViewById(R.id.btn_sell);

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
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gallery = new Intent();
                gallery.setType("image/*");
                gallery.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(gallery,"select Picture"),PICK_IMAGE);

            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean insert = DB.insertimage(id.getText().toString(),username,name.getText().toString(),image,price.getText().toString(),buynow.getText().toString());
                boolean insert2 =DB.insertintosetbids(id.getText().toString());
                if(insert && insert2)
                {
                    Toast toast1 = Toast.makeText(getApplicationContext(), "product added!!", Toast.LENGTH_SHORT);
                    toast1.show();
                }
                else
                {
                    Toast toast1 = Toast.makeText(getApplicationContext(), "product not added", Toast.LENGTH_SHORT);
                    toast1.show();
                }
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==PICK_IMAGE && resultCode==RESULT_OK)
        {
            Uri imageUri = data.getData();
            try{
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),imageUri);
                img.setImageBitmap(bitmap);
                ByteArrayOutputStream bytearray = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG,1,bytearray);
                image=bytearray.toByteArray();
            }catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

}