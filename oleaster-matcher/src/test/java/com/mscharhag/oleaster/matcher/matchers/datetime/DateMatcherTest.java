package com.mscharhag.oleaster.matcher.matchers.datetime;

import static com.mscharhag.oleaster.runner.StaticRunnerSupport.*;

import com.mscharhag.oleaster.matcher.TestUtil;
import com.mscharhag.oleaster.runner.OleasterRunner;
import org.junit.runner.RunWith;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RunWith(OleasterRunner.class)
public class DateMatcherTest {
    private DateMatcher matcher;

    private Date date(String text) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            return format.parse(text);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
{
    describe("DateMatcher tests", () -> {
        beforeEach(() -> {
            matcher = new DateMatcher(date("2015-06-05 12:30:00"));
        });


        describe("when toBeBefore() is called", () -> {
            it("is ok if the stored date is before the passed date", () -> {
                matcher.toBeBefore(date("2015-06-05 12:31:00"));
            });

            it("fails if the stored date is equal to the passed date", () -> {
                TestUtil.expectAssertionError(() -> matcher.toBeBefore(date("2015-06-05 12:30:00")),
                        "Expected '2015-06-05 12:30:00.000' to be before '2015-06-05 12:30:00.000'");
            });

            it("fails if the stored date is after the passed date", () -> {
                TestUtil.expectAssertionError(() -> matcher.toBeBefore(date("2015-06-05 12:29:00")),
                        "Expected '2015-06-05 12:30:00.000' to be before '2015-06-05 12:29:00.000'");
            });
        });

        describe("when toBeAfter() is called", () -> {
            it("is ok if the stored date is after the passed date", () -> {
                matcher.toBeAfter(date("2015-06-05 12:29:00"));
            });

            it("fails if the stored date is equal to the passed date", () -> {
                TestUtil.expectAssertionError(() -> matcher.toBeAfter(date("2015-06-05 12:30:00")),
                        "Expected '2015-06-05 12:30:00.000' to be after '2015-06-05 12:30:00.000'");
            });

            it("fails if the stored date is before the passed date", () -> {
                TestUtil.expectAssertionError(() -> matcher.toBeAfter(date("2015-06-05 12:31:00")),
                        "Expected '2015-06-05 12:30:00.000' to be after '2015-06-05 12:31:00.000'");
            });
        });

        describe("when toBeBetween() is called", () -> {
            it("is ok if the stored date is between the passed values", () -> {
                matcher.toBeBetween(date("2015-06-05 12:29:00"), date("2015-06-05 12:31:00"));
            });

            it("is ok if the stored date is equal to the lower value", () -> {
                matcher.toBeBetween(date("2015-06-05 12:30:00"), date("2015-06-05 12:31:00"));
            });

            it("fails if the stored date is before the lower value", () -> {
                TestUtil.expectAssertionError(() -> matcher.toBeBetween(date("2015-06-05 12:31:00"), date("2015-06-05 12:32:00")),
                        "Expected '2015-06-05 12:30:00.000' to be between '2015-06-05 12:31:00.000' and '2015-06-05 12:32:00.000'");
            });

            it("fails if the stored date is after the upper value", () -> {
                TestUtil.expectAssertionError(() -> matcher.toBeBetween(date("2015-06-05 12:28:00"), date("2015-06-05 12:29:00")),
                        "Expected '2015-06-05 12:30:00.000' to be between '2015-06-05 12:28:00.000' and '2015-06-05 12:29:00.000'");
            });
        });

        describe("when toBeCloseTo() is called", () -> {
            it("is ok if the stored date is closed to the passed value", () -> {
                matcher.toBeCloseTo(date("2015-06-05 12:29:59"), 1001);
            });

            it("fails if the stored date is not close to the passed value", () -> {
                TestUtil.expectAssertionError(() -> matcher.toBeCloseTo(date("2015-06-05 12:29:59"), 999),
                        "Expected '2015-06-05 12:30:00.000' to be close to '2015-06-05 12:29:59.000', delta: 999ms");
            });
        });
    });

}}
