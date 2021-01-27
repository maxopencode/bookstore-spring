package com.instrument.bookstore.repo;

import com.instrument.bookstore.model.Book;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookRepository extends PagingAndSortingRepository<Book, Long>,
        JpaSpecificationExecutor<Book>, BookRepositoryCustom {

    Optional<Book> findByIsbn(String isbn);

    void deleteByIsbn(String isbn);
}
