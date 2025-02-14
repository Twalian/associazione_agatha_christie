package com.example.associazione_agatha_christie.controller;

import com.example.associazione_agatha_christie.model.Biblioteca;
import com.example.associazione_agatha_christie.model.Credenziale;
import com.example.associazione_agatha_christie.service.BibliotecaService;
import com.example.associazione_agatha_christie.service.CredenzialeService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/login")
public class LoginController {
    
    @Autowired
    private CredenzialeService credenzialeService;

    @Autowired
    private BibliotecaService bibliotecaService;

    @GetMapping
    public String getPage(@RequestParam(required = false) String errore,
                          Model model,
                          HttpSession session) {
        if(session.getAttribute("utenteAdmin") != null)
            return "redirect:/gestione-admin";
        if(session.getAttribute("utenteBiblioteca") != null) {
            int idBiblioteca = (int) session.getAttribute("idBiblioteca");
            return "redirect:/gestione-biblioteca?id=" + idBiblioteca;
        }
        model.addAttribute("errore", errore);
        return "login";
    }
    
    @PostMapping
    public String formManager(@RequestParam String username, @RequestParam String password, @RequestParam String ruolo, HttpSession session) {
        
        if (!credenzialeService.loginUtente(username, password, ruolo, session))
            return "redirect:/login?errore";
        else if (session.getAttribute("utenteAdmin")!=null) {
            return "redirect:/gestione-admin";
        } else {
            Credenziale credenziale = (Credenziale) session.getAttribute("utenteBiblioteca");

            Biblioteca biblioteca = bibliotecaService.trovaBibliotecaDaCredenziale(credenziale.getId());

            session.setAttribute("idBiblioteca", biblioteca.getId());
            return "redirect:/gestione-biblioteca?id=" + biblioteca.getId();
        }
    }
}
