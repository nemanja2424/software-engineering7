package model;

import java.time.LocalDateTime;

public class FudbalskiTeren extends Teren {
    private String tipPodloge;
    private boolean imaReflektore;

    public FudbalskiTeren(String id, String naziv, String lokacija, RadnoVreme radnoVreme,
                          String tipPodloge, boolean imaReflektore) {
        super(id, naziv, lokacija, radnoVreme);
        this.tipPodloge = tipPodloge;
        this.imaReflektore = imaReflektore;
    }

    public String getTipPodloge() {
        return tipPodloge;
    }

    public boolean imaSvetlaZaNocneUtakmice() {
        return imaReflektore;
    }

    @Override
    public String toString() {
        return super.toString() + " [Fudbalski teren, Podloga: " + tipPodloge +
                ", Reflektori: " + (imaReflektore ? "Da" : "Ne") + "]";
    }
}
