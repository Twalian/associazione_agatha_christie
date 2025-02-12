package com.example.associazione_agatha_christie.service;

import com.example.associazione_agatha_christie.model.Biblioteca;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface BibliotecaService {

    List<Biblioteca> elencoBiblioteche();

    Biblioteca datiBiblioteca(int id);

    void registraBiblioteca(Biblioteca biblioteca, String nome, String comune, String indirizzo, String orarioApertura, String sito, String email, String telefono, String maps, MultipartFile logo, MultipartFile foto, int idCredenziale, String descrizione, HttpSession session);

    void eliminaBiblioteca(int id);

    void nuovaBiblioteca(Biblioteca biblioteca);

    Biblioteca trovaBibliotecaDaCredenziale(int id);
}
