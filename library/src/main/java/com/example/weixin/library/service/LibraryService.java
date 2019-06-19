package com.example.weixin.library.service;

import org.springframework.data.domain.Page;

import com.example.weixin.library.domain.Book;

public interface LibraryService {

	Page<Book> search(String keyword, int pageNumber);

}
