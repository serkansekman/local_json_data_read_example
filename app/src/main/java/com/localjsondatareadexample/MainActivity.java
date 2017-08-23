package com.localjsondatareadexample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.google.gson.Gson;
import com.localjsondatareadexample.model.CityList;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CityList cityList = new CityList();

        try {
            //Load File
            BufferedReader jsonReader = new BufferedReader(new InputStreamReader(this.getResources().openRawResource(R.raw.jsondata)));
            StringBuilder jsonBuilder = new StringBuilder();
            for (String line = null; (line = jsonReader.readLine()) != null; ) {
                jsonBuilder.append(line).append("\n");
            }

            Gson gson = new Gson();
            cityList = gson.fromJson(jsonBuilder.toString(),CityList.class);

            Log.d("Deneme",cityList.getCityDetail().get(0).getName());


        } catch (FileNotFoundException e) {
            Log.e("jsonFile", "file not found");
        } catch (IOException e) {
            Log.e("jsonFile", "ioerror");
        }


        Spinner spinner = (Spinner) findViewById(R.id.spinner);

        List<String> spinnerData = new ArrayList<>();

        for(int i=0;i<cityList.getCityDetail().size();i++){
            spinnerData.add(cityList.getCityDetail().get(i).getName());
        }

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, spinnerData);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);

    }
}
