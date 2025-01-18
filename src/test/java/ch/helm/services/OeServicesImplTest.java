package ch.helm.services;

import ch.helm.db.Mitarbeiters;
import ch.helm.db.OEs;
import ch.helm.model.Abteilung;
import ch.helm.model.Sektion;
import ch.helm.model.Team;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OeServicesImplTest {

    @BeforeEach
    void setUp() {
        OEs.DB.clear();
        Mitarbeiters.DB.clear();
        var abteilung = new Abteilung("Research and Development");
        abteilung.setBudget(1200);
        var sekt1 = new Sektion("Development");
        sekt1.setBudget(480);
        var sekt2 = new Sektion("Research");
        sekt2.setBudget(500);
        var t1 = new Team("Test");
        t1.setBudget(20);
        var t2 = new Team("Planung");
        t2.setBudget(200);
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
    void testCreateAbteilung() {
    }

    @Test
    void teatCreateSektion() {
    }

    @Test
    void testCreateTeam() {
    }

    @Test
    void testsucheOe() {
    }

    @Test
    void testsucheAlle() {
    }

    @Test
    void testZuOrdnen() {
    }

    @Test
    void testEntfernen() {
    }

    @Test
    void testBudgetGeben() {
    }

    @Test
    void testGetGanzeHierarchievVonOE() {
    }

    @Test
    void testGetAllUebergeordneten() {
    }

    @Test
    void testGetAlleUnterheordneten() {
    }

    @Test
    void testGetAlleMitarbeiterInHierarchieDerOE() {
    }

    @Test
    void testGetAlleMitarbeiterDerOE() {
    }
}