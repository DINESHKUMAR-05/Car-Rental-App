package com.example.car_rental;
import android.Manifest;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Telephony;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Spinner;
import android.location.Location;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;



public class Booking extends AppCompatActivity {
    FusedLocationProviderClient fusedLocationClient;
    SmsManager manager = SmsManager.getDefault();
    String myLocation;
    private DBHelperBook db;
    EditText pdate, ptime, rdate, rtime, fname, lname, lic, ph;
    Button book;
    Spinner carTypeSpinner,carTypeSpinner2;
    ArrayAdapter<CharSequence> carTypeAdapter;
    // Notification channel ID and name
    private static final String CHANNEL_ID = "CarRental";
    private static final String CHANNEL_NAME = "Car Rental Notification";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        fusedLocationClient.getLastLocation()
                .addOnSuccessListener(new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        if (location != null) {
                            // Logic to handle location object
                            location.getAltitude();
                            location.getLongitude();
                            myLocation = "http://maps.google.com/maps?q=loc:"+location.getLatitude()+","+location.getLongitude();
                        }else {
                            myLocation = "http://maps.google.com/maps?q=loc:11.0273817,77.0264465";
                        }
                    }
                });

        ImageButton profile = findViewById(R.id.ProfileButton);
        ImageButton home = findViewById(R.id.HomeButton);
        pdate = (EditText) findViewById(R.id.pickup_date);
        ptime = (EditText) findViewById(R.id.pickup_time);
        rdate = (EditText) findViewById(R.id.return_date);
        rtime = (EditText) findViewById(R.id.return_time);
        fname = (EditText) findViewById(R.id.first_name);
        lname = (EditText) findViewById(R.id.last_name);
        lic = (EditText) findViewById(R.id.license_number);
        ph = (EditText) findViewById(R.id.phone_number);
        TextView car_name= findViewById(R.id.carname);

        book = findViewById(R.id.book);
        Intent intent = getIntent();
        String em = intent.getStringExtra("email");
        String car = intent.getStringExtra("car");
        String type= intent.getStringExtra("type");
        String costperday = intent.getStringExtra("costperday");
        car_name.setText(car);

        db = new DBHelperBook(this);

        // Initialize the Spinner object
        carTypeSpinner = findViewById(R.id.car_type_spinner);
        carTypeSpinner2 = findViewById(R.id.car_type_spinner2);

        // Initialize the ArrayAdapter and set it with the options
        carTypeAdapter = ArrayAdapter.createFromResource(this, R.array.car_types, android.R.layout.simple_spinner_item);
        carTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Set the ArrayAdapter to the Spinner object
        carTypeSpinner.setAdapter(carTypeAdapter);
        carTypeSpinner2.setAdapter(carTypeAdapter);

        // Add a listener to the spinner to handle the selected option
        carTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String carType = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        carTypeSpinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String carType = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String p_date = pdate.getText().toString();
                String p_time = ptime.getText().toString();
                String r_date = rdate.getText().toString();
                String r_time = rtime.getText().toString();
                String f_name = fname.getText().toString();
                String l_name = lname.getText().toString();
                String license = lic.getText().toString();
                String phone = ph.getText().toString();

                String pickuplocation = carTypeSpinner.getSelectedItem().toString();
                String droplocation = carTypeSpinner2.getSelectedItem().toString();

                db.insertBookingData(em, car,type, costperday, p_date, p_time, r_date, r_time, f_name, l_name, license, phone,pickuplocation,droplocation);
                manager.sendTextMessage(phone,null,"Booking Confirmed Location for Pickup:\n"+myLocation,null,null);
                // Create and send notification
                createNotification();

                // Send email confirmation
                sendEmailConfirmation(em);

                Intent i = new Intent(getApplicationContext(), booking_confirmation.class);
                i.putExtra("email", em);
                startActivity(i);

            }
        });
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

    private void createNotification() {
        // Create notification channel for Android 8.0 and above
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }

        // Build notification
        NotificationCompat.Builder ncb = new NotificationCompat.Builder(getApplicationContext(), CHANNEL_ID);
        ncb.setSmallIcon(R.drawable.ic_android_black_24dp);
        ncb.setContentTitle("Booking Confirmed");
        ncb.setContentText("Your car booking has been confirmed.");

        // Show notification
        NotificationManagerCompat nmc = NotificationManagerCompat.from(Booking.this);
        nmc.notify(1, ncb.build());
    }

    private void sendEmailConfirmation(String email) {
        String subject = "Car Rental Booking Confirmation";
        String message = "Dear Customer,\n\nThank you for booking a car with Car Rental. Your booking has been confirmed.\n\nBest regards,\nThe Car Rental Team";
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("message/rfc822");
        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"nk42434@gmail.com"});
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, message);
        try {
            startActivity(Intent.createChooser(intent, "Send email using..."));
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(this, "No email clients installed.", Toast.LENGTH_SHORT).show();
        }
    }
}