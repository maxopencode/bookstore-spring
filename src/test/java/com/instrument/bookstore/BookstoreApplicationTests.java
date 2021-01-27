package com.instrument.bookstore;

import com.instrument.bookstore.controller.AuthorController;
import com.instrument.bookstore.controller.BookController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class BookstoreApplicationTests {

	@Autowired
	private AuthorController authorController;

	@Autowired
	private BookController bookController;

	@Test
	void contextLoads() {
		assertThat(authorController).isNotNull();
		assertThat(bookController).isNotNull();
	}

}
