package com.example.associazione_agatha_christie.model;

import jakarta.persistence.*;

@Entity
@Table (name = "libri")
public class Libro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String titolo;

    @Column
    private int anno;

    @Column
    private String editore;

    @Column
    private String trama;

    @Column
    private String copertina;

    @Column
    private String copertinaDettaglio;

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "id_ciclo", referencedColumnName = "id")
    private Ciclo ciclo;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public int getAnno() {
        return anno;
    }

    public void setAnno(int anno) {
        this.anno = anno;
    }

    public String getEditore() {
        return editore;
    }

    public void setEditore(String editore) {
        this.editore = editore;
    }

    public String getTrama() {
        return trama;
    }

    public void setTrama(String trama) {
        this.trama = trama;
    }

    public String getCopertina() {
        return copertina;
    }

    public void setCopertina(String copertina) {
        this.copertina = copertina;
    }

    public String getCopertinaDettaglio() {
        return copertinaDettaglio;
    }

    public void setCopertinaDettaglio(String copertinaDettaglio) {
        this.copertinaDettaglio = copertinaDettaglio;
    }

    public Ciclo getCiclo() {
        return ciclo;
    }

    public void setCiclo(Ciclo ciclo) {
        this.ciclo = ciclo;
    }
}
