package com.example.listycity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    ListView cityList;
    ArrayAdapter<String> cityAdapter;
    ArrayList<String> dataList;
    Button btnAdd, btnDelete, btnConfirm;
    EditText etCity;
    LinearLayout bottomBar;
    int selectedPos = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cityList = findViewById(R.id.city_list);
        btnAdd = findViewById(R.id.btn_add);
        btnDelete = findViewById(R.id.btn_delete);
        bottomBar = findViewById(R.id.bottom_bar);
        etCity = findViewById(R.id.et_city);
        btnConfirm = findViewById(R.id.btn_confirm);

        String []cities = {"Edmonton", "Vancouver"};

        dataList = new ArrayList<>();
        dataList.addAll(Arrays.asList(cities));

        cityAdapter = new ArrayAdapter<>(this, R.layout.content, R.id.content_view, dataList);
        cityList.setAdapter(cityAdapter);

        cityList.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        final int[] selectedPos = {-1};
        cityList.setOnItemClickListener((parent, view, position, id) -> {
            selectedPos[0] = position;
        });

        btnAdd.setOnClickListener(v -> {
            bottomBar.setVisibility(View.VISIBLE);
            etCity.setText("");
            etCity.requestFocus();
        });

        btnConfirm.setOnClickListener(v -> {
            String newCity = etCity.getText().toString().trim();
            dataList.add(newCity);
            cityAdapter.notifyDataSetChanged();
            bottomBar.setVisibility(View.GONE);
        });

        btnDelete.setOnClickListener(v -> {
            if (selectedPos[0] == -1) return;
            dataList.remove(selectedPos[0]);
            cityAdapter.notifyDataSetChanged();
            cityList.clearChoices();
            selectedPos[0] = -1;
        });
    }
}