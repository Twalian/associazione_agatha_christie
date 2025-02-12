package com.example.associazione_agatha_christie.service;

import com.example.associazione_agatha_christie.dao.BibliotecaDao;
import com.example.associazione_agatha_christie.model.Biblioteca;
import com.example.associazione_agatha_christie.model.Credenziale;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Service
public class BibliotecaServiceImpl implements BibliotecaService {

    @Autowired
    private BibliotecaDao bibliotecaDao;

    @Autowired
    private CredenzialeService credenzialeService;

    @Override
    public List<Biblioteca> elencoBiblioteche() {
        return (List<Biblioteca>) bibliotecaDao.findAll();
    }

    @Override
    public Biblioteca datiBiblioteca(int id) {

        Optional<Biblioteca> bibliotecaOptional = bibliotecaDao.findById(id);

        if (bibliotecaOptional.isPresent())
            return bibliotecaOptional.get();
        return null;
    }

    @Override
    public void registraBiblioteca(Biblioteca biblioteca, String nome, String comune, String indirizzo, String orarioApertura, String sito, String email, String telefono, String maps, MultipartFile logo, MultipartFile foto, int idCredenziale, String descrizione, HttpSession session) {

        biblioteca.setNome(nome);
        biblioteca.setComune(comune);
        biblioteca.setIndirizzo(indirizzo);
        biblioteca.setOrarioApertura(orarioApertura);
        biblioteca.setSito(sito);
        biblioteca.setEmail(email);
        biblioteca.setTelefono(telefono);
        biblioteca.setMaps(maps);

        if (logo != null && !logo.isEmpty()) {
            try {
                String formato = logo.getContentType();
                String logoCodificato = "data:" + formato + ";base64," + Base64.getEncoder().encodeToString(logo.getBytes());
                biblioteca.setLogo(logoCodificato);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        if (foto != null && !foto.isEmpty()) {
            try {
                String formato = foto.getContentType();
                String fotoCodificata = "data:" + formato + ";base64," + Base64.getEncoder().encodeToString(foto.getBytes());
                biblioteca.setFoto(fotoCodificata);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        biblioteca.setCredenziale(credenzialeService.datiCredenziale(idCredenziale));

        biblioteca.setDescrizione(descrizione);

        bibliotecaDao.save(biblioteca);

        session.setAttribute("utenteBiblioteca", biblioteca);
    }

    @Override
    public void eliminaBiblioteca(int id) {

        Biblioteca biblioteca = datiBiblioteca(id);

        bibliotecaDao.delete(biblioteca);
    }

    @Override
    public void nuovaBiblioteca(Biblioteca biblioteca) {
       Credenziale credenziale = new Credenziale();
       credenziale.setRuolo("curatore");
       credenziale.setPassword(biblioteca.getCredenziale().getPassword());
       credenziale.setUsername(biblioteca.getCredenziale().getUsername());
       biblioteca.setCredenziale(credenziale);
       bibliotecaDao.save(biblioteca);
    }

    @Override
    public Biblioteca trovaBibliotecaDaCredenziale(int id) {
        return bibliotecaDao.findByCredenzialeId(id);
    }
}
