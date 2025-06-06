package model;

public class Administrator extends Korisnik {

    public Administrator(String id, String ime, String prezime, String email, String telefon) {
        super(id, ime, prezime, email, telefon);
    }

    // Ove metode bi se u praksi pozivale iz servis sloja.
    // Ovde samo dajemo ideju da admin ima posebne ovlašćene akcije.

    public void dodajTeren() {
        System.out.println("Admin dodaje novi teren...");
        // Implementacija ide u servis klasu
    }

    public void izmeniTeren() {
        System.out.println("Admin menja postojeći teren...");
    }

    public void otkaziRezervaciju() {
        System.out.println("Admin otkazuje rezervaciju...");
    }

    @Override
    public String toString() {
        return "[ADMIN] " + super.toString();
    }
}
