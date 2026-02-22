package com.project.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.demo.entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

}
