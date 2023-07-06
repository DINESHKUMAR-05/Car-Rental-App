package com.example.car_rental;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

public class home extends AppCompatActivity {
    ImageButton profile;
    ImageButton sedan;
    ImageButton suv;
    ImageButton hatchback;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Intent intent=getIntent();
        String em=intent.getStringExtra("email");
        profile=findViewById(R.id.ProfileButton);
        sedan=findViewById(R.id.sedan);
        suv=findViewById(R.id.suv);
        hatchback=findViewById(R.id.hatchback);
        profile.setOnClickListener(view ->{
            Intent i2 = new Intent(getApplicationContext(),Profile.class);
            i2.putExtra("email",em);
            startActivity(i2);
        });
        ImageButton history = findViewById(R.id.BookingButton);
        history.setOnClickListener(view -> {
            Intent i2 = new Intent(getApplicationContext(), History.class);
            i2.putExtra("email", em);
            startActivity(i2);
        });
        sedan.setOnClickListener(view ->{
            Intent i3 = new Intent(getApplicationContext(),sedan.class);
            i3.putExtra("email",em);
            startActivity(i3);
        });
        suv.setOnClickListener(view ->{
            Intent i3 = new Intent(getApplicationContext(),suv.class);
            i3.putExtra("email",em);
            startActivity(i3);
        });
        hatchback.setOnClickListener(view ->{
            Intent i3 = new Intent(getApplicationContext(),hatchback.class);
            i3.putExtra("email",em);
            startActivity(i3);
        });



    }
}