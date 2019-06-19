package com.example.weixin.library.comtroller;

import com.example.weixin.library.domain.DebitList;
import com.example.weixin.library.service.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.context.request.WebRequest;

@Controller
@RequestMapping("/zzr_1/library/debit")
// 只要在@SessionAttributes里面列出来的名字，那么就会自动加入Session里面，并且在访问控制器的时候会从Session里面取出来。
// 以后只需要把对象放入Model里面，名字在@SessionAttributes列出，对象自动会进入Session。
@SessionAttributes({ "debitList" })
public class DebitController {

	@Autowired
	private LibraryService libraryService;

	@RequestMapping
	public String debit(@RequestParam("id") String id, WebRequest request) {

		DebitList list = (DebitList) request.getAttribute("debitList", WebRequest.SCOPE_SESSION);
		// 必须要保证list不能为null
		if (list == null) {
			list = new DebitList();
			// 把新建的list放入Session里面
			request.setAttribute("debitList", list, WebRequest.SCOPE_SESSION);
		}

		this.libraryService.add(id, list);

		// 添加完成以后，重定向到借阅列表显示的页面
		return "redirect:/zzr_1/library/debit/list";
	}

	@RequestMapping("list")
	public String list() {
		return "/WEB-INF/views/library/debit/list.jsp";
	}

	// 大括号里面是一个【路径参数】，参数的名称随意。
	// 所谓的路径参数：把路径的一部分作为参数来使用。在REST规范中，每个资源都有唯一的一个URL。
	// /book 表示所有书
	// /book/1 表示id为1的数
	// 针对这些资源的CRUD，通过HTTP的方法来区分
	// 路径参数都是通过@PathVariable注解来获取的，里面的字符串要跟路径参数大括号里面的字符串相同
	@RequestMapping("remove/{id}")
	public String remove(@PathVariable("id") String id,
			// 从Session里面或名为debitList的对象，并且自动强制类型转换
			@SessionAttribute("debitList") DebitList list) {
		this.libraryService.remove(id, list);
		return "redirect:/zzr_1/library/debit/list";
	}
}
