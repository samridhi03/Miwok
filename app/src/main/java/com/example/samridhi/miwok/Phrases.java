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

public class Phrases extends AppCompatActivity {
private MediaPlayer mMediaPlayer ;
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
        setContentView(R.layout.activity_phrases);
        final ArrayList<word> words = new ArrayList<word>();

    mAudioPlayer = (AudioManager) getSystemService(Context.AUDIO_SERVICE);


    words.add(new word("Where are you going?", "minto wuksus",R.raw.phrase_where_are_you_going));
        words.add(new word("What is your name?", "tinnә oyaase'nә",R.raw.phrase_what_is_your_name));
        words.add(new word("My name is...", "oyaaset...",R.raw.phrase_my_name_is));
        words.add(new word("How are you feeling?", "michәksәs?",R.raw.phrase_how_are_you_feeling));
        words.add(new word("I’m feeling good.", "kuchi achit",R.raw.phrase_im_feeling_good));
        words.add(new word("Are you coming?", "әәnәs'aa?",R.raw.phrase_are_you_coming));
        words.add(new word("Yes, I’m coming.", "hәә’ әәnәm",R.raw.phrase_yes_im_coming));
        words.add(new word("I’m coming.", "әәnәm",R.raw.phrase_im_coming));
        words.add(new word("Let’s go.", "yoowutis",R.raw.phrase_lets_go));
        words.add(new word("Come here.", "әnni'nem",R.raw.phrase_come_here));

        wordAdapter adapter = new wordAdapter(this, words,R.color.colorAccent);
        ListView listView = (ListView) findViewById(R.id.phrases);
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

                    mMediaPlayer = MediaPlayer.create(Phrases.this, w.getAudioResourceId());
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
