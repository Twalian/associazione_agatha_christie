package com.example.associazione_agatha_christie.controller;

import com.example.associazione_agatha_christie.model.Biblioteca;
import com.example.associazione_agatha_christie.model.Credenziale;
import com.example.associazione_agatha_christie.service.BibliotecaService;
import jakarta.servlet.http.HttpSession;
import jakarta.websocket.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/profilo")
public class ProfiloController {

    @Autowired
    private BibliotecaService bibliotecaService;

    Biblioteca biblioteca;

    @GetMapping
    private String getPage(HttpSession session, Model model) {
        biblioteca = bibliotecaService.datiBiblioteca((Integer) session.getAttribute("idBiblioteca"));
        model.addAttribute("utente", biblioteca);
        session.setAttribute("utente", biblioteca);
        return "profilo";
    }

    @PostMapping
    public String formManager (@RequestParam String nome,
                               @RequestParam String comune,
                               @RequestParam (required = false) String indirizzo,
                               @RequestParam (required = false) String orarioApertura,
                               @RequestParam (required = false) String sito,
                               @RequestParam (required = false) String email,
                               @RequestParam (required = false) String telefono,
                               @RequestParam (required = false) String maps,
                               @RequestParam (required = false) MultipartFile logo,
                               @RequestParam (required = false) MultipartFile foto,
                               @RequestParam (required = false) String descrizione,
                                HttpSession session) {

        biblioteca = (Biblioteca) session.getAttribute("utente");

        int idCredenziale = biblioteca.getCredenziale().getId();

        bibliotecaService.registraBiblioteca(biblioteca, nome, comune, indirizzo, orarioApertura, sito, email, telefono, maps, logo, foto, idCredenziale, descrizione, session);

        int idBiblioteca = (int) session.getAttribute("idBiblioteca");
        return "redirect:/profilo?id=" + idBiblioteca;
    }


}
