package com.tntran.tryhard.dialog;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;

import com.tntran.tryhard.R;
import com.tntran.tryhard.date.DatePicked;

import java.util.Calendar;

/**
 * Created by TTN on 6/20/2018.
 */

public class DialogActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.checkbox);
    }
    public void ClickBeginDate(View v) {
        DatePicked newFragment = new DatePicked();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }


    // handle the date selected
    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        // store the values selected into a Calendar instance
        final Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, monthOfYear);
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
    }



}
