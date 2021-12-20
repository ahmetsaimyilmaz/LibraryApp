package com.example.libraryapp.book;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BookService {
    private final BookRepository BookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.BookRepository = bookRepository;
    }

    public List<Book> getBook() {
        return this.BookRepository.findAll();
    }

    public void addNewBook(Book book) {
        this.BookRepository.save(book);
    }

    public Optional<Book> getSingleBook(int i) {
        return this.BookRepository.findById(Integer.valueOf(i));
    }

    public void delete(int theId) {
        this.BookRepository.deleteById(Integer.valueOf(theId));
    }

    public void saveMethod(Book book) {
        this.BookRepository.save(book);
    }
}
