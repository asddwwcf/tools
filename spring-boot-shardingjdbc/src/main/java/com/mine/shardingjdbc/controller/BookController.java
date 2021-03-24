package com.mine.shardingjdbc.controller;

import com.mine.shardingjdbc.entity.Book;
import com.mine.shardingjdbc.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookController {

    @Autowired
    BookService bookService;

    @RequestMapping(value = "/book", method = RequestMethod.GET)
    public List<Book> getItems(){
        return bookService.getBookList();
    }

    @RequestMapping(value = "/book",method = RequestMethod.POST)
    public Boolean saveItem(Book book){
        return bookService.save(book);
    }
}
