package com.wwp.wkb.other;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.CalendarView;
import android.widget.Toast;

import com.wwp.wkb.R;

import java.util.Calendar;

public class CalendarActivity extends AppCompatActivity {

    private static final String TAG = "CalendarActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        getSchemeInfo();

        CalendarView cv_calendar = (CalendarView) findViewById(R.id.cv_calendar);
        cv_calendar.setMinDate(System.currentTimeMillis());
        cv_calendar.setMaxDate(System.currentTimeMillis() + 20 * 30 * 24 * 60 * 60 * 1000);

        Calendar cal = Calendar.getInstance();
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        cv_calendar.setMinDate(cal.getTimeInMillis());
        cal.add(Calendar.MONTH, 1);
        cv_calendar.setMaxDate(cal.getTimeInMillis());

        cv_calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                String checktime = "" + year;
                if (month + 1 < 10) {
                    checktime = checktime + "-0" + (month + 1);
                } else {
                    checktime = checktime + "-" + (month + 1);
                }
                if (0 <= dayOfMonth && dayOfMonth <= 9) {
                    checktime = checktime + "-0" + dayOfMonth;
                } else {
                    checktime = checktime + "-" + dayOfMonth;
                }
                Toast.makeText(getApplicationContext(), checktime, Toast.LENGTH_SHORT).show();
            }
        });
    }

    //参考：wUtils\app\src\main\assets\share_test.html
    public void getSchemeInfo() {
        Intent intent = getIntent();
        String action = intent.getAction();

        if (Intent.ACTION_VIEW.equals(action)) {
            Uri uri = intent.getData();
            if (uri != null) {
                String name = uri.getQueryParameter("name");
                String age = uri.getQueryParameter("age");
                Log.d(TAG, "getSchemeInfo: name:" + name);
                Log.d(TAG, "getSchemeInfo: age:" + age);
            }
        }
    }
}
