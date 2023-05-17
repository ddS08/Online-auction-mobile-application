package com.example.register_login_application;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class profile extends AppCompatActivity {
    EditText name,pass,repass,email,phone;
    TextView userid;
    DBhelper DB;
    Button ep;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

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
        Toast toast1 = Toast.makeText(getApplicationContext(), username, Toast.LENGTH_SHORT);
        toast1.show();

        String pro_name=DB.name(username);
        name=(EditText)findViewById(R.id.profile_name);
        name.setText(pro_name);
        userid=(TextView) findViewById(R.id.profile_username);
        userid.setText(username);
        String pro_email=DB.email(username);
        email=(EditText)findViewById(R.id.profile_email);
        email.setText(pro_email);
        String pro_pass=DB.pass(username);
        email=(EditText)findViewById(R.id.profile_pass);
        email.setText(pro_pass);
        String pro_phone=DB.phone(username);
        phone=(EditText)findViewById(R.id.profile_phone);
        phone.setText(pro_phone);
        pass=(EditText)findViewById(R.id.profile_pass);
        repass=(EditText)findViewById(R.id.profile_repass);
        //name=(EditText) findViewById(R.id.profile_name);
        //name.setText(pro_name);
        ep = findViewById(R.id.btnlog2);
        ep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast toast1 = Toast.makeText(getApplicationContext(), "product not added", Toast.LENGTH_SHORT);
                toast1.show();
                if(pass.getText().toString().equals(repass.getText().toString()))
                {
                    boolean ans=DB.updatedata(name.getText().toString(),userid.getText().toString(),pass.getText().toString(),email.getText().toString(),phone.getText().toString());
                    if (ans) {
                        Toast toast2 = Toast.makeText(getApplicationContext(), "Data updated", Toast.LENGTH_SHORT);
                        toast2.show();
                    } else {
                        Toast toast2 = Toast.makeText(getApplicationContext(), "ERROR", Toast.LENGTH_SHORT);
                        toast2.show();
                    }
                }

            }
        });
    }
}