package com.ashraf.library.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ashraf.library.dao.GenreRepository;
import com.ashraf.library.entity.Client;
import com.ashraf.library.entity.Genre;

@Service
public class GenreServiceImpl implements GenreService {

	private GenreRepository genreRepo;
	
	@Autowired
	public GenreServiceImpl(GenreRepository genreRepo) {
		this.genreRepo = genreRepo;
	}
	
	
	@Override
	public List<Genre> findAll() {

		return genreRepo.findAll();
	}

	@Override
	public Genre save(Genre genre) {

		return genreRepo.save(genre);
	}

	@Override
	public Genre findGenre(int id) {

		Optional<Genre> result = this.genreRepo.findById(id);
		Genre genre = null;
		if(result.isPresent()) {
			genre = result.get();
		} else {
			throw new RuntimeException("Did not find book id "+id);
		}
		return genre;	
	}

	@Override
	public String deleteGenre(int id) {
		this.genreRepo.deleteById(id);
		return "Deleted genre id " + id;
	}

}
