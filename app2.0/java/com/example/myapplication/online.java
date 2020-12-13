package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

public class online extends AppCompatActivity {
    private Button mBtReturn;
    private  Button clickbtn ;
    private Button button1;
    private EditText search_box,ed;
    private String search,ed_text;

    private void getEditString(){
        search_box = (EditText) findViewById(R.id.search_box);
        ed = (EditText) findViewById(R.id.ed);
        search=search_box.getText().toString().trim();
        ed_text=ed.getText().toString().trim();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bf);
        final EditText editText1 =(EditText) findViewById (R.id.ed);
        final EditText editText2 =(EditText) findViewById (R.id.search_box);
        button1 = (Button) findViewById(R.id.search_box_a);

        button1.setOnClickListener(new View.OnClickListener() {
                                       @Override
                                       public void onClick(View v) {
                                           getEditString();
                                           if(TextUtils.isEmpty(search)){
                                               Toast.makeText(online.this, "请输入歌曲名", Toast.LENGTH_SHORT).show();
                                           }
                                           else {
                                               String mp3_url=null;
                                               StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                                                       .detectDiskReads().detectDiskWrites().detectNetwork()
                                                       .penaltyLog().build());
                                               StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
                                                       .detectLeakedSqlLiteObjects().detectLeakedClosableObjects()
                                                       .penaltyLog().penaltyDeath().build());

                                               String c = editText2.getText().toString();
                                               httpurlconnection a= new httpurlconnection();//获取歌曲id的类
                                               String text = null;
                                               try {
                                                   text=a.httpurlconnection(c);//获取歌曲id
                                               } catch (IOException e) {
                                                   e.printStackTrace();
                                               }
                                               getmp3 mp3 = new getmp3();//获取id歌曲的url的类
                                               try {
                                                   mp3_url= mp3.get_url(text);//获取id歌曲的url
                                               } catch (IOException e) {
                                                   e.printStackTrace();
                                               }
                                               Intent intent = new Intent(online.this,bofang.class);
                                               //传入参数，账号，密码   //username、password是跳转目标页面获取它的参数   UserName、UserPassword是要传入的参数
                                               intent.putExtra("mp3_url",mp3_url);
                                               intent.putExtra("id",text);
                                               intent.putExtra("name",c);
                                               startActivity(intent);
                                           }
                                       }

                                   }
        );
        clickbtn =  (Button) findViewById(R.id.a);
        clickbtn.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v)  {
                                            //获取MP3url
                                            getEditString();
                                            if(TextUtils.isEmpty(ed_text)){
                                                Toast.makeText(online.this, "请输入歌曲名", Toast.LENGTH_SHORT).show();
                                            }
                                            else {
                                                String mp3_url=null;
                                                StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                                                        .detectDiskReads().detectDiskWrites().detectNetwork()
                                                        .penaltyLog().build());
                                                StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
                                                        .detectLeakedSqlLiteObjects().detectLeakedClosableObjects()
                                                        .penaltyLog().penaltyDeath().build());
                                                String c = editText1.getText().toString();
                                                httpurlconnection a= new httpurlconnection();//获取歌曲id的类
                                                String text = null;
                                                try {
                                                    text=a.httpurlconnection(c);//获取歌曲id
                                                } catch (IOException e) {
                                                    e.printStackTrace();
                                                }
                                                //String str = text.substring(2,8);
                                                Toast toast=Toast.makeText(getApplicationContext(), "歌曲ID："+text, Toast.LENGTH_SHORT);
                                                toast.show();
                                                compare cp = new compare();//获取推荐歌曲的id的类
                                                try {
                                                    String[] t = cp.find(text);//获取推荐歌曲id
                                                    toast =Toast.makeText(getApplicationContext(), "推荐歌曲ID："+t[0], Toast.LENGTH_SHORT);

                                                    toast =Toast.makeText(getApplicationContext(), "推荐歌曲名称："+t[1], Toast.LENGTH_SHORT);

                                                    getmp3 mp3 = new getmp3();//获取id歌曲的url的类
                                                    mp3_url= mp3.get_url(t[0]);//获取id歌曲的url
                                                    toast =Toast.makeText(getApplicationContext(), "推荐歌曲url："+mp3_url, Toast.LENGTH_SHORT);

                                                    Intent intent = new Intent(online.this,bofang.class);
                                                    //传入参数，账号，密码   //username、password是跳转目标页面获取它的参数   UserName、UserPassword是要传入的参数
                                                    intent.putExtra("mp3_url",mp3_url);
                                                    intent.putExtra("id",t[0]);
                                                    intent.putExtra("name",t[1]);
                                                    startActivity(intent);
                                                } catch (IOException e) {
                                                    e.printStackTrace();
                                                    toast =Toast.makeText(getApplicationContext(), "错误："+e.toString(), Toast.LENGTH_SHORT);
                                                    toast.show();
                                                }
                                            }



                                            //播放




                                        }



                                    }

        );
    }

}