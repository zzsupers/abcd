package com.tntran.tryhard.date;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

import java.util.Calendar;

/**
 * Created by TTN on 6/20/2018.
 */

public class DatePicked extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Calendar date = Calendar.getInstance();
        int year = date.get( Calendar.YEAR );
        int month = date.get( Calendar.MONTH );
        int day = date.get( Calendar.DAY_OF_MONTH );
        DatePickerDialog.OnDateSetListener listener = (DatePickerDialog.OnDateSetListener) getActivity();
        return new DatePickerDialog(getActivity(), listener, year, month, day);

    }


}
