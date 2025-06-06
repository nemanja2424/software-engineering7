package controller;

import model.*;
import service.AutentikacijaServis;
import service.RezervacijaServis;
import service.TerenServis;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

public class GlavniKontroler {
    private AutentikacijaServis authServis;
    private RezervacijaServis rezervacijaServis;
    private TerenServis terenServis;
    private Scanner scanner;

    public GlavniKontroler(AutentikacijaServis authServis,
                           RezervacijaServis rezervacijaServis,
                           TerenServis terenServis) {
        this.authServis = authServis;
        this.rezervacijaServis = rezervacijaServis;
        this.terenServis = terenServis;
        this.scanner = new Scanner(System.in);
    }

    public void prikaziMeniZaKorisnika(Korisnik korisnik) {
        int izbor;
        do {
            System.out.println("\n--- KORISNIK MENI ---");
            System.out.println("1. Pregled svih terena");
            System.out.println("2. Rezervacija termina");
            System.out.println("3. Prikaz mojih rezervacija");
            System.out.println("0. Izlaz");
            System.out.print("Izbor: ");
            izbor = scanner.nextInt();
            scanner.nextLine();

            switch (izbor) {
                case 1 -> prikaziSveTerene();
                case 2 -> rezervisiTermin(korisnik);
                case 3 -> prikaziRezervacijeKorisnika(korisnik);
                case 0 -> System.out.println("Izlaz...");
                default -> System.out.println("Nepoznat izbor.");
            }
        } while (izbor != 0);
    }

    public void prikaziMeniZaAdmina(Administrator admin) {
        int izbor;
        do {
            System.out.println("\n--- ADMIN MENI ---");
            System.out.println("1. Pregled svih terena");
            System.out.println("2. Dodaj teren");
            System.out.println("3. Obrisi teren");
            System.out.println("4. Prikaz svih rezervacija");
            System.out.println("0. Izlaz");
            System.out.print("Izbor: ");
            izbor = scanner.nextInt();
            scanner.nextLine();

            switch (izbor) {
                case 1 -> prikaziSveTerene();
                case 2 -> dodajNoviTeren();
                case 3 -> obrisiTeren();
                case 4 -> prikaziSveRezervacije();
                case 0 -> System.out.println("Izlaz...");
                default -> System.out.println("Nepoznat izbor.");
            }
        } while (izbor != 0);
    }

    private void prikaziSveTerene() {
        List<Teren> tereni = terenServis.getSviTereni();
        System.out.println("--- Lista terena ---");
        for (Teren t : tereni) {
            System.out.println(t);
        }
    }

    private void rezervisiTermin(Korisnik korisnik) {
        System.out.print("Unesite ID terena: ");
        String terenId = scanner.nextLine();
        Teren teren = terenServis.nadjiPoId(terenId);

        if (teren == null) {
            System.out.println("Teren nije pronađen.");
            return;
        }

        System.out.print("Unesite datum i vreme (YYYY-MM-DDTHH:MM, npr 2025-06-06T18:00): ");
        String unos = scanner.nextLine();
        try {
            LocalDateTime datum = LocalDateTime.parse(unos);
            rezervacijaServis.napraviRezervaciju(teren, korisnik, datum);
        } catch (Exception e) {
            System.out.println("❌ Neispravan format datuma.");
        }
    }

    private void prikaziRezervacijeKorisnika(Korisnik korisnik) {
        var lista = rezervacijaServis.getRezervacijeZaKorisnika(korisnik);
        System.out.println("--- Moje rezervacije ---");
        for (var r : lista) {
            System.out.println(r);
        }
    }

    private void dodajNoviTeren() {
        System.out.println("Dodavanje novog terena.");

        System.out.print("Unesite ID terena: ");
        String id = scanner.nextLine();

        System.out.print("Unesite naziv terena: ");
        String naziv = scanner.nextLine();

        System.out.print("Unesite lokaciju terena: ");
        String lokacija = scanner.nextLine();

        // Unos radnog vremena
        System.out.print("Unesite početak radnog vremena (HH:mm): ");
        String pocetak = scanner.nextLine();

        System.out.print("Unesite kraj radnog vremena (HH:mm): ");
        String kraj = scanner.nextLine();

        RadnoVreme radnoVreme;
        try {
            radnoVreme = new RadnoVreme(
                    java.time.LocalTime.parse(pocetak),
                    java.time.LocalTime.parse(kraj)
            );
        } catch (Exception e) {
            System.out.println("Neispravan format vremena.");
            return;
        }

        System.out.println("Izaberite tip terena:");
        System.out.println("1. Košarkaški teren");
        System.out.println("2. Fudbalski teren");
        System.out.println("3. Teniski teren");
        System.out.print("Izbor: ");
        int tip = scanner.nextInt();
        scanner.nextLine(); // pročisti unos

        Teren noviTeren = null;

        switch (tip) {
            case 1 -> {
                System.out.print("Unesite broj koševa: ");
                int brojKoseva = scanner.nextInt();
                scanner.nextLine();

                System.out.print("Da li teren ima podlogu za košarku? (true/false): ");
                boolean podloga = scanner.nextBoolean();
                scanner.nextLine();

                noviTeren = new KosarkaskiTeren(id, naziv, lokacija, radnoVreme, brojKoseva, podloga);
            }
            case 2 -> {
                System.out.print("Unesite tip terena (npr. prirodna, veštačka): ");
                String tipPodloge = scanner.nextLine();

                System.out.print("Da li teren ima reflektore? (true/false): ");
                boolean reflektori = scanner.nextBoolean();
                scanner.nextLine();

                noviTeren = new FudbalskiTeren(id, naziv, lokacija, radnoVreme, tipPodloge, reflektori);
            }
            case 3 -> {
                System.out.print("Unesite tip podloge (npr. šljaka, trava): ");
                String tipPodloge = scanner.nextLine();

                System.out.print("Da li teren ima zatvorenu halu? (true/false): ");
                boolean zatvorenaHala = scanner.nextBoolean();
                scanner.nextLine();

                noviTeren = new TeniskiTeren(id, naziv, lokacija, radnoVreme, tipPodloge, zatvorenaHala);
            }
            default -> {
                System.out.println("Nepoznat tip terena.");
                return;
            }
        }

        terenServis.dodajTeren(noviTeren);
        System.out.println("✅ Novi teren dodat: " + noviTeren);
    }


    private void obrisiTeren() {
        System.out.print("Unesite ID terena za brisanje: ");
        String id = scanner.nextLine();
        if (terenServis.obrisiTeren(id)) {
            System.out.println("✅ Teren obrisan.");
        } else {
            System.out.println("❌ Teren nije pronađen.");
        }
    }

    private void prikaziSveRezervacije() {
        var lista = rezervacijaServis.getSveRezervacije();
        System.out.println("--- Sve rezervacije ---");
        for (var r : lista) {
            System.out.println(r);
        }
    }
}
