package com.example.roomdatabase;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class ViewModal extends AndroidViewModel {

    private CourseRepository repository;

    // below line is to create a variable for live
    // data where all the courses are present.
    private LiveData<List<CourseModal>> allCourses;

    // constructor for our view modal.
    public ViewModal(@NonNull Application application) {
        super(application);
        repository = new CourseRepository(application);
//        allCourses = repository.getAllCourses();
    }

    // below method is use to insert the data to our repository.
    public void insert(CourseModal model) {
        repository.insert(model);
    }



    public LiveData<List<CourseModal>> getAllCourses() {
        return allCourses;
    }
}
