package com.example.anishilvjose.wolverine;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class viewmap extends AppCompatActivity implements AdapterView.OnItemClickListener{
        ListView L;
    ArrayAdapter <String> ar;
    String[] str={"Kakkanad","Edappally"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewmap);
        L=(ListView)findViewById(R.id.list);
        L.setOnItemClickListener(this);
        ar=new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,str);
        L.setAdapter(ar);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(getApplicationContext(), "complaint"+str[position], Toast.LENGTH_SHORT).show();
    }
}
