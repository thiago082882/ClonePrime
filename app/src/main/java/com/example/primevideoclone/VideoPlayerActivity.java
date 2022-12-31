package com.example.primevideoclone;


import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ProgressBar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.LoadControl;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.PlaybackParameters;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.metadata.Metadata;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

import java.util.List;


public class VideoPlayerActivity extends AppCompatActivity {

    private PlayerView playerView;
    SimpleExoPlayer simpleExoPlayer;
    BandwidthMeter bandwidthMeter;
    private DefaultTrackSelector trackSelector;
    private static final String FILE_URL = "";
    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_video_player);

        playerView = findViewById(R.id.exo_player);
        progressBar = findViewById(R.id.progress_bar);
        try {
            setUpExoPlayer(getIntent().getStringExtra("url"));
        } catch (NullPointerException e) {

        }


        simpleExoPlayer.addListener(new Player.EventListener() {
            @Override
            public void onTimelineChanged(Timeline timeline, int reason) {
                Player.EventListener.super.onTimelineChanged(timeline, reason);
            }

            @Override
            public void onTimelineChanged(Timeline timeline, @Nullable Object manifest, int reason) {
                Player.EventListener.super.onTimelineChanged(timeline, manifest, reason);
            }

            @Override
            public void onMediaItemTransition(@Nullable MediaItem mediaItem, int reason) {
                Player.EventListener.super.onMediaItemTransition(mediaItem, reason);
            }

            @Override
            public void onTracksChanged(TrackGroupArray trackGroups, TrackSelectionArray trackSelections) {
                Player.EventListener.super.onTracksChanged(trackGroups, trackSelections);
            }

            @Override
            public void onStaticMetadataChanged(List<Metadata> metadataList) {
                Player.EventListener.super.onStaticMetadataChanged(metadataList);
            }

            @Override
            public void onIsLoadingChanged(boolean isLoading) {
                Player.EventListener.super.onIsLoadingChanged(isLoading);
            }

            @Override
            public void onLoadingChanged(boolean isLoading) {
                Player.EventListener.super.onLoadingChanged(isLoading);
            }

            @Override
            public void onPlayerStateChanged(boolean playWhenReady, int playbackState) {
                Player.EventListener.super.onPlayerStateChanged(playWhenReady, playbackState);
                if (playbackState == Player.STATE_BUFFERING) {

                    progressBar.setVisibility(View.VISIBLE);
                } else if (playbackState == Player.STATE_READY) {
                    progressBar.setVisibility(View.GONE);
                }
            }

            @Override
            public void onPlaybackStateChanged(int state) {
                Player.EventListener.super.onPlaybackStateChanged(state);
            }

            @Override
            public void onPlayWhenReadyChanged(boolean playWhenReady, int reason) {
                Player.EventListener.super.onPlayWhenReadyChanged(playWhenReady, reason);
            }

            @Override
            public void onPlaybackSuppressionReasonChanged(int playbackSuppressionReason) {
                Player.EventListener.super.onPlaybackSuppressionReasonChanged(playbackSuppressionReason);
            }

            @Override
            public void onIsPlayingChanged(boolean isPlaying) {
                Player.EventListener.super.onIsPlayingChanged(isPlaying);
            }

            @Override
            public void onRepeatModeChanged(int repeatMode) {
                Player.EventListener.super.onRepeatModeChanged(repeatMode);
            }

            @Override
            public void onShuffleModeEnabledChanged(boolean shuffleModeEnabled) {
                Player.EventListener.super.onShuffleModeEnabledChanged(shuffleModeEnabled);
            }

            @Override
            public void onPlayerError(ExoPlaybackException error) {
                Player.EventListener.super.onPlayerError(error);
            }

            @Override
            public void onPositionDiscontinuity(int reason) {
                Player.EventListener.super.onPositionDiscontinuity(reason);
            }

            @Override
            public void onPlaybackParametersChanged(PlaybackParameters playbackParameters) {
                Player.EventListener.super.onPlaybackParametersChanged(playbackParameters);
            }

            @Override
            public void onSeekProcessed() {
                Player.EventListener.super.onSeekProcessed();
            }

            @Override
            public void onExperimentalOffloadSchedulingEnabledChanged(boolean offloadSchedulingEnabled) {
                Player.EventListener.super.onExperimentalOffloadSchedulingEnabledChanged(offloadSchedulingEnabled);
            }

            @Override
            public void onExperimentalSleepingForOffloadChanged(boolean sleepingForOffload) {
                Player.EventListener.super.onExperimentalSleepingForOffloadChanged(sleepingForOffload);
            }

            @Override
            public void onEvents(Player player, Player.Events events) {
                Player.EventListener.super.onEvents(player, events);
            }
        });
    }

    private void setUpExoPlayer(String url) {


        // Load Controls
        LoadControl loadControl = new DefaultLoadControl();

        //BamdWidth meter
         bandwidthMeter = new DefaultBandwidthMeter();

      TrackSelector trackSelector = new DefaultTrackSelector(
        new AdaptiveTrackSelection.Factory()


        );





  simpleExoPlayer= ExoPlayerFactory.newSimpleInstance(this,trackSelector,loadControl);
  playerView.setPlayer(simpleExoPlayer);
        DataSource.Factory dataSourceFactory = new DefaultDataSourceFactory(this, Util.getUserAgent(this, this.getString(R.string.app_name)));
        MediaSource mediaSource = new ExtractorMediaSource.Factory(dataSourceFactory).createMediaSource(Uri.parse(url));
        simpleExoPlayer.prepare(mediaSource);
        simpleExoPlayer.setPlayWhenReady(true);


}


    @Override
    protected void onDestroy() {
        super.onDestroy();
        simpleExoPlayer.release();
    }
}