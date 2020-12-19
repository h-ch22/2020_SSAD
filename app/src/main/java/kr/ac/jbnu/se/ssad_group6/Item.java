package kr.ac.jbnu.se.ssad_group6;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Item {
    private String place;
    private Date makeDate;


    public Item() {
        this.place = "";
        this.makeDate = new Date();
    }

    public Item(String place, Date makeDate) {
        this.place = place;
        this.makeDate=makeDate;
    }

    public String getPlace()
    {
        return place;
    }

    public String getDate()
    {
        SimpleDateFormat newDateFormat = new SimpleDateFormat("EE d MMM yyyy");
        String MySDate = newDateFormat.format(this.makeDate);
        return MySDate;
    }

    public String getTime()
    {
        SimpleDateFormat newDateFormat = new SimpleDateFormat("HH:mm");
        String MySDate = newDateFormat.format(this.makeDate);
        return MySDate;
    }

    public String getMonth()
    {
        SimpleDateFormat newDateFormat = new SimpleDateFormat("MM/dd");
        String MySDate = newDateFormat.format(this.makeDate);
        return MySDate;
    }

    public String getYear()
    {
        SimpleDateFormat newDateFormat = new SimpleDateFormat("yyyy");
        String MySDate = newDateFormat.format(this.makeDate);
        return MySDate;
    }
}

