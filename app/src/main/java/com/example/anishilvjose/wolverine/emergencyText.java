package com.example.anishilvjose.wolverine;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.anishilvjose.wolverine.R;

public class emergencyText extends AppCompatActivity {
    ListView ls;
    ArrayAdapter <String> ar;
    String[] str={"hdasiu","fsaf","safaf"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency_text);
        ls=(ListView)findViewById(R.id.list);
        ar=new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,str);
        ls.setAdapter(ar);
    }
}
