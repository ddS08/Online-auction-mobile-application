package com.example.register_login_application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class register extends AppCompatActivity {
    EditText name_ed,user_ed,email_ed,pass_ed,repass_ed,phone_ed;
    Button btnregister;
    DBhelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }
    public void input(View v)
    {
        name_ed = (EditText) findViewById(R.id.name_id);
        user_ed = (EditText) findViewById(R.id.user_id);
        email_ed = (EditText) findViewById(R.id.email_id);
        pass_ed = (EditText) findViewById(R.id.pass_id);
        repass_ed = (EditText) findViewById(R.id.repass_id);
        phone_ed = (EditText) findViewById(R.id.phone_id);
        btnregister = (Button) findViewById(R.id.btnregister);
        DB = new DBhelper(this,"onlineauction.db",1);
        btnregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Boolean checkuser = DB.checkUsername(user_ed.getText().toString());
                if (!checkuser) {
                    if(pass_ed.getText().toString().equals(repass_ed.getText().toString())){
                        if(phone_ed.getText().toString().length()==10) {
                            Boolean insert = DB.insertData(user_ed.getText().toString(), pass_ed.getText().toString(), name_ed.getText().toString(), email_ed.getText().toString(), phone_ed.getText().toString());
                            if (insert)
                            {
                                Toast toast1 = Toast.makeText(getApplicationContext(), "Registered Successfully!!", Toast.LENGTH_SHORT);
                                toast1.show();
                                Intent i = new Intent(register.this, login.class);

                                startActivity(i);

                            } else {
                                Toast toast3 = Toast.makeText(getApplicationContext(), "Registration failed!!!", Toast.LENGTH_SHORT);
                                toast3.show();
                            }
                        }else
                        {
                            Toast toast3 = Toast.makeText(getApplicationContext(), "Phone number must be of 10 digits", Toast.LENGTH_SHORT);
                            toast3.show();
                        }

                    }
                    else
                    {
                        Toast toast6 = Toast.makeText(getApplicationContext(), "Passwords don't match!!!", Toast.LENGTH_SHORT);
                        toast6.show();
                    }

                }
                else {
                    Toast toast6 = Toast.makeText(getApplicationContext(), "Username already existing!!", Toast.LENGTH_SHORT);
                    toast6.show();
                }
            }
        });

    }
}