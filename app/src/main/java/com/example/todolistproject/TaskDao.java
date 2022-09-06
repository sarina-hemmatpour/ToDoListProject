package com.example.todolistproject;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.ArrayList;
import java.util.List;

//DAO = database access object
@Dao
public interface TaskDao {

    @Insert
    long add(Task task);

    @Update
    int update(Task task);

    @Delete
    int delete(Task task);

    @Query("DELETE FROM tbl_tasks")
    void deleteAll();

    @Query("SELECT * FROM tbl_tasks")
    List<Task> getAll();

    @Query("SELECT * FROM tbl_tasks WHERE title LIKE '%' || :query || '%'")
    List<Task> search(String query);
}
