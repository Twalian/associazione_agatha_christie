package com.example.associazione_agatha_christie.controller;

import com.example.associazione_agatha_christie.model.Biblioteca;
import com.example.associazione_agatha_christie.service.BibliotecaService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequestMapping("/gestione-admin")
public class GestioneAdminController {

    @Autowired
    private BibliotecaService bibliotecaService;

    Biblioteca biblioteca;

    @GetMapping
    public String getPage(Model model,
                          @RequestParam(required = false) Integer id,
                          HttpSession session
                              ) {

        if (session.getAttribute("utenteAdmin") == null)
            return "redirect:/login";

        List<Biblioteca> biblioteche = bibliotecaService.elencoBiblioteche();

        biblioteca = new Biblioteca();
        model.addAttribute("biblioteca", biblioteca);
        model.addAttribute("biblioteche", biblioteche);

        return "gestione-admin";
    }

    @GetMapping("/elimina")
    public String eliminaBiblioteca(@RequestParam int id) {
        bibliotecaService.eliminaBiblioteca(id);
        return "redirect:/gestione-admin";
    }

    @GetMapping("/logout")
    public String logoutAdmin(HttpSession session) {
        session.removeAttribute("utenteAdmin");
        return "redirect:/";
    }


    @PostMapping
    public String formManager(@Valid @ModelAttribute Biblioteca biblioteca, BindingResult result, HttpSession session, Model model) {
        if (result.hasErrors()) {
            session.setAttribute("formError", true);
            model.addAttribute("biblioteca", biblioteca);
            model.addAttribute("biblioteche", bibliotecaService.elencoBiblioteche());
            return "gestione-admin";
        }

        session.removeAttribute("formError");
        bibliotecaService.nuovaBiblioteca(biblioteca);
        session.setAttribute("biblioteca", biblioteca);

        return "redirect:/gestione-admin";
    }


}
