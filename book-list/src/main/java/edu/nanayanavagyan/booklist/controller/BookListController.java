package edu.nanayanavagyan.booklist.controller;

import java.lang.reflect.ParameterizedType;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import edu.nanayanavagyan.booklist.models.Book;
import edu.nanayanavagyan.booklist.models.BookUnit;
import edu.nanayanavagyan.booklist.models.Rating;
import edu.nanayanavagyan.booklist.models.ReaderRating;

@RestController
@RequestMapping("/bookList")
public class BookListController {

    @Autowired
    private RestTemplate restTemplate;


    
    @RequestMapping("/{readerId}")
    public List<BookUnit> getBooks(@PathVariable("readerId") String readerId) {



        
        //get all rated book IDs
        ReaderRating ratings = restTemplate.getForObject("http://localhost:8083/ratings/reader/" + readerId, ReaderRating.class);


          //For each book, get its details and add all these books into a list

        return ratings.getReaderRating().stream().map(rating -> {

            //For each book Id, get its details from the book information service
            Book book = restTemplate.getForObject("http://localhost:8081/books/" + rating.getBookId(), Book.class);
            //Put all the books together
            return new BookUnit(book.getName(), "This is a good book!", rating.getRating());

        })
        .collect(Collectors.toList());    
        
    }
}
