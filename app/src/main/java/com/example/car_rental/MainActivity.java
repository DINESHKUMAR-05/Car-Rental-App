package com.example.car_rental;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button login;
    Button create_user;
    EditText email,password;
    private DBHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        email = (EditText) findViewById(R.id.ema);
        password = (EditText) findViewById(R.id.pwd);
        create_user= (Button) findViewById(R.id.create_user);
        login= (Button) findViewById(R.id.login);
        db = new DBHelper(this);
        create_user.setOnClickListener(view -> openCreateUserActivity());
        login.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view1){
                String em = email.getText().toString();
                String pass = password.getText().toString();
                if(em.equals("") || pass.equals("")){
                    Toast.makeText(MainActivity.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                }
                else{
                    Boolean check = db.Checkemailpassword(em,pass);
                    if (check == true){
                        Toast.makeText(MainActivity.this, "Login is successfull", Toast.LENGTH_SHORT).show();
                        Intent i2 = new Intent(getApplicationContext(),home.class);
                        i2.putExtra("email",em);
                        startActivity(i2);
                    }
                    else{
                        Toast.makeText(MainActivity.this, "Login is Unsuccessful", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
    }
    public void openCreateUserActivity(){
        Intent intent = new Intent(this,newuser.class);
        startActivity(intent);
    }

}