package ch.helm.db;

import ch.helm.model.Mitarbeiter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MitarbeitersTest {

    

    @BeforeEach
    void setUp() {
        Mitarbeiters.DB.clear();
    }


    @Test
    void testAddMitarbeiter() {
        var mitarbeiter = new Mitarbeiter("Heinrich", "Meier");
        Mitarbeiters.DB.addMitarbeiter(mitarbeiter);
        var mitarbeiters = Mitarbeiters.DB.getAllMitarbeiters();
        assertEquals(1, mitarbeiters.size());
        assertEquals(mitarbeiter, mitarbeiters.get(0));
        assertEquals(mitarbeiters.get(0).getNachHame(), "Meier");
        assertEquals(mitarbeiters.get(0).getVorName(), "Heinrich");
        assertNull(mitarbeiters.get(0).getLohn());
    }

    @Test()
    void testAddMitarbeiterWhoAlreadyExists() {
        var mitarbeiter = new Mitarbeiter("Heinrich", "Meier");
        Mitarbeiters.DB.addMitarbeiter(mitarbeiter);
        try {
            Mitarbeiters.DB.addMitarbeiter(mitarbeiter);
            fail("Expecte Runtime Exception"); 
        } catch (RuntimeException exception) {
            assertEquals(exception.getMessage(),String.format("Mitarbeiter : <%s> already exists", mitarbeiter));
        }

    }

    @Test
    void testExistsMitarbeiter() {
        var m1 = new Mitarbeiter("Heinrich", "Meier");
        var m2 = new Mitarbeiter("Maria", "Hunziger");
        Mitarbeiters.DB.addMitarbeiter(m1);
        Mitarbeiters.DB.addMitarbeiter(m2);
        assertTrue(Mitarbeiters.DB.existsMitarbeiter(m1));
        assertTrue(Mitarbeiters.DB.existsMitarbeiter(m2));
        assertFalse(Mitarbeiters.DB.existsMitarbeiter(new Mitarbeiter("Urs", "Frisch")));
    }

    @Test
    void testGetAllMitarbeiters() {
        var m1 = new Mitarbeiter("Hula", "Meier");
        var m2 = new Mitarbeiter("Chris", "Hunziger");
        Mitarbeiters.DB.addMitarbeiter(m1);
        Mitarbeiters.DB.addMitarbeiter(m2);
        var mitarbeiters = Mitarbeiters.DB.getAllMitarbeiters();
        assertEquals(2, mitarbeiters.size());
        assertTrue(mitarbeiters.contains(m1));
        assertTrue(mitarbeiters.contains(m2));
    }

    @Test
    void testRemoveMitarbeiter() {
        var m1 = new Mitarbeiter("Joe", "Meier");
        var m2 = new Mitarbeiter("Ulrich", "Hunziger");
        Mitarbeiters.DB.addMitarbeiter(m1);
        Mitarbeiters.DB.addMitarbeiter(m2);
        assertEquals(2, Mitarbeiters.DB.getAllMitarbeiters().size());
        Mitarbeiters.DB.removeMitarbeiter(m1);
        assertEquals(1, Mitarbeiters.DB.getAllMitarbeiters().size());
        assertFalse(Mitarbeiters.DB.existsMitarbeiter(m1));
        Mitarbeiters.DB.removeMitarbeiter(m2);
        assertEquals(0, Mitarbeiters.DB.getAllMitarbeiters().size());
        assertFalse(Mitarbeiters.DB.existsMitarbeiter(m2));

    }

    @Test
    void testRemoveMitarbeiterWhichDoesntExist() {
        var m1 = new Mitarbeiter("Joe", "Meier");
        var m2 = new Mitarbeiter("Ulrich", "Hunziger");
        Mitarbeiters.DB.addMitarbeiter(m1);
        assertEquals(1, Mitarbeiters.DB.getAllMitarbeiters().size());
        try {
            Mitarbeiters.DB.removeMitarbeiter(m2);
            fail("Expecte Runtime Exception"); 
        } catch (RuntimeException exception) {
            assertEquals(exception.getMessage(),String.format("Mitarbeitter : <%s> doesnt exists", m2));
        }
    }

    @Test
    void testSucheMitarbeiter() {
        var m1 = new Mitarbeiter("Heinrich", "Meier");
        var m2 = new Mitarbeiter("Maria", "Hunziger");
        Mitarbeiters.DB.addMitarbeiter(m1);
        Mitarbeiters.DB.addMitarbeiter(m2);
        var suche1 = Mitarbeiters.DB.sucheMitarbeiter("Ulrich", "Pfiffig"); 
        assertNull(suche1);
        var suche2 = Mitarbeiters.DB.sucheMitarbeiter("Heinrich", "Meier"); 
        assertNotNull(suche2);
        assertEquals(m1,suche2);
    }
}