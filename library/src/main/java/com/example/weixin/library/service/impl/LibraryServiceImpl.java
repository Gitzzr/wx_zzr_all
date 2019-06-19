package com.example.weixin.library.service.impl;

import java.util.LinkedList;

import com.example.weixin.library.domain.Book;
import com.example.weixin.library.domain.DebitList;
import com.example.weixin.library.service.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class LibraryServiceImpl implements LibraryService {

	@Autowired
	private BookRepository bookRepository;

	@Override
	public Page<Book> search(String keyword, int pageNumber) {

		// 创建分页条件，页码从外面（页面）传入，每页固定最多显示3条数据
		Pageable pageable = PageRequest.of(pageNumber, 3);

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

	@Override
	public void add(String id, DebitList list) {
		if (list.getBooks() == null) {
			list.setBooks(new LinkedList<>());
		}

		// 1.判断id对应的图书是否在借阅列表中。
		boolean exists = false;
		for (Book book : list.getBooks()) {
			if (book.getId().equals(id)) {
				// 图书已经存在
				exists = true;
				break;
			}
		}
		if (!exists) {
			// 2.如果不存在，则根据id查询图书。
			// 函数式编程
			this.bookRepository.findById(id)
					// 3.把图书加入借阅列表。
					.ifPresent(list.getBooks()::add);

			// 这种是命令式编程
//			Book book = this.bookRepository.findById(id)
//					// 如果Book不存在，返回null
//					.orElse(null);
//			if (book != null) {
//				list.getBooks().add(book);
//			}

			// Lambda表达式的演进语句
//			this.bookRepository.findById(id)//
//					.ifPresent(new Consumer<Book>() {
//						@Override
//						public void accept(Book book) {
//							list.getBooks().add(book);
//						}
//					});

//			this.bookRepository.findById(id)//
//					.ifPresent((Book book) -> {
//						list.getBooks().add(book);
//					});

//			this.bookRepository.findById(id)//
//					.ifPresent(book -> list.getBooks().add(book));
		}
	}

	@Override
	public void remove(String id, DebitList list) {
		list.getBooks()
				// 把集合转换为Stream
				.stream()
				// 把id相同的图书找出来
				// filter在调用find方法的时候才会（延迟）执行，并且由于调用的是findFirst，
				// 那么在找到第一个结果以后，就不会继续执行filter。
				.filter(book -> book.getId().equals(id))
				// 找到过滤后的第一个结果
				.findFirst()
				// 把找到的图书，从集合里面删除
				.ifPresent(list.getBooks()::remove);

//		Book book = null;
//		// 循环所有的图书
//		for (Book b : list.getBooks()) {
//			// 过滤需要的图书
//			if (b.getId().equals(id)) {
//				// 找到需要的图书以后，跳出循环
//				book = b;
//				break;
//			}
//		}
//		// 从集合里面删除图书
//		list.getBooks().remove(book);
	}
}
