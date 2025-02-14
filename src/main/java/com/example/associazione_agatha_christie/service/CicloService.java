package com.example.associazione_agatha_christie.service;

import com.example.associazione_agatha_christie.model.Ciclo;

import java.util.List;

public interface CicloService {

    Ciclo datiCiclo(int id);
    List<Ciclo> listaCicli();
}
