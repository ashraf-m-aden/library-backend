package com.ashraf.library.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ashraf.library.entity.Borrow;

public interface BorrowRepository extends JpaRepository<Borrow, Integer> {

	public List<Borrow> findByClientId(int id);
	public List<Borrow> findByBookId(int id);
}
