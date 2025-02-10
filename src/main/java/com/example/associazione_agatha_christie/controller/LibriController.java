package com.example.associazione_agatha_christie.controller;



import com.example.associazione_agatha_christie.model.Libro;
import com.example.associazione_agatha_christie.service.LibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/libri")
public class LibriController {

    @Autowired
    private LibroService libroService;

    @GetMapping
    public String getpage(Model model) {
        List<Libro> libri = libroService.elencoLibri();
        model.addAttribute("libri", libri);
        return "libri";
    }
}
