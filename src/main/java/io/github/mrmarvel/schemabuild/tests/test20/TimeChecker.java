package io.github.mrmarvel.schemabuild.tests.test20;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class TimeChecker {
    String maybe_date;
    Date date;

    private static final String TIMEZONE_MOSCOW = "GMT+3";
    private static final String TIMEZONE_VLADIVOSTOK = "GMT+10";
    private static final String DATETIME_FORMAT = "dd.MM.yyyy HH:mm";
    private final TimeZone tz_mos = TimeZone.getTimeZone(TIMEZONE_MOSCOW);

    public TimeChecker(String Maybe_date) throws ParseException {
        maybe_date = Maybe_date;
        SimpleDateFormat formatter = new SimpleDateFormat(DATETIME_FORMAT);
        formatter.setTimeZone(tz_mos);
        date = formatter.parse(maybe_date);
    }
    public String getCurrentTime() {//sb test 20 08.11.2021 2:52
        TimeZone tz_vlad = TimeZone.getTimeZone(TIMEZONE_VLADIVOSTOK);
        SimpleDateFormat sdf_mos = new SimpleDateFormat(DATETIME_FORMAT);
        SimpleDateFormat sdf_vlad = new SimpleDateFormat(DATETIME_FORMAT);
        sdf_mos.setTimeZone(tz_mos);
        sdf_vlad.setTimeZone(tz_vlad);
        return String.format("Moscow date: %s\nVladivostok date: %s", sdf_mos.format(date), sdf_vlad.format(date));
    }
}
