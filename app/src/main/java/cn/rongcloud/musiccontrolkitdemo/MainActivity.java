package cn.rongcloud.musiccontrolkitdemo;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import cn.rongcloud.musiccontrolkitdemo.musiccontrolkit.MusicControlKitActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void showMusicControlKit(View view) {
        MusicControlKitActivity.launch(this);
    }
}