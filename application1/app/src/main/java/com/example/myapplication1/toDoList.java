package com.example.myapplication1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.myapplication1.toDoDatebase;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class toDoList extends AppCompatActivity {
    private final static String TAG=MainActivity.class.getName();
    toDoDatebase todoDatebase;
    toDoDao todoDao;
    List<toDo> list;

    private ListView listView;
    List<Map<String, Object>> mList=new ArrayList<Map<String,Object>>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do_list);
        todoDatebase= Room.databaseBuilder(this,toDoDatebase.class,"todo_database") //new a database
                .allowMainThreadQueries()
                .build();

        todoDao = todoDatebase.getToDoDao();  // new a dao

        initList();

        this.listView = (ListView) findViewById(R.id.toDoListview);

        final myAdapter adapter = new myAdapter(toDoList.this, mList);
        listView.setAdapter(adapter);

        //ListView item的点击事件
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(toDoList.this, "Click item" + i, Toast.LENGTH_SHORT).show();
            }
        });

        //ListView item 中的删除按钮的点击事件
        adapter.setOnItemDeleteClickListener(new myAdapter.onItemDeleteListener() {
            @Override
            public void onDeleteClick(int i) {
               // mList.remove(i);
                Log.e(TAG,"delete id "+i);
                toDo now= new toDo("Hello","world");
                now.setId(list.get(i).id);
                todoDao.deletetoDo(now);
                mList.clear();
                initList();
                adapter.notifyDataSetChanged();
            }
        });

//        adapter.setOnItemCheckClickListener(new myAdapter.onItemCheckListener() {
//            @Override
//            public void onCheckClick(int i) {
//                // mList.remove(i);
//                Log.e(TAG,"choose id "+i);
//                listView.setAdapter(adapter);
//                adapter.notifyDataSetChanged();
//            }
//        });

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),addTodoActivity.class));
            }
        });
    }

    /**
     * 初始化数据
     */
    private void initList() {
        list= todoDao.getAlltoDos();
        for(int i=0;i<list.size();i++)
        {
            Map<String, Object> map = new HashMap<String, Object>();
            String title_s=list.get(i).getActivity();if (title_s==null) title_s="wocao";
            String time_s=list.get(i).getTime();if (time_s==null) time_s="wocao";
            map.put("title", title_s);
            map.put("time", time_s);
            mList.add(map);
        }
    }

}