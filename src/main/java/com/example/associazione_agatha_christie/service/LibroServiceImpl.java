package com.example.associazione_agatha_christie.service;

import com.example.associazione_agatha_christie.dao.LibroDao;
import com.example.associazione_agatha_christie.model.Libro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Service
public class LibroServiceImpl implements LibroService {

    @Autowired
    private LibroDao libroDao;

    @Autowired
    private CicloService cicloService;

    @Override
    public List<Libro> elencoLibri() {
        return (List<Libro>) libroDao.findAll();
    }

    @Override
    public Libro datiLibro(int id) {

        Optional<Libro> libroOptional = libroDao.findById(id);

        if(libroOptional.isPresent())
            return libroOptional.get();
        return null;
    }

    @Override
    public void registraLibro(Libro libro, String titolo, int anno, String editore, String trama, MultipartFile copertina, MultipartFile copertinaDettaglio, int idCiclo) {

        libro.setTitolo(titolo);
        libro.setAnno(anno);
        libro.setEditore(editore);
        libro.setTrama(trama);

        if (copertina != null && !copertina.isEmpty()) {
            try {
                String formato = copertina.getContentType();
                String copertinaCodificata = "data:" + formato + ";base64," + Base64.getEncoder().encodeToString(copertina.getBytes());
                libro.setCopertina(copertinaCodificata);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        if (copertinaDettaglio != null && !copertinaDettaglio.isEmpty()) {
            try {
                String formato = copertinaDettaglio.getContentType();
                String copertinaDettaglioCodificata = "data:" + formato + ";base64," + Base64.getEncoder().encodeToString(copertinaDettaglio.getBytes());
                libro.setCopertinaDettaglio(copertinaDettaglioCodificata);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        libro.setCiclo(cicloService.datiCiclo(idCiclo));

        libroDao.save(libro);

    }
}
