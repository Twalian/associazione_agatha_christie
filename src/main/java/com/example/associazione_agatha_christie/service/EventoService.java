package com.example.associazione_agatha_christie.service;

import com.example.associazione_agatha_christie.model.Evento;

import java.time.LocalDateTime;
import java.util.List;

public interface EventoService {

    List<Evento> elencoEventi();

    Evento datiEvento(int id);

    void eliminaEvento(int id);

    void registraEvento(Evento evento, LocalDateTime dataOra, String durata, String linkDiretta, int idLibro, int idBiblioteca);

    List<Evento> eventiBiblioteca(int id);
}
