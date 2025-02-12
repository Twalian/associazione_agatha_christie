package com.example.associazione_agatha_christie.service;

import com.example.associazione_agatha_christie.dao.EventoDao;
import com.example.associazione_agatha_christie.model.Biblioteca;
import com.example.associazione_agatha_christie.model.Evento;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class EventoServiceImpl implements EventoService {

    @Autowired
    private EventoDao eventoDao;

    @Autowired
    private LibroService libroService;

    @Autowired
    private BibliotecaService bibliotecaService;

    @Override
    public List<Evento> elencoEventi() {
        return (List<Evento>) eventoDao.findAll();
    }

    @Override
    public Evento datiEvento(int id) {

        Optional<Evento> eventoOptional = eventoDao.findById(id);

        if (eventoOptional.isPresent())
            return eventoOptional.get();
        return null;
    }

    @Override
    public void eliminaEvento(int id) {

        eventoDao.deleteById(id);

    }

    @Override
    public void registraEvento(Evento evento, String nome, LocalDateTime dataOra, String durata, String descrizione, String linkDiretta, int idLibro, HttpSession session) {

        evento.setNome(nome);
        evento.setDataOra(dataOra);
        evento.setDurata(durata);
        evento.setDescrizione(descrizione);
        evento.setLinkDiretta(linkDiretta);
        evento.setLibro(libroService.datiLibro(idLibro));
        Biblioteca biblioteca = (Biblioteca) session.getAttribute("biblioteca");

        if (biblioteca == null) {
            throw new IllegalStateException("Biblioteca non trovata nella sessione.");
        }

        int id = biblioteca.getId();
        evento.setBiblioteca(bibliotecaService.datiBiblioteca(id));

        eventoDao.save(evento);
    }

    public List<Evento> eventiBiblioteca(int id) {
        return (List<Evento>) eventoDao.findByBibliotecaId(id);
    }
}
