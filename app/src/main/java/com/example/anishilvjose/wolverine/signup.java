package com.example.anishilvjose.wolverine;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class signup extends AppCompatActivity {
    EditText user, pass1, pass2, vehmod, vehcol, vehno, contact, email;
    Button signup;
    String a, b, c, d, e, f, g, h;
    String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_signup);
        user = (EditText) findViewById(R.id.user);
        pass1 = (EditText) findViewById(R.id.pass1);
        pass2 = (EditText) findViewById(R.id.pass2);
        vehmod = (EditText) findViewById(R.id.vehmod);
        vehcol = (EditText) findViewById(R.id.vehcol);
        vehno = (EditText) findViewById(R.id.vehno);
        contact = (EditText) findViewById(R.id.contact);
        email = (EditText) findViewById(R.id.email);
        signup = (Button) findViewById(R.id.signup);
        signup.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                a = user.getText().toString();
                b = pass1.getText().toString();
                c = pass2.getText().toString();
                d = vehmod.getText().toString();
                e = vehcol.getText().toString();
                f = vehno.getText().toString();
                g = email.getText().toString();
                h = contact.getText().toString();

                url = "signupjson.php?username=" + a + "&password1=" + b + "&password2=" + c + "&vehmod=" + d + "&vehcol=" + e + "&vehno=" + f + "&contact=" + h + "&email=" + g;
                url = url.replace(" ", "%20");
                new signup.Loads().execute();
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
                Toast.makeText(getApplicationContext(), "Registration Complete", Toast.LENGTH_LONG).show();
//                Toast.makeText(getApplicationContext(),LocationService.lati+", "+LocationService.logi, Toast.LENGTH_LONG).show();
                Intent userHome = new Intent(getApplicationContext(), Main2Activity.class);
                startActivity(userHome);
            } else {

                Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
            }
        }
    }
}