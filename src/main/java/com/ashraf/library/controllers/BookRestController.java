package com.ashraf.library.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ashraf.library.entity.Book;
import com.ashraf.library.entity.Genre;
import com.ashraf.library.services.BookService;
import com.ashraf.library.services.GenreService;

@RestController
public class BookRestController {


	private BookService bookService;
	private GenreService genreService;
	
	// quick and dirty use constructor injection for employeeDao
	@Autowired
	public BookRestController(BookService bookService,GenreService genreService) {
		this.bookService = bookService;
		this.genreService = genreService;

	}
	
	@CrossOrigin(origins = "https://nationallibrary-13f4b.web.app")
	@GetMapping("/books")
	public List<Book> findAll(){
		
		return bookService.findAll();
	}
	
	@CrossOrigin(origins = "https://nationallibrary-13f4b.web.app")
	@GetMapping("/books/{id}")
	public Book getBook(@PathVariable int id){
		
		return bookService.findBook(id);
	}
	
	@CrossOrigin(origins = "https://nationallibrary-13f4b.web.app")
	@GetMapping("/books/genre/{id}")
	public List<Book> getBookByGenre(@PathVariable int id){
		
		return bookService.findByGenreId(id);
	}
	
	@CrossOrigin(origins = "https://nationallibrary-13f4b.web.app")
	@PostMapping("/books/{id}")
	public Book save(@RequestBody Book book, @PathVariable int id){
		
		// retrieve the genre
		Genre genre =  genreService.findGenre(id);

		//set the genre
		book.setGenre(genre);
		
		return bookService.save(book);
	}
	
	@CrossOrigin(origins = "https://nationallibrary-13f4b.web.app")
	@PutMapping("/books")
	public Book update(@RequestBody Book book){
		
		return bookService.save(book);
	}
	
	@CrossOrigin(origins = "https://nationallibrary-13f4b.web.app")
	@DeleteMapping("/books/{id}")
	public String delete(@PathVariable int id){
		bookService.deleteBook(id);
		return "Deleted Book id: " + id;
	}
}
