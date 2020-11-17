package com.example.dsmusic;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {

    private Button mBtChoose;
    private Button mBtReturn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBtChoose = findViewById(R.id.bt_choose);
        mBtChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, test1.class);
                startActivity(intent);
            }
        });
        mBtReturn = findViewById(R.id.bt_return);
        mBtReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, test1.class);
                startActivity(intent);
            }
        });
    }
    public void endSport(View view) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setMessage("你真的要结束运动吗？");
        dialog.setPositiveButton("确认",click1);
        dialog.setNegativeButton("取消", click2);
        AlertDialog alertDialog1 = dialog.create();
        alertDialog1.show();
    }
    private DialogInterface.OnClickListener click1=new DialogInterface.OnClickListener()
    {
        @Override
        public void onClick(DialogInterface arg0,int arg1)
        {
            Intent intent = new Intent(MainActivity.this, test1.class);
            startActivity(intent);
        }
    };
    private DialogInterface.OnClickListener click2=new DialogInterface.OnClickListener()
    {   @Override
         public void onClick(DialogInterface arg0,int arg1)
        {
            arg0.cancel();
        }
    };

}
