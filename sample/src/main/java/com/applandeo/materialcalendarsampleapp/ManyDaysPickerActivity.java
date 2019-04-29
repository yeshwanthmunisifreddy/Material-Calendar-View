package com.applandeo.materialcalendarsampleapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.Toast;

import com.applandeo.materialcalendarview.CalendarView;
import com.applandeo.materialcalendarview.EventDay;
import com.applandeo.materialcalendarview.utils.DateUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Mateusz Kornakiewicz on 23.10.2017.
 */

public class ManyDaysPickerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.many_days_picker_activity);

        CalendarView calendarView = findViewById(R.id.calendarView);

        calendarView.setOnForwardPageChangeListener(() ->
                Toast.makeText(getApplicationContext(), "Forward", Toast.LENGTH_SHORT).show());

        calendarView.setOnPreviousPageChangeListener(() ->
                Toast.makeText(getApplicationContext(), "Previous", Toast.LENGTH_SHORT).show());

        calendarView.setSelectedDates(getSelectedDays());

        List<EventDay> events = new ArrayList<>();

        Calendar min = Calendar.getInstance();
        min.add(Calendar.DAY_OF_MONTH, -getTodayDate());
        Calendar max = Calendar.getInstance();
        max.add(Calendar.DAY_OF_MONTH, getDaysInMonth());
        calendarView.setMaximumDate(max);
        calendarView.setMinimumDate(min);
        calendarView.setSelectedDates(getSelectedDays());
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, 0);
        calendarView.setSwipeEnabled(false);
        calendarView.setIsEditable(false);

        Button getDateButton = findViewById(R.id.getDateButton);
        getDateButton.setOnClickListener(v -> {
            for (Calendar calendar : calendarView.getSelectedDates()) {
                System.out.println(calendar.getTime().toString());

                Toast.makeText(getApplicationContext(),
                        calendar.getTime().toString(),
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    private List<Calendar> getSelectedDays() {
        List<Calendar> calendars = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            Calendar calendar = DateUtils.getCalendar();
            calendar.add(Calendar.DAY_OF_MONTH, i);
            calendars.add(calendar);
        }

        return calendars;
    }

    private int getDaysInMonth() {
        Date d1 = new Date();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String startDate = dateFormat.format(d1);
        String[] todayDate = startDate.split("/");
        int today = Integer.parseInt(todayDate[0]);
        int month = Integer.parseInt(todayDate[1]);
        int year = Integer.parseInt(todayDate[2]);

        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month - 1, today);
        int daysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        int maxDays = daysInMonth - today;
        return maxDays;
    }

    private int getTodayDate() {
        Date d1 = new Date();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String startDate = dateFormat.format(d1);
        String[] todayDate = startDate.split("/");
        int today = Integer.parseInt(todayDate[0]);
        return today;
    }
}