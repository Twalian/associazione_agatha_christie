package com.example.associazione_agatha_christie.controller;

import com.example.associazione_agatha_christie.model.Biblioteca;
import com.example.associazione_agatha_christie.model.Evento;
import com.example.associazione_agatha_christie.model.Libro;
import com.example.associazione_agatha_christie.service.BibliotecaService;
import com.example.associazione_agatha_christie.service.EventoService;
import com.example.associazione_agatha_christie.service.LibroService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/gestione-biblioteca")
public class GestioneBibliotecaController {

    @Autowired
    private BibliotecaService bibliotecaService;

    @Autowired
    private EventoService eventoService;

    @Autowired
    private LibroService libroService;

    Evento evento;

    @GetMapping
    public String getPage(@RequestParam Integer id,
                          @RequestParam(required = false) Integer idEvento,
                          Model model,
                          HttpSession session) {

        if (session.getAttribute("utenteBiblioteca") == null)
            return "redirect:/login";

        boolean mostraForm = (idEvento != null); // Se idEvento è presente, il form deve essere visibile

        evento = idEvento == null ? new Evento() : eventoService.datiEvento(idEvento);

        Biblioteca biblioteca = bibliotecaService.datiBiblioteca(id);

        if (biblioteca == null) {
            throw new IllegalArgumentException("Nessuna biblioteca trovata con ID: " + id);
        }

        List<Evento> eventiBiblioteca = eventoService.eventiBiblioteca(id);

        List<Libro> libri = libroService.elencoLibri();

        model.addAttribute("biblioteca", biblioteca);

        session.setAttribute("biblioteca", biblioteca);

        model.addAttribute("eventiBiblioteca", eventiBiblioteca);

        model.addAttribute("evento", evento);

        model.addAttribute("libri", libri);

        model.addAttribute("mostraForm", mostraForm);

        return "gestione-biblioteca";
    }


    @GetMapping("/elimina")
    public String eliminaEvento(@RequestParam int id, HttpSession session) {
        eventoService.eliminaEvento(id);
        int idBiblioteca = (int) session.getAttribute("idBiblioteca");
        return "redirect:/gestione-biblioteca?id=" + idBiblioteca;
    }

    @GetMapping("/logout")
    public String logoutAdmin(HttpSession session) {
        session.removeAttribute("utenteBiblioteca");
        return "redirect:/";
    }

    @PostMapping
    public String formManager(@RequestParam(required = false) Integer idEvento,
                              @RequestParam String nome,
                              @RequestParam LocalDateTime dataOra,
                              @RequestParam String durata,
                              @RequestParam String descrizione,
                              @RequestParam String linkDiretta,
                              @RequestParam int idLibro,
                              HttpSession session) {

        evento = idEvento == null ? new Evento() : eventoService.datiEvento(idEvento);

        eventoService.registraEvento(evento, nome,dataOra, durata, descrizione, linkDiretta, idLibro, session);
        int idBiblioteca = (int) session.getAttribute("idBiblioteca");
        return "redirect:/gestione-biblioteca?id=" + idBiblioteca;
    }
}

