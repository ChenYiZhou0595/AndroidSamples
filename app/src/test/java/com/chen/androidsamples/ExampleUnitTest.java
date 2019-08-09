package com.chen.androidsamples;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        String s1 = StringUtils.num2thousand00("11");
        String s2 = StringUtils.num2thousand00("11.29");
        String s3 = StringUtils.num2thousand00("11.229");
        String s4 = StringUtils.num2thousand00("123456");
        String s5 = StringUtils.num2thousand00("12345678952.19");
        String s6 = StringUtils.num2thousand00("1324567894213.1313245613");
        System.out.println(s1);
        System.out.println(s2);
        System.out.println(s3);
        System.out.println(s4);
        System.out.println(s5);
        System.out.println(s5);
        System.out.println(s6);
    }

    @Test
    public void addition_isCorrect2() {
        System.out.println(StringUtils.isValid("([{{{()}}}]){}{}{{{}}}"));
    }
}