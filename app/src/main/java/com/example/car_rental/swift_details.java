package com.example.car_rental;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;

public class swift_details extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swift_details);
        Button bswift=findViewById(R.id.bookswift);
        Intent intent=getIntent();
        String em=intent.getStringExtra("email");
        bswift.setOnClickListener(view ->{
            Intent i4 = new Intent(getApplicationContext(),Booking.class);
            i4.putExtra("car","Swift");
            i4.putExtra("type","Hatchback");
            i4.putExtra("costperday","3500");
            i4.putExtra("email",em);
            startActivity(i4);
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