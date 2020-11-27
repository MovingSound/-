package com.example.myapplication;

import android.animation.ObjectAnimator;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import static java.lang.Integer.parseInt;

public class Music_Activity extends AppCompatActivity implements View.OnClickListener {
    // 定义音乐的播放状态，0x11代表没有播放；0x12代表正在播放；0x13代表暂停
    int status = 0x11;
    private static SeekBar sb;
    private static TextView tv_progress, tv_total, name_song;
    private ObjectAnimator animator;
    private MusicService.MusicControl musicControl;
    String name;
    Intent intent1, intent2;
    MyServiceConn conn;
    private boolean isUnbind = false;//记录服务是否被解绑
    private boolean qh_status = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);
        intent1 = getIntent();
        init();
        Button fanhui1= findViewById(R.id.fanhui1);
        fanhui1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Music_Activity.this, main4.class);
                startActivity(intent);
            }
        });
    }

    private void init() {
        tv_progress = (TextView) findViewById(R.id.tv_progress);
        tv_total = (TextView) findViewById(R.id.tv_total);
        sb = (SeekBar) findViewById(R.id.sb);
        name_song = (TextView) findViewById(R.id.song_name);

        findViewById(R.id.pause).setOnClickListener(this);
        findViewById(R.id.pre_song2).setOnClickListener(this);
        findViewById(R.id.next_song2).setOnClickListener(this);

        name = intent1.getStringExtra("name");
        name_song.setText(name);
        intent2 = new Intent(this, MusicService.class);//创建意图对象
        conn = new MyServiceConn();//创建服务连接对象
        bindService(intent2, conn, BIND_AUTO_CREATE);//绑定服务
        //为滑动条添加事件监听
        sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                //进度条改变时，会调用此方法
                if (progress == seekBar.getMax()) {//当滑动条到末端时，结束动画
                    int p=0;
                    String position = intent1.getStringExtra("position");
                    String size = intent1.getStringExtra("size");
                    if(parseInt(position)<parseInt(size)-1){
                        p = parseInt(position) + 1;
                    }
                        System.out.println(p);
                        intent1.putExtra("position", String.valueOf(p));
                        ImageView iv_music = (ImageView) findViewById(R.id.iv_music);
                        iv_music.setImageResource(frag1.icons[p]);
                        String[] names = intent1.getStringArrayExtra("names");
                        name_song = (TextView) findViewById(R.id.song_name);
                        name_song.setText(names[p]);
                        musicControl.play(p);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {//滑动条开始滑动时调用
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {//滑动条停止滑动时调用
                //根据拖动的进度改变音乐播放进度
                int progress = seekBar.getProgress();//获取seekBar的进度
                musicControl.seekTo(progress);//改变播放进度
            }
        });
        ImageView iv_music = (ImageView) findViewById(R.id.iv_music);
        String position = intent1.getStringExtra("position");
        int i = parseInt(position);
        iv_music.setImageResource(frag1.icons[i]);

        animator = ObjectAnimator.ofFloat(iv_music, "rotation", 0f, 360.0f);
        animator.setDuration(10000);//动画旋转一周的时间为10秒
        animator.setInterpolator(new LinearInterpolator());//匀速
        animator.setRepeatCount(-1);//-1表示设置动画无限循环
    }


    public static Handler handler = new Handler() {//创建消息处理器对象
        //在主线程中处理从子线程发送过来的消息
        @Override
        public void handleMessage(Message msg) {
            Bundle bundle = msg.getData();//获取从子线程发送过来的音乐播放进度
            int duration = bundle.getInt("duration");
            int currentPosition = bundle.getInt("currentPosition");
            sb.setMax(duration);
            sb.setProgress(currentPosition);
            //歌曲总时长
            int minute = duration / 1000 / 60;
            int second = duration / 1000 % 60;
            String strMinute = null;
            String strSecond = null;
            if (minute < 10) {//如果歌曲的时间中的分钟小于10
                strMinute = "0" + minute;//在分钟的前面加一个0
            } else {
                strMinute = minute + "";
            }
            if (second < 10) {//如果歌曲中的秒钟小于10
                strSecond = "0" + second;//在秒钟前面加一个0
            } else {
                strSecond = second + "";
            }
            tv_total.setText(strMinute + ":" + strSecond);
            //歌曲当前播放时长
            minute = currentPosition / 1000 / 60;
            second = currentPosition / 1000 % 60;
            if (minute < 10) {//如果歌曲的时间中的分钟小于10
                strMinute = "0" + minute;//在分钟的前面加一个0
            } else {
                strMinute = minute + " ";
            }
            if (second < 10) {//如果歌曲中的秒钟小于10
                strSecond = "0" + second;//在秒钟前面加一个0
            } else {
                strSecond = second + " ";
            }
            tv_progress.setText(strMinute + ":" + strSecond);
        }
    };

    class MyServiceConn implements ServiceConnection {//用于实现连接服务

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            musicControl = (MusicService.MusicControl) service;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    }

    private void unbind(boolean isUnbind) {
        if (!isUnbind) {//判断服务是否被解绑
            musicControl.pausePlay();//暂停播放音乐
            unbindService(conn);//解绑服务
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.pause:
//                System.out.println("点击了");
                ImageButton ib = (ImageButton) findViewById(R.id.pause);
                if (status == 0x11) {
                    // 准备、并播放音乐
                    String pos = intent1.getStringExtra("position");
                    int pi = parseInt(pos);
                    musicControl.play(pi);
                    animator.start();
                    status = 0x12;
                    ib.setBackgroundResource(R.drawable.pause);
                }
                // 原来处于播放状态
                else if (status == 0x12) {
                    // 暂停
                    musicControl.pausePlay();
                    animator.pause();
                    // 改变为暂停状态
                    status = 0x13;
                    ib.setBackgroundResource(R.drawable.bofangqi);
                }
                // 原来处于暂停状态
                else if (status == 0x13) {
                    // 播放
                    musicControl.continuePlay();
                    animator.start();
                    // 改变状态
                    status = 0x12;
                    ib.setBackgroundResource(R.drawable.pause);
                }
                break;
            case R.id.pre_song2:
                ImageView iv_music = (ImageView) findViewById(R.id.iv_music);
                String position2 = intent1.getStringExtra("position");

                if (parseInt(position2) == 0) {
                    Toast.makeText(Music_Activity.this, "已经是第一首了", Toast.LENGTH_SHORT).show();
                    break;
                }
                intent1.putExtra("position", String.valueOf(parseInt(position2) - 1));
                int p2 = parseInt(position2) - 1;

                iv_music.setImageResource(frag1.icons[p2]);
                String[] names = intent1.getStringArrayExtra("names");
                name_song = (TextView) findViewById(R.id.song_name);
                name_song.setText(names[p2]);

                if(status==0x12){
                    musicControl.play(p2);
                    ImageButton ib3 = (ImageButton) findViewById(R.id.pause);
                    ib3.setBackgroundResource(R.drawable.pause);
                }
                else if(status==0x13) {
                    musicControl.continuePlay();
                    animator.start();
                    musicControl.play(p2);
                    status = 0x12;
                    ImageButton ib2 = (ImageButton) findViewById(R.id.pause);
                    ib2.setBackgroundResource(R.drawable.pause);
                }
                break;

            case R.id.next_song2:
                ImageView iv_music2 = (ImageView) findViewById(R.id.iv_music);
                String position3 = intent1.getStringExtra("position");
                String size = intent1.getStringExtra("size");

                if(parseInt(position3)==parseInt(size)-1){
                    Toast.makeText(Music_Activity.this, "已经是最后一首了", Toast.LENGTH_SHORT).show();
                    break;
                }

                intent1.putExtra("position", String.valueOf(parseInt(position3) + 1));
                int p3 = parseInt(position3) + 1;

                ImageView iv_music3 = (ImageView) findViewById(R.id.iv_music);
                iv_music3.setImageResource(frag1.icons[p3]);
                String[] names2 = intent1.getStringArrayExtra("names");
                name_song = (TextView) findViewById(R.id.song_name);
                name_song.setText(names2[p3]);

                if(status==0x12){
                    musicControl.play(p3);
                    ImageButton ib3 = (ImageButton) findViewById(R.id.pause);
                    ib3.setBackgroundResource(R.drawable.pause);
                }
                else if(status==0x13) {
                    musicControl.continuePlay();
                    animator.start();
                    musicControl.play(p3);
                    status = 0x12;
                    ImageButton ib3 = (ImageButton) findViewById(R.id.pause);
                    ib3.setBackgroundResource(R.drawable.pause);

            }
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbind(isUnbind);//解绑服务
    }
}


