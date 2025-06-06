package model;

public class Korisnik {
    private String id;
    private String ime;
    private String prezime;
    private String email;
    private String telefon;

    public Korisnik(String id, String ime, String prezime, String email, String telefon) {
        this.id = id;
        this.ime = ime;
        this.prezime = prezime;
        this.email = email;
        this.telefon = telefon;
    }

    public String getId() {
        return id;
    }

    public String getIme() {
        return ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public String getEmail() {
        return email;
    }

    public String getTelefon() {
        return telefon;
    }

    public String getPunoIme() {
        return ime + " " + prezime;
    }

    @Override
    public String toString() {
        return getPunoIme() + " (" + email + ")";
    }
}
