package com.project.demo.common;

import java.util.Arrays;

public enum BookSortField {

    ID("id"),
    TITLE("title"),
    PRICE("price"),
    PUBLICATION_YEAR("publicationYear");

    private final String fieldName;

    BookSortField(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getFieldName() {
        return fieldName;
    }

    public static BookSortField from(String value) {
        return Arrays.stream(values())
                .filter(f ->
                        f.name().equalsIgnoreCase(value) ||
                        f.fieldName.equalsIgnoreCase(value)
                )
                .findFirst()
                .orElse(TITLE);
    }
}