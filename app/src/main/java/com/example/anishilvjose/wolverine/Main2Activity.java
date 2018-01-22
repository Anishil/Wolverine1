package com.example.anishilvjose.wolverine;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Main2Activity extends AppCompatActivity {
    EditText user,pass;
    Button login;
    String a,b;
    TextView T;
  public static  String logid,username;
String url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        user=(EditText)findViewById(R.id.user);
        pass=(EditText)findViewById(R.id.pass);
        T=(TextView)findViewById(R.id.t1);
        login=(Button)findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                a=user.getText().toString();
                b=pass.getText().toString();
                url = "loginjson.php?username=" + a + "&password=" + b;
                url = url.replace(" ", "%20");
                new Loads().execute();
            }
        });
        T.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                  Intent q = new Intent(getApplicationContext(),signup.class);
                  startActivity(q);
                    Toast.makeText(getApplicationContext(), "Please Register", Toast.LENGTH_SHORT).show();

            }
        });




    }

    private String getData() {
        String ret = "na";
        JsonAct ja = new JsonAct();
        String result = ja.setJsonVal(url);
        try {
            JSONArray arr = new JSONArray(result);
            String s = arr.getString(0).trim();
            Log.v("Exception", "********" + s);
            if (!s.equalsIgnoreCase("na")) {
                JSONObject ob = arr.getJSONObject(0);
                logid = ob.getString("user_id");
                username=ob.getString("username");

                ret = "ok";
            } else {
                ret = "na";
            }
        } catch (JSONException e) {
            ret = e.toString();
        }
        return ret;
    }
    private class Loads extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... strings) {
            String res = getData();
            return res;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if (s.equalsIgnoreCase("ok")) {
                // Toast.makeText(getApplicationContext(),logid,Toast.LENGTH_LONG).show();
                Toast.makeText(getApplicationContext(),"Login success..!!!!", Toast.LENGTH_LONG).show();
//                Toast.makeText(getApplicationContext(),LocationService.lati+", "+LocationService.logi, Toast.LENGTH_LONG).show();
                Intent userHome = new Intent(getApplicationContext(), homepage.class);
                startActivity(userHome);
                startService(new Intent(getApplicationContext(),LocationService.class));
            } else {

                Toast.makeText(getApplicationContext(),"Invalid Username or Password..!!!",Toast.LENGTH_LONG).show();
            }
        }
    }
}
