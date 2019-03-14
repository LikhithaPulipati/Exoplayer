package com.example.likhi.exoplayer;

import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;

import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

public class MainActivity extends AppCompatActivity {
    SimpleExoPlayerView simpleExoPlayerView;
    SimpleExoPlayer simpleExoPlayer;
    long currentposition;
    boolean playwhenready;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        simpleExoPlayerView = findViewById(R.id.exoplayerview);
        /*if (savedInstanceState == null) {
            startPlayer();

            // savedInstanceState.getLong();
        } else {
            currentposition = savedInstanceState.getLong("currentposition");
            playwhenready = savedInstanceState.getBoolean("playwhenReady");
            simpleExoPlayer.seekTo(currentposition);
            simpleExoPlayer.setPlayWhenReady(playwhenready);

        }*/

        startPlayer();

    }

    public void startPlayer() {
        simpleExoPlayer = ExoPlayerFactory.newSimpleInstance(new DefaultRenderersFactory(this),
                new DefaultTrackSelector(), new DefaultLoadControl());
        Uri videouri = Uri.parse("http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4\n");
        String useragent = Util.getUserAgent(this, "Exoplayerexample");
        MediaSource mediaSource = new ExtractorMediaSource(videouri,
                new DefaultDataSourceFactory(this, useragent, null),
                new DefaultExtractorsFactory(), null, null);
        //simpleExoPlayerView.setPlayer(simpleExoPlayer);
        simpleExoPlayer.prepare(mediaSource);
        simpleExoPlayer.setPlayWhenReady(true);
        simpleExoPlayer.seekTo(currentposition);
        simpleExoPlayerView.setPlayer(simpleExoPlayer);

    }

    public void stopPlayer() {
        if (simpleExoPlayer != null) {
            currentposition = simpleExoPlayer.getCurrentPosition();
            simpleExoPlayer.release();
            simpleExoPlayer.stop();
            simpleExoPlayer = null;
        }


    }

    @Override
    protected void onStart() {
        super.onStart();
        if(Util.SDK_INT<=23){
            startPlayer();;
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        startPlayer();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (Util.SDK_INT <=23) {
            stopPlayer();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (Util.SDK_INT >23 ) {
            stopPlayer();
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (Util.SDK_INT <= 23 ) {
            startPlayer();

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopPlayer();
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        outState.putLong("currentposition",
                Long.parseLong(String.valueOf(simpleExoPlayer.getCurrentPosition())));
        outState.putBoolean("playwhenready", simpleExoPlayer.getPlayWhenReady());
    }
}
