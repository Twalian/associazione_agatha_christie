package com.example.associazione_agatha_christie.dao;

import com.example.associazione_agatha_christie.model.Libro;
import org.springframework.data.repository.CrudRepository;

public interface LibroDao extends CrudRepository<Libro, Integer> {
}
