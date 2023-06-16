package com.example.roomdatabase;

import androidx.room.Insert;
@androidx.room.Dao

public interface  Dao {

    @Insert
    void insert(CourseModal model);


}
