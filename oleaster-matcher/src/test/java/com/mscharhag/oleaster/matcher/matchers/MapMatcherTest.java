package com.mscharhag.oleaster.matcher.matchers;

import com.mscharhag.oleaster.runner.OleasterRunner;
import org.junit.runner.RunWith;

import java.util.LinkedList;
import java.util.List;

import static com.mscharhag.oleaster.runner.StaticRunnerSupport.*;

@RunWith(OleasterRunner.class)
public class MapMatcherTest {{
    describe("MapMatcher test", () -> {
        it("bla", () -> {
            final List<String> list = new LinkedList<>();
            list.add("one");
            list.add("two");
            list.add("three");


        });
    });
}}
