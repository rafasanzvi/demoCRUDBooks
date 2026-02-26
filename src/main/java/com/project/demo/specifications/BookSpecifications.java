package com.project.demo.specifications;

import org.springframework.data.jpa.domain.Specification;

import com.project.demo.entity.Book;

public class BookSpecifications {

	public static Specification<Book> searchByTitle(String title) {

		return (root, query, cb) -> title == null ? null
				: cb.like(cb.lower(root.get("title")), "%" + title.toLowerCase() + "%");
	}

	public static Specification<Book> searchByAuthor(String author) {

		return (root, query, cb) -> author == null ? null
				: cb.like(cb.lower(root.get("author")), "%" + author.toLowerCase() + "%");
	}

	public static Specification<Book> searchByPublicationYear(Integer publicationYear) {

		return (root, query, cb) -> publicationYear == null ? null
				: cb.equal(root.get("publicationYear"), publicationYear);
	}
	
	public static Specification<Book> searchByPrice(Double minPrice, Double maxPrice) {

		return (root, query, cb) -> {
			if(minPrice == null || maxPrice == null) return null;
			return cb.between(root.get("price"), minPrice, maxPrice);
		}; 
	}
}
