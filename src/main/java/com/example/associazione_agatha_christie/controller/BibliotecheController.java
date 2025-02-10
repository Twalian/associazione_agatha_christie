package com.example.associazione_agatha_christie.controller;

import com.example.associazione_agatha_christie.model.Biblioteca;
import com.example.associazione_agatha_christie.service.BibliotecaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/biblioteche")
public class BibliotecheController {

    @Autowired
    private BibliotecaService bibliotecaService;

    @GetMapping
    public String getPage(Model model) {
        List<Biblioteca> biblioteche = bibliotecaService.elencoBiblioteche();
        model.addAttribute("biblioteche", biblioteche);
        return "biblioteche";
    }
}
