package com.ashraf.library.services;

import java.util.List;

import com.ashraf.library.entity.Genre;


public interface GenreService {
	
	public List<Genre> findAll();
	public Genre save(Genre genre);
	public Genre findGenre(int id);
	public String deleteGenre(int id);
}