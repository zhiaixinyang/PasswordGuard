package com.mdove.passwordguard.ui.calendar.materialcalendarview.format;


import com.mdove.passwordguard.ui.calendar.materialcalendarview.CalendarDay;

/**
 * Used to format a {@linkplain com.mdove.passwordguard.ui.calendar.materialcalendarview.CalendarDay} to a string for the month/year title
 */
public interface TitleFormatter {

    /**
     * Converts the supplied day to a suitable month/year title
     *
     * @param day the day containing relevant month and year information
     * @return a label to display for the given month/year
     */
    CharSequence format(CalendarDay day);
}
