package com.example.register_login_application;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class listeditems extends AppCompatActivity {
    DBhelper DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listeditems);
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
        String sold[]=DB.solditems(username);
        String unsold[]=DB.unsolditems(username);
        Toast toast1 = Toast.makeText(getApplicationContext(), sold[0], Toast.LENGTH_SHORT);
        toast1.show();
        int sold1=sold.length;
        int unsold1=unsold.length;
        int tot=sold1+unsold1;
        String total[]=new String[tot];
        for(int i=0;i<sold1;i++)
        {
            total[i]=sold[i]+"(sold)";
        }
        int k=0;
        for(int i=sold1;i<tot;i++)
        {
            total[i]=unsold[k];
            k++;
        }
        ListAdapter ls = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,total);
        ListView lv = (ListView) findViewById(R.id.list1);
        lv.setAdapter(ls);
    }
}