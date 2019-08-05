package com.chen.sqlite;

/**
 * @description:
 * @author: Chenyz
 * @date: 2019/8/5 11:37
 */
public class Category {

    private int id;
    private String categoryName;
    private String categoryCode;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode;
    }
}
