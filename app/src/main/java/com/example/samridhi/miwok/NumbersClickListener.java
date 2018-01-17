package com.example.samridhi.miwok;

import android.view.View;
import android.widget.Toast;

/**
 * Created by Samridhi on 10-01-2018.
 */

public class NumbersClickListener implements View.OnClickListener{

    @Override
    public void onClick(View view) {
        Toast.makeText(view.getContext(),"Open the list of numbers",Toast.LENGTH_SHORT).show();
    }
}