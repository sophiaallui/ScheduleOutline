package com.example.schedule;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;


public class LoginActivity extends AppCompatActivity {
    public static final String TAG = "LoginActivity";
    private EditText etUsername;
    private EditText etPassword;
    private Button btnLogin;
    private Button btnSignup;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Logout will be within main activity because users would be able to logout from there
        //ParseUser.logOut();
        //ParseUser currentUser = ParseUser.getCurrentUser();

        if(ParseUser.getCurrentUser()!=null) {
            goMainActivity();
        }

        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnSignup = findViewById(R.id.btnSignup);
        
        /*btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG, "OnClick signup button");
                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();
                signUp(username, password);
            }
        });*/
        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG, "onClick sign up button");
                goToSignupActivity();


            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "onClick login button");
                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();
                loginUser(username, password);
            }
        });
    }

    private void goToSignupActivity() {
        Intent i = new Intent(this, SignupActivity.class);
        startActivity(i);
        finish();
    }

    /*private void signUp(String username, String password) {
        Log.i(TAG, "Attempting to sign user up"+username);
        ParseUser user = new ParseUser();
        user.setUsername(username);
        user.setPassword(password);

        user.signUpInBackground(new SignUpCallback() {
            @Override
            public void done(ParseException e) {
                if(e != null) {
                   Log.e(TAG,"issue with sign up", e);
                   return;
                }

                Log.i(TAG, "Successful Sign Up!");
                goMainActivity();
                Toast.makeText(LoginActivity.this, "Successful Sign Up!", Toast.LENGTH_SHORT).show();

            }
        });

    }*/

    private void loginUser(String username, String password) {
        Log.i(TAG, "Attempting to login user" + username);
        ParseUser.logInInBackground(username, password, new LogInCallback() {
            @Override
            public void done(ParseUser user, ParseException e) {
                if(e != null){
                    Log.e(TAG, "issue with login", e);
                    return;
                }
                goMainActivity();
                Toast.makeText(LoginActivity.this,"Success!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void goMainActivity() {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();
    }
}


