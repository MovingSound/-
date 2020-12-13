package com.example.myapplication;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

public class bofang extends AppCompatActivity {
    EditText urlst,name1;
    Button click;
    static MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.online);
        urlst=findViewById(R.id.urlst);
        name1=findViewById(R.id.name);
        click=findViewById(R.id.click);
        click.setText("播放");
        String id=getIntent().getStringExtra("id");
        String name=getIntent().getStringExtra("name");
        urlst.setText("播放歌曲id为"+id);
        name1.setText("播放歌曲名称为"+name);
        String mp3_url=getIntent().getStringExtra("mp3_url");
        if(mediaPlayer==null)
        {
            mediaPlayer = new MediaPlayer();
            try {
                mediaPlayer.setDataSource(mp3_url);
            } catch (IOException e) {
                e.printStackTrace();
            }
            mediaPlayer.prepareAsync();
        }
        else{
            try {
                mediaPlayer.reset();
                mediaPlayer.setDataSource(mp3_url);
                mediaPlayer.prepareAsync();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(mediaPlayer!=null)
                {

                    if(mediaPlayer.isPlaying())
                    {
                        mediaPlayer.pause();
                        click.setText("继续");
                    }
                    else
                    {
                        mediaPlayer.start();
                        click.setText("暂停");
                    }
                }
            }
        });
    }
}

