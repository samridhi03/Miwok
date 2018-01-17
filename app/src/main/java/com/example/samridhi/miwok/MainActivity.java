package com.example.samridhi.miwok;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       //For NUMBERS

        TextView numbers = (TextView) findViewById(R.id.numbers_field);

        // Set a click listener on that View
        numbers.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the numbers category is clicked on.
            @Override
            public void onClick(View view) {
                // Create a new intent to open the {@link NumbersActivity}
                Intent numbersIntent = new Intent(MainActivity.this, Numbers.class);

                // Start the new activity
                startActivity(numbersIntent);
            }
        });





        //For family members

        TextView familyMember = (TextView) findViewById(R.id.family_text_view);

        // Set a click listener on that View
        familyMember.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the numbers category is clicked on.
            @Override
            public void onClick(View view) {
                // Create a new intent to open the {@link NumbersActivity}
                Intent familyMemberIntent = new Intent(MainActivity.this, FamilyMembers.class);
                // Start the new activity
                startActivity(familyMemberIntent);
            }
        });




        //for colors

        TextView col = (TextView) findViewById(R.id.color_text_view);

        // Set a click listener on that View
        col.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the numbers category is clicked on.
            @Override
            public void onClick(View view) {
                // Create a new intent to open the {@link NumbersActivity}
                Intent colorsIntent = new Intent(MainActivity.this, Colors.class);

                // Start the new activity
                startActivity(colorsIntent);

            }
        });




        //for phrases



        TextView phrases = (TextView) findViewById(R.id.phrases_text_view);

        // Set a click listener on that View
        phrases.setOnClickListener(new View.OnClickListener() {
            // The code in this method will be executed when the numbers category is clicked on.
            @Override
            public void onClick(View view) {
                // Create a new intent to open the {@link NumbersActivity}
                Intent phrasesIntent = new Intent(MainActivity.this, Phrases.class);

                // Start the new activity
                startActivity(phrasesIntent);
            }
        });





    }
}
