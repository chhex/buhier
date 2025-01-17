package ch.helm.db;

import java.util.List;

import ch.helm.model.Mitarbeiter;

public interface MitarbeiterDb {

    void addMitarbeiter(Mitarbeiter mitarbeiter);
    void removeMitarbeiter(Mitarbeiter mitarbeiter);
    Mitarbeiter sucheMitarbeiter(String vorName, String nachNanne);
    boolean existsMitarbeiter(Mitarbeiter mitarbeiter);
    List<Mitarbeiter> getAllMitarbeiters();
    void clear();

}
