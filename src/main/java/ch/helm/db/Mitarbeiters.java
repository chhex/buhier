package ch.helm.db;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import ch.helm.model.Mitarbeiter;

public class Mitarbeiters implements MitarbeiterDb {

    public static Mitarbeiters DB = new Mitarbeiters();

    private List<Mitarbeiter> mitarbeiters = new ArrayList<Mitarbeiter>(); 

    private Mitarbeiters() {
    }

    @Override
    public void addMitarbeiter(Mitarbeiter mitarbeiter) {
        if (existsMitarbeiter(mitarbeiter)) {
            throw new RuntimeException(String.format("Mitarbieter : <%s> already exists", mitarbeiter.toString()));
        } 
        mitarbeiters.add(mitarbeiter); 
    }

    @Override
    public boolean existsMitarbeiter(Mitarbeiter mitarbeiter) {
        return mitarbeiters.contains(mitarbeiter);
    }

    @Override
    public List<Mitarbeiter> getAllMitarbeiters() {
        return Collections.unmodifiableList(mitarbeiters);
    }

    @Override
    public void removeMitarbeiter(Mitarbeiter mitarbeiter) {
        if (!existsMitarbeiter(mitarbeiter)) {
            throw new RuntimeException(String.format("Mitarbieter : <%s> doesnt exists", mitarbeiter.toString()));
        } 
        mitarbeiters.add(mitarbeiter);
    }

    @Override
    public Mitarbeiter sucheMitarbeiter(String vorName, String nachNanne) {
         for (Mitarbeiter mitarbeiter : mitarbeiters) {
            if (mitarbeiter.getVorName().equals(vorName) && mitarbeiter.getNachHame().equals(nachNanne)) {
                return mitarbeiter;
            }
         }
         return null;
    }



}
