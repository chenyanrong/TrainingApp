package com.tonychen.trainingapp.view.itemact;

import android.os.Bundle;

import com.tonychen.trainingapp.R;
import com.tonychen.trainingapp.view.base.BaseTitleActivity;

import tv.danmaku.ijk.media.player.IjkMediaPlayer;


public class IjkPlayerActivity extends BaseTitleActivity {

    private static final String VIDEO_URL = "http://flv2.bn.netease.com/videolib3/1611/28/GbgsL3639/SD/movie_index.m3u8";
    private static final String VIDEO_HD_URL = "http://flv2.bn.netease.com/videolib3/1611/28/GbgsL3639/HD/movie_index.m3u8";
    private static final String IMAGE_URL = "http://vimg2.ws.126.net/image/snapshot/2016/11/I/M/VC62HMUIM.jpg";

    private IjkMediaPlayer mVideoView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ijk_player);
    }

    @Override
    protected void initView() {
        super.initView();
//        mVideoView = (IjkMediaPlayer) findViewById(R.id.video_view);
//        try {
//            mVideoView.setVideoURI(Uri.parse(VIDEO_URL));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        mVideoView.start();
    }
}
