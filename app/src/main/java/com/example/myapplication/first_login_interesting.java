package com.example.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class first_login_interesting extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interesting);
        View.OnClickListener interesting = new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.p1b1:
                        System.out.println("click");
                        break;
                }
            }
        };
         Button p1b1 = (Button) findViewById(R.id.p1b1);
         p1b1.setOnClickListener(interesting);
    }
}
