package com.mingdi101.annotations.restController;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.Lists;
import com.mingdi101.annotations.model.Author;
import com.mingdi101.annotations.model.Book;
import com.mingdi101.annotations.model.BookInfo;

@RestController
@RequestMapping("/bind")
public class DataBinderRestController {

	/**
	 * Binding data with prefix, use following request URL will create book and
	 * author object separately.
	 * binder defined in ApiResultHandler
	 * POST
	 * http://localhost:8080/bind/preBind?book.name=1111&isbn=2222&price=3333&author.name=4444&age=555
	 * HTTP/1.1
	 */
	@PostMapping("/preBind")
	public BookInfo bindData(@ModelAttribute("book") Book book, @ModelAttribute("author") Author author) {
		return new BookInfo(book, author);
	}
	
	
	/**
	 * one request takes 2 object, book1 is obtained from request body, book2 is from url attrs
	 * full request is as follow:
	 POST http://localhost:8080/bind/twoBooks?name=book2&isbn=2222&price=2.22 HTTP/1.1
		Accept-Encoding: gzip,deflate
		Content-Type: application/json
		Content-Length: 50
		Host: localhost:8080
		Connection: Keep-Alive
		User-Agent: Apache-HttpClient/4.1.1 (java 1.5)
		
		{"name":"book1","isbn":"1111","price":"1.111"}
	 * @param book1
	 * @param book2
	 * @return
	 */
	@PostMapping("/twoBooks")
	public List<Book> twoBooks(@RequestBody Book book1,Book book2) {
		return Lists.newArrayList(book1, book2);
	}
	@PostMapping("/twoBooksFromUrl")
	public List<Book> twoBooks2(@RequestParam("b1") Book book1,@RequestParam("b2") Book book2) {
		return Lists.newArrayList(book1, book2);
	}
}
