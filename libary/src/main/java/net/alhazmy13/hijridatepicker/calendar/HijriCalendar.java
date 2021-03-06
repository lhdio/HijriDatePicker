package net.alhazmy13.hijridatepicker.calendar;

import android.content.Context;
import android.util.Log;


import com.github.msarhan.ummalqura.calendar.UmmalquraCalendar;

import net.alhazmy13.hijridatepicker.R;

import java.util.Calendar;

/**
 * Created by Alhazmy13 on 10/14/15.
 */
class HijriCalendar implements CustomCalendarView {
    private static final String TAG = "HijriCalendar";
    private UmmalquraCalendar calendar;
    private String[] monthNames;
    private int countMonth,countYear,currentMonth,currentYear;

    public HijriCalendar(Context context){
        calendar=new UmmalquraCalendar();
        monthNames=new String[]{
                context.getResources().getString(R.string.muharram),
                context.getResources().getString(R.string.safar),
                context.getResources().getString(R.string.rabi_i),
                context.getResources().getString(R.string.rabi_ii),
                context.getResources().getString(R.string.jamada_i),
                context.getResources().getString(R.string.jamada_ii),
                context.getResources().getString(R.string.rajab),
                context.getResources().getString(R.string.shaban),
                context.getResources().getString(R.string.ramadan),
                context.getResources().getString(R.string.shawal),
                context.getResources().getString(R.string.dhu_alqadah),
                context.getResources().getString(R.string.dhu_alhijjah)};
        countMonth=calendar.get(Calendar.MONTH);
        countYear=calendar.get(Calendar.YEAR);
        currentMonth=countMonth;
        Log.d(TAG, "HijriCalendar: "+countMonth);
        currentYear=countYear;
    }



    @Override
    public void plusMonth(){
        countMonth++;
        if(countMonth==13) {
            countMonth = 1;
            countYear++;
        }
        calendar=new UmmalquraCalendar(countYear,countMonth,calendar.get(Calendar.DAY_OF_MONTH));

    }

    @Override
    public void minusMonth(){
        countMonth--;
        if(countMonth==0) {
            countMonth = 12;
            countYear--;
        }
        calendar=new UmmalquraCalendar(countYear,countMonth,calendar.get(Calendar.DAY_OF_MONTH));

    }

    @Override
    public boolean isCurrentMonth(){
        return (countMonth==currentMonth && currentYear==countYear);
    }
    @Override
    public void setMonth(int month){
        this.countMonth=month;
        calendar.set(Calendar.MONTH,month);
    }
    @Override
    public void setDay(int day){
        calendar.set(Calendar.DAY_OF_MONTH,day);
    }
    @Override
    public void setYear(int year) {
        this.countYear = year;
        calendar.set(Calendar.YEAR,year);
    }
    @Override
    public int getWeekStartFrom(){
        UmmalquraCalendar temp=new UmmalquraCalendar();
        temp.set(calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),1);
        return temp.get(Calendar.DAY_OF_WEEK);
    }
    @Override
    public int getLastDayOfMonth(){
        return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    }
    @Override
    public int getDayOfMonth(){
        return calendar.get(Calendar.DAY_OF_MONTH);
    }
    @Override
    public int getMonth(){
        return countMonth;
    }
    @Override
    public String getMonthName() {

        return monthNames[getOffsetMonthCount()];
    }
    @Override
    public int getYear(){
        return calendar.get(Calendar.YEAR);
    }

    @Override
    public int lengthOfMonth() {
        return calendar.lengthOfMonth();
    }

    @Override
    public int getCurrentMonth() {
        return getOffsetMonthCount();
    }
    @Override
    public int getOffsetMonthCount() {
        int temp = countMonth;
        if(temp == -1)
            temp = 11;
        else if(temp>= 12)
            temp = 0;
        return temp;
    }

    @Override
    public String[] getMonths() {
        return monthNames;
    }

    @Override
    public int getCurrentYear() {
        return countYear;
    }
}
