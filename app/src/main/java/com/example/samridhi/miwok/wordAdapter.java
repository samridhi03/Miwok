package com.example.samridhi.miwok;

import android.app.Activity;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Samridhi on 11-01-2018.
 */

public class wordAdapter extends ArrayAdapter<word> {
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        word currentAndroidWord = getItem(position);

        View playicon = listItemView.findViewById(R.id.play_icon);


        TextView nameTextView = (TextView) listItemView.findViewById(R.id.default_text_view);
        nameTextView.setText(currentAndroidWord.getmDefaultTranslation());

        TextView numberTextView = (TextView) listItemView.findViewById(R.id.miwok_text_view);
        numberTextView.setText(currentAndroidWord.getmMiwokTranslation());

        ImageView imageview = (ImageView) listItemView.findViewById(R.id.image);

        if(currentAndroidWord.hasImage()){
            imageview.setImageResource(currentAndroidWord.ImageResourceId());
            imageview.setVisibility(View.VISIBLE);
        } else {
            // Otherwise hide the ImageView (set visibility to GONE)
            imageview.setVisibility(View.GONE);

        }



return listItemView;

    }
private int mcolorsResourceId;

    public wordAdapter(Activity context, ArrayList<word> androidWords, int colorsResourceId)
{
    super(context,0,androidWords);
    mcolorsResourceId=colorsResourceId;
}



}
