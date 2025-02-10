package com.example.associazione_agatha_christie.controller;


import com.example.associazione_agatha_christie.model.Biblioteca;
import com.example.associazione_agatha_christie.service.BibliotecaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/dettagliobiblioteca")
public class DettaglioBibliotecaController {

    @Autowired
    private BibliotecaService bibliotecaService;

    @GetMapping
    public String getpage(@RequestParam int id,
                          Model model) {
        Biblioteca biblioteca = bibliotecaService.datiBiblioteca(id);
        model.addAttribute("biblioteca", biblioteca);
        return "dettagliobiblioteca";
    }

}
