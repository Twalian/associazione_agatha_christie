package com.example.associazione_agatha_christie.dao;

import com.example.associazione_agatha_christie.model.Biblioteca;
import org.springframework.data.repository.CrudRepository;

public interface BibliotecaDao extends CrudRepository<Biblioteca, Integer> {

    Biblioteca findByCredenzialeId(int id);
}
