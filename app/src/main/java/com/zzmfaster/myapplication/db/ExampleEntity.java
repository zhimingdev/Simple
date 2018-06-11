package com.zzmfaster.myapplication.db;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;

@Entity
public class ExampleEntity {
    @Id
    public long id;
    //账本分类
    private int bookSort;
    private int year;
    private int month;
    private int day;
    private long time;
    private String bookNote;
    private double amount;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getBookSort() {
        return bookSort;
    }

    public void setBookSort(int bookSort) {
        this.bookSort = bookSort;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getBookNote() {
        return bookNote;
    }

    public void setBookNote(String bookNote) {
        this.bookNote = bookNote;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}