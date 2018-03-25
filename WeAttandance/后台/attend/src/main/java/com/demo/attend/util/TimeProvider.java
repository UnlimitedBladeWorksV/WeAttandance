package com.demo.attend.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

@Component
public class TimeProvider implements Serializable {


    private static final long serialVersionUID = -3301695478208950415L;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final long ONE_SECOND_IN_MILLIS = 1000; //one second millisecs
    private final long ONE_MINUTE_IN_MILLIS=60000;//one minute millisecs
    private final  long ONE_DAY_IN_MILLIS = 86400000; // one day millisecs
    public Date now() {
        return new Date();
    }

    public Date overDuebyMillis(int millis) {
        Calendar date = Calendar.getInstance();
        logger.info(date.getTime()+" Time Provider");

        return new Date(date.getTimeInMillis() + millis);
    }

    public Date overDuebySecond(int sec) {
        Calendar date = Calendar.getInstance();
        logger.info(date.getTime()+" Time Provider");

        return new Date(date.getTimeInMillis() + (long)(sec * ONE_SECOND_IN_MILLIS));
    }

    public Date overDuebyMinute(int min) {
        Calendar date = Calendar.getInstance();
        logger.info(date.getTime()+" Time Provider");

        return new Date(date.getTimeInMillis() + (long)(min * ONE_MINUTE_IN_MILLIS));
    }

    public Date overDuebyDay(int day) {
        Calendar date = Calendar.getInstance();
        logger.info(date.getTime()+" Time Provider");

        return new Date(date.getTimeInMillis() + (long)(day * ONE_DAY_IN_MILLIS));
    }
}