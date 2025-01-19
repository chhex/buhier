package ch.helm.services;

import ch.helm.model.*;

import java.util.List;

/**
 * The interface Oe services.
 */
public interface OeServices {

    /**
     * Suche die  {@link OrgEinheit} mit dem Namen in der Datenbank.
     *
     * @param name the name
     * @return die {@link OrgEinheit}. Null, wenn die die  {@link OrgEinheit} nicht gefunden wird
     */
    OrgEinheit sucheOe(String name);

    /**
     * Hole alle  {@link OrgEinheit} von der Datenbank
     *
     * @return die {@link List} der  {@link OrgEinheit}
     */
    List<OrgEinheit> sucheAlle();

    /**
     * Der  {@link OrgEinheit} wird ein Budget zu gewiesen.
     * Es kann mur einer Abteilung ein Budget zu gewiesen werden
     * Das Budget darf nicht kleiner sein als die Summe der Loehne,
     * aller bereits direkt und indirekt unterstellten {@link Mitarbeiter}
     * @param oe     the oe
     * @param budget the budget
     */
    void budgetGeben(Abteilung oe, Integer budget);

    /**
     * Holt {@link Abteilung} von einer {@link OrgEinheit}.
     * Wenn die {@link OrgEinheit} selbst eine {@link Abteilung} ist,
     * wird diese als Result zurückgegeben
     *
     * @param oe the oe, der Parameter kann auch null sein kann
     * @return the abteilung von oe, null, wenn nicht gefunden wird oder die  {@link OrgEinheit} null ist
     */
    Abteilung getAbteilungVonOE(OrgEinheit oe);


    /**
     *  Weise alle Untergeordneten {@link OrgEinheit} einer {@link OrgEinheit} aus
     *
     * @param oe the oe {@link OrgEinheit} , kann null sein
     * @return the alle untergeordneten oes von oe
     */
    List<OrgEinheit> getAlleUntergeordnetenOesVonOe(OrgEinheit oe);


    /**
     * Alle {@link Mitarbeiter} dieser {@link OrgEinheit} und
     * aller untergeordneten {@link OrgEinheit}, siehe {@link #getAlleUntergeordnetenOesVonOe(OrgEinheit)}
     *
     * @param oe the oe, kann null
     * @return the alle mitarbeiter von dieser {@link OrgEinheit} und aller untergeordneten {@link OrgEinheit}
     */
    List<Mitarbeiter> getAlleMitarbeiterVonOE(OrgEinheit oe);




}
