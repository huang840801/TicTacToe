package com.huang.hello.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button_A = findViewById(R.id.button_A);
        button_A.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, page2Activity.class);
                startActivity(intent);
            }
        });
        Button button_B = findViewById(R.id.button_B);
        button_B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, page3Activity.class);
                startActivity(intent);
            }
        });
        Button button_C = findViewById(R.id.button_C);
        button_C.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, page4Activity.class);
                startActivity(intent);
            }
        });



    }
}
