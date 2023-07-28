package com.example.demo1.service;


import com.example.demo1.pojo.Book;
import com.example.demo1.pojo.Page;

import java.util.List;

public interface BookService {
    public Page<Book> page(int pageNo, int pageSize);
    public void addBook(Book book);

    public void deleteBookById(Integer id);

    public void updateBook(Book book);

    public Book queryBookById(Integer id);

    public List<Book> queryBooks();

    Page<Book> pageByPrice(int pageNo, int pageSize, int min, int max);
}

