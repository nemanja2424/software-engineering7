package model;

import java.time.LocalDateTime;

public class TeniskiTeren extends Teren {
    private String tipPodloge;
    private boolean imaSudiju;

    public TeniskiTeren(String id, String naziv, String lokacija, RadnoVreme radnoVreme,
                        String tipPodloge, boolean imaSudiju) {
        super(id, naziv, lokacija, radnoVreme);
        this.tipPodloge = tipPodloge;
        this.imaSudiju = imaSudiju;
    }

    public String getTipPodloge() {
        return tipPodloge;
    }

    public boolean imaSudijuNaTerenu() {
        return imaSudiju;
    }

    @Override
    public String toString() {
        return super.toString() + " [Teniski teren, Podloga: " + tipPodloge +
                ", Sudija na terenu: " + (imaSudiju ? "Da" : "Ne") + "]";
    }
}
