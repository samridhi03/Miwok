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

public class FamilyMembers extends AppCompatActivity {
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
        setContentView(R.layout.activity_family_members);

        mAudioPlayer = (AudioManager) getSystemService(Context.AUDIO_SERVICE);


        final ArrayList<word> words = new ArrayList<word>();
        words.add(new word("father", "әpә", R.drawable.family_father,R.raw.family_father));
        words.add(new word("mother", "әṭa", R.drawable.family_mother,R.raw.family_mother));
        words.add(new word("son", "angsi", R.drawable.family_son,R.raw.family_son));
        words.add(new word("daughter", "tune", R.drawable.family_daughter,R.raw.family_daughter));
        words.add(new word("older brother", "taachi", R.drawable.family_older_brother,R.raw.family_older_brother));
        words.add(new word("younger brother", "chalitti", R.drawable.family_younger_brother,R.raw.family_younger_brother));
        words.add(new word("older sister", "teṭe", R.drawable.family_older_sister,R.raw.family_older_sister));
        words.add(new word("younger sister", "kolliti", R.drawable.family_younger_sister,R.raw.family_younger_sister));
        words.add(new word("grandmother ", "ama", R.drawable.family_grandmother,R.raw.family_grandmother));
        words.add(new word("grandfather", "paapa", R.drawable.family_grandfather,R.raw.family_grandfather));

        wordAdapter adapter = new wordAdapter(this, words,R.color.colorAccent);
        ListView listView = (ListView) findViewById(R.id.family_members);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                word w = words.get(position);
                //We have gained audio focus

                // Request audio focus for playback
                int result = mAudioPlayer.requestAudioFocus(mOnAudioFocusChangeListener,
                        // Use the music stream.
                        AudioManager.STREAM_MUSIC,
                        // Request permanent focus.
                        AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    // Start playback

                    mMediaPlayer = MediaPlayer.create(FamilyMembers.this, w.getAudioResourceId());
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