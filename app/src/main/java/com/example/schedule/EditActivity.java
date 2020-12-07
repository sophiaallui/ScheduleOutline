package com.example.schedule;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

//import android.support.v7.app.AppCompatActivity;

public class EditActivity extends AppCompatActivity {

    EditText updateItem;
    Button btnSave;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        updateItem = findViewById(R.id.updateItem);
        btnSave = findViewById(R.id.btnSave);

        getSupportActionBar().setTitle("Edit Item");

        // Brings the chosen data into update activity ui
        updateItem.setText(getIntent().getStringExtra(MainActivity.KEY_TASK_TEXT));
        // Click save after updating content
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Create an intent which contains the result
                // Making it as an empty shell
                Intent intent = new Intent ();

                // Pass the data (results of editing)
                intent.putExtra(MainActivity.KEY_TASK_TEXT, updateItem.getText().toString());
                intent.putExtra(MainActivity.KEY_TASK_POSITION, getIntent().getExtras().getInt(MainActivity.KEY_TASK_POSITION));

                // Set the result of the intent
                setResult(RESULT_OK, intent);

                // finish activity, close the current activity and go back to main
                finish();

            }
        });
    }
}