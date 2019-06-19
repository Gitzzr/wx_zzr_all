package com.example.weixin.library.domain;

import java.util.List;

// 该对象目前不放入数据库
public class DebitList {

	// 准备要借阅的图书列表
	private List<Book> books;

	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}
}
