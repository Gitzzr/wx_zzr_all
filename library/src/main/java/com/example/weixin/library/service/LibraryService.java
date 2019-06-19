package com.example.weixin.library.service;

import org.springframework.data.domain.Page;

import com.example.weixin.library.domain.Book;
import com.example.weixin.library.domain.DebitList;

public interface LibraryService {

	Page<Book> search(String keyword, int pageNumber);

	void add(String id, DebitList list);

	void remove(String id, DebitList list);

}
