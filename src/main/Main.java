package main;

import controller.GlavniKontroler;
import model.*;
import service.AutentikacijaServis;
import service.RezervacijaServis;
import service.TerenServis;

import java.time.LocalTime;

public class Main {
    public static void main(String[] args) {
        // Servisi
        AutentikacijaServis auth = new AutentikacijaServis();
        RezervacijaServis rezervacijaServis = new RezervacijaServis();
        TerenServis terenServis = new TerenServis();

        // Kontroler
        GlavniKontroler kontroler = new GlavniKontroler(auth, rezervacijaServis, terenServis);

        // Dodaj demo korisnika i admina
        Korisnik korisnik1 = new Korisnik("u1", "Nemanja", "Nemanjic", "nemanja@gmail.com", "0641234567");
        Administrator admin1 = new Administrator("a1", "Nemanja", "Jakovljevic", "jakovljevic@gmail.com", "0609876543");

        auth.registrujKorisnika(korisnik1);
        auth.dodajAdministratora(admin1);

        // Dodaj demo terene
        RadnoVreme rv = new RadnoVreme(LocalTime.of(8, 0), LocalTime.of(22, 0));
        terenServis.dodajTeren(new KosarkaskiTeren("t1", "Košarkaška hala", "Niš", rv, 2, true));
        terenServis.dodajTeren(new FudbalskiTeren("t2", "Stadion Zvezda", "Beograd", rv, "prirodna", true));
        terenServis.dodajTeren(new TeniskiTeren("t3", "Teniski centar", "Novi Sad", rv, "šljaka", false));

        // Start sistema – izaberemo da li ulazi korisnik ili admin
        java.util.Scanner sc = new java.util.Scanner(System.in);
        System.out.println("Dobrodošli u sistem rezervacije terena!");
        System.out.print("Unesite vaš email za prijavu: ");
        String email = sc.nextLine();

        Administrator a = auth.loginAdministrator(email);
        if (a != null) {
            kontroler.prikaziMeniZaAdmina(a);
        } else {
            Korisnik k = auth.loginKorisnik(email);
            if (k != null) {
                kontroler.prikaziMeniZaKorisnika(k);
            } else {
                System.out.println("Neispravan email ili korisnik nije pronađen.");
            }
        }


        System.out.println("Program završen.");
    }
}
