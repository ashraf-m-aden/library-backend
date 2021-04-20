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
import com.ashraf.library.entity.Borrow;
import com.ashraf.library.entity.Client;
import com.ashraf.library.entity.User;
import com.ashraf.library.services.BookService;
import com.ashraf.library.services.BorrowService;
import com.ashraf.library.services.ClientService;
import com.ashraf.library.services.UserService;

import net.minidev.json.JSONObject;

@CrossOrigin(origins = "https://nationallibrary-13f4b.web.app", maxAge = 3600)
@RestController
public class BorrowRestController {

	

	private BorrowService borrowService;
	private UserService userService;
	private ClientService clientService;
	private BookService bookService;
	
	// quick and dirty use constructor injection for employeeDao
	@Autowired
	public BorrowRestController(BorrowService borrowService,ClientService clientService, UserService userService, BookService bookService) {
		this.borrowService = borrowService;
		this.userService = userService;
		this.bookService = bookService;
		this.clientService = clientService;
	}
	
	@GetMapping("/borrows")
	public List<Borrow> findAll(){
		
		return borrowService.findAll();
	}
	
	@GetMapping("/borrows/{id}")
	public Borrow getBorrow(@PathVariable int id){
		
		return borrowService.findBorrow(id);
	}
	
	
	@GetMapping("/borrows/client/{id}")
	public List<Borrow> getBorrowByUser(@PathVariable int id){
		
		return borrowService.findByClientId(id);
	}
	
	@GetMapping("/borrows/book/{id}")
	public List<Borrow> getBorrowByBook(@PathVariable int id){
		
		return borrowService.findByBookId(id);
	}
	
	@PostMapping("/borrows")
	public Borrow save(@RequestBody JSONObject borrow){
		int clientId = (int) borrow.get("clientId");
		String userId =  (String) borrow.get("userId");
		int bookId = (int) borrow.get("bookId");
		String borrowDate = (String) borrow.get("borrowDate");
		String returnDate = (String) borrow.get("returnDate");
		int returned = (int) borrow.getAsNumber("returned");
		Book book = bookService.findBook(bookId);
		User user = userService.findUser(Integer.valueOf(userId));
		Client client = clientService.findClient(clientId);
		book.setDisponible(0);
		Borrow newBorrow = new Borrow(book, client, user,borrowDate,returnDate, returned);

		return borrowService.save(newBorrow);
	}
	
	@PutMapping("/borrows")
	public Borrow update(@RequestBody Borrow borrow){
		
		borrow.getBook().setDisponible(1);
		
		return borrowService.save(borrow);
		
		//return borrowService.save(borrow);
	}
	
	@DeleteMapping("/borrows/{id}")
	public String delete(@PathVariable int id){
		borrowService.deleteBorrow(id);
		return "Deleted Borrow id: " + id;
	}
	
}
