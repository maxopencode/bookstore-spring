package com.instrument.bookstore.search;

import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// see https://www.baeldung.com/rest-api-search-language-spring-data-specifications
public class SearchCriteria {

    private static final Pattern NAME_VALUE_PATTERN = Pattern.compile("(\\w+?):(.+?),", Pattern.UNICODE_CHARACTER_CLASS);

    private final Map<String, Object> nameToValueMap = new HashMap<>();

    private SearchCriteria() {
    }

    public static SearchCriteria parse(String filter) {
        if (StringUtils.isBlank(filter)) {
            return null;
        }

        SearchCriteria criteria = new SearchCriteria();
        Matcher matcher = NAME_VALUE_PATTERN.matcher(filter + ",");
        while (matcher.find()) {
            criteria.add(matcher.group(1), matcher.group(2));
        }
        return criteria;
    }

    public String getStringValue(String name) {
        return MapUtils.getString(nameToValueMap, name);
    }

    public Integer getIntegerValue(String name) {
        return MapUtils.getInteger(nameToValueMap, name);
    }

    public void add(String name, String value) {
        nameToValueMap.put(name, value);
    }

    public boolean isEmpty() {
        return nameToValueMap.isEmpty();
    }

    public int size() {
        return nameToValueMap.size();
    }
}
