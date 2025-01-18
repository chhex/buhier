package ch.helm.services;

import ch.helm.model.*;

import java.util.List;

public class OeServicesImpl implements OeServices{
    @Override
    public Abteilung createAbteilung(String name) {
        return null;
    }

    @Override
    public Sektion createSektion(String name) {
        return null;
    }

    @Override
    public Team createTeam(String name) {
        return null;
    }

    @Override
    public OrgEinheit sucheOe(String name) {
        return null;
    }

    @Override
    public List<OrgEinheit> sucheAlle() {
        return null;
    }

    @Override
    public void zuOrdnen(OrgEinheit untergeordnet, OrgEinheit uebergeordnet) {

    }

    @Override
    public void entfernen(OrgEinheit untergeordnet) {

    }

    @Override
    public void budgetGeben(OrgEinheit oe, Integer budget) {

    }

    @Override
    public List<OrgEinheit> getGanzeHierarchievVonOE(OrgEinheit oe) {
        return null;
    }

    @Override
    public List<OrgEinheit> getAllUebergeordneten(OrgEinheit oe) {
        return null;
    }

    @Override
    public List<OrgEinheit> getAlleUnterheordneten(OrgEinheit oe) {
        return null;
    }

    @Override
    public List<Mitarbeiter> getAlleMitarbeiterInHierarchieDerOE(OrgEinheit oe) {
        return null;
    }

    @Override
    public List<Mitarbeiter> getAlleMitarbeiterDerOE(OrgEinheit oe) {
        return null;
    }
}
