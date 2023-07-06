package com.example.car_rental;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class newuser extends AppCompatActivity {
    Button create;
    EditText email,username,license,phno,dob,password;
    private DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newuser);
        username = (EditText) findViewById(R.id.username);
        email = (EditText) findViewById(R.id.email);
        license = (EditText) findViewById(R.id.license);
        phno = (EditText) findViewById(R.id.phno);
        dob = (EditText) findViewById(R.id.dob);
        password = (EditText) findViewById(R.id.password);
        create = (Button) findViewById(R.id.create);
        db = new DBHelper(this);

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String em = email.getText().toString();
                String user = username.getText().toString();
                String lic = license.getText().toString();
                String ph = phno.getText().toString();
                String DOB = dob.getText().toString();
                String pass = password.getText().toString();
                db.insertData(em,user,lic,ph,DOB,pass);
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
            }
        });
    }
}