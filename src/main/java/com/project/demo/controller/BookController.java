package com.project.demo.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.demo.dto.BookDto;
import com.project.demo.service.BookService;

@RestController
@RequestMapping("/books")
public class BookController {
	
	private final BookService service;
	
	public BookController(BookService service) {
		this.service = service;
	}
	
	@GetMapping("/book/{id}")
	public ResponseEntity<BookDto> getBook(@PathVariable Long id) {
		return ResponseEntity.ok(service.getBookById(id));
	}
	
	@GetMapping("/allBooks")
	public ResponseEntity<List<BookDto>> getAllBooks() {
		return ResponseEntity.ok(service.getAllBooks());
	}
	
	@GetMapping("/bookByTitle")
	public ResponseEntity<List<BookDto>> searchByTitle(@RequestParam String title) {
		return ResponseEntity.ok(service.findByTitle(title));
	}
	
	@GetMapping("/bookByAuthor")
	public ResponseEntity<List<BookDto>> searchByAuthor(@RequestParam String author) {
		return ResponseEntity.ok(service.findByTitle(author));
	}
	
	@PostMapping("/create")
	public ResponseEntity<BookDto> createBook(@RequestBody BookDto bookDto){
		return ResponseEntity.ok(service.createBook(bookDto));
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<BookDto> updateBook(@RequestBody BookDto bookDto, @PathVariable Long id){
		return ResponseEntity.ok(service.updateBook(bookDto, id));
	}
	
	@PatchMapping("/updateTitle/{id}")
	public ResponseEntity<BookDto> updateBookTitle(@PathVariable Long id, @RequestParam String title){
		return ResponseEntity.ok(service.updateBookTitle(id, title));
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Long> deleteBook(Long id) {
		return ResponseEntity.ok(service.deleteBook(id));
	}
}
