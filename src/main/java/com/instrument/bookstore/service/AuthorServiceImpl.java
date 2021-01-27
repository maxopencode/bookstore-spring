package com.instrument.bookstore.service;

import com.instrument.bookstore.model.Author;
import com.instrument.bookstore.repo.AuthorRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AuthorServiceImpl extends AbstractCrudService<Author, Long, AuthorRepository> implements AuthorService {

    public AuthorServiceImpl(AuthorRepository repository) {
        super(repository);
    }

    protected void beforeCreate(Author author) {
        author.setId(null);
    }

    @Override
    protected void updateState(Author existingAuthor, Author author) {
        if (StringUtils.isNotBlank(author.getName())) {
            existingAuthor.setName(author.getName());
        }
        if (author.getBirthdate() != null) {
            existingAuthor.setBirthdate(author.getBirthdate());
        }
    }
}
