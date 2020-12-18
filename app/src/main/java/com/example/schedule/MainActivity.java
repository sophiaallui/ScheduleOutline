package com.example.schedule;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.parse.ParseUser;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

//import android.os.FileUtils;

public class MainActivity  extends AppCompatActivity {

    private TextView username;
    private Button btnAdd;
    private EditText addItem;
    private RecyclerView taskList;
    private List<String> tasks;
    TasksAdapter tasksAdapter;
    public static final String KEY_TASK_TEXT = "task text";
    public static final String KEY_TASK_POSITION = "task_position";
    public static final int EDIT_TEXT_CODE = 20;


    //ItemsAdapter itemsAdapter;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainactivity);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_logout, menu);
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


