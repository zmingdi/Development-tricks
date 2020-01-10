package com.mingdi101.annotations.model;

import java.math.BigDecimal;

/**
 * Book Object. use as a sample entity of test.
 * In change book function, even change the point to new book object, when return to main function. 
 * The b1 object still pointed to original object. 
 * it tells that JAVA is using value passed to functions.
 * following code:
 * a=String("111");
 * function (a) {
 * 	a = "222";
 * }
 * means 
 * a = string(111);
 * function(){
 * 	this.scope.a = outer.scope.a;
 * 	this.scope.a = '222'; //  only inner scope impacted. it doesn't impact the outer.a
 * }
 * @author Administrator
 *
 */
public class Book {

	private String name;
	private String isbn;
	private BigDecimal price;
	
	public Book() {
		
	}
	public Book(String name, String isbn, BigDecimal price) {
		this.name = name;
		this.isbn = isbn;
		this.price = price;
	}
	
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	} 
	@Override
	public boolean equals(Object b) {
		return this.hashCode() == b.hashCode();
	}
	@Override
	public int hashCode() {
		return new StringBuffer().append(this.name).append(this.isbn).toString().hashCode();
	}
	
	public static void main(String[] args) {
		Book b1 = new Book("80 days travel", "9903-4421-3379", BigDecimal.valueOf(56l));
		System.out.println("### in main function, before change Book " +b1.hashCode());
		changeBook(b1);
		System.out.println("### in main function, after change Book  " +b1.hashCode());
	}
	
	public static void changeBook(Book b) {
		b = new Book("twisted tower", "7786-3312-6578", BigDecimal.valueOf(78l));
		System.out.println("### in changeBook function " +b.hashCode());
	}
}
