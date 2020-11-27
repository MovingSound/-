package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class gedan extends AppCompatActivity {
    private int flag = 0;
    private Button clickbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gedan);

        View.OnClickListener listener = new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Button btn = findViewById(v.getId());
                switch (flag){
                    case 0:
                        btn.setActivated(false);
                        flag = 1;
                        break;
                    case 1:
                        btn.setActivated(true);
                        flag = 0;
                        break;
                }
            }
        };

        //语种控件
        Button buttona = (Button) findViewById(R.id.yuzhong_btn1);
        Button buttonb = (Button) findViewById(R.id.yuzhong_btn2);
        Button buttonc = (Button) findViewById(R.id.yuzhong_btn3);
        Button buttond = (Button) findViewById(R.id.yuzhong_btn4);

        //情感控件
        Button button21 = (Button) findViewById(R.id.qinggan_btn1);
        Button button22 = (Button) findViewById(R.id.qinggan_btn2);
        Button button23 = (Button) findViewById(R.id.qinggan_btn3);
        Button button24 = (Button) findViewById(R.id.qinggan_btn4);
        Button button25 = (Button) findViewById(R.id.qinggan_btn5);
        Button button26 = (Button) findViewById(R.id.qinggan_btn6);
        Button button27 = (Button) findViewById(R.id.qinggan_btn7);

        //风格控件(不小心命名成id=liuxing)
        Button button31 = (Button) findViewById(R.id.liuxing_btn1);
        Button button32 = (Button) findViewById(R.id.liuxing_btn2);
        Button button33 = (Button) findViewById(R.id.liuxing_btn3);
        Button button34 = (Button) findViewById(R.id.liuxing_btn4);
        Button button35 = (Button) findViewById(R.id.liuxing_btn5);
        Button button36 = (Button) findViewById(R.id.liuxing_btn6);
        Button button37 = (Button) findViewById(R.id.liuxing_btn7);
        Button button38 = (Button) findViewById(R.id.liuxing_btn8);
        Button button39 = (Button) findViewById(R.id.liuxing_btn9);
        Button button310 = (Button) findViewById(R.id.liuxing_btn10);
        Button button311 = (Button) findViewById(R.id.liuxing_btn11);
        Button button312 = (Button) findViewById(R.id.liuxing_btn12);
        Button button313 = (Button) findViewById(R.id.liuxing_btn13);
        Button button314 = (Button) findViewById(R.id.liuxing_btn14);

        //完成
        Button button41 = (Button) findViewById(R.id.wancheng_btn);

        //语种监听
        buttona.setOnClickListener(listener);
        buttonb.setOnClickListener(listener);
        buttonc.setOnClickListener(listener);
        buttond.setOnClickListener(listener);

        //情感监听
        button21.setOnClickListener(listener);
        button22.setOnClickListener(listener);
        button23.setOnClickListener(listener);
        button24.setOnClickListener(listener);
        button25.setOnClickListener(listener);
        button26.setOnClickListener(listener);
        button27.setOnClickListener(listener);

        //风格监听
        button31.setOnClickListener(listener);
        button32.setOnClickListener(listener);
        button33.setOnClickListener(listener);
        button34.setOnClickListener(listener);
        button35.setOnClickListener(listener);
        button36.setOnClickListener(listener);
        button37.setOnClickListener(listener);
        button38.setOnClickListener(listener);
        button39.setOnClickListener(listener);
        button310.setOnClickListener(listener);
        button311.setOnClickListener(listener);
        button312.setOnClickListener(listener);
        button313.setOnClickListener(listener);
        button314.setOnClickListener(listener);


        button41.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(gedan.this, main4.class);
                startActivity(intent);
            }
        });
    }
}
