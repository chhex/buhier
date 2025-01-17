package ch.helm.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MitarbeiterTest {
    @Test
    void testMitarbeiter() {
        var mitArbeiter  = new Mitarbeiter("Mirian", "Heinrich");
        assertNull(mitArbeiter.getLohn());
        assertEquals(mitArbeiter.getNachHame(), "Heinrich");
        assertEquals(mitArbeiter.getVorName(), "Mirian");
        mitArbeiter.setLohn(700);
        assertEquals(mitArbeiter.getLohn(), 700);
        mitArbeiter.setNachHame("Mueller");
        assertEquals(mitArbeiter.getNachHame(),"Mueller");
        mitArbeiter.setVorName("Miriam");
        assertEquals(mitArbeiter.getVorName(),"Miriam");
    }

    @Test
    void testMitarbeiterEquals() {
        var m1  = new Mitarbeiter("Mirian", "Heinrich");
        var m2  = new Mitarbeiter("Mirian", "Heinrich");
        assertEquals(m1,m2);
        var m3  = new Mitarbeiter("Heini", "Besserman");
        assertNotEquals(m1,m3);

    }

    @Test
    void testMitarbeiterHashcode() {
        var m1  = new Mitarbeiter("Mirian", "Heinrich");
        var m2  = new Mitarbeiter("Mirian", "Heinrich");
        assertEquals(m1.hashCode(),m2.hashCode());
        var m3  = new Mitarbeiter("Heini", "Besserman");
        assertNotEquals(m1.hashCode(),m3.hashCode());

    }
}