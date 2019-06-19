package com.example.weixin.library.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.weixin.library.domain.Book;
import com.example.weixin.library.service.LibraryService;

@Service
public class LibraryServiceImpl implements LibraryService {

	@Autowired
	private BookRepository bookRepository;

	@Override
	public Page<Book> search(String keyword, int pageNumber) {

		// 创建分页条件，页码从外面（页面）传入，每页固定最多显示3条数据
		Pageable pageable = PageRequest.of(pageNumber, 10);

		Page<Book> page;
		if (StringUtils.isEmpty(keyword)) {
			// 没有查询的关键字
			// where disabled == false
			page = this.bookRepository.findByDisabledFalse(pageable);
		} else {
			// 有关键字，要根据关键字来查询数据
			// where name like '%' + keyword + '%' and disabled == false
			page = this.bookRepository.findByNameContainingAndDisabledFalse(keyword, pageable);
		}

		return page;
	}
}
