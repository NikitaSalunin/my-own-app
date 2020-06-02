package com.example.myownapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.snackbar.Snackbar;

import java.util.Random;

public class Main2Activity extends AppCompatActivity {
    TextView display;
    private View add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final Random random = new Random();
        display = (TextView) findViewById(R.id.textView2);

        Button fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG).show();
            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            int [] array = new int [5];
            public void onClick(View v) {
                String str = "";
                for (int i=0;i<array.length;i++){
                    array[i] = (int) Math.random()*10;
                    str += (array[i]  + "\n");
                }
                display.setText(str);

            }
        });

    }


}
