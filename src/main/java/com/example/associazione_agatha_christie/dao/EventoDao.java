package com.example.associazione_agatha_christie.dao;

import com.example.associazione_agatha_christie.model.Evento;
import org.springframework.data.repository.CrudRepository;

public interface EventoDao extends CrudRepository<Evento, Integer> {
}
