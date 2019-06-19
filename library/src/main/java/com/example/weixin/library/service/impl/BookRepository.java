package com.example.weixin.library.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.weixin.library.domain.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, String> {

	Page<Book> findByDisabledFalse(Pageable pageable);

	Page<Book> findByNameContainingAndDisabledFalse(String keyword, Pageable pageable);
}
