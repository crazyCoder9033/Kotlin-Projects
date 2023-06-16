package com.example.roomdatabase;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class CourseRepository {

    private Dao dao;
    private LiveData<List<CourseModal>> allCourses;

    // creating a constructor for our variables
    // and passing the variables to it.
    public CourseRepository(Application application) {
        CourseDatabase database = CourseDatabase.getInstance(application);
        dao = database.Dao();
//        allCourses = dao.getAllCourses();
    }

    // creating a method to insert the data to our database.
    public void insert(CourseModal model) {
        new InsertCourseAsyncTask(dao).execute(model);
    }

    private static class InsertCourseAsyncTask extends AsyncTask<CourseModal, Void, Void> {
        private Dao dao;

        private InsertCourseAsyncTask(Dao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(CourseModal... model) {
            // below line is use to insert our modal in dao.
            dao.insert(model[0]);
            return null;
        }
    }
}
