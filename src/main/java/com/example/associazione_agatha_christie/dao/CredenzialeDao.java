package com.example.associazione_agatha_christie.dao;

import com.example.associazione_agatha_christie.model.Credenziale;
import org.springframework.data.repository.CrudRepository;

public interface CredenzialeDao extends CrudRepository<Credenziale, Integer> {

    Credenziale findByUsernameAndPasswordAndRuolo(String username, String password, String ruolo);
}
