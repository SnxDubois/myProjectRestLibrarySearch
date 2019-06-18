package com.myLibrary.bookLibrary.Controller;

import com.myLibrary.bookLibrary.Model.Book;
import com.myLibrary.bookLibrary.Repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @GetMapping("/books")
    public List<Book> findAllBooks() {
        return bookRepository.findAll();
    }

    @GetMapping("/books/{id}")
    public Book findOneBook(@PathVariable Long bookId) {
        return bookRepository.findById(bookId).get();
    }

    @PostMapping("/books")
    public Book createBook(@RequestBody Book book) {
        return bookRepository.save(book);
    }

    @PutMapping("/books/{bookId}")
    public Book updateBook(@PathVariable long bookId, @RequestBody Book book) {
        Book bookToUpdate = bookRepository.findById(bookId).get();
        bookToUpdate.setAuthor(book.getAuthor());
        bookToUpdate.setTitle(book.getTitle());
        bookToUpdate.setDescription(book.getDescription());
        return bookRepository.save(bookToUpdate);
    }

    @DeleteMapping("/books/{bookId}")
    public boolean deleteBook(@PathVariable long bookId) {
        bookRepository.deleteById(bookId);
        return true;
    }

    @PostMapping("/books/search")
    public List<Book> searchBook(@RequestBody Map<String, String> body) {
        String searchTerm = body.get("text");
        return bookRepository.findByTitleContainingOrDescriptionContaining(searchTerm, searchTerm);
    }
}
