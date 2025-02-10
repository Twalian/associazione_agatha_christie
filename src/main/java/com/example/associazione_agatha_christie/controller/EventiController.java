package com.example.associazione_agatha_christie.controller;

import com.example.associazione_agatha_christie.model.Evento;
import com.example.associazione_agatha_christie.service.EventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/eventi")
public class EventiController {

    @Autowired
    private EventoService eventoService;

    @GetMapping
    public String getPage(Model model) {
        List<Evento> eventi = eventoService.elencoEventi();
        model.addAttribute("eventi", eventi);
        return "eventi";
    }
}
