package com.example.schedule;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.parse.ParseUser;

public class MainActivity  extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainactivity);
    }

        public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_logout, menu);
        //return super.onCreateOptionsMenu(menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.action_logout) {
            ParseUser.logOut();
            goToFront();
        }
        return super.onOptionsItemSelected(item);
    }

    private void goToFront() {
        Intent i = new Intent(this, FrontActivity.class);
        startActivity(i);
        finish();
    }

}


