package com.example.samridhi.miwok;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class Colors extends AppCompatActivity {
private MediaPlayer mMediaPlayer;
private AudioManager mAudioPlayer;

    //  private Handler mHandler = new Handler();
    AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener =
            new AudioManager.OnAudioFocusChangeListener() {

                public void onAudioFocusChange(int focusChange) {
                    if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                        // Permanent loss of audio focus
                        // Pause playback immediately
                        releaseMediaPlayer();
                        // Wait 30 seconds before stopping playback

                    }
                    else if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT  || focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                        // Pause playback
                        mMediaPlayer.pause();
                        mMediaPlayer.seekTo(0);

                    } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                        // Your app has been granted audio focus again
                        // Raise volume to normal, restart playback if necessary
                        mMediaPlayer.start();
                    }
                }
            };


    private MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            // Now that the sound file has finished playing, release the media player resources.
            releaseMediaPlayer();
        }
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colors);
        final ArrayList<word> words = new ArrayList<word>();

        mAudioPlayer = (AudioManager) getSystemService(Context.AUDIO_SERVICE);


        words.add(new word("red", "weṭeṭṭi", R.drawable.color_red,R.raw.color_red));
        words.add(new word("mustard yellow", "chiwiiṭә", R.drawable.color_mustard_yellow,R.raw.color_mustard_yellow));
        words.add(new word("dusty yellow", "ṭopiisә", R.drawable.color_dusty_yellow,R.raw.color_dusty_yellow));
        words.add(new word("green", "chokokki", R.drawable.color_green,R.raw.color_green));
        words.add(new word("brown", "ṭakaakki", R.drawable.color_brown,R.raw.color_brown));
        words.add(new word("gray", "ṭopoppi", R.drawable.color_gray,R.raw.color_gray));
        words.add(new word("black", "kululli", R.drawable.color_black,R.raw.color_black));
        words.add(new word("white", "kelelli", R.drawable.color_white,R.raw.color_white));

        wordAdapter adapter = new wordAdapter(this, words,R.color.colorPrimary);
        ListView listView = (ListView) findViewById(R.id.category_colors);
listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                word w = words.get(position);
                releaseMediaPlayer();

                //We have gained audio focus

                // Request audio focus for playback
                int result = mAudioPlayer.requestAudioFocus(mOnAudioFocusChangeListener,
                        // Use the music stream.
                        AudioManager.STREAM_MUSIC,
                        // Request permanent focus.
                        AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    // Start playback

                    mMediaPlayer = MediaPlayer.create(Colors.this, w.getAudioResourceId());
                    mMediaPlayer.start();
                    mMediaPlayer.setOnCompletionListener(mCompletionListener);
                }
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        // When the activity is stopped, release the media player resources because we won't
        // be playing any more sounds.
        releaseMediaPlayer();
    }

    private void releaseMediaPlayer() {
        // If the media player is not null, then it may be currently playing a sound.
        if (mMediaPlayer != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            mMediaPlayer.release();
            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            mMediaPlayer = null;
            mAudioPlayer.abandonAudioFocus(mOnAudioFocusChangeListener);
        }
    }
}
