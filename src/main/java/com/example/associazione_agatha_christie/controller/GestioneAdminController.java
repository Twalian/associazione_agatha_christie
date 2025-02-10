package com.example.associazione_agatha_christie.controller;

import com.example.associazione_agatha_christie.model.Biblioteca;
import com.example.associazione_agatha_christie.service.BibliotecaService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequestMapping("/gestioneadmin")
public class GestioneAdminController {

    @Autowired
    private BibliotecaService bibliotecaService;

    Biblioteca biblioteca;

    @GetMapping
    public String getPage(Model model,
                          @RequestParam(required = false) Integer id) {
        List<Biblioteca> biblioteche = bibliotecaService.elencoBiblioteche();
        biblioteca = id ==null? new Biblioteca() : bibliotecaService.datiBiblioteca(id);
        model.addAttribute("biblioteca", biblioteca);
        model.addAttribute("biblioteche", biblioteche);
        return "gestioneadmin";
    }

    @GetMapping("/elimina")
    public String eliminaBiblioteca(@RequestParam int id) {
        bibliotecaService.eliminaBiblioteca(id);
        return "redirect:/gestioneadmin";
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
                               @RequestParam int idCredenziale,
                               HttpSession session) {
        bibliotecaService.registraBiblioteca(biblioteca, nome, comune, indirizzo, orarioApertura, sito, email, telefono, maps, logo, foto, idCredenziale, session);
        return "redirect:/gestioneadmin";
    }
}
