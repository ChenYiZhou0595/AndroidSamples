package com.chen.greendao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * @description:
 * @author: Chenyz
 * @date: 2019/8/5 11:21
 */
@Entity
public class Book {

    // 用 int 会报错
    @Id(autoincrement = true)
    private long id;
    private String name;
    private String author;
    private double price;
    private int pages;
    private String press;

    @Generated(hash = 1062014619)
    public Book(long id, String name, String author, double price, int pages,
            String press) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.price = price;
        this.pages = pages;
        this.press = press;
    }
    @Generated(hash = 1839243756)
    public Book() {
    }
    public long getId() {
        return this.id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getAuthor() {
        return this.author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public double getPrice() {
        return this.price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public int getPages() {
        return this.pages;
    }
    public void setPages(int pages) {
        this.pages = pages;
    }
    public String getPress() {
        return this.press;
    }
    public void setPress(String press) {
        this.press = press;
    }
}
