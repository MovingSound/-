package com.flag.httpmusic;
import androidx.appcompat.app.AppCompatActivity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import java.io.IOException;
public class bofang extends AppCompatActivity {
    EditText urlst;
    Button click;
    MediaPlayer mediaPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        urlst=findViewById(R.id.urlst);
        click=findViewById(R.id.click);
        click.setText("播放");
        String t=getIntent().getStringExtra("t");
        urlst.setText("相似歌曲id为"+t);
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
        click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(mediaPlayer!=null)
                {

                    if(mediaPlayer.isPlaying())
                    {
                        mediaPlayer.pause();
                    }
                    else
                    {
                        mediaPlayer.start();
                    }
                }
            }
        });
    }
}

