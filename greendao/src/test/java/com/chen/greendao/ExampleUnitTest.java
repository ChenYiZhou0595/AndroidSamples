package com.chen.greendao;

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
        StringBuffer stringBuffer = new StringBuffer("18850211512");
        StringBuffer replace = stringBuffer.replace(3, 7, "*");
        System.out.println(replace.toString());
    }
}