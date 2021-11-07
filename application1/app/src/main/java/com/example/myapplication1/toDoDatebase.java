package com.example.myapplication1;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.myapplication1.toDo;
import com.example.myapplication1.toDoDao;
@Database(entities = {toDo.class},version = 1, exportSchema = false)
public abstract class toDoDatebase extends RoomDatabase {
    public abstract toDoDao getToDoDao();
}
