package com.example.schedule.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.schedule.EditActivity;
import com.example.schedule.FrontActivity;
import com.example.schedule.MainActivity;
import com.example.schedule.R;
import com.example.schedule.TasksAdapter;
import com.parse.ParseUser;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import static com.parse.Parse.getApplicationContext;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class HomeworkFragment extends Fragment {

    public static final String TAG = "ComposeFragment";
    private Button btnAdd;
    private EditText addItem;
    private RecyclerView taskList;
    private List<String> tasks;
    private String hw = "homework";
    TasksAdapter tasksAdapter;
    public static final String KEY_TASK_TEXT = "task text";
    public static final String KEY_TASK_POSITION = "task_position";
    public static final int EDIT_TEXT_CODE = 20;


    public HomeworkFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_homework, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btnAdd = view.findViewById(R.id.btnAdd);
        addItem = view.findViewById(R.id.etTask);
        taskList = view.findViewById(R.id.rvItems);
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
                Log.d("HomeworkFragment", "Single click at position" + position);
                // Create the new activity
                /* MainActivity.this: instance of MainActivity
                   EditActivity.class: class to go to */
                Intent i = new Intent(getContext(), EditActivity.class);
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
        taskList.setLayoutManager(new LinearLayoutManager(getContext()));

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String todoItem = addItem.getText().toString();
                tasks.add(todoItem);
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
            Log.e("HomeworkFragment", "Error writing items", e);
        }
    }
    private void loadItems(){
        try {
            tasks = new ArrayList<>(FileUtils.readLines(getDataFile(), Charset.defaultCharset()));
        } catch (IOException e) {
            Log.e("HomeworkFragment", "Error reading items", e);
            tasks = new ArrayList<>();
        }
    }
    // Get data and store in data file
    private File getDataFile(){
        // getFilersDir: directory of file and name
        return new File(getContext().getFilesDir(), "data.txt");
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
        Intent i = new Intent(getContext(), FrontActivity.class);
        startActivity(i);
        getActivity().finish();
    }
}