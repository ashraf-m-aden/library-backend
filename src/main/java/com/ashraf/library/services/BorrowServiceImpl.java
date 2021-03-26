package com.ashraf.library.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ashraf.library.dao.BorrowRepository;
import com.ashraf.library.entity.Borrow;
import com.ashraf.library.entity.Genre;

@Service
public class BorrowServiceImpl implements BorrowService {

	
	private BorrowRepository borrowRepo;
	
	@Autowired
	public BorrowServiceImpl(BorrowRepository borrowRepo) {
		this.borrowRepo = borrowRepo;
	}
	
	@Override
	@Transactional
	public List<Borrow> findAll() {
		
		return borrowRepo.findAll();
	}

	@Override
	@Transactional
	public Borrow save(Borrow borrow) {
		
		return borrowRepo.save(borrow);
	}

	@Override
	@Transactional
	public Borrow findBorrow(int id) {
		Optional<Borrow> result = this.borrowRepo.findById(id);
		Borrow borrow = null;
		if(result.isPresent()) {
			borrow = result.get();
		} else {
			throw new RuntimeException("Did not find book id "+id);
		}
		return borrow;
		
	}

	@Override
	@Transactional
	public String deleteBorrow(int id) {
		borrowRepo.deleteById(id);
		return "Deleted borrow id: " + id;
	}

	@Override
	@Transactional
	public List<Borrow> findByClientId(int id) {
		
		return borrowRepo.findByClientId(id);
	}

	@Override
	@Transactional
	public List<Borrow> findByBookId(int id) {
		return borrowRepo.findByBookId(id);

	}

}
