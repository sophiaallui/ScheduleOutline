package com.example.schedule;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.parse.ParseUser;

public class FrontActivity extends AppCompatActivity {

    public static final String TAG = "FrontActivity";
    private Button btnLogin;
    private Button btnSignup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_front);

        if(ParseUser.getCurrentUser()!=null) {
            goMainActivity();
        }

        btnLogin = findViewById(R.id.btnLogin);
        btnSignup = findViewById(R.id.btnSignup);

        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG, "onClick Register button");
                goToSignupActivity();


            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG, "onClick Login button");
                goToLoginActivity();

            }
        });
    }

    private void goMainActivity() {
        Log.i(TAG, "User already logged in");
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();
    }

    private void goToLoginActivity() {
        Log.i(TAG, "clicked Login");
        Intent i = new Intent(this, LoginActivity.class);
        startActivity(i);
        finish();
    }

    private void goToSignupActivity() {
        Log.i(TAG, "clicked Signup");
        Intent i = new Intent(this, SignupActivity.class);
        startActivity(i);
        finish();
    }


}