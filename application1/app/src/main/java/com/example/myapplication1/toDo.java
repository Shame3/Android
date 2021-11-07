package com.example.myapplication1;
import com.example.myapplication1.*;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.sql.Timestamp;

@Entity(tableName = "toDo")
public class toDo {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="id")
    public  int id;

    @ColumnInfo(name="activity")
    public  String activity;

    @ColumnInfo(name="toDotime")
    public String toDotime;

    public toDo(){
        this.activity="hello ";this.toDotime="world";
    }
    public toDo(String activity,String time){
        this.activity=activity;this.toDotime=time;
    }

    public int getId() {
        return id;
    }

    public String getActivity() {
        return activity;
    }

    public String getTime() {
        return toDotime;
    }


    // set
    public void setId(int id) {
        this.id = id;
    }

    public void setActivity(String activity) {
        this.activity=activity;
    }

    public void setTime(String time) {
        this.toDotime=time;
    }
}
