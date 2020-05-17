package com.shahinnazarov.gradle;

import org.junit.Assert;
import org.junit.Test;

import java.util.regex.Pattern;

public class RegexTest {

    @Test
    public void test() {
        boolean matchingResult = Pattern.matches("[$][{]([A-Za-z0-9._-]+)[}]", "${hey}");
        Assert.assertTrue(matchingResult);
    }
}
