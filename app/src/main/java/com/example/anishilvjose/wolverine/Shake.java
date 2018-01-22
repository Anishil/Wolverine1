package com.example.anishilvjose.wolverine;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.hardware.SensorListener;
import android.hardware.SensorManager;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.preference.PreferenceManager;
import android.speech.tts.TextToSpeech;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Locale;

@SuppressWarnings("deprecation")
@TargetApi(Build.VERSION_CODES.GINGERBREAD)
public class Shake extends Activity implements SensorListener,TextToSpeech.OnInitListener {
    // For shake motion detection.
    private SensorManager sensorMgr;
    private long lastUpdate = -1;
    private float x, y, z;
    private float last_x, last_y, last_z;
    private static final int SHAKE_THRESHOLD = 1400;
    String phoneid="";
	String url1,url2;
    private static String tmpplc="";
    Handler handler=new Handler();
	Button b1;
    private TextToSpeech textToSpeech;
    
   
    //String METHOD_NAME1 ="distruptiontype_user";
    //String SOAP_ACTION1=User.namespace+"distruptiontype_user";
    
    //String METHOD_NAME2="getdistruptionstatusbylocation";
   // String SOAP_ACTION2=User.namespace+"getdistruptionstatusbylocation";
	 
	String url="";
//    
	TextView ta;
//    Button b,b1;
//    Button bclr;
    ImageView img;
    String uid;
   
  //  private static String route = "getrouteinfo"; 
    
    @TargetApi(Build.VERSION_CODES.GINGERBREAD)
	protected void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);	
	    setContentView(R.layout.activity_shake);

		b1 = (Button) findViewById(R.id.button4);
		b1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i=new Intent(getApplicationContext(),homepage.class);
				startActivity(i);


			}
		});
	    
	    textToSpeech = new TextToSpeech(this, this);
	    
	    handler.post(AlertFinder);	    
	    
	    ta=(TextView)findViewById(R.id.textView1);

	    img=(ImageView)findViewById(R.id.imageView1);
	    img.setVisibility(View.INVISIBLE);
	  
	   
	    
	    // start motion detection
	    sensorMgr = (SensorManager) getSystemService(SENSOR_SERVICE);
	    boolean accelSupported = sensorMgr.registerListener(this, SensorManager.SENSOR_ACCELEROMETER, SensorManager.SENSOR_DELAY_GAME);
	
	    if (!accelSupported) {
	        // on accelerometer on this device
	        sensorMgr.unregisterListener((SensorListener) this,
	                SensorManager.SENSOR_ACCELEROMETER);
	    }
	    
	   // TelephonyManager telephonyManager  = (TelephonyManager)getApplicationContext().getSystemService(Context.TELEPHONY_SERVICE);
	   // phoneid=telephonyManager.getDeviceId().toString();
	   // Log.d("phoneid", phoneid);
	   // Toast.makeText(getApplicationContext(), phoneid, Toast.LENGTH_LONG).show();

//	    Intent in=new Intent(getApplicationContext(),LocationService.class);
//	    startService(in);
    }
    
    

