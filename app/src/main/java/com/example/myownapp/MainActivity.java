package com.example.myownapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

private EditText pass;
private Button button;
private RatingBar rating;
private TextView text_show;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addListenerOnButton();
    }

    public void addListenerOnButton() {
        button= (Button) findViewById(R.id.button);
        button.setOnClickListener(
        new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                button.setText("Done");
                Toast.makeText(
                        MainActivity.this, pass.getText(),
                        Toast.LENGTH_SHORT
                ).show();
            }


        });
button.setOnClickListener(
        new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(".Main2Activity");
startActivity(intent);


        }

});
}
}
