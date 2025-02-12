package com.example.associazione_agatha_christie.dao;

import com.example.associazione_agatha_christie.model.Evento;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EventoDao extends CrudRepository<Evento, Integer> {
    List<Evento> findByBibliotecaId (int id);
}
