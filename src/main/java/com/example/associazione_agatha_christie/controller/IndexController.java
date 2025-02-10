package com.example.associazione_agatha_christie.controller;

import com.example.associazione_agatha_christie.model.Evento;
import com.example.associazione_agatha_christie.model.Libro;
import com.example.associazione_agatha_christie.service.EventoService;
import com.example.associazione_agatha_christie.service.LibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/")
public class IndexController {

    @Autowired
    private LibroService libroService;

    @Autowired
    private EventoService eventoService;

    @GetMapping
    public String getPage(Model model) {

        List<Libro> libri = libroService.elencoLibri();
        List<Evento> eventi = eventoService.elencoEventi();
        model.addAttribute("libri", libri);
        model.addAttribute("eventi", eventi);

        return "index1";
    }
}
