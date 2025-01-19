package ch.helm.services;

import ch.helm.db.Mitarbeiters;
import ch.helm.db.OEs;
import ch.helm.model.Abteilung;
import ch.helm.model.Mitarbeiter;
import ch.helm.model.Sektion;
import ch.helm.model.Team;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OeServicesTest {

    private final OeServices service = new OeServicesPapsImpl();

    @BeforeEach
    void setUp() {
        OEs.DB.clear();
        Mitarbeiters.DB.clear();
        // Vollstaendige OE Hierarchie
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
        // Mitarbeiter
        var m1  = new Mitarbeiter("Vreni", "Heinrich");
        abteilung.addMitarbeiter(m1);
        var m2  = new Mitarbeiter("Fritz", "Mueller");
        sekt1.addMitarbeiter(m2);
        var m3  = new Mitarbeiter("Regina", "Hunziker");
        sekt2.addMitarbeiter(m3);
        var m4  = new Mitarbeiter("Felix", "Hungerbuehl");
        var m5  = new Mitarbeiter("Heinrich", "Von Tobel");
        t1.addMitarbeiter(m4);
        t1.addMitarbeiter(m5);
        OEs.DB.addOE(abteilung);
        OEs.DB.addOE(sekt1);
        OEs.DB.addOE(sekt2);
        OEs.DB.addOE(t1);
        OEs.DB.addOE(t2);
        // Unvollstaendige Hierarchie, dh ohne Abteilung
        var sekt3 = new Sektion("Neue Sektion");
        var t3 = new Team("Neues Team 1");
        var t4 = new Team("Neues Team 2");
        sekt3.addUnterGeordneteEinheit(t3);
        sekt3.addUnterGeordneteEinheit(t4);
        OEs.DB.addOE(sekt3);
        OEs.DB.addOE(t3);
        OEs.DB.addOE(t4);
    }

    @Test
    void sucheOeTestOk() {
        var result = service.sucheOe("Planung");
        assertNotNull(result);
        result = service.sucheOe("Research");
        assertNotNull(result);
        result = service.sucheOe("XXXXX");
        assertNull(result);
    }

    @Test
    void sucheAlleOes() {
        var result = service.sucheAlle();
        assertEquals(8, result.size());
        assertTrue(result.contains(new Abteilung("Research and Development")));
        assertTrue(result.contains(new Sektion("Development")));
        assertTrue(result.contains(new Sektion("Research")));
        assertTrue(result.contains(new Team("Test")));
        assertTrue(result.contains(new Team("Planung")));
        assertTrue(result.contains(new Sektion("Neue Sektion")));
        assertTrue(result.contains(new Team("Neues Team 1")));
        assertTrue(result.contains(new Team("Neues Team 2")));
    }

    @Test
    void sucheAbteilungVonAbteilung() {
        var result = service.sucheOe("Research and Development");
        assertTrue(result instanceof Abteilung);
        var resultAbt = service.getAbteilungVonOE(result);
        assertNotNull(resultAbt);
        assertEquals(result, resultAbt);
    }

    @Test
    void sucheAbteilungVonSektion() {
        var abteilung = service.sucheOe("Research and Development");
        var result = service.sucheOe("Development");
        assertTrue(result instanceof Sektion);
        var resultAbt = service.getAbteilungVonOE(result);
        assertNotNull(resultAbt);
        assertEquals(abteilung, resultAbt);
    }

    @Test
    void sucheAbteilungVonTeam() {
        var abteilung = service.sucheOe("Research and Development");
        var result = service.sucheOe("Planung");
        assertTrue(result instanceof Team);
        var resultAbt = service.getAbteilungVonOE(result);
        assertNotNull(resultAbt);
        assertEquals(abteilung, resultAbt);
    }


    @Test
    void sucheSektionOhneAbteilung() {
        var result = service.sucheOe("Neue Sektion");
        assertTrue(result instanceof Sektion);
        var resultAbt = service.getAbteilungVonOE(result);
        assertNull(resultAbt);
    }

    @Test
    void sucheTeamMitSektionOhneAbteilung() {
        var result = service.sucheOe("Neues Team 1");
        assertTrue(result instanceof Team);
        var resultAbt = service.getAbteilungVonOE(result);
        assertNull(resultAbt);
    }

    @Test
    void alleUntergeordneteOesEinesTeams() {
        var result = service.sucheOe("Neues Team 1");
        assertTrue(result instanceof Team);
        var resultUnter = service.getAlleUntergeordnetenOesVonOe(result);
        assertEquals(0, resultUnter.size());
    }

    @Test
    void alleUntergeordneteOesEinerSektion() {
        var result = service.sucheOe("Development");
        assertTrue(result instanceof Sektion);
        var resultUnter = service.getAlleUntergeordnetenOesVonOe(result);
        assertEquals(1, resultUnter.size());
        assertEquals(new Team("Test"), resultUnter.get(0));
    }

    @Test
    void alleUntergeordneteOesEinerAbteilung()
    {
        var result = service.sucheOe("Research and Development");
        assertTrue(result instanceof Abteilung);
        var resultUnter = service.getAlleUntergeordnetenOesVonOe(result);
        assertEquals(4, resultUnter.size());
        assertTrue(resultUnter.contains(new Sektion("Development")));
        assertTrue(resultUnter.contains(new Sektion("Research")));
        assertTrue(resultUnter.contains(new Team("Test")));
        assertTrue(resultUnter.contains(new Team("Planung")));
    }

    @Test
    void alleMitarbeitereinesTeamsMitMitarbeiter()
    {
        var team = service.sucheOe("Test");
        var mitarbeiter = service.getAlleMitarbeiterVonOE(team);
        assertEquals(2, mitarbeiter.size());
        assertTrue(mitarbeiter.contains(new Mitarbeiter("Felix", "Hungerbuehl")));
        assertTrue(mitarbeiter.contains(new Mitarbeiter("Heinrich", "Von Tobel")));

    }

    @Test
    void alleMitarbeitereinesTeamsOhneMitarbeiter()
    {
        var team = service.sucheOe("Planung");
        var mitarbeiter = service.getAlleMitarbeiterVonOE(team);
        assertEquals(0, mitarbeiter.size());
    }

    @Test
    void alleMitarbeitereinesEinerAbteilung()
    {
        var team = service.sucheOe("Research and Development");
        var mitarbeiter = service.getAlleMitarbeiterVonOE(team);
        assertEquals(5, mitarbeiter.size());
        assertTrue(mitarbeiter.contains(new Mitarbeiter("Felix", "Hungerbuehl")));
        assertTrue(mitarbeiter.contains(new Mitarbeiter("Heinrich", "Von Tobel")));
        var m1  = new Mitarbeiter("Vreni", "Heinrich");
        var m2  = new Mitarbeiter("Fritz", "Mueller");
        var m3  = new Mitarbeiter("Regina", "Hunziker");
        var m4  = new Mitarbeiter("Felix", "Hungerbuehl");
        var m5  = new Mitarbeiter("Heinrich", "Von Tobel");
        assertTrue(mitarbeiter.contains(m1));
        assertTrue(mitarbeiter.contains(m2));
        assertTrue(mitarbeiter.contains(m3));
        assertTrue(mitarbeiter.contains(m4));
        assertTrue(mitarbeiter.contains(m5));

    }

    @Test
    void budgetOk()
    {
        var orgEinheit = service.sucheOe("Research and Development");
        assertTrue(orgEinheit instanceof Abteilung);
        var abteilung = (Abteilung) orgEinheit;
        service.budgetGeben(abteilung,10000);
        var result = service.sucheOe("Research and Development");
        assertNotNull(result.getBudget());
        assertEquals(10000,result.getBudget());

    }

    @Test
    void budgetTooLow()
    {
        var orgEinheit = service.sucheOe("Research and Development");
        assertTrue(orgEinheit instanceof Abteilung);
        var mitArbeiter = service.getAlleMitarbeiterVonOE(orgEinheit);
        for (Mitarbeiter m : mitArbeiter)  {
            m.setLohn(2000);
        }
        var abteilung = (Abteilung) orgEinheit;
        try {
            service.budgetGeben(abteilung,5000);
            fail("Budget should not be acepted, since lower then total of Mitarbeiter lohn");
        } catch (RuntimeException e) {
            assertEquals(e.getMessage(),"Lohnsumme : <10000> darf nicht groesser sein als das Budget <5000>");
        }

    }


}