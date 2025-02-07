package com.example.associazione_agatha_christie.controller;

import com.example.associazione_agatha_christie.service.CredenzialeService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/login")
public class LoginController {
    
    @Autowired
    private CredenzialeService credenzialeService;

    @GetMapping
    public String getPage() {
        return "login";
    }
    
    @PostMapping
    public String formManager(@RequestParam String username, @RequestParam String password, @RequestParam String ruolo, HttpSession session) {
        
        if (!credenzialeService.loginUtente(username, password, ruolo, session))
            return "redirect:/login?errore";
        else if (session.getAttribute("utenteAdmin")) {
            
        }

    }
}
