package com.javappa.start.Advices;

public class PoradaTreningowa {
    private int idPorady;
    private String tytul;
    private String kategoria;
    private String tresc;

    @Override
    public String toString() {
        return "PoradaTreningowa{" +
                "idPorady=" + idPorady +
                ", tytul='" + tytul + '\'' +
                ", kategoria='" + kategoria + '\'' +
                ", tresc='" + tresc + '\'' +
                '}';
    }

    public PoradaTreningowa(int idPorady, String tytul, String kategoria, String tresc) {
        this.idPorady = idPorady;
        this.tytul = tytul;
        this.kategoria = kategoria;
        this.tresc = tresc;
    }

    // Gettery i settery
    public int getIdPorady() {
        return idPorady;
    }

    public void setIdPorady(int idPorady) {
        this.idPorady = idPorady;
    }

    public String getTytul() {
        return tytul;
    }

    public void setTytul(String tytul) {
        this.tytul = tytul;
    }

    public String getKategoria() {
        return kategoria;
    }

    public void setKategoria(String kategoria) {
        this.kategoria = kategoria;
    }

    public String getTresc() {
        return tresc;
    }

    public void setTresc(String tresc) {
        this.tresc = tresc;
    }
}
