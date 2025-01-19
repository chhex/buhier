package ch.helm.services;

import ch.helm.model.Abteilung;
import ch.helm.model.Mitarbeiter;
import ch.helm.model.OrgEinheit;

import java.util.List;

public class OeServicesElImpl implements  OeServices {
    /**
     * Suche die  {@link OrgEinheit} mit dem Namen in der Datenbank.
     *
     * @param name the name
     * @return die {@link OrgEinheit}. Null, wenn die die  {@link OrgEinheit} nicht gefunden wird
     */
    @Override
    public OrgEinheit sucheOe(String name) {
        return null;
    }

    /**
     * Hole alle  {@link OrgEinheit} von der Datenbank
     *
     * @return die {@link List} der  {@link OrgEinheit}
     */
    @Override
    public List<OrgEinheit> sucheAlle() {
        return null;
    }

    /**
     * Der  {@link OrgEinheit} wird ein Budget zu gewiesen.
     * Es kann mur einer Abteilung ein Budget zu gewiesen werden
     * Das Budget darf nicht kleiner sein als die Summe der Loehne,
     * aller direkt und indirekt unterstellten {@link Mitarbeiter}
     *
     * @param oe     the oe
     * @param budget the budget
     */
    @Override
    public void budgetGeben(Abteilung oe, Integer budget) {

    }

    /**
     * Holt {@link Abteilung} von einer {@link OrgEinheit}.
     * Wenn die {@link OrgEinheit} selbst eine {@link Abteilung} ist,
     * wird diese als Result zurückgegeben
     *
     * @param oe the oe, der Parameter kann auch null sein kann
     * @return the abteilung von oe, null, wenn nicht gefunden wird oder die  {@link OrgEinheit} null ist
     */
    @Override
    public Abteilung getAbteilungVonOE(OrgEinheit oe) {
        return null;
    }

    /**
     * Weise alle Untergeordneten {@link OrgEinheit} einer {@link OrgEinheit} aus
     *
     * @param oe the oe {@link OrgEinheit} , kann null sein
     * @return the alle untergeordneten oes von oe
     */
    @Override
    public List<OrgEinheit> getAlleUntergeordnetenOesVonOe(OrgEinheit oe) {
        return null;
    }

    /**
     * Alle {@link Mitarbeiter} dieser {@link OrgEinheit} und
     * aller untergeordneten {@link OrgEinheit}, siehe {@link #getAlleUntergeordnetenOesVonOe(OrgEinheit)}
     *
     * @param oe the oe, kann null
     * @return the alle mitarbeiter von dieser {@link OrgEinheit} und aller untergeordneten {@link OrgEinheit}
     */
    @Override
    public List<Mitarbeiter> getAlleMitarbeiterVonOE(OrgEinheit oe) {
        return null;
    }
}
