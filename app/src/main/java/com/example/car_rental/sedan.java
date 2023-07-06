package com.example.car_rental;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.widget.ImageButton;

public class sedan extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sedan);
        ImageButton sedan1 = findViewById(R.id.detailsvirtus);
        ImageButton sedan2 = findViewById(R.id.detailscity);
        Intent intent = getIntent();
        String em = intent.getStringExtra("email");
        sedan1.setOnClickListener(view -> {
            Intent i3 = new Intent(getApplicationContext(), virtus_details.class);
            i3.putExtra("email", em);
            startActivity(i3);
        });
        sedan2.setOnClickListener(view -> {
            Intent i3 = new Intent(getApplicationContext(), city_details.class);
            i3.putExtra("email", em);
            startActivity(i3);
        });
        ImageButton profile = findViewById(R.id.ProfileButton);
        ImageButton home = findViewById(R.id.HomeButton);
        ImageButton history = findViewById(R.id.BookingButton);
        history.setOnClickListener(view -> {
            Intent i2 = new Intent(getApplicationContext(), History.class);
            i2.putExtra("email", em);
            startActivity(i2);
        });
        profile.setOnClickListener(view -> {
            Intent i2 = new Intent(getApplicationContext(), Profile.class);
            i2.putExtra("email", em);
            startActivity(i2);
        });
        home.setOnClickListener(view -> {
            Intent i2 = new Intent(getApplicationContext(), home.class);
            i2.putExtra("email", em);
            startActivity(i2);
        });


    }

}