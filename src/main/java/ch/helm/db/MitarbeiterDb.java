package ch.helm.db;

import java.util.List;

import ch.helm.model.Mitarbeiter;

public interface MitarbeiterDb {

    public void addMitarbeiter(Mitarbeiter mitarbeiter);
    public void removeMitarbeiter(Mitarbeiter mitarbeiter); 
    public Mitarbeiter sucheMitarbeiter(String vorName, String nachNanne); 
    public boolean existsMitarbeiter(Mitarbeiter mitarbeiter); 
    public List<Mitarbeiter> getAllMitarbeiters(); 

}
