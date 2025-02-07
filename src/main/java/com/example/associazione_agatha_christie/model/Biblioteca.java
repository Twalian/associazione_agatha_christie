package com.example.associazione_agatha_christie.model;

import jakarta.persistence.*;
import jakarta.validation.Valid;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "biblioteche")
public class Biblioteca {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String nome;

    @Column
    private String comune;

    @Column
    private String indirizzo;

    @Column
    private String orarioApertura;

    @Column
    private String sito;

    @Column
    private String email;

    @Column
    private String telefono;

    @Column
    private String maps;

    @Column
    private String logo;

    @Column
    private String foto;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_credenziale",
    referencedColumnName = "id")
    @Valid
    private Credenziale credenziale;

    @OneToMany(
            mappedBy = "biblioteca",
            cascade = CascadeType.REMOVE,
            fetch = FetchType.EAGER,
            orphanRemoval = true
    )
    private List<Evento> eventi = new ArrayList<>();

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

    public String getComune() {
        return comune;
    }

    public void setComune(String comune) {
        this.comune = comune;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    public String getOrarioApertura() {
        return orarioApertura;
    }

    public void setOrarioApertura(String orarioApertura) {
        this.orarioApertura = orarioApertura;
    }

    public String getSito() {
        return sito;
    }

    public void setSito(String sito) {
        this.sito = sito;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getMaps() {
        return maps;
    }

    public void setMaps(String maps) {
        this.maps = maps;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public Credenziale getCredenziale() {
        return credenziale;
    }

    public void setCredenziale(Credenziale credenziale) {
        this.credenziale = credenziale;
    }
}
