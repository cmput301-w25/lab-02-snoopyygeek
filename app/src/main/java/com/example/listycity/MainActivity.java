package com.example.listycity;

import android.icu.text.Transliterator;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    ListView cityList;
    ArrayAdapter<String> cityAdapter;
    ArrayList<String> dataList;
    EditText enterCity;
    Button addCity;
    Button confirm;

    Button deleteCity;

    String selectText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        cityList=findViewById(R.id.city_list);

        String []cities={"Edmonton", "Vancouver","Moscow","Sydney", "Berlin","Vienna","Tokyo", "Beijing", "Osaka", "New Delhi"};

        dataList= new ArrayList<>();
        dataList.addAll(Arrays.asList(cities));

        cityAdapter=new ArrayAdapter<>(this, R.layout.content,dataList);
        cityList.setAdapter(cityAdapter);

        addCity =findViewById(R.id.add_city);
        deleteCity=findViewById(R.id.delete_city);

        //must become visible when addCity clicked
        enterCity =findViewById(R.id.editTextText);
        confirm =findViewById(R.id.confirm);

        addCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enterCity.setVisibility(View.VISIBLE);
                confirm.setVisibility(View.VISIBLE);
            }
        });

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newCity=enterCity.getText().toString();
                dataList.add(newCity);
                cityAdapter.notifyDataSetChanged();
                enterCity.setText("");
                enterCity.setVisibility(View.GONE);
                confirm.setVisibility(View.GONE);
            }
        });

        cityList.setOnItemClickListener((parent, view, position, id) -> {
            selectText= dataList.get(position);
        });


        deleteCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataList.remove(selectText);
                cityAdapter.notifyDataSetChanged();
            }
        });



//        public void onClick(View view) {
//            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                    .setAnchorView(R.id.fab)
//                    .setAction("Action", null).show();
//        }

    }
}