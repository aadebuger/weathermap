package com.aadebuger.weathermap;

import org.json.JSONObject;


import com.aadebuger.weathermap.model.BaiduWeather;
import com.aadebuger.weathermap.model.BaiduWeatherData;
import com.aadebuger.weathermap.model.BaiduWeatherResults;
import com.aadebuger.weathermap.model.WeatherMap;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	//	makeRequestcbinjson("http://api.map.baidu.com/telematics/v3/weather?location=±±¾©&output=json&ak=4dee58d4f9c17dbbfdf850487b02b36e");
	//	makeRequestcbinjson("http://php.weather.sina.com.cn/xml.php?city=%B1%B1%BE%A9&password=DJOYnieT8234jlsK&day=0");	
		new WeatherMap().makeAll();
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	void makeRequestcbinjson(String serviceurl) {
        AsyncHttpClient client = new AsyncHttpClient();

        client.get(serviceurl
        		, null, 
        		new AsyncHttpResponseHandler() {
          	@Override
              public void onStart() {
          		Log.d("restful","onStart");
              }
              @Override
              public void onSuccess(String response) {
              	   Log.d("Restful","Lanmaouser success");
                  System.out.println(response);
                  BaiduWeather bw = new BaiduWeather();
                  BaiduWeather bw1 = bw.parse(response);
                  System.out.println("bw1 ="+bw1);
                  System.out.println("bw1="+bw1.getDate());
                  for ( BaiduWeatherResults bwr : bw1.getResulsts())
                  {
                	  System.out.println("bwr"+bwr.getCurrentCity());
                	  for ( BaiduWeatherData bwd : bwr.getWeather_data())
                	  {
                		  System.out.println("bwd="+ bwd.getDate()+ " "+ bwd.getTemperature() );
                		  System.out.println("bwd="+ bwd.getDate()+ " "+ bwd.getWeather() );
                		  
                		  
                	  }
                  }
              }
              @Override
              public void onFailure(Throwable e, String response) {
                  // Response failed :(
              	 Log.d("Restful","Failure");
              	 System.out.println("response ="+response);
              }

              @Override
              public void onFinish() {
                  // Completed the request (either success or failure)
              	 Log.d("Restful","Finish");
              }
          });
        		

	}
	
	
}
