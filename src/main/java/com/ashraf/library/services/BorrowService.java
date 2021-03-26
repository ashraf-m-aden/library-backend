package com.ashraf.library.services;

import java.util.List;

import com.ashraf.library.entity.Borrow;


public interface BorrowService {

	public List<Borrow> findAll();
	public Borrow save(Borrow borrow);
	public Borrow findBorrow(int id);
	public String deleteBorrow(int id);
	public List<Borrow> findByClientId(int id);
	public List<Borrow> findByBookId(int id);

}