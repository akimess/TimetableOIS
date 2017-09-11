package com.akimess.timetableois;

import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.akimess.timetableois.models.Course;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private Adapter adapter;
    private List<Course> courseList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        courseList = new ArrayList<>();
        adapter = new Adapter(this, courseList);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        prepareCourses();
    }

    //Testing
    private void prepareCourses(){
        Course c = new Course("Foundations and Management of Cyber Security",
                "vanemteadur Hayretdin Bahsi", "lecture", "ICT-315","ITX8043", "2017-09-12 10:00:00",
                "2017-09-12 11:30:00");
        courseList.add(c);

        c = new Course("Legal Aspects of Cyber Security", "tunnitasuline Agnes Kasper",
                "lecture", "ICT-A1", "ITC8010", "2017-09-12 16:00:00",
                "2017-09-12 17:30:00");
        courseList.add(c);

        c = new Course("Network Technology I", "lektor Truls Tuxen Ringkjob", "practuce", "EIK-415",
                "ITV8040", "2017-09-12 17:45:00", "2017-09-12 19:15:00");
        courseList.add(c);

        adapter.notifyDataSetChanged();
    }

}
