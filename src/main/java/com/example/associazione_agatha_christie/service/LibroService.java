package com.example.associazione_agatha_christie.service;

import com.example.associazione_agatha_christie.model.Libro;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface LibroService {

    List<Libro> elencoLibri();

    Libro datiLibro(int id);

    void registraLibro(Libro libro, String titolo, int anno, String editore, String trama, MultipartFile copertina, MultipartFile copertinaDettaglio, int idCiclo);

}
