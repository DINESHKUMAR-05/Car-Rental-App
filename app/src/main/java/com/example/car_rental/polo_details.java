package com.example.car_rental;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;

public class polo_details extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_polo_details);
        Button bpolo=findViewById(R.id.bookpolo);
        Intent intent=getIntent();
        String em=intent.getStringExtra("email");
        bpolo.setOnClickListener(view ->{
            Intent i4 = new Intent(getApplicationContext(),Booking.class);
            i4.putExtra("car","Polo");
            i4.putExtra("type","Hatchback");
            i4.putExtra("costperday","4000");
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