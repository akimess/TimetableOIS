package com.akimess.timetableois;

import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.akimess.timetableois.models.Course;

import net.fortuna.ical4j.data.CalendarBuilder;
import net.fortuna.ical4j.filter.Filter;
import net.fortuna.ical4j.filter.PeriodRule;
import net.fortuna.ical4j.model.Calendar;
import net.fortuna.ical4j.model.Component;
import net.fortuna.ical4j.model.DateTime;
import net.fortuna.ical4j.model.Dur;
import net.fortuna.ical4j.model.ParameterList;
import net.fortuna.ical4j.model.Period;
import net.fortuna.ical4j.model.component.VEvent;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
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

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        prepareCourses();
    }

    //Testing
    private void prepareCourses(){
//        Course c = new Course("Foundations and Management of Cyber Security",
//                "vanemteadur Hayretdin Bahsi", "lecture", "ICT-315","ITX8043", "2017-09-12 10:00:00",
//                "2017-09-12 11:30:00");
//        courseList.add(c);
//
//        c = new Course("Legal Aspects of Cyber Security", "tunnitasuline Agnes Kasper",
//                "lecture", "ICT-A1", "ITC8010", "2017-09-12 16:00:00",
//                "2017-09-12 17:30:00");
//        courseList.add(c);
//
//        c = new Course("Network Technology I", "lektor Truls Tuxen Ringkjob", "practice", "EIK-415",
//                "ITV8040", "2017-09-12 17:45:00", "2017-09-12 19:15:00");
//        courseList.add(c);
//
//        c = new Course("Network Technology II", "lektor Truls Tuxen Ringkjob", "practice", "EIK-415",
//                "ITV8040", "2017-09-12 17:45:00", "2017-09-12 19:15:00");
//        courseList.add(c);

        AssetManager assetManager = getAssets();
        CalendarBuilder builder = new CalendarBuilder();


        try {
            AssetFileDescriptor fileDescriptor = assetManager.openFd("18_09_2017_15_36_55.ics");
            FileInputStream fin = fileDescriptor.createInputStream();

            Calendar calendar = builder.build(fin);

            //Today only
            java.util.Calendar today = java.util.Calendar.getInstance();

            today.add(java.util.Calendar.DATE, 1);

            today.set(java.util.Calendar.HOUR_OF_DAY, 0);
            today.clear(java.util.Calendar.MINUTE);
            today.clear(java.util.Calendar.SECOND);

            Period period = new Period(new DateTime(today.getTime()), new Dur(1, 0, 0, 0));
            Filter filter = new Filter(new PeriodRule(period));

            Collection eventsToday = filter.filter(calendar.getComponents(Component.VEVENT));

            //Log.d("CHECK_COLLECTION", eventsToday.toString());

            for (Object eventObject : eventsToday){
                Course c = event2Course((VEvent) eventObject);
                courseList.add(c);
            }

        } catch (Exception e){
            Log.e("ASSET_ERROR", e.toString());
        }



        adapter.notifyDataSetChanged();
    }

    private Course event2Course(VEvent event){
        //        Course c = new Course("Foundations and Management of Cyber Security",
//                "vanemteadur Hayretdin Bahsi", "lecture", "ICT-315","ITX8043", "2017-09-12 10:00:00",
//                "2017-09-12 11:30:00");

        //Location
        String[] locationFull = event.getLocation().getValue().split("\n");
        String cLocation = locationFull[0].substring(7);

        //Course name, code and type
        String summary = event.getSummary().getValue();
        String[] summarySplit = summary.split("-");
        String cCode = summarySplit[0];
        String cName = summarySplit[1];
        String cType = summarySplit[2].replace(">","").trim();

        //Start date, End date
        Date startDate = event.getStartDate().getDate();
        Date endDate = event.getEndDate().getDate();

        //Lecturer name
        String[] description = event.getDescription().getValue().split("\n");
        String cLecturer = description[1].substring(9);

        Log.d("CHECK", cLocation + "  " + cCode + "  " + cName + "  " + cType + "  " + cLecturer);

        Course course = new Course(cName, cLecturer, cType, cLocation, cCode, startDate, endDate);
        return course;
    }

}
