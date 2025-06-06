package service;

import model.Teren;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TerenServis {
    private List<Teren> sviTereni = new ArrayList<>();

    public void dodajTeren(Teren teren) {
        sviTereni.add(teren);
        System.out.println("✅ Dodat teren: " + teren);
    }

    public boolean obrisiTeren(String id) {
        return sviTereni.removeIf(t -> t.getId().equals(id));
    }

    public List<Teren> pretraziPoLokaciji(String lokacija) {
        return sviTereni.stream()
                .filter(t -> t.getLokacija().equalsIgnoreCase(lokacija))
                .collect(Collectors.toList());
    }

    public List<Teren> pretraziPoNazivu(String naziv) {
        return sviTereni.stream()
                .filter(t -> t.getNaziv().toLowerCase().contains(naziv.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<Teren> getSviTereni() {
        return sviTereni;
    }

    public Teren nadjiPoId(String id) {
        for (Teren t : sviTereni) {
            if (t.getId().equals(id)) {
                return t;
            }
        }
        return null;
    }
}
