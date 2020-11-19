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
public class frame_2 extends Fragment {

    private String content;
    public frame_2(String content) {
        this.content = content;
    }
    private Button button2,button3;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frame_2_bk,container,false);
        button2=view.findViewById(R.id.bt_choose);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), gedan.class);
                startActivity(intent);
            }
        });
        button3=view.findViewById(R.id.startsport);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), main1.class);
                startActivity(intent);
            }
        });
        return view;
    }
}
