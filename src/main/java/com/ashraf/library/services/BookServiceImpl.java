package com.ashraf.library.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ashraf.library.dao.BookRepository;
import com.ashraf.library.entity.Book;

@Service
public class BookServiceImpl implements BookService {

	private BookRepository bookRepo;
	
	// inject with the constructor
	@Autowired
	public BookServiceImpl(BookRepository bookRepo) {
		this.bookRepo = bookRepo;
	}
	
	
	
	
	@Override
	@Transactional
	public List<Book> findAll() {
		
		return this.bookRepo.findAll();
	}

	@Override
	@Transactional
	public Book save(Book book) {

		return this.bookRepo.save(book);
	}

	@Override
	@Transactional
	public Book findBook(int id) {
	
		Optional<Book> result = bookRepo.findById(id);
		Book book = null;
		if(result.isPresent()) {
			book = result.get();
		} else {
			throw new RuntimeException("Did not find book id "+id);
		}
		return book;
	}

	@Override
	@Transactional
	public String deleteBook(int id) {
		bookRepo.deleteById(id);
		return "Deleted book id " + id;
	}


	@Override
	@Transactional
	public List<Book> findByGenreId(int id_genre) {
	
		return this.bookRepo.findByGenreId(id_genre);

	}

}
