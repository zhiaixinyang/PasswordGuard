package com.mdove.passwordguard.ui.calendar.decorators;

import android.graphics.drawable.GradientDrawable;

import com.mdove.passwordguard.ui.calendar.materialcalendarview.CalendarDay;
import com.mdove.passwordguard.ui.calendar.materialcalendarview.DayViewDecorator;
import com.mdove.passwordguard.ui.calendar.materialcalendarview.DayViewFacade;


/**
 * Created by Administrator on 2018/2/4 0004.
 */

public class ColorDecorator implements DayViewDecorator {

    private int color;
    private CalendarDay calendarDay;

    public ColorDecorator(CalendarDay day, int color) {
        this.calendarDay = day;
        this.color = color;
    }

    @Override
    public boolean shouldDecorate(CalendarDay day) {

        return calendarDay.getDay() == day.getDay();
    }

    @Override
    public void decorate(DayViewFacade view) {
        int selectcolor = color;
        GradientDrawable drawable = new GradientDrawable();
        drawable.setCornerRadius(32);
        drawable.setColor(selectcolor);
        drawable.setStroke(1, selectcolor);
        view.setSelectionDrawable(drawable);
    }
}
