package com.mscharhag.oleaster.matcher.matchers.datetime;

import com.mscharhag.oleaster.matcher.matchers.ObjectMatcher;
import com.mscharhag.oleaster.matcher.util.Arguments;

import java.text.SimpleDateFormat;
import java.util.Date;

import static com.mscharhag.oleaster.matcher.util.Expectations.expectNotNull;
import static com.mscharhag.oleaster.matcher.util.Expectations.expectTrue;

public class DateMatcher extends ObjectMatcher<Date> {

    protected static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");


    public DateMatcher(Date date) {
        super(date);
    }

    private String formatDate(Date date) {
        return dateFormat.format(date);
    }


    public void toBeBefore(Date otherDate) {
        Arguments.ensureNotNull(otherDate, "Cannot test if a date is before null, otherDate cannot be null");
        expectNotNull(this.getValue(), "Expected null to be before '%s'", this.formatDate(otherDate));
        expectTrue(this.getValue().before(otherDate), "Expected '%s' to be before '%s'",
                this.formatDate(this.getValue()), this.formatDate(otherDate));
    }


    public void toBeAfter(Date otherDate) {
        Arguments.ensureNotNull(otherDate, "Cannot test if a date is after null, otherDate cannot be null");
        expectNotNull(this.getValue(), "Expected null to be after '%s'", this.formatDate(otherDate));
        expectTrue(this.getValue().after(otherDate), "Expected '%s' to be after '%s'",
                this.formatDate(this.getValue()), this.formatDate(otherDate));
    }


    public void toBeBetween(Date first, Date second) {
        Arguments.ensureNotNull(first, "first cannot be null");
        Arguments.ensureNotNull(second, "second cannot be null");
        Arguments.ensureTrue(first.before(second), "first date needs to be before second date");
        expectNotNull(this.getValue(), "Expected null to be between '%s' and '%s'",
                this.formatDate(first), this.formatDate(second));
        expectTrue((this.getValue().equals(first) || this.getValue().after(first)) && this.getValue().before(second),
                "Expected '%s' to be between '%s' and '%s'", this.formatDate(this.getValue()), this.formatDate(first), this.formatDate(second));
    }


    public void toBeCloseTo(Date otherDate, long deltaMillis) {
        Arguments.ensureNotNull(otherDate, "otherDate cannot be null");
        Arguments.ensureTrue(deltaMillis > 0, "deltaMillis need to be greater than zero");
        expectNotNull(this.getValue(), "Expected null to be close to '%s', delta: %sms",
                this.formatDate(otherDate), deltaMillis);

        long millis = otherDate.getTime();
        Date min = new Date(millis - deltaMillis);
        Date max = new Date(millis + deltaMillis);

        expectTrue(this.getValue().after(min) && this.getValue().before(max),
                "Expected '%s' to be close to '%s', delta: %sms", this.formatDate(this.getValue()),
                this.formatDate(otherDate), deltaMillis);
    }

}
