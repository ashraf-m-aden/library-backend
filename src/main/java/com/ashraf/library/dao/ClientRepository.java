package com.ashraf.library.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ashraf.library.entity.Client;

public interface ClientRepository extends JpaRepository<Client, Integer> {

}
