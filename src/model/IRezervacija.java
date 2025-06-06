package model;

import java.time.LocalDateTime;

public interface IRezervacija {
    boolean proveriDostupnost(LocalDateTime datum);
    boolean rezervisi(Korisnik korisnik, LocalDateTime datum);
}