public Runnable AlertFinder = new Runnable(){
		
		public void run(){
			
			String loc=LocationService.place;
			String lati=LocationService.lati;
			String logi=LocationService.logi;
            Toast.makeText(Shake.this, "latti"+lati+"long"+logi, Toast.LENGTH_SHORT).show();
//			if(!loc.equalsIgnoreCase(tmpplc))
//			{
//			if(LocationService.curLocation!=null)
//			{
//				
				Toast.makeText(getApplicationContext(),"Response", Toast.LENGTH_LONG).show();
			url1 = "congestion.php?lattitude="+ lati +"&longitude="+logi+"&uid="+Main2Activity.logid;
			url1 = url1.replace(" ", "%20");
			new Loads().execute();
//				SoapObject req=new SoapObject(User.namespace,METHOD_NAME2);
//				//req.addProperty("placename",loc);
//				req.addProperty("latitude", lati);
//				req.addProperty("longitude", logi);
//
//				SoapSerializationEnvelope env=new SoapSerializationEnvelope(SoapEnvelope.VER11);
//				 env.setOutputSoapObject(req);
//
//
//
//			        HttpTransportSE se=new HttpTransportSE(User.url);
//					try {
//						se.call(SOAP_ACTION2, env);
//
//					Log.d("in loc", "....1.....");
//					String Rre=env.getResponse().toString();
//					if(Rre.equalsIgnoreCase("ok"))
//					{
//						String s="Carefull some distruptions are there";
//			    		Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();
//			    		convertTextToSpeech(s);
//			    		tmpplc=loc;
//					}
//
//					} catch (Exception e) {
//						// TODO Auto-generated catch block
//						Toast.makeText(getApplicationContext(),"e.getmessage"+e.getMessage(), Toast.LENGTH_LONG).show();
//					}
					
		//	}
	//		}			
			handler.postDelayed(AlertFinder,10000);
		}
};
 
    protected void onPause() {
    if (sensorMgr != null) {
        sensorMgr.unregisterListener(this,
                SensorManager.SENSOR_ACCELEROMETER);
        sensorMgr = null;
        }
    super.onPause();
    }

    public void onAccuracyChanged(int arg0, int arg1) {
    // TODO Auto-generated method stub
    }

    public void onSensorChanged(int sensor, float[] values) {
    if (sensor == SensorManager.SENSOR_ACCELEROMETER) {
        long curTime = System.currentTimeMillis();
        // only allow one update every 100ms.
        if ((curTime - lastUpdate) > 100) {
        long diffTime = (curTime - lastUpdate);
        lastUpdate = curTime;

        x = values[SensorManager.DATA_X];
        y = values[SensorManager.DATA_Y];
        z = values[SensorManager.DATA_Z];

        if(Round(x,4)>10.0000){
            Log.d("sensor", "X Right axis: " + x);
       //     Toast.makeText(this, "Right shake detected", Toast.LENGTH_SHORT).show();
        }
        else if(Round(y,4)>10.0000){
            Log.d("sensor", "X Right axis: " + x);
        //    Toast.makeText(this, "Top shake detected", Toast.LENGTH_SHORT).show();
        }
        else if(Round(y,4)>-10.0000){
            Log.d("sensor", "X Right axis: " + x);
       //     Toast.makeText(this, "Bottom shake detected", Toast.LENGTH_SHORT).show();
        }
        else if(Round(x,4)<-10.0000){
            Log.d("sensor", "X Left axis: " + x);
        //    Toast.makeText(this, "Left shake detected", Toast.LENGTH_SHORT).show();
        }

        float speed = Math.abs(x+y+z - last_x - last_y - last_z) / diffTime * 10000;

        // Log.d("sensor", "diff: " + diffTime + " - speed: " + speed);
        if (speed > SHAKE_THRESHOLD) 
        {
            Log.d("sensor", "Shake detected w/ speed: " + speed);
            cn=0;
            handler.post(rn);
   	
        	Location loc=LocationService.curLocation;
 
	       // SharedPreferences sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
	       // uid=sh.getString("logid","");
	    	
//	    	SoapObject request=new SoapObject(User.namespace,METHOD_NAME1);
//	    	//request.addProperty("uid",uid);
//        request.addProperty("disruption","distruption occured");
////	    	request.addProperty("place","Narikuni");
////		    request.addProperty("latitude","11.3658");
////		    request.addProperty("longitude","75.8636");
//		    request.addProperty("place",LocationService.place);
//		    request.addProperty("latitude",LocationService.lati);
//		    request.addProperty("longitude",LocationService.logi);
//
//	        SoapSerializationEnvelope env=new SoapSerializationEnvelope(SoapEnvelope.VER11);
//	        env.setOutputSoapObject(request);
//
//
//	        HttpTransportSE se=new HttpTransportSE(User.url);
//    		try {
//				se.call(SOAP_ACTION1, env);
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//    		Log.d("in loc", "....1.....");
//    		SoapObject res=(SoapObject)env.bodyIn;
//    		if(res!=null)
//    		{
//        		if(res.equals("ok"))
//        		{
//        			Toast.makeText(getApplicationContext(), "Distruption Occured", Toast.LENGTH_SHORT).show();
//        			Intent i=new Intent(getApplicationContext(),Userhome.class);
//        			startActivity(i);
//        		}
//    			//cal.setText(res.getProperty(0).toString());
//    		}
//    		else
//    		{
//    			Toast.makeText(getApplicationContext(),"No Response", Toast.LENGTH_LONG).show();
//    		}
        }
        
        ta.setText(speed+"");
        
        last_x = x;
        last_y = y;
        last_z = z;
        }
    }
    }
static int cn=0;
    Runnable rn=new Runnable() {
		@Override
		public void run() {
			if(cn<10){
				img.setBackgroundResource(R.drawable.car);
	            img.setVisibility(View.VISIBLE);

			}
			else{
				img.setVisibility(View.INVISIBLE);
			}
			cn++;
			handler.postDelayed(rn, 1000);
		}
	};
    
    public static float Round(float Rval, int Rpl) {
    float p = (float) Math.pow(10,Rpl);
    Rval = Rval * p;
    float tmp = Math.round(Rval);
    return (float)tmp/p;
    }

 
	
	private void convertTextToSpeech(String text) {
		
		if (null == text || "".equals(text)) {
			text = "Please give some input.";
		}
		textToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, null);
	}

	
	
	@Override
	public void onInit(int status) {
		if (status == TextToSpeech.SUCCESS) {
			int result = textToSpeech.setLanguage(Locale.US);
			if (result == TextToSpeech.LANG_MISSING_DATA
					|| result == TextToSpeech.LANG_NOT_SUPPORTED) {
				Log.e("error", "This Language is not supported");
			} else {
				convertTextToSpeech("Started");
			}
		} else {
		
			Log.e("error", "Initilization Failed!");
		}
	}
	private String getData() {
		String ret = "na";
		JsonAct ja = new JsonAct();
		String result = ja.setJsonVal(url1);
		try {
			JSONArray arr = new JSONArray(result);
			String s = arr.getString(0).trim();
			//Toast.makeText(getApplicationContext(),s+"",Toast.LENGTH_LONG).show();
			Log.v("Exception", "********" + s);
			if (s.equalsIgnoreCase("ok")) {
				JSONObject ob = arr.getJSONObject(0);

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
			if (!s.equalsIgnoreCase("na")) {

                Toast.makeText(getApplicationContext(), "Carefull some distruptions are there", Toast.LENGTH_LONG).show();
                convertTextToSpeech("Carefull some distruptions are there");
			} else {


                Toast.makeText(getApplicationContext(), "Smooth driving...", Toast.LENGTH_LONG).show();
			}
		}
	}
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		
		handler.removeCallbacks(AlertFinder);
	}
}
