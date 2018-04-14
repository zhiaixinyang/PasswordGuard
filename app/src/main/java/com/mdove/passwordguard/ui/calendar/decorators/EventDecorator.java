package com.mdove.passwordguard.ui.calendar.decorators;

import com.mdove.passwordguard.App;
import com.mdove.passwordguard.greendao.DailyPlanDao;
import com.mdove.passwordguard.greendao.entity.DailyPlan;
import com.mdove.passwordguard.ui.calendar.materialcalendarview.CalendarDay;
import com.mdove.passwordguard.ui.calendar.materialcalendarview.DayViewDecorator;
import com.mdove.passwordguard.ui.calendar.materialcalendarview.DayViewFacade;
import com.mdove.passwordguard.ui.calendar.materialcalendarview.spans.DotSpan;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

/**
 * Created by dickliu on 2017/4/26.
 */

public class EventDecorator implements DayViewDecorator {

    private int color;
    private DailyPlanDao mDao;
    private QueryBuilder<DailyPlan> mQuery;

    public EventDecorator(int color) {
        this.color = color;
        mDao = App.getDaoSession().getDailyPlanDao();
        mQuery = mDao.queryBuilder();
    }

    @Override
    public boolean shouldDecorate(CalendarDay day) {
//        int days = day.getDay();
//        int year = day.getYear();
//        int month = day.getMonth();
//        Calendar calendar = Calendar.getInstance();
//        calendar.clear();
//        calendar.set(year,month,days);
//        long curTime = calendar.getTimeInMillis();
//        DailyPlan list = mQuery.where(DailyPlanDao.Properties.MTimeStamp.ge(curTime))
//                .unique();
//        if (list != null) {
//            return true;
//        }
        return false;
    }

    @Override
    public void decorate(DayViewFacade view) {
        view.addSpan(new DotSpan(5, color));
    }
}
