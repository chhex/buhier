package ch.helm.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrgEinheitTest {

    @Test
    void testCreateAbteilung() {
        var abteilung = new Abteilung("Research and Development");
        assertEquals(abteilung.getName(),"Research and Development");
        assertTrue(abteilung.getMitarbeiter().isEmpty());
        assertTrue(abteilung.getUnterGeordneteEinheiten().isEmpty());
        assertNull(abteilung.getUeberGeordneteEinheit());
        assertNull(abteilung.getChef());
        abteilung.setName("Research and Development (RAD)");
        assertEquals(abteilung.getName(),"Research and Development (RAD)");
    }

    @Test
    void testCreateSektion() {
        var abteilung = new Sektion("Development");
        assertEquals(abteilung.getName(),"Development");
        assertTrue(abteilung.getMitarbeiter().isEmpty());
        assertTrue(abteilung.getUnterGeordneteEinheiten().isEmpty());
        assertNull(abteilung.getUeberGeordneteEinheit());
        assertNull(abteilung.getChef());
        abteilung.setName("Development (D)");
        assertEquals(abteilung.getName(),"Development (D)");
    }

    @Test
    void testCreateTeam() {
        var abteilung = new Team("Test");
        assertEquals(abteilung.getName(),"Test");
        assertTrue(abteilung.getMitarbeiter().isEmpty());
        assertTrue(abteilung.getUnterGeordneteEinheiten().isEmpty());
        assertNull(abteilung.getUeberGeordneteEinheit());
        assertNull(abteilung.getChef());
        abteilung.setName("Test (T)");
        assertEquals(abteilung.getName(),"Test (T)");
    }

    @Test
    void testCreateOeAndAssignChef() {
        var abteilung = new Abteilung("Research and Development");
        var chef1 = new Mitarbeiter("Heini", "Mueller");
        abteilung.setChef(chef1);
        assertEquals(abteilung.getChef(), chef1);
        var chef2 = new Mitarbeiter("Ueli", "Heinrich");
        abteilung.setChef(chef2);
        assertEquals(abteilung.getChef(), chef2);
        assertTrue(abteilung.isChefVonOE(chef2));
        assertTrue(abteilung.isMitarbeiterVonOE(chef2));
        var m1 = abteilung.removeChef();
        assertFalse(abteilung.isMitarbeiterVonOE(chef2));
        assertEquals(m1,chef2);

    }


    @Test
    void testGetUnterGeordneteEinheiten() {
        var abteilung = new Abteilung("Research and Development");
        var sekt1 = new Sektion("Development");
        var sekt2 = new Sektion("Research");
        var t1 = new Team("Test");
        var t2 = new Team("Planung");
        sekt1.addUnterGeordneteEinheit(t1);
        sekt2.addUnterGeordneteEinheit(t2);
        abteilung.addUnterGeordneteEinheit(sekt1);
        abteilung.addUnterGeordneteEinheit(sekt2);
        assertEquals(2, abteilung.getUnterGeordneteEinheiten().size());
        assertEquals(abteilung.getUnterGeordneteEinheiten().get(0), sekt1);
        assertEquals(abteilung.getUnterGeordneteEinheiten().get(1), sekt2);
        assertEquals(1, sekt1.getUnterGeordneteEinheiten().size());
        assertEquals(sekt1.getUnterGeordneteEinheiten().get(0), t1);
        assertEquals(1, sekt2.getUnterGeordneteEinheiten().size());
        assertEquals(sekt2.getUnterGeordneteEinheiten().get(0), t2);
    }

    @Test
    void testGetUeberGeordneteEinheit() {
        var abteilung = new Abteilung("Research and Development");
        var sekt1 = new Sektion("Development");
        var sekt2 = new Sektion("Research");
        var t1 = new Team("Test");
        var t2 = new Team("Planung");
        sekt1.addUnterGeordneteEinheit(t1);
        sekt2.addUnterGeordneteEinheit(t2);
        abteilung.addUnterGeordneteEinheit(sekt1);
        abteilung.addUnterGeordneteEinheit(sekt2);
        assertNull(abteilung.getUeberGeordneteEinheit());
        assertEquals(sekt1.getUeberGeordneteEinheit(),abteilung);
        assertEquals(sekt2.getUeberGeordneteEinheit(),abteilung);
        assertEquals(t1.getUeberGeordneteEinheit(),sekt1);
        assertEquals(t2.getUeberGeordneteEinheit(),sekt2);
    }

    @Test
    void isUeberGeordneteOE() {
        var abteilung = new Abteilung("Research and Development");
        var sekt1 = new Sektion("Development");
        var sekt2 = new Sektion("Research");
        var t1 = new Team("Test");
        var t2 = new Team("Planung");
        sekt1.addUnterGeordneteEinheit(t1);
        sekt2.addUnterGeordneteEinheit(t2);
        abteilung.addUnterGeordneteEinheit(sekt1);
        abteilung.addUnterGeordneteEinheit(sekt2);
        assertTrue(sekt1.isUeberGeordneteOE(abteilung));
        assertTrue(sekt1.isUeberGeordneteOE(abteilung));
        assertTrue(t1.isUeberGeordneteOE(sekt1));
        assertTrue(t2.isUeberGeordneteOE(sekt2));

    }

    @Test
    void isUnterGeordneteOE() {
        var abteilung = new Abteilung("Research and Development");
        var sekt1 = new Sektion("Development");
        var sekt2 = new Sektion("Research");
        var t1 = new Team("Test");
        var t2 = new Team("Planung");
        sekt1.addUnterGeordneteEinheit(t1);
        sekt2.addUnterGeordneteEinheit(t2);
        abteilung.addUnterGeordneteEinheit(sekt1);
        abteilung.addUnterGeordneteEinheit(sekt2);
        assertTrue(abteilung.isUnterGeordneteOE(sekt1));
        assertTrue(abteilung.isUnterGeordneteOE(sekt2));
        assertFalse(abteilung.isUnterGeordneteOE(t1));
        assertFalse(abteilung.isUnterGeordneteOE(t2));
        assertTrue(sekt1.isUnterGeordneteOE(t1));
        assertFalse(sekt1.isUnterGeordneteOE(t2));
        assertTrue(sekt2.isUnterGeordneteOE(t2));
        assertFalse(sekt2.isUnterGeordneteOE(t1));
    }


    @Test
    void removeUnterGeordneteEinheit() {
        var abteilung = new Abteilung("Research and Development");
        var sekt1 = new Sektion("Development");
        var sekt2 = new Sektion("Research");
        var t1 = new Team("Test");
        var t2 = new Team("Planung");
        sekt1.addUnterGeordneteEinheit(t1);
        sekt2.addUnterGeordneteEinheit(t2);
        abteilung.addUnterGeordneteEinheit(sekt1);
        abteilung.addUnterGeordneteEinheit(sekt2);
        abteilung.removeUnterGeordneteEinheit(sekt1);
        assertFalse(abteilung.isUnterGeordneteOE(sekt1));
        assertTrue(abteilung.isUnterGeordneteOE(sekt2));
        assertFalse(abteilung.isUnterGeordneteOE(t1));
        assertFalse(abteilung.isUnterGeordneteOE(t2));
        assertTrue(sekt1.isUnterGeordneteOE(t1));
        assertFalse(sekt1.isUnterGeordneteOE(t2));
        assertTrue(sekt2.isUnterGeordneteOE(t2));
        assertFalse(sekt2.isUnterGeordneteOE(t1));
        try {
            sekt1.addUnterGeordneteEinheit(t1);
            fail("Same OE cannot be added twice");
        } catch (RuntimeException ignored) {

        }
    }



    @Test
    void teataddMitarbeiter() {
        var team = new Team("Test");
        var m1 = new Mitarbeiter("Heini", "Mueller");
        var m2 = new Mitarbeiter("Fritz", "Heiner");
        var m3 = new Mitarbeiter("Doris", "Schwab");
        team.addMitarbeiter(m1);
        team.addMitarbeiter(m2);
        assertEquals(m1.getOrgEinheit(),team);
        assertEquals(m2.getOrgEinheit(),team);
        assertNull(m3.getOrgEinheit());
        assertTrue(team.getMitarbeiter().contains(m1));
        assertTrue(team.getMitarbeiter().contains(m2));
        assertFalse(team.getMitarbeiter().contains(m3));
        try {
            team.addMitarbeiter(m1);
            fail("Same Mitarbeiter annot be added twice");
        } catch (RuntimeException ignored) {

        }
    }

    @Test
    void removeMitarbeiter() {
        var team = new Team("Test");
        var m1 = new Mitarbeiter("Heini", "Mueller");
        var m2 = new Mitarbeiter("Fritz", "Heiner");
        var m3 = new Mitarbeiter("Doris", "Schwab");
        team.addMitarbeiter(m1);
        team.addMitarbeiter(m2);
        team.addMitarbeiter(m3);
        team.removeMitarbeiter(m3);
        assertTrue(team.getMitarbeiter().contains(m1));
        assertTrue(team.getMitarbeiter().contains(m2));
        assertFalse(team.getMitarbeiter().contains(m3));
        assertNull(m3.getOrgEinheit());
    }

    @Test
    void isMitarbeiterVonOE() {
        var team = new Team("Test");
        var m1 = new Mitarbeiter("Heini", "Mueller");
        var m2 = new Mitarbeiter("Fritz", "Heiner");
        var m3 = new Mitarbeiter("Doris", "Schwab");
        team.addMitarbeiter(m1);
        team.addMitarbeiter(m2);
        assertTrue(team.isMitarbeiterVonOE(m1));
        assertTrue(team.isMitarbeiterVonOE(m2));
        assertFalse(team.isMitarbeiterVonOE(m3));
    }

    @Test
    void isNotMitarbeiterVonOE() {
        var team = new Team("Test");
        var m1 = new Mitarbeiter("Heini", "Mueller");
        var m2 = new Mitarbeiter("Fritz", "Heiner");
        var m3 = new Mitarbeiter("Doris", "Schwab");
        team.addMitarbeiter(m1);
        team.addMitarbeiter(m2);
        assertTrue(team.isMitarbeiterVonOE(m1));
        assertTrue(team.isMitarbeiterVonOE(m2));
        assertTrue(team.isNotMitarbeiterVonOE(m3));
    }

    @Test
    void testNoUnterOeTeam() {
        var abteilung = new Abteilung("Research and Development");
        var sekt1 = new Sektion("Development");
        var t1 = new Team("Test");
        var t3 = new Team("XXXX");
        try {
            abteilung.addUnterGeordneteEinheit(t1);
            fail("Exception excepted on Sketion allowed");
        } catch (RuntimeException ignored) {

        }
        try {
            sekt1.addUnterGeordneteEinheit(abteilung);
            fail("Exception excepted on Team allow");
        } catch (RuntimeException ignored) {

        }
        try {
            t1.addUnterGeordneteEinheit(t3);
            fail("Exception excepted not allowed");
        } catch (RuntimeException ignored) {

        }

    }
}