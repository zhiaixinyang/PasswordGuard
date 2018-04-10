package com.mdove.passwordguard.ui.calendar.materialcalendarview.format;

import com.mdove.passwordguard.ui.calendar.materialcalendarview.CalendarDay;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Created by dickliu on 2017/5/3.
 */

public class CustomTitleFormatter implements TitleFormatter {
    private final DateFormat dateFormat;

    /**
     * Format using "LLLL yyyy" for formatting
     */
    public CustomTitleFormatter() {
        this.dateFormat = new SimpleDateFormat(
                "yyyy年MM月dd日E"
        );
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public CharSequence format(CalendarDay day) {

        return dateFormat.format(day.getDate());
    }
}
