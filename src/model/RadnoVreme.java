package model;

import java.time.LocalTime;

public class RadnoVreme {
    private LocalTime pocetak;
    private LocalTime kraj;

    public RadnoVreme(LocalTime pocetak, LocalTime kraj) {
        this.pocetak = pocetak;
        this.kraj = kraj;
    }

    public LocalTime getPocetak() {
        return pocetak;
    }

    public void setPocetak(LocalTime pocetak) {
        this.pocetak = pocetak;
    }

    public LocalTime getKraj() {
        return kraj;
    }

    public void setKraj(LocalTime kraj) {
        this.kraj = kraj;
    }

    public boolean jeUOpsegu(LocalTime vreme) {
        return !vreme.isBefore(pocetak) && !vreme.isAfter(kraj);
    }

    @Override
    public String toString() {
        return "Radno vreme: " + pocetak + " - " + kraj;
    }
}
