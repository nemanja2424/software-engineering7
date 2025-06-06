package model;

import java.time.LocalDateTime;

public class Rezervacija {
    private LocalDateTime datum;
    private Korisnik korisnik;
    private Teren teren;

    public Rezervacija(LocalDateTime datum, Korisnik korisnik, Teren teren) {
        this.datum = datum;
        this.korisnik = korisnik;
        this.teren = teren;
    }

    public LocalDateTime getDatum() {
        return datum;
    }

    public Korisnik getKorisnik() {
        return korisnik;
    }

    public Teren getTeren() {
        return teren;
    }

    @Override
    public String toString() {
        return "Rezervacija: " + datum + " | Teren: " + teren.getNaziv() +
                " | Korisnik: " + korisnik.getPunoIme();
    }
}
