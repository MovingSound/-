package com.example.myapplication;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

@SuppressLint("ValidFragment")
public class frame_1 extends Fragment {
    private String content;
    public frame_1(String content) {
        this.content = content;
    }
    private  Button button1,button2;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.frame_1_bk,container,false);
        button1=view.findViewById(R.id.pic_chose_t3);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), heartrate.class);
                startActivity(intent);
            }
        });
        button2=view.findViewById(R.id.pic_chose_t5);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), main1.class);
                startActivity(intent);
            }
        });

        return view;
    }
}
