package com.akimess.timetableois;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.akimess.timetableois.models.Course;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder>{

    private Context mContext;
    private List<Course> courseList;

    public class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView title, lecturer, type, location, code, time;

        public MyViewHolder(View view){
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            code = (TextView) view.findViewById(R.id.code);
            lecturer = (TextView) view.findViewById(R.id.lecturer);
            type = (TextView) view.findViewById(R.id.type);
            location = (TextView) view.findViewById(R.id.location);
            time = (TextView) view.findViewById(R.id.time);
        }
    }

    public Adapter(Context mContext, List<Course> courseList){
        this.mContext = mContext;
        this.courseList = courseList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.course_view, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position){
        Course course = courseList.get(position);
        holder.lecturer.setText(course.getLecturer());
        holder.code.setText(course.getCode());
        holder.location.setText(course.getLocation());
        SimpleDateFormat df = new SimpleDateFormat("HH:mm", Locale.ENGLISH);
        holder.time.setText(df.format(course.getStartTime()));
        holder.type.setText(course.getType());
        holder.title.setText(course.getName());

    }

    @Override
    public int getItemCount(){
        return courseList.size();
    }
}
