package com.example.demo.service;

import com.example.demo.model.Book;
import com.example.demo.repository.BookRepository;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class BookService {

  private static final Logger logger = LoggerFactory.getLogger(BookService.class);
  private final BookRepository bookRepository;

  public BookService(BookRepository bookRepository) {
    this.bookRepository = bookRepository;
  }

  public List<Book> getAllBooks() {
    logger.info("Fetching all books from the database");
    return bookRepository.findAll();
  }

  public Optional<Book> getBookById(Long id) {
    logger.info("Fetching book with ID: {}", id);
    return bookRepository.findById(id);
  }

  public Book createBook(Book book) {
    logger.info("Creating a new book: {}", book);
    return bookRepository.save(book);
  }

  public Book updateBook(Long id, Book updatedBook) {
    logger.info("Updating book with ID: {}", id);
    Book existingBook = bookRepository.findById(id).orElseThrow();
    existingBook.setTitle(updatedBook.getTitle());
    existingBook.setAuthor(updatedBook.getAuthor());
    return bookRepository.save(existingBook);
  }

  public void deleteBook(Long id) {
    logger.info("Deleting book with ID: {}", id);
    bookRepository.deleteById(id);
  }

  public void partiallyUpdateBook(Long id, Book updatedBook) {
    logger.info("Partially updating book with ID: {}", id);
  }
}
