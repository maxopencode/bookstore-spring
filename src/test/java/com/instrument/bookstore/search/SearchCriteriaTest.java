package com.instrument.bookstore.search;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SearchCriteriaTest {

    @Test
    public void testParseReturnsNullWhenFilterNull() {
        SearchCriteria searchCriteria = SearchCriteria.parse(null);
        assertNull(searchCriteria, "Search criteria should be null");
    }

    @Test
    public void testParseReturnsNullWhenFilterEmpty() {
        SearchCriteria searchCriteria = SearchCriteria.parse("");
        assertNull(searchCriteria, "Search criteria should be null");
    }

    @Test
    public void testParseReturnsNullWhenFilterBlank() {
        SearchCriteria searchCriteria = SearchCriteria.parse("   ");
        assertNull(searchCriteria, "Search criteria should be null");
    }

    @Test
    public void testParseCreateSingleCriteria() {
        SearchCriteria searchCriteria = SearchCriteria.parse("author:Franz Kafka");

        assertNotNull(searchCriteria, "Search criteria shouldn't be null");
        assertEquals(searchCriteria.size(), 1, "Size should be 1");
        assertEquals(searchCriteria.getStringValue("author"), "Franz Kafka");
    }

    @Test
    public void testParseCreateMultipleCriteria() {
        SearchCriteria searchCriteria = SearchCriteria.parse("publicationYear:1915, author:Franz Kafka");

        assertNotNull(searchCriteria, "Search criteria shouldn't be null");
        assertEquals(searchCriteria.size(), 2, "Size should be 2");
        assertEquals(searchCriteria.getIntegerValue("publicationYear"), 1915);
        assertEquals(searchCriteria.getStringValue("author"), "Franz Kafka");
    }

}