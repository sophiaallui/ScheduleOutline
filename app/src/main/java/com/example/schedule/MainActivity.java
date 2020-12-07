package com.example.schedule;

import android.content.Intent;
import android.os.Bundle;
//import android.os.FileUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import org.apache.commons.io.FileUtils;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.parse.ParseUser;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

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

        username = findViewById(R.id.username);
        btnAdd = findViewById(R.id.btnAdd);
        addItem = findViewById(R.id.addItem);
        taskList = findViewById(R.id.itemList);

        loadItems();
        TasksAdapter.OnLongClickListener onLongClickListener = new TasksAdapter.OnLongClickListener(){
            /* REMOVE ITEM*/
            @Override
            public void onItemLongClicked(int position) {
                // HAve exact position
                // Delete item from model and notify adapter
                tasks.remove(position);
                tasksAdapter.notifyItemRemoved(position);
                Toast.makeText(getApplicationContext(), "Item was Removed", Toast.LENGTH_SHORT).show();
                saveItems();
            }
        };

        TasksAdapter.OnClickListener onClickListener = new TasksAdapter.OnClickListener(){
            @Override
            public void onItemClicked(int position) {
                // Open up activity
                Log.d("MainActivity", "Single click at position" + position);
                // Create the new activity
                /* MainActivity.this: instance of MainActivity
                   EditActivity.class: class to go to */
                Intent i = new Intent(MainActivity.this, EditActivity.class);
                // Pass relevant data being edited
                i.putExtra(KEY_TASK_TEXT, tasks.get(position));
                i.putExtra(KEY_TASK_POSITION, position);
                // Display the activity
                startActivityForResult(i, EDIT_TEXT_CODE);
            }
        };
        // Construct Adapter
        tasksAdapter = new TasksAdapter(tasks, onLongClickListener,onClickListener);
        taskList.setAdapter(tasksAdapter);
        // Runs this vertically
        taskList.setLayoutManager(new LinearLayoutManager(this));

        /* ADDING ITEM */
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*ADD*/
                //Takes the input item data
                String todoItem = addItem.getText().toString();
                //Add item to model
                tasks.add(todoItem);
                //Notify adapter added an item to the list
                tasksAdapter.notifyItemInserted(tasks.size()-1);
                addItem.setText("");
                Toast.makeText(getApplicationContext(), "Item was Added", Toast.LENGTH_SHORT).show();
                saveItems();
            }
        });
    }

    private void saveItems(){
        try {
            FileUtils.writeLines(getDataFile(), tasks);
        } catch (IOException e) {
            Log.e("MainActivity", "Error writing items", e);
        }
    }
    /*  CALLED ONCE WHEN APP STARTS UP */
    // Load items by reading every line of the data file
    private void loadItems(){
        // Parameters: read all lines and populate into arraylist (list of model)
        try {
            // Tries this first then catch the bug if anything
            tasks = new ArrayList<>(FileUtils.readLines(getDataFile(), Charset.defaultCharset()));
        } catch (IOException e) {
            // What's really happening in the program
            Log.e("MainActivity", "Error reading items", e);
            // If we do end up with an exception,
            tasks = new ArrayList<>();
        }
    }
    // Get data and store in data file
    private File getDataFile(){
        // getFilersDir: directory of file and name
        return new File(getFilesDir(), "data.txt");
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


