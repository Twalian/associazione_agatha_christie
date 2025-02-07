package com.example.associazione_agatha_christie.service;

import com.example.associazione_agatha_christie.dao.CredenzialeDao;
import com.example.associazione_agatha_christie.model.Credenziale;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CredenzialeServiceImpl implements CredenzialeService{

    @Autowired
    private CredenzialeDao credenzialeDao;

    @Override
    public boolean loginUtente(String username, String password, String ruolo, HttpSession session) {

        Credenziale utente = credenzialeDao.findByUsernameAndPasswordAndRuolo(username, password, ruolo);

        if (utente != null && utente.getRuolo().equals("admin")) {
            session.setAttribute("utenteAdmin", utente);
            return true;
        } else if (utente != null && utente.getRuolo().equals("curatore")) {
            session.setAttribute("utenteBiblioteca", utente);
            return true;
        }
        return false;
    }

    @Override
    public Credenziale datiCredenziale(int id) {

        Optional<Credenziale> credenzialeOptional = credenzialeDao.findById(id);

        if (credenzialeOptional.isPresent())
            return credenzialeOptional.get();
        return null;
    }
}
