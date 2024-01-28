package com.javappa.start;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PoradaService {

    private final PoradaRepository poradaRepository;

    @Autowired
    public PoradaService(PoradaRepository poradaRepository) {
        this.poradaRepository = poradaRepository;
    }

    public void wyswietlWszystkiePorady() {
        // Pobierz wszystkie porady z bazy danych
        List<Porada> porady = poradaRepository.findAll();

        // Wyświetl wszystkie porady
        for (Porada porada : porady) {
            System.out.println("ID porady: " + porada.getId_porady());
            System.out.println("Tytuł: " + porada.getTytul());
            System.out.println("Kategoria: " + porada.getKategoria());
            System.out.println("Treść: " + porada.getTresc());
            System.out.println("-------------------");
        }
    }
}
