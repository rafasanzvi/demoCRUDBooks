package com.project.demo.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.demo.dto.BookDto;
import com.project.demo.entity.Book;
import com.project.demo.mapper.MapperBook;
import com.project.demo.repository.BookRepository;
import com.project.demo.service.BookService;

@Service
@Transactional
public class BookServiceimpl implements BookService {
	
	private final BookRepository repository;
	private final MapperBook mapper;
	
	public BookServiceimpl(BookRepository repository, MapperBook mapper) {
		this.repository = repository;
		this.mapper = mapper;
	}

	@Override
	@Transactional(readOnly = true)
	public BookDto getBookById(Long id) {

		Book book = repository.findById(id)
				.orElseThrow(() -> new RuntimeException("Book not found with ID " + id));

		return mapper.toDto(book);
	}

	@Override
	@Transactional(readOnly = true)
	public List<BookDto> getAllBooks() {

			return repository.findAll()
				.stream()
				.map(mapper::toDto)
				.collect(Collectors.toList());
	}

	@Override
	public BookDto createBook(BookDto bookDto) {
		
		Book book = mapper.toEntity(bookDto);
		Book saved = repository.save(book);
		
		return mapper.toDto(saved);
	}

	@Override
	public BookDto updateBook(BookDto bookDto, Long id) {
		
		Book book = repository.findById(id).orElseThrow(() -> new RuntimeException("Book not found with id " + id));
		
			book.setTitle(bookDto.getTitle());
			book.setAuthor(bookDto.getAuthor());
			book.setPublicationYear(bookDto.getPublicationYear());
			book.setIsbn(bookDto.getIsbnCode());
			book.setDischargeDate(bookDto.getDischargeDate() != null ? java.time.LocalDate.parse(bookDto.getDischargeDate()) : null);
		
		return mapper.toDto(book);
			
	}

	@Override
	public BookDto updateBookTitle(Long id, String title) {
		
		Book book = repository.findById(id).orElseThrow(() -> new RuntimeException("Book not found with id " + id));
		
		book.setTitle(title);
		
		Book saved = repository.save(book);
		
		return mapper.toDto(saved);
	}

	@Override
	public Long deleteBook(Long id) {

		Book book = repository.findById(id).orElseThrow(() -> new RuntimeException("Book not found with id " + id));
		
		repository.delete(book);
		
		return id;
	}

}
