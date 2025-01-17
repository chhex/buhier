package ch.helm.db;

import ch.helm.model.Sektion;
import ch.helm.model.Team;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OEsTest {

    @BeforeEach
    void setUp() {
        OEs.DB.clear();
    }

    @Test
    void testAddOE() {
        var team = new Team("Forschung");
        OEs.DB.addOE(team);
        var oes = OEs.DB.getOEs();
        assertEquals(1, oes.size());
        assertEquals(team, oes.get(0));
        assertEquals(oes.get(0).getName(), "Forschung");
    }

    @Test
    void testAddOEAlreadyExists() {
        var team = new Team("Forschung");
        OEs.DB.addOE(team);
        try  {
            OEs.DB.addOE(team);
            fail("Exception expected, cannot add same OE twice");
        } catch (RuntimeException e) {
            assertEquals(e.getMessage(),String.format("OE : <%s> already exists", team) );
        }
    }

    @Test
    void testRemoveOE() {
        var team = new Team("Forschung");
        OEs.DB.addOE(team);
        OEs.DB.removeOE(team);
        var oes = OEs.DB.getOEs();
        assertEquals(0, oes.size());
    }

    @Test
    void testRemoveOENotExists() {
        var team = new Team("Forschung");
        try {
            OEs.DB.removeOE(team);
            fail("Exception expected, cannot remove a OE which has not been added");
        } catch (RuntimeException e){
            assertEquals(e.getMessage(),String.format("OE : <%s> doesnt exists", team ));
        }
    }

    @Test
    void testRemoveOEWhichDoesnotExist() {
        var team = new Team("Forschung");
        try {
             OEs.DB.removeOE(team);
            fail("Exception expected, cannot remove OE which has not been added");
        } catch (RuntimeException e)  {
            assertEquals(e.getMessage(),String.format("OE : <%s> doesnt exists", team) );
        }
    }

    @Test
    void testSucheOE() {
        var t1 = new Team("Forschung");
        OEs.DB.addOE(t1);
        var t2 = new Team("Labor");
        OEs.DB.addOE(t2);
        var sektion = new Sektion("Research & Test");
        OEs.DB.addOE(sektion);
        var suche1 = OEs.DB.sucheOE("Research & Test");
        assertNotNull(suche1);
        assertEquals(sektion,suche1);
        var suche2 = OEs.DB.sucheOE("XXX");
        assertNull(suche2);

    }

    @Test
    void testExistsOE() {
        var t1 = new Team("Forschung");
        OEs.DB.addOE(t1);
        var t2 = new Team("Labor");
        OEs.DB.addOE(t2);
        var sektion = new Sektion("Research & Test");
        assertTrue(OEs.DB.existsOE(t1));
        assertTrue(OEs.DB.existsOE(t2));
        assertFalse(OEs.DB.existsOE(sektion));
    }

    @Test
    void testGetOEs() {
        var t1 = new Team("Forschung");
        OEs.DB.addOE(t1);
        var t2 = new Team("Labor");
        OEs.DB.addOE(t2);
        var sektion = new Sektion("Research & Test");
        OEs.DB.addOE(sektion);
        var oes = OEs.DB.getOEs();
        assertEquals(3, oes.size());
        assertEquals(oes.get(0), t1);
        assertEquals(oes.get(1), t2);
        assertEquals(oes.get(2), sektion);



    }
}