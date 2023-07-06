package com.example.car_rental;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class Profile extends AppCompatActivity {
    private DBHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Button logout=findViewById(R.id.logout);
        ImageButton home= findViewById(R.id.HomeButtonprofile);
        TextView emai=findViewById(R.id.emailprofile);
        TextView lic=findViewById(R.id.license_profile);
        TextView phone=findViewById(R.id.phone_profile);
        TextView dob=findViewById(R.id.dob_profile);
        TextView u_name=findViewById(R.id.user_name_profile);
        Intent intent1=getIntent();
        String email = intent1.getStringExtra("email");
        emai.setText(email);
        db=new DBHelper(this);
        String user_name=db.retrieveusername(email);
        String license=db.retrievelicense(email);
        String phone_no=db.retrievephone(email);
        String Dob=db.retrieveDOB(email);
        u_name.setText(user_name);
        lic.setText(license);
        phone.setText(phone_no);
        dob.setText(Dob);
        ImageButton history = findViewById(R.id.BookingButtonprofile);
        history.setOnClickListener(view -> {
            Intent i2 = new Intent(getApplicationContext(), History.class);
            i2.putExtra("email", email);
            startActivity(i2);
        });
        home.setOnClickListener(view->{
            Intent intent2 = new Intent(getApplicationContext(), home.class);
            intent2.putExtra("email",email);
            startActivity(intent2);
        });
        logout.setOnClickListener(view->{
            Intent intent3 = new Intent(getApplicationContext(),MainActivity.class);
            startActivity(intent3);
        });
    }
}