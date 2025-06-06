package model;

import java.time.LocalDateTime;

public class KosarkaskiTeren extends Teren {
    private int brojKoseva;
    private boolean elektronskiSemafor;

    public KosarkaskiTeren(String id, String naziv, String lokacija, RadnoVreme radnoVreme,
                           int brojKoseva, boolean elektronskiSemafor) {
        super(id, naziv, lokacija, radnoVreme);
        this.brojKoseva = brojKoseva;
        this.elektronskiSemafor = elektronskiSemafor;
    }

    public int getBrojKoseva() {
        return brojKoseva;
    }

    public boolean imaElektronskiSemafor() {
        return elektronskiSemafor;
    }

    @Override
    public String toString() {
        return super.toString() + " [Košarkaški teren, Broj koševa: " + brojKoseva +
                ", Semafor: " + (elektronskiSemafor ? "Da" : "Ne") + "]";
    }
}
