package com.example.associazione_agatha_christie.service;

import com.example.associazione_agatha_christie.dao.CicloDao;
import com.example.associazione_agatha_christie.model.Ciclo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CicloServiceImpl implements CicloService {

    @Autowired
    private CicloDao cicloDao;

    @Override
    public Ciclo datiCiclo(int id) {

        Optional<Ciclo> cicloOptional = cicloDao.findById(id);

        if (cicloOptional.isPresent())
            return cicloOptional.get();
        return null;
    }

    @Override
    public List<Ciclo> listaCicli() {
        return (List<Ciclo>) cicloDao.findAll();
    }
}
