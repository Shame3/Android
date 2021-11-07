package com.example.myapplication1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class addTodoActivity extends AppCompatActivity {
    private final static String TAG=MainActivity.class.getName();
    toDoDatebase todoDatebase;
    toDoDao todoDao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_todo);

        todoDatebase = Room.databaseBuilder(this, toDoDatebase.class, "todo_database") //new a database
                .allowMainThreadQueries()
                .build();

        todoDao = todoDatebase.getToDoDao();  // new a dao
        Button button1 = (Button) findViewById(R.id.confirmAdd);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText addActivity = (EditText) findViewById(R.id.editTextAddActivity);
                EditText addTime = (EditText) findViewById(R.id.editTextAddTime);
                // Toast.makeText(addTodoActivity.this, "点击", Toast.LENGTH_SHORT).show();

                String activity = addActivity.getText().toString();
                String time = addActivity.getText().toString();
                if (activity != null && time != null) {
                    toDo now = new toDo(activity, time);
                    todoDao.insertdoDo(now);

                    List<toDo> list = todoDao.getAlltoDos();
                    Log.e(TAG, list.size() + "size:");
                    startActivity(new Intent(getApplicationContext(), toDoList.class));
                }
            }
        });

    }
}