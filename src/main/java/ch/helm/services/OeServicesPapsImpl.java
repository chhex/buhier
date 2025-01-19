package ch.helm.services;

import ch.helm.db.OEs;
import ch.helm.model.*;

import java.util.ArrayList;
import java.util.List;

public class OeServicesPapsImpl implements OeServices {

    @Override
    public OrgEinheit sucheOe(String name) {
        return OEs.DB.sucheOE(name);
    }

    @Override
    public List<OrgEinheit> sucheAlle() {
        return OEs.DB.getOEs();
    }


    @Override
    public void budgetGeben(Abteilung oe, Integer budget) {
        var alleMitarbeiter = getAlleMitarbeiterVonOE(oe);
        int lohnSumme = 0;
        for (Mitarbeiter mitarbeiter : alleMitarbeiter) {
            lohnSumme =  lohnSumme + (mitarbeiter.getLohn() == null ?  0 : mitarbeiter.getLohn());
        }
        if (lohnSumme > budget) {
            throw new RuntimeException(String.format("Lohnsumme : <%s> darf nicht groesser sein als das Budget <%s>", lohnSumme, budget));
        }
        oe.setBudget(budget);

    }

    @Override
    public Abteilung getAbteilungVonOE(OrgEinheit oe) {
        if (oe == null) {
            return null;
        }
        var previous = oe;
        var ueberoe = oe.getUeberGeordneteEinheit();
        while (ueberoe != null) {
            previous = ueberoe;
            ueberoe = ueberoe.getUeberGeordneteEinheit();
        }
        if (previous instanceof Abteilung) {
            return (Abteilung) previous;
        }
        return null;
    }

    @Override
    public List<OrgEinheit> getAlleUntergeordnetenOesVonOe(OrgEinheit oe) {
        List<OrgEinheit> alle = new ArrayList<>();
        getAlleUntergeordnetenOesVonOe(oe, alle);
        return alle;
    }

    private void getAlleUntergeordnetenOesVonOe(OrgEinheit oe, List<OrgEinheit> alle) {
        alle.addAll(oe.getUnterGeordneteEinheiten());
        for (OrgEinheit untoe : oe.getUnterGeordneteEinheiten()) {
            getAlleUntergeordnetenOesVonOe(untoe, alle);
        }
    }

    @Override
    public List<Mitarbeiter> getAlleMitarbeiterVonOE(OrgEinheit oe) {
        List<Mitarbeiter> alle = new ArrayList<>(oe.getMitarbeiter());
        var unteroes = getAlleUntergeordnetenOesVonOe(oe);
        for (OrgEinheit untoe : unteroes) {
            alle.addAll(untoe.getMitarbeiter());
        }
        return alle;
    }

}
