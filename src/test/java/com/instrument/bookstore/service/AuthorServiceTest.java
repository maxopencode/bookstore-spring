package com.instrument.bookstore.service;

import com.instrument.bookstore.model.Author;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;

import static com.instrument.bookstore.helper.AuthorTestHelper.newAuthor;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
@ActiveProfiles("test")
public class AuthorServiceTest {

	@Autowired
	private AuthorService service;

	@Test
	public void testShouldCreateAuthor() {
		LocalDate birthdate = LocalDate.of(1799, 5, 26);
		Author author = newAuthor(null, "Aleksandr Pushkin", birthdate);

		Author createdAuthor = service.create(author);
		Author loadedAuthor = service.findById(createdAuthor.getId());

		assertThat("Author shouldn't be null", loadedAuthor, notNullValue());
		assertThat("Author id shouldn't be null", loadedAuthor.getId(), notNullValue());
		assertThat("Author name doesn't match", loadedAuthor.getName(), equalTo("Aleksandr Pushkin"));
		assertThat("Author birthdate doesn't match", loadedAuthor.getBirthdate(), equalTo(birthdate));
	}

	@Test
	public void shouldReturnNullForNotExistingAuthor() {
		Author author = service.findById(12345L);

		assertNull(author, "Author should be null");
	}

	@Test
	public void testShouldUpdateAuthor() {
		LocalDate birthdate = LocalDate.of(1799, 5, 26);
		Author author = newAuthor(null, "Aleksandr Pushkin", birthdate);


		Author createdAuthor = service.create(author);

		LocalDate birthdate2 = LocalDate.of(1814, 10, 15);
		createdAuthor.setName("Mikhail Lermontov");
		createdAuthor.setBirthdate(birthdate2);
		service.update(createdAuthor.getId(), createdAuthor);

		Author loadedAuthor = service.findById(createdAuthor.getId());

		assertThat("Author shouldn't be null", loadedAuthor, notNullValue());
		assertThat("Author id shouldn't be null", loadedAuthor.getId(), notNullValue());
		assertThat("Author name doesn't match", loadedAuthor.getName(), equalTo("Mikhail Lermontov"));
		assertThat("Author birthdate doesn't match", loadedAuthor.getBirthdate(), equalTo(birthdate2));
	}

	@Test
	public void testShouldDeleteAuthor() {
		LocalDate birthdate = LocalDate.of(1799, 5, 26);
		Author author = newAuthor(null, "Aleksandr Pushkin", birthdate);

		Author createdAuthor = service.create(author);

		service.deleteById(createdAuthor.getId());

		Author loadedAuthor = service.findById(createdAuthor.getId());

		assertNull(loadedAuthor, "Author should be null");
	}
}