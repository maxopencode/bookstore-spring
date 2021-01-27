package com.instrument.bookstore.search;

import com.google.common.collect.Lists;
import com.instrument.bookstore.model.Author;
import com.instrument.bookstore.model.Book;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.List;

public class BookSpecification implements Specification<Book> {

    private final SearchCriteria criteria;

    public BookSpecification(SearchCriteria criteria) {
        this.criteria = criteria;
    }

    @Override
    public Predicate toPredicate(Root<Book> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        List<Predicate> predicates = Lists.newArrayList();

        Join<Book, Author> authorJoin = root.join("author");

        String author = criteria.getStringValue("author");
        if (StringUtils.isNotBlank(author)) {
            predicates.add(builder.like(
                    builder.lower(authorJoin.get("name")), "%" + author.toLowerCase() + "%"));
        }

        Integer publicationYear = criteria.getIntegerValue("publicationYear");
        if (publicationYear != null) {
            predicates.add(builder.equal(root.get("publicationYear"), publicationYear));
        }

        return builder.and(predicates.toArray(new Predicate[0]));
    }
}
