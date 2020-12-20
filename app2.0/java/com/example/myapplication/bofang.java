package com.example.myapplication;


import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Timer;
import java.util.TimerTask;

public class bofang extends AppCompatActivity {
    TextView urlst,name1;
    Button click;
    private TextView musicLength,musicCur;
    static MediaPlayer mediaPlayer;
    private SeekBar seekBar;
    private Timer timer;
    private boolean isSeekBarChanging;//互斥变量，防止进度条与定时器冲突。
    private int currentPosition;//当前音乐播放的进度
    SimpleDateFormat format;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.online);
        seekBar = (SeekBar) findViewById(R.id.seekBar);
        seekBar.setOnSeekBarChangeListener(new MySeekBar());
        urlst=findViewById(R.id.urlst);
        name1=findViewById(R.id.name);
        musicLength = (TextView) findViewById(R.id.music_length);
        musicCur = (TextView) findViewById(R.id.music_cur);
        click=findViewById(R.id.click);
        click.setText("播放");
        format = new SimpleDateFormat("mm:ss");
        String id=getIntent().getStringExtra("id");
        String name=getIntent().getStringExtra("name");
        urlst.setText("歌曲id:"+id);
        name1.setText("歌曲:"+name);
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
            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                public void onPrepared(MediaPlayer mp) {
                    seekBar.setMax(mediaPlayer.getDuration());
                    musicLength.setText(format.format(mediaPlayer.getDuration()) + "");
                    musicCur.setText("00:00");

                }
            });
            mediaPlayer.seekTo(currentPosition);
            timer = new Timer();
            timer.schedule(new TimerTask() {

                Runnable updateUI = new Runnable() {
                    @Override
                    public void run() {
                        musicCur.setText(format.format(mediaPlayer.getCurrentPosition())+"");
                    }
                };
                @Override
                public void run() {
                    if(!isSeekBarChanging){
                        seekBar.setProgress(mediaPlayer.getCurrentPosition());
                        runOnUiThread(updateUI);
                    }
                }
            },0,50);
        }
        else{
            try {
                Toast.makeText(bofang.this, "切换歌曲为"+name, Toast.LENGTH_SHORT).show();
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
    public class MySeekBar implements SeekBar.OnSeekBarChangeListener {

        public void onProgressChanged(SeekBar seekBar, int progress,
                                      boolean fromUser) {
        }

        /*滚动时,应当暂停后台定时器*/
        public void onStartTrackingTouch(SeekBar seekBar) {
            isSeekBarChanging = true;
        }
        /*滑动结束后，重新设置值*/
        public void onStopTrackingTouch(SeekBar seekBar) {
            isSeekBarChanging = false;
            mediaPlayer.seekTo(seekBar.getProgress());
        }
    }
}

