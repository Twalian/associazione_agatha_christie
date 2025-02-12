package com.example.associazione_agatha_christie.controller;

import com.example.associazione_agatha_christie.model.Biblioteca;
import com.example.associazione_agatha_christie.model.Evento;
import com.example.associazione_agatha_christie.service.BibliotecaService;
import com.example.associazione_agatha_christie.service.EventoService;
import com.example.associazione_agatha_christie.service.EventoServiceImpl;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/gestione-biblioteca")
public class GestioneBibliotecaController {

    @Autowired
    private BibliotecaService bibliotecaService;

    @Autowired
    private EventoService eventoService;

    Biblioteca biblioteca;
    Evento evento;

    @GetMapping
    public String getPage(@RequestParam Integer id, Model model) {
        List<Evento> eventiBiblioteca = eventoService.eventiBiblioteca(id);
        evento = id == null ? new Evento() : eventoService.datiEvento(id);
        model.addAttribute("eventiBiblioteca", eventiBiblioteca);
        model.addAttribute("evento", evento);
        return "gestione-biblioteca";
    }


    @GetMapping("/elimina")
    public String eliminaEvento(@RequestParam int id) {
        eventoService.eliminaEvento(id);
        return "redirect:/gestione-biblioteca";
    }

    @GetMapping("/logout")
    public String logoutAdmin(HttpSession session) {
        session.removeAttribute("utenteBiblioteca");
        return "redirect:/";
    }

    @PostMapping
    public String formManager(@RequestParam LocalDateTime dataOra,
                              @RequestParam String durata,
                              @RequestParam String linkDiretta,
                              @RequestParam int idLibro,
                              @RequestParam int idBiblioteca) {
        eventoService.registraEvento(evento, dataOra, durata, linkDiretta, idLibro, idBiblioteca);
        return "redirect:/gestione-biblioteca";
    }
}

