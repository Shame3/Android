package com.example.myapplication1;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import com.example.myapplication1.toDo;

import java.util.List;

@Dao
public interface toDoDao {
    @Insert
    void  insertdoDo(toDo... todo);
    @Update
    int updatetoDo(toDo... todo);
    @Delete
    void deletetoDo(toDo...todo);

    @Query("DELETE FROM toDo")
    void deletetoDo();

    @Query("SELECT * FROM toDo ORDER BY id DESC")
    List<toDo> getAlltoDos();
}
