package com.ashraf.library.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ashraf.library.entity.Genre;

public interface GenreRepository extends JpaRepository<Genre, Integer> {

}
