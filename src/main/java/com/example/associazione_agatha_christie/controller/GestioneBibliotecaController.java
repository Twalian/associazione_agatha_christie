package com.example.associazione_agatha_christie.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/gestionebiblioteca")
public class GestioneBibliotecaController {

    @GetMapping
    public String getPage() {
        return "gestionebiblioteca.html";
    }
}
