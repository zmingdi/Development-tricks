package com.mingdi101.annotations.cache;

import java.math.BigDecimal;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import com.google.common.collect.Sets;
import com.mingdi101.annotations.model.Book;

/**
 * Define a cache bean using component annotation
 *
 */
@Component
public class BookCache {

	final private Set<Book> bookCache;

	public BookCache() {
		Book b1 = new Book("80 days travel", "9903-4421-3379", BigDecimal.valueOf(56l));
		Book b2 = new Book("twisted tower", "7786-3312-6578", BigDecimal.valueOf(78l));
		Book b3 = new Book("Forest Gump", "2212-6542-1234", BigDecimal.valueOf(33l));
		bookCache = Sets.newHashSet(b1, b2, b3);
	}

	/**
	 * PostConstruct, a java native annotation. 
	 * After the instance initialized, 
	 */
	@PostConstruct
	public void getAllBooks() {
		// In here, if the hashCode is not overrided, the new book will inserted in the
		// set.
		// cause Set object uses hashCode to identity inner object. when new 2 object
		// even the same content, the hashCode will be still the same.
		bookCache.add(new Book("Forest Gump", "2212-6542-1234", BigDecimal.valueOf(33l)));
	}

	
	public Book findByName(String bookName) {
		return bookCache.stream().filter(b -> b.getName().equals(bookName)).findFirst().get();
	}
}
