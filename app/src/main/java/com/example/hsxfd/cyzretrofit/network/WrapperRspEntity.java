package com.example.hsxfd.cyzretrofit.network;

public class WrapperRspEntity<T> {
    private int count;
    private T books;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public T getBooks() {
        return books;
    }

    public void setBooks(T books) {
        this.books = books;
    }

    public WrapperRspEntity(int count, T books) {
        this.count = count;
        this.books = books;
    }
}
