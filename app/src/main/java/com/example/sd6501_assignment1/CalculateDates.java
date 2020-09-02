package com.example.sd6501_assignment1;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CalculateDates {

    public CalculateDates() {
    }

    /* Set how the date will be displayed, get the current date, timezone and
       location, add days to date, then pass that info to a date object and display. */
    public String calculateDate(int days){

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, days);
        Date calculatedDate = new Date(calendar.getTimeInMillis());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat( "dd-MM-yyyy");
        return simpleDateFormat.format(calculatedDate);
    }
}
