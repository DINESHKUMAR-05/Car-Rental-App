package com.example.car_rental;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

public class booking_confirmation extends AppCompatActivity {
    private DBHelperBook db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_confirmation);
        Intent intent = getIntent();
        String em = intent.getStringExtra("email");
        db=new DBHelperBook(this);
        TextView carh=findViewById(R.id.car_b);
        TextView typeh=findViewById(R.id.type_b);
        TextView costh=findViewById(R.id.cost_b);
        TextView pdateh=findViewById(R.id.pickup_date_b);
        TextView rdateh=findViewById(R.id.return_date_b);
        TextView ptimeh=findViewById(R.id.pickup_time_b);
        TextView rtimeh=findViewById(R.id.return_time_b);
        TextView dfnameh=findViewById(R.id.dname_b);
        TextView dlnameh=findViewById(R.id.lname_b);
        TextView licenseh=findViewById(R.id.license_b);
        TextView phoneh=findViewById(R.id.phone_b);
        TextView plocationh=findViewById(R.id.pickup_location_b);
        TextView dlocationh=findViewById(R.id.drop_location_b);

        String car=db.retrievecar(em);
        String type=db.retrievetype(em);
        String cost=db.retrievecost(em);
        String pdate=db.retrievepdate(em);
        String rdate=db.retrieverdate(em);
        String ptime=db.retrieveptime(em);
        String rtime=db.retrievertime(em);
        String dfname=db.retrievedfname(em);
        String dlname=db.retrievedlname(em);
        String license=db.retrievelicense(em);
        String phone=db.retrievephone(em);
        String plocation=db.retrieveplocation(em);
        String dlocation=db.retrievedlocation(em);

        carh.setText(car);
        typeh.setText(type);
        costh.setText(cost);
        pdateh.setText(pdate);
        rdateh.setText(rdate);
        ptimeh.setText(ptime);
        rtimeh.setText(rtime);
        dfnameh.setText(dfname);
        dlnameh.setText(dlname);
        licenseh.setText(license);
        phoneh.setText(phone);
        plocationh.setText(plocation);
        dlocationh.setText(dlocation);

        ImageButton profile = findViewById(R.id.ProfileButtonprofile);
        ImageButton home = findViewById(R.id.HomeButtonprofile);
        ImageButton history = findViewById(R.id.BookingButtonprofile);
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