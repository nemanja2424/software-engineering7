package service;

import model.Administrator;
import model.Korisnik;

import java.util.ArrayList;
import java.util.List;

public class AutentikacijaServis {
    private List<Korisnik> korisnici = new ArrayList<>();
    private List<Administrator> administratori = new ArrayList<>();

    public void registrujKorisnika(Korisnik korisnik) {
        korisnici.add(korisnik);
        System.out.println("✅ Registrovan korisnik: " + korisnik.getPunoIme());
    }

    public void dodajAdministratora(Administrator admin) {
        administratori.add(admin);
        System.out.println("✅ Dodat administrator: " + admin.getPunoIme());
    }

    public Korisnik loginKorisnik(String email) {
        for (Korisnik k : korisnici) {
            if (k.getEmail().equalsIgnoreCase(email)) {
                return k;
            }
        }
        return null;
    }

    public Administrator loginAdministrator(String email) {
        for (Administrator a : administratori) {
            if (a.getEmail().equalsIgnoreCase(email)) {
                return a;
            }
        }
        return null;
    }

    public List<Korisnik> getSviKorisnici() {
        return korisnici;
    }

    public List<Administrator> getSviAdministratori() {
        return administratori;
    }
}
