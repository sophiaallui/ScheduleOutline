package com.example.schedule;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

//healdaskldalkdaslkasd.
public class LoginActivity extends AppCompatActivity {
    public static final String TAG = "LoginActivity";
    private EditText etUsername;
    private EditText etPassword;
    private Button btnLogin;
    private ImageButton btnBackarrow;
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
        btnBackarrow = findViewById(R.id.btnBackarrow);

        btnBackarrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToFront();
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "onClick login button");
                String username = etUsername.getText().toString();
                Log.i(TAG, "Username: " + username);
                String password = etPassword.getText().toString();
                Log.i(TAG, "Password: " + password);

                loginUser(username, password);
            }
        });
    }

    private void goToFront() {
        Intent i = new Intent(this, FrontActivity.class);
        startActivity(i);
        finish();
    }

    private void goToSignupActivity() {
        Intent i = new Intent(this, SignupActivity.class);
        startActivity(i);
        finish();
    }

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


