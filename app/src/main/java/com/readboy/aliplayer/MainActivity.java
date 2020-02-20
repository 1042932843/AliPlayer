package com.readboy.aliplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.aliyun.player.AliPlayer;
import com.aliyun.player.AliPlayerFactory;
import com.aliyun.player.source.UrlSource;

public class MainActivity extends AppCompatActivity {

    AliPlayer player;
    TextView play;
    EditText editText;
    SurfaceView surfaceView;
    String url="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        play=findViewById(R.id.play);
        surfaceView=findViewById(R.id.surface_view);


        editText=findViewById(R.id.editText);
        editText.setText("rtsp://192.168.3.114:8554/alc");//rtsp貌似不支持
        surfaceView.getHolder().addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder surfaceHolder) {
                player= AliPlayerFactory.createAliPlayer(MainActivity.this);
                player.setDisplay(surfaceView.getHolder());

            }

            @Override
            public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
                player.setDisplay(null);
            }
        });
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                play( editText.getText().toString());
            }
        });
    }

    public void play(String url){
        if(url.isEmpty()){
            url="http://img1.readboy.com/aircourse/0218/01202002151715099225.mp4";
        }
        UrlSource urlSource=new UrlSource();
        urlSource.setUri(url);
        player.getConfig().mMaxDelayTime=100;
        player.setAutoPlay(true);
        player.setDataSource(urlSource);
        player.prepare();
        player.start();
    }
}
