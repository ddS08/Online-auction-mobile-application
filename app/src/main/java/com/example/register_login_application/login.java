package com.example.register_login_application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class login extends AppCompatActivity {
    EditText user_ed;
    EditText pass_ed;
    Button btnlogin;
    DBhelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        DB = new DBhelper(this,"onlineauction.db",1);
        btnlogin = (Button) findViewById(R.id.btnlog);

        btnlogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                user_ed = (EditText) findViewById(R.id.user_id2);
                pass_ed = ( EditText) findViewById(R.id.pass_id2);

                if(user_ed.getText().toString().equals("") || pass_ed.getText().toString().equals(""))
                {
                    Toast toast = Toast.makeText(getApplicationContext(),"Username/password cannot be blank space",Toast.LENGTH_SHORT);
                    toast.show();
                }
                else
                {
                    Boolean checkuser = DB.checkUsername(user_ed.getText().toString());

                    if(checkuser == false)
                    {
                        Toast toast1 = Toast.makeText(getApplicationContext(),"No such user found!!!\n Please Register/Re-enter username",Toast.LENGTH_SHORT);
                        toast1.show();
                    }
                    else
                    {
                        Boolean checkusernamepass = DB.checkusernamepassword(user_ed.getText().toString(),pass_ed.getText().toString());
                        if(checkusernamepass)
                        {
                            Toast toast2 = Toast.makeText(getApplicationContext(),"Login Successful!!!",Toast.LENGTH_SHORT);
                            toast2.show();
                            Intent i = new Intent(login.this,homeActivity.class);
                            i.putExtra("key", user_ed.getText().toString());
                            startActivity(i);
                        }
                        else
                        {
                            Toast toast3 = Toast.makeText(getApplicationContext(),"Invalid Username/Password",Toast.LENGTH_SHORT);
                            toast3.show();
                        }
                    }
                }
            }
        });
    }
}