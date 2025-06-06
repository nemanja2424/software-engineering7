package model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public abstract class Teren implements IRezervacija {
    protected String id;
    protected String naziv;
    protected String lokacija;
    protected RadnoVreme radnoVreme;
    protected List<Rezervacija> listaRezervacija;

    public Teren(String id, String naziv, String lokacija, RadnoVreme radnoVreme) {
        this.id = id;
        this.naziv = naziv;
        this.lokacija = lokacija;
        this.radnoVreme = radnoVreme;
        this.listaRezervacija = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public String getNaziv() {
        return naziv;
    }

    public String getLokacija() {
        return lokacija;
    }

    public RadnoVreme getRadnoVreme() {
        return radnoVreme;
    }

    public List<Rezervacija> getListaRezervacija() {
        return listaRezervacija;
    }

    @Override
    public boolean proveriDostupnost(LocalDateTime datum) {
        // Proveri da li je termin već rezervisan
        for (Rezervacija r : listaRezervacija) {
            if (r.getDatum().equals(datum)) {
                return false;
            }
        }
        // Proveri da li je u radnom vremenu
        return radnoVreme.jeUOpsegu(datum.toLocalTime());
    }

    @Override
    public boolean rezervisi(Korisnik korisnik, LocalDateTime datum) {
        if (proveriDostupnost(datum)) {
            Rezervacija nova = new Rezervacija(datum, korisnik, this);
            listaRezervacija.add(nova);
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return naziv + " (" + lokacija + ")";
    }
}
