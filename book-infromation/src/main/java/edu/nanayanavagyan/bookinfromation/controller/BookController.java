package edu.nanayanavagyan.bookinfromation.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.nanayanavagyan.bookinfromation.models.Book;

@RestController
@RequestMapping("/books")
public class BookController {

    @RequestMapping("/{bookId}")
    //Change request to get
    public Book getBookInfo(@PathVariable("bookId") Integer bookId) {
        return new Book(bookId, "Anna Karenina","Leo Tolstoy","Novel", 1877);    
    } 
}
