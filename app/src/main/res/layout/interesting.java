package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class interesting extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interesting);
        Button b1 =  (Button) findViewById(R.id.p1b1);
        Button b2 =  (Button) findViewById(R.id.p1b2);
        Button b3 =  (Button) findViewById(R.id.p1b3);
        Button b4 =  (Button) findViewById(R.id.p1b4);
        Button b5 =  (Button) findViewById(R.id.p1b5);
        Button b6 =  (Button) findViewById(R.id.p1b6);
        Button b7 =  (Button) findViewById(R.id.p1b7);
        Button blogin= findViewById(R.id.blogin);
        blogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(interesting.this, MainActivity.class);
                startActivity(intent);
            }
        });


    }
}
