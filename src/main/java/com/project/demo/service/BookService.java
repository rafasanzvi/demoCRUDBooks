package com.project.demo.service;

import java.util.List;

import com.project.demo.dto.BookDto;

public interface BookService {

	//Get
	BookDto getBookById(Long id);
	
	List<BookDto> getAllBooks();
	
	//Post
	BookDto createBook(BookDto bookDto);
	
	//Put
	BookDto updateBook(BookDto bookDto, Long id);
	
	//Patch
	BookDto updateBookTitle(Long id, String title);
	
	//Delete
	Long deleteBook(Long id);
}
