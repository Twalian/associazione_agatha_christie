package com.example.associazione_agatha_christie.service;

import com.example.associazione_agatha_christie.dao.EventoDao;
import com.example.associazione_agatha_christie.model.Evento;
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
    public void registraEvento(Evento evento, LocalDateTime dataOra, String durata, String linkDiretta, int idLibro, int idBiblioteca) {

        evento.setDataOra(dataOra);
        evento.setDurata(durata);
        evento.setLinkDiretta(linkDiretta);
        evento.setLibro(libroService.datiLibro(idLibro));
        evento.setBiblioteca(bibliotecaService.datiBiblioteca(idBiblioteca));

        eventoDao.save(evento);
    }
}
