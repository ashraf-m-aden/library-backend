package com.ashraf.library.services;

import java.util.List;

import com.ashraf.library.entity.Book;


public interface BookService {

	public List<Book> findAll();
	public Book save(Book book);
	public Book findBook(int id);
	public String deleteBook(int id);
	public List<Book> findByGenreId(int id_genre);

}
