package ch.helm.services;

import ch.helm.db.Mitarbeiters;
import ch.helm.db.OEs;
import ch.helm.model.Abteilung;
import ch.helm.model.Sektion;
import ch.helm.model.Team;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MitarbeiterServicesTest {

    private final MitarbeiterService service = new MitarbeiterServicePapsImpl();

    @BeforeEach
    void setUp() {
        OEs.DB.clear();
        Mitarbeiters.DB.clear();
        var abteilung = new Abteilung("Research and Development");
        abteilung.setBudget(1200);
        var sekt1 = new Sektion("Development");
        var sekt2 = new Sektion("Research");
        var t1 = new Team("Test");
        var t2 = new Team("Planung");
        sekt1.addUnterGeordneteEinheit(t1);
        sekt2.addUnterGeordneteEinheit(t2);
        abteilung.addUnterGeordneteEinheit(sekt1);
        abteilung.addUnterGeordneteEinheit(sekt2);
        OEs.DB.addOE(abteilung);
        OEs.DB.addOE(sekt1);
        OEs.DB.addOE(sekt2);
        OEs.DB.addOE(t1);
        OEs.DB.addOE(t2);
    }

    @Test
    void testCreateUndSucheMitarbeiter() {
        var mitarbeiter = service.create("Mueller", "Heinrich");
        var mitArbeiterinDb = service.suche("Mueller", "Heinrich");
        assertEquals(mitArbeiterinDb, mitarbeiter);
        assertNull(mitArbeiterinDb.getLohn());
        assertNull(mitArbeiterinDb.getOrgEinheit());
        assertEquals(mitArbeiterinDb.getNachHame(), "Mueller");
        assertEquals(mitArbeiterinDb.getVorName(), "Heinrich");
    }

    @Test
    void testCreateMitarbeitUndGetOrgEinheit() {
        var mitarbeiter = service.create("Heinrich", "Mueller");
        assertNull(service.getAbteilungVonMitarbeiter(mitarbeiter));
        assertNull(service.getOrgEinheitVonMitarbeiter(mitarbeiter));
    }

    @Test
    void testAnstellenMitarbeitOk() {
        var mitarbeiter = service.create("Heinrich", "Mueller");
        var oe = OEs.DB.sucheOE("Planung");
        assertNotNull(oe);
        service.anstellen(mitarbeiter, oe);
    }

    @Test
    void testAnstellenMitarbeitSchonAngestellt() {
        var mitarbeiter = service.create("Heinrich", "Mueller");
        var oe = OEs.DB.sucheOE("Planung");
        assertNotNull(oe);
        service.anstellen(mitarbeiter, oe);
        try {
            service.anstellen(mitarbeiter, oe);
            fail("Mitarbeiter darf nicht zweimal angestellt werden, Exception erwartet");
        } catch (RuntimeException e) {
            assertEquals(e.getMessage(), (String.format("Mitarbeiter : <%s> ist bereits bei OE <%s> angestellt", mitarbeiter.toString(), mitarbeiter.getOrgEinheit().toString())));
        }
    }

    @Test
    void lohnFuerMitarbeiterOk() {
        var m1 = service.create("Heinrich", "Mueller");
        var oe1 = OEs.DB.sucheOE("Development");
        service.anstellen(m1, oe1);
        var m2 = service.create("Fritz", "Wanner");
        var oe2 = OEs.DB.sucheOE("Planung");
        service.anstellen(m2, oe2);
        service.lohn(m1, 1000);
        var result = service.suche("Heinrich", "Mueller");
        assertEquals(result.getLohn(), 1000);
    }

    @Test
    void lohnFuerMitarbeiterNot() {
        var m1 = service.create("Heinrich", "Mueller");
        var oe1 = OEs.DB.sucheOE("Development");
        service.anstellen(m1, oe1);
        var m2 = service.create("Fritz", "Wanner");
        var oe2 = OEs.DB.sucheOE("Planung");
        service.anstellen(m2, oe2);
        service.lohn(m1, 1000);
        try {
            service.lohn(m2, 1000);
            fail("Expected Error since Budget in Setup for Abteilung is 1200");
        } catch (RuntimeException e) {
            assertEquals(e.getMessage(), "Lohnsumme : <2000> darf nicht groesser sein als das Budget <1200>");
        }
    }


}