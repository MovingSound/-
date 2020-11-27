package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class fankui extends AppCompatActivity implements View.OnClickListener{

    private TextView heart_1;
    private TextView heart_2;
    private TextView heart_3;
    private TextView heart_4;
    private TextView heart_5;
    private TextView face_1;
    private TextView face_2;
    private TextView face_3;
    private TextView face_4;
    private TextView face_5;
    private Button b_1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fankui);
        heart_1 = findViewById(R.id.heart_1);
        heart_2 = findViewById(R.id.heart_2);
        heart_3 = findViewById(R.id.heart_3);
        heart_4 = findViewById(R.id.heart_4);
        heart_5 = findViewById(R.id.heart_5);
        face_1 = findViewById(R.id.face_1);
        face_2 = findViewById(R.id.face_2);
        face_3 = findViewById(R.id.face_3);
        face_4 = findViewById(R.id.face_4);
        face_5 = findViewById(R.id.face_5);
        b_1 = findViewById(R.id.b_1);

        heart_1.setOnClickListener(this);
        heart_2.setOnClickListener(this);
        heart_3.setOnClickListener(this);
        heart_4.setOnClickListener(this);
        heart_5.setOnClickListener(this);
        face_1.setOnClickListener(this);
        face_2.setOnClickListener(this);
        face_3.setOnClickListener(this);
        face_4.setOnClickListener(this);
        face_5.setOnClickListener(this);
        b_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(fankui.this, "感谢反馈", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(fankui.this,main4.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.heart_1:
                heart_1.setSelected(true);
                face_1.setSelected(false);
                break;
            case R.id.heart_2:
                heart_2.setSelected(true);
                face_2.setSelected(false);
                break;
            case R.id.heart_3:
                heart_3.setSelected(true);
                face_3.setSelected(false);
                break;
            case R.id.heart_4:
                heart_4.setSelected(true);
                face_4.setSelected(false);
                break;
            case R.id.heart_5:
                heart_5.setSelected(true);
                face_5.setSelected(false);
                break;
            case R.id.face_1:
                face_1.setSelected(true);
                heart_1.setSelected(false);
                break;
            case R.id.face_2:
                face_2.setSelected(true);
                heart_2.setSelected(false);
                break;
            case R.id.face_3:
                face_3.setSelected(true);
                heart_3.setSelected(false);
                break;
            case R.id.face_4:
                face_4.setSelected(true);
                heart_4.setSelected(false);
                break;
            case R.id.face_5:
                face_5.setSelected(true);
                heart_5.setSelected(false);
                break;
        }
    }
}
