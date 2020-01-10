package com.mingdi101.annotations.model;

public class BookInfo {

	private Book book;
	private Author author;
	public BookInfo(Book book, Author author) {
		this.book = book;
		this.author = author;
	}
	public Author getAuthor() {
		return author;
	}
	public void setAuthor(Author author) {
		this.author = author;
	}
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
}
