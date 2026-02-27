package com.project.demo.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.demo.dto.BookDto;
import com.project.demo.entity.Book;
import com.project.demo.mapper.MapperBook;
import com.project.demo.repository.BookRepository;
import com.project.demo.service.BookService;
import com.project.demo.specifications.BookSpecifications;

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

		Book book = repository.findById(id).orElseThrow(() -> new RuntimeException("Book not found with ID " + id));

		return mapper.toDto(book);
	}

	@Override
	@Transactional(readOnly = true)
	public List<BookDto> findByTitle(String title) {

		return repository.findByTitleContainingIgnoreCase(title).stream().map(mapper::toDto).toList();
	}

	@Override
	@Transactional(readOnly = true)
	public List<BookDto> findByAuthor(String author) {

		return repository.findByAuthorContainingIgnoreCase(author).stream().map(mapper::toDto).toList();
	}

	@Override
	@Transactional(readOnly = true)
	public List<BookDto> findByPriceBetween(Double minPrice, Double maxPrice) {

		return repository.findByPriceBetween(minPrice, maxPrice).stream().map(mapper::toDto).toList();
	}

	@Override
	@Transactional(readOnly = true)
	public List<BookDto> getAllBooks() {

		return repository.findAll().stream().map(mapper::toDto).collect(Collectors.toList());
	}

	@Override
	@Transactional(readOnly = true)
	public Page<BookDto> filterBooks(
			String title, 
			String author, 
			Integer publicationYear, 
			Double minPrice, 
			Double maxPrice, 
			Integer page, 
			Integer size, 
			String sortBy, 
			String sortDir) {
		
		//Building specification by combining the filters
		Specification<Book> spec = Specification
				.where(BookSpecifications.searchByTitle(title))
				.and(BookSpecifications.searchByAuthor(author))
				.and(BookSpecifications.searchByPublicationYear(publicationYear))
				.and(BookSpecifications.searchByPrice(minPrice, maxPrice));
		
		//Dynamic sort is configured
		Sort sort = Sort.by(sortBy != null ? sortBy : "id");
		sort = "desc".equalsIgnoreCase(sortDir) ? sort.descending() : sort.ascending();
		
		//Pageable is created
		Pageable pageable = PageRequest.of(page != null ? page : 0, size != null ? size : 5, sort);
		
		//The query is executed and mapped to a dto, maintaining the page
		return repository.findAll(spec, pageable).map(mapper::toDto);
				
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
		book.setDischargeDate(
				bookDto.getDischargeDate() != null ? java.time.LocalDate.parse(bookDto.getDischargeDate()) : null);

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
