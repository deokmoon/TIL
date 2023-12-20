package com.til.gof.iterator;

import java.util.Iterator;

public class IteratorMain {
    public static void main(String[] args) {
        BookShelf bookShelf = new BookShelf(4);
        bookShelf.appendBook(new Book("Java"));
        bookShelf.appendBook(new Book("Oracle"));
        bookShelf.appendBook(new Book("Redis"));
        bookShelf.appendBook(new Book("Kafka"));

        // 명시적으로 사용
        Iterator<Book> it = bookShelf.iterator();
        while (it.hasNext()) {
            Book book = it.next();
            System.out.println(book.getName());
        }
        System.out.println();

        // for each 사용
        for (Book book : bookShelf) {
            System.out.println(book.getName());
        }
        System.out.println();
    }
}
