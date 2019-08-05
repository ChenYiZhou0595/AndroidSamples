package com.chen.sqlite;

import org.litepal.crud.LitePalSupport;

/**
 * @description:
 * @author: Chenyz
 * @date: 2019/8/5 11:21
 */
public class Book extends LitePalSupport {

    private int id;
    private String name;
    private String author;
    private double price;
    private int pages;
    private String press;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public String getPress() {
        return press;
    }

    public void setPress(String press) {
        this.press = press;
    }
}
