package com.example.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class interesting extends Activity {

    int[] insid = {R.id.in1, R.id.in2, R.id.in3, R.id.in4, R.id.in5, R.id.in6, R.id.in7, R.id.in8, R.id.in9, R.id.in10, R.id.in11, R.id.in12
            , R.id.in13, R.id.in14, R.id.in15, R.id.in16, R.id.in17, R.id.in18, R.id.in19, R.id.in20, R.id.in21, R.id.in22};
    boolean[] flag = new boolean[25];
    int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interesting);
        for (int i = 0; i < 25; i++) {
            flag[i] = false;
        }
        View.OnClickListener interesting = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button b = (Button) v;
                for (int i = 0; i < insid.length; i++) {
                    if (insid[i] == b.getId()) {
                        index = i;
                        break;
                    }
                }
                if (flag[index]) {
                    flag[index] = false;
                } else {
                    flag[index] = true;
                }
                b.setActivated(flag[index]);
            }

        };

        for (int id : insid) {
            Button b = (Button) findViewById(id);
            b.setOnClickListener(interesting);
        }

        Button ins_choose1 = (Button) findViewById(R.id.in23);
        ins_choose1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //关闭当前页面
            }
        });

        Button ins_choose2 = (Button) findViewById(R.id.in24);
        ins_choose2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //传递数组为true的位置(感兴趣的)
                String s = "";
                for (int i = 0; i < 22; i++) {
                    if (flag[i]) {
                        s += ((Button) findViewById(insid[i])).getText().toString()+" ";
                    }
                }
                System.out.println(s);
            }
        });
    }
}
