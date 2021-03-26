package com.ashraf.library.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ashraf.library.entity.Book;

public interface BookRepository extends JpaRepository<Book, Integer> {

	
	// get book by genre
	public List<Book> findByGenreId(int id_genre);
}
