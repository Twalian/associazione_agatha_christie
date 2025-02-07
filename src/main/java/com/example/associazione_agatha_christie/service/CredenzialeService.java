package com.example.associazione_agatha_christie.service;

import com.example.associazione_agatha_christie.model.Credenziale;
import jakarta.servlet.http.HttpSession;

public interface CredenzialeService {

    boolean loginUtente(String username, String password, String ruolo, HttpSession session);

    Credenziale datiCredenziale(int id);
}
