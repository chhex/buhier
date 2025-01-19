package ch.helm.services;

import ch.helm.db.Mitarbeiters;
import ch.helm.model.Abteilung;
import ch.helm.model.Mitarbeiter;
import ch.helm.model.OrgEinheit;

public class MitarbeiterServicePapsImpl implements MitarbeiterService {
    final OeServices oeServices = new OeServicesPapsImpl();


    @Override
    public Mitarbeiter create(String nachName, String vorname) {
        var mitarbeiter =  new Mitarbeiter(vorname, nachName);
        Mitarbeiters.DB.addMitarbeiter(mitarbeiter);
        return mitarbeiter;
    }

    @Override
    public Mitarbeiter suche(String nachName, String vorname) {
        return Mitarbeiters.DB.sucheMitarbeiter(vorname, nachName);
    }

    @Override
    public Abteilung getAbteilungVonMitarbeiter(Mitarbeiter mitarbeiter) {
        return oeServices.getAbteilungVonOE(mitarbeiter.getOrgEinheit());
    }

    @Override
    public OrgEinheit getOrgEinheitVonMitarbeiter(Mitarbeiter mitarbeiter) {
        return mitarbeiter.getOrgEinheit();
    }

    @Override
    public void anstellen(Mitarbeiter mitarbeiter, OrgEinheit oe) {
        if (mitarbeiter.getLohn() != null) {
            throw new RuntimeException(String.format("Mitarbeiter : <%s> darf keinen  Lohn haben bei Anstellung", mitarbeiter));
        }
        if (mitarbeiter.getOrgEinheit() != null) {
            throw new RuntimeException(String.format("Mitarbeiter : <%s> ist bereits bei OE <%s> angestellt", mitarbeiter, mitarbeiter.getOrgEinheit().toString()));
        }
        if (oeServices.getAbteilungVonOE(oe) == null) {
            throw new RuntimeException(String.format("Die OE : <%s> hat keine Abteilung", oe.toString()));
        }
        oe.addMitarbeiter(mitarbeiter);
    }


    @Override
    public Integer lohn(Mitarbeiter mitarbeiter, Integer lohn) {
        if (mitarbeiter.getOrgEinheit() == null) {
            throw new RuntimeException(String.format("Mitarbeiter : <%s> ist nicht angestellt", mitarbeiter));
        }
        var alterLohn = mitarbeiter.getLohn() == null ? 0 : mitarbeiter.getLohn();
        var abteilung = getAbteilungVonMitarbeiter(mitarbeiter);
        var alleMitarbeiter = oeServices.getAlleMitarbeiterVonOE(abteilung);
        int lohnSumme = 0;
        for (Mitarbeiter mitarbeiterVonAbt : alleMitarbeiter) {
            lohnSumme =  lohnSumme + (mitarbeiterVonAbt.getLohn() == null ?  0 : mitarbeiterVonAbt.getLohn());
        }
        lohnSumme = lohnSumme - alterLohn + lohn;
        if (lohnSumme > abteilung.getBudget()) {
            throw new RuntimeException(String.format("Lohnsumme : <%s> darf nicht groesser sein als das Budget <%s>", lohnSumme, abteilung.getBudget()));
        }
        mitarbeiter.setLohn(lohn);
        return alterLohn;
    }






}
