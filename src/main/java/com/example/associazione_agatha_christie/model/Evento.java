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
    private LocalDateTime dataOra;

    @Column
    private String durata;

    @Column
    private String linkDiretta;

    @OneToMany
            (
                    mappedBy = "evento",
                    cascade = CascadeType.MERGE,
                    fetch = FetchType.EAGER,
                    orphanRemoval = false
            )
    @JoinColumn(name = "idLibro", referencedColumnName = "id")
    private Libro libro;

    @ManyToMany
            (cascade = CascadeType.ALL)
    @JoinColumn(name = "idBiblioteca", referencedColumnName = "id")
    private Biblioteca biblioteca;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
