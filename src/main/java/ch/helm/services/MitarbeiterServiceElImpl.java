package ch.helm.services;

import ch.helm.model.Abteilung;
import ch.helm.model.Mitarbeiter;
import ch.helm.model.OrgEinheit;

public class MitarbeiterServiceElImpl implements  MitarbeiterService {
    /**
     * Kriert ein {@link Mitarbeiter} und speichert diesen in der Datenbank
     *
     * @param nachName Der Nachname des {@link Mitarbeiter}
     * @param vorname  Der Vorname des {@link Mitarbeiter}
     * @return ein {@link Mitarbeiter}
     */
    @Override
    public Mitarbeiter create(String nachName, String vorname) {
        return null;
    }

    /**
     * Suche {@link Mitarbeiter} mit Vorname und Nachname in der Datenbank
     *
     * @param nachName the nach name
     * @param vorname  the vorname
     * @return the mitarbeiter , null wenn nicht gefunden
     */
    @Override
    public Mitarbeiter suche(String nachName, String vorname) {
        return null;
    }

    /**
     * Weise {@link Abteilung} aus von {@link Mitarbeiter}.
     *
     * @param mitarbeiter {@link Mitarbeiter}
     * @return the {@link Abteilung} von mitarbeiter, null, wenn {@link Mitarbeiter} nicht angestellt ist
     */
    @Override
    public Abteilung getAbteilungVonMitarbeiter(Mitarbeiter mitarbeiter) {
        return null;
    }

    /**
     * Weise {@link OrgEinheit} aus von {@link Mitarbeiter}.
     *
     * @param mitarbeiter {@link Mitarbeiter}
     * @return die  {@link OrgEinheit} des {@link Mitarbeiter}. Null, wenn {@link Mitarbeiter} nicht angestellt ist
     */
    @Override
    public OrgEinheit getOrgEinheitVonMitarbeiter(Mitarbeiter mitarbeiter) {
        return null;
    }

    /**
     * Der  {@link Mitarbeiter} wird einer {@link OrgEinheit} zugewiesen.
     * Die  {@link OrgEinheit} muss eine Abteilung haben, siehe {@link #getAbteilungVonMitarbeiter(Mitarbeiter)}
     * Der {@link Mitarbeiter} darf nicht bereits angestellt sein, sonst wird
     * eine  {@link RuntimeException} geworfen
     * Der {@link Mitarbeiter} darf keinen Lohn haben, dieser wird seperate erfasst,
     * siehe {@link #lohn(Mitarbeiter, Integer)}
     *
     * @param mitarbeiter der {@link Mitarbeiter}, der anzustellen ist
     * @param oe          , die {@link OrgEinheit}, wo der {@link Mitarbeiter} angestellt wird
     */
    @Override
    public void anstellen(Mitarbeiter mitarbeiter, OrgEinheit oe) {

    }

    /**
     * Einem {@link Mitarbeiter} wird ein Lohn zugewiesen.
     * Der {@link Mitarbeiter} muss angestellt sein, siehe {@link #anstellen(Mitarbeiter, OrgEinheit)}
     * Die Gesammtsumme der Loehne aller Mitarbeiter der {@link OrgEinheit} darf
     * aber nicht dem Budget der {@link OrgEinheit} uebersteigen.
     * sonst wird eine {@link RuntimeException} geworfen
     *
     * @param mitarbeiter the mitarbeiter
     * @param lohn        the lohn
     * @return the integer
     */
    @Override
    public Integer lohn(Mitarbeiter mitarbeiter, Integer lohn) {
        return null;
    }
}
