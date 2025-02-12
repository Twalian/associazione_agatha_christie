package com.example.associazione_agatha_christie.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "eventi")
public class Evento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String nome;

    @Column
    private LocalDateTime dataOra;

    @Column
    private String durata;

    @Column
    private String descrizione;

    @Column
    private String linkDiretta;

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idLibro", referencedColumnName = "id")
    private Libro libro;

    @ManyToOne
            (cascade = CascadeType.REFRESH)
    @JoinColumn(name = "idBiblioteca", referencedColumnName = "id")
    private Biblioteca biblioteca;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDateTime getDataOra() {
        return dataOra;
    }

    public void setDataOra(LocalDateTime dataOra) {
        this.dataOra = dataOra;
    }

    public String getDurata() {
        return durata;
    }

    public void setDurata(String durata) {
        this.durata = durata;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public String getLinkDiretta() {
        return linkDiretta;
    }

    public void setLinkDiretta(String linkDiretta) {
        this.linkDiretta = linkDiretta;
    }

    public Libro getLibro() {
        return libro;
    }

    public void setLibro(Libro libro) {
        this.libro = libro;
    }

    public Biblioteca getBiblioteca() {
        return biblioteca;
    }

    public void setBiblioteca(Biblioteca biblioteca) {
        this.biblioteca = biblioteca;
    }
}
