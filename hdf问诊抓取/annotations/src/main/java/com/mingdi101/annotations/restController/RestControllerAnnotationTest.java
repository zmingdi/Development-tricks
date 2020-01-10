package com.mingdi101.annotations.restController;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mingdi101.annotations.cache.BookCache;
import com.mingdi101.annotations.exception.NoBookFoundException;
import com.mingdi101.annotations.model.Book;

@RestController
@RequestMapping("/rest")
public class RestControllerAnnotationTest {

	@Autowired
	BookCache bookCache;
	
	@GetMapping("/findByName")
	public Book findBookByName(@RequestParam("bookName") String bookName) throws NoBookFoundException {
		try {
			Book book = bookCache.findByName(bookName);
			return book;
		}catch(NoSuchElementException e) {
			throw new NoBookFoundException("no book with name: " + bookName + "found");
		}
	}
	@GetMapping("/findByName2")
	public Book findBookByName2(@RequestParam("bookName") String bookName) throws NoBookFoundException {
		try {
			Book book = bookCache.findByName(bookName);
			return book;
		}catch(NoSuchElementException e) {
			throw new NoBookFoundException("no book with name: " + bookName + " found!");
		}
	}
}
