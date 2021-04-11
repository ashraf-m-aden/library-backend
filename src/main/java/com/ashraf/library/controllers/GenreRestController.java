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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ashraf.library.entity.Genre;
import com.ashraf.library.services.GenreService;

@CrossOrigin
@RestController
public class GenreRestController {

	private GenreService genreService;
	
	// quick and dirty use constructor injection for employeeDao
	@Autowired
	public GenreRestController(GenreService genreService) {
		this.genreService = genreService;
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/genres")
	public List<Genre> findAll(){
		
		return genreService.findAll();
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/genres/{id}")
	public Genre getGenre(@PathVariable int id){
		
		return genreService.findGenre(id);
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("/genres")
	public Genre save(@RequestBody Genre genre){
		return genreService.save(genre);
	}
	
	@CrossOrigin(origins = "http://localhost:4200")
	@PutMapping("/genres")
	public Genre update(@RequestBody Genre genre){
		
		return genreService.save(genre);
	}
	

	@DeleteMapping("/genres/{id}")
	public String delete(@PathVariable int id){
		genreService.deleteGenre(id);
		return "Deleted genre id: " + id;
	}
}
