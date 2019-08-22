package com.synectiks.cms.graphql.types.Book;

import com.synectiks.cms.domain.Book;

import java.util.List;

public class RemoveBookPayload {
    private final List<Book> books;

    public RemoveBookPayload(List<Book> books) {
        this.books = books;
    }
}