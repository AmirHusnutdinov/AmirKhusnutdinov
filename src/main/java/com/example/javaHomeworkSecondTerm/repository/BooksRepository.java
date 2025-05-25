package com.example.javaHomeworkSecondTerm.repository;

import com.example.javaHomeworkSecondTerm.model.Book;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BooksRepository extends JpaRepository<Book, UUID> {

}
