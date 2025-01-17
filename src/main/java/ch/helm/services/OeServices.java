package ch.helm.services;

import ch.helm.model.*;

import java.util.List;

public interface OeServices {

    Abteilung createAbteilung(String name);
    Sektion createSektion(String name);
    Team createTeam(String name);
    OrgEinheit sucheOe(String name);
    List<OrgEinheit> sucheAlle();
    void zuOrdnen(OrgEinheit untergeordnet, OrgEinheit uebergeordnet);
    void entfernen(OrgEinheit untergeordnet);
    void budgetGeben(OrgEinheit oe, Integer budget);
    List<OrgEinheit> getGanzeHierarchievVonOE(OrgEinheit oe);
    List<OrgEinheit> getAllUebergeordneten(OrgEinheit oe);
    List<OrgEinheit> getAlleUnterheordneten(OrgEinheit oe);
    List<Mitarbeiter> getAlleMitarbeiterInHierarchieDerOE(OrgEinheit oe);
    List<Mitarbeiter> getAlleMitarbeiterDerOE(OrgEinheit oe);




}
