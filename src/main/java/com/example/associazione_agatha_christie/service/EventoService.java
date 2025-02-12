package com.example.associazione_agatha_christie.service;

import com.example.associazione_agatha_christie.model.Biblioteca;
import com.example.associazione_agatha_christie.model.Evento;
import jakarta.servlet.http.HttpSession;

import java.time.LocalDateTime;
import java.util.List;

public interface EventoService {

    List<Evento> elencoEventi();

    Evento datiEvento(int id);

    void eliminaEvento(int id);

    void registraEvento(Evento evento, String nome, LocalDateTime dataOra, String durata, String descrizione, String linkDiretta, int idLibro, HttpSession session);

    List<Evento> eventiBiblioteca(int id);

}
