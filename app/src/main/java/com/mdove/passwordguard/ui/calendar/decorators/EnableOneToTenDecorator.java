package com.mdove.passwordguard.ui.calendar.decorators;

import com.mdove.passwordguard.ui.calendar.materialcalendarview.CalendarDay;
import com.mdove.passwordguard.ui.calendar.materialcalendarview.DayViewDecorator;
import com.mdove.passwordguard.ui.calendar.materialcalendarview.DayViewFacade;

/**
 * Created by dickliu on 2018/1/12.
 */

public class EnableOneToTenDecorator implements DayViewDecorator {

    @Override
    public boolean shouldDecorate(CalendarDay day) {
        return day.getDay() <= 10;
    }

    @Override
    public void decorate(DayViewFacade view) {
        view.setDaysDisabled(true);
    }
}
