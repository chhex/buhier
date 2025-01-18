package ch.helm.services;

import ch.helm.model.Mitarbeiter;
import ch.helm.model.OrgEinheit;

public interface MitarbeiterService {

    Mitarbeiter create(String nachName, String vorname);
    void anstellen(Mitarbeiter mitarbeiter, OrgEinheit oe, Integer lohn);
    void befoerdern(Mitarbeiter mitarbeiter, OrgEinheit oe, Integer lohn);
    void transferieren(Mitarbeiter mitarbeiter, OrgEinheit oe);
}
