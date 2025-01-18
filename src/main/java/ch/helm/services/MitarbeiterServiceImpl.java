package ch.helm.services;

import ch.helm.model.Mitarbeiter;
import ch.helm.model.OrgEinheit;

public class MitarbeiterServiceImpl implements MitarbeiterService {

    @Override
    public Mitarbeiter create(String nachName, String vorname) {
        return null;
    }

    @Override
    public void anstellen(Mitarbeiter mitarbeiter, OrgEinheit oe, Integer lohn) {
    }

    @Override
    public void befoerdern(Mitarbeiter mitarbeiter, OrgEinheit oe, Integer lohn) {

    }

    @Override
    public void transferieren(Mitarbeiter mitarbeiter, OrgEinheit oe) {

    }
}
