package service;

import model.Korisnik;
import model.Rezervacija;
import model.Teren;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class RezervacijaServis {
    private List<Rezervacija> sveRezervacije = new ArrayList<>();

    public boolean proveriDostupnost(Teren teren, LocalDateTime datum) {
        return teren.proveriDostupnost(datum);
    }

    public boolean napraviRezervaciju(Teren teren, Korisnik korisnik, LocalDateTime datum) {
        if (teren.rezervisi(korisnik, datum)) {
            Rezervacija r = new Rezervacija(datum, korisnik, teren);
            sveRezervacije.add(r);
            System.out.println("✅ Rezervacija uspešna: " + r);
            return true;
        } else {
            System.out.println("❌ Termin nije dostupan!");
            return false;
        }
    }

    public boolean otkaziRezervaciju(Rezervacija rezervacija) {
        return sveRezervacije.remove(rezervacija);
    }

    public List<Rezervacija> getSveRezervacije() {
        return sveRezervacije;
    }

    public List<Rezervacija> getRezervacijeZaKorisnika(Korisnik korisnik) {
        List<Rezervacija> rezultat = new ArrayList<>();
        for (Rezervacija r : sveRezervacije) {
            if (r.getKorisnik().getId().equals(korisnik.getId())) {
                rezultat.add(r);
            }
        }
        return rezultat;
    }
}
