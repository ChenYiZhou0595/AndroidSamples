package com.chen.androidsamples;

import org.junit.Test;

import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

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

    @Test
    public void addition_isCorrect3() {
        Map<String, Object> map = new HashMap<>();
        map.put("a", "a");
        System.out.println(map.get("b"));
    }

    @Test
    public void addition_isCorrect4() {
        URL url;
        try {
            url = new URL("qwewqe");
            InputStream in = url.openStream();
            System.out.println("连接可用");
        } catch (Exception e) {
            System.out.println("连接打不开!");
            url = null;
        }
    }
}