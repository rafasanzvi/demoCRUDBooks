package com.project.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.demo.entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

	
	List<Book> findByTitleContainingIgnoreCase(String title);
}
