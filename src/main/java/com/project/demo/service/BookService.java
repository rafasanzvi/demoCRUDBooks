package com.project.demo.service;

import java.util.List;

import com.project.demo.dto.BookDto;

public interface BookService {

	//Get
	BookDto getBookById(Long id);
	
	List<BookDto> findByTitle(String title);
	
	List<BookDto> getAllBooks();
	
	List<BookDto> findByAuthor(String author);
	
	List<BookDto> findByPriceBetween(Double minPrice, Double maxPrice);
	
	//Filter with Specs
	List<BookDto> filterBooks(String title, String author, Integer publicationYear, Double minPrice, Double maxPrice);
	
	//Post
	BookDto createBook(BookDto bookDto);
	
	//Put
	BookDto updateBook(BookDto bookDto, Long id);
	
	//Patch
	BookDto updateBookTitle(Long id, String title);
	
	//Delete
	Long deleteBook(Long id);
}
