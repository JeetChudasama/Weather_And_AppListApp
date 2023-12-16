package com.example.myapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;

public class WeatherData extends Fragment {

    private EditText editTextcity, editTextcode;
    private TextView result;
    private Button checkButton;
    private final String url = "http://api.openweathermap.org/data/2.5/weather";
    private final String appid = "6cb3178378d634a6c6722428081c2dba";
    DecimalFormat df = new DecimalFormat("#.##");

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View myview = inflater.inflate(R.layout.weather_data, container, false);

        editTextcity = myview.findViewById(R.id.city_name);
        editTextcode = myview.findViewById(R.id.pin_code);
        result = myview.findViewById(R.id.result);
        checkButton = myview.findViewById(R.id.check_weather);

        checkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tempurl = "";
                String city = editTextcity.getText().toString().trim();
                String code = editTextcode.getText().toString().trim();
                if (city.equals("")){
                    Toast.makeText(getContext(), "city field cannot be empty", Toast.LENGTH_SHORT).show();
                }else {
                    if (!code.equals("")){
                        tempurl = url + "?q=" + city + "," + code + "&appid=" + appid;
                    }else {
                        tempurl = url + "?q=" + city + "&appid=" + appid;
                    }
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, tempurl, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Log.d("MyApp", "Response received: " + response);
                            Toast.makeText(getContext(), "Success", Toast.LENGTH_SHORT).show();
                            String output = "";
                            try {
                                JSONObject jsonResponse = new JSONObject(response);
                                JSONArray jsonArray = jsonResponse.getJSONArray("weather");
                                JSONObject jsonObjectWeather = jsonArray.getJSONObject(0);
                                String description = jsonObjectWeather.getString("description");
                                JSONObject jsonObjectMain = jsonResponse.getJSONObject("main");
                                double temp = jsonObjectMain.getDouble("temp") - 273.15;
                                double feelsLike = jsonObjectMain.getDouble("feels_like") - 273.15;
                                float pressure = jsonObjectMain.getInt("pressure");
                                int humidity = jsonObjectMain.getInt("humidity");
                                JSONObject jsonObjectWind = jsonResponse.getJSONObject("wind");
                                String wind = jsonObjectWind.getString("speed");
                                JSONObject jsonObjectClouds = jsonResponse.getJSONObject("clouds");
                                String clouds = jsonObjectClouds.getString("all");
                                JSONObject jsonObjectSys = jsonResponse.getJSONObject("sys");
                                String countryName = jsonObjectSys.getString("country");
                                String cityName = jsonResponse.getString("name");
                                output += "Current weather of " + cityName + " (" + countryName + ")"
                                        + "\n Temp: " + df.format(temp) + " °C"
                                        + "\n Feels Like: " + df.format(feelsLike) + " °C"
                                        + "\n Humidity: " + humidity + "%"
                                        + "\n Climate: " + description
                                        + "\n Wind Speed: " + wind + "m/s (meters per second)"
                                        + "\n Cloudiness: " + clouds + "%"
                                        + "\n Pressure: " + pressure + " hPa";
                                result.setText(output);
                            } catch (JSONException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(getContext(), error.toString().trim(), Toast.LENGTH_SHORT).show();
                        }
                    });
                    RequestQueue requestQueue = Volley.newRequestQueue(requireActivity().getApplication());
                    requestQueue.add(stringRequest);
                }
            }
        });

        return myview;
    }

}