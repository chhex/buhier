package ch.helm.model;

public class Sektion extends AbstractOE {

    public Sektion(String name) {
        super(name);
    }

    @Override
    public void addUnterGeordneteEinheit(OrgEinheit orgEinheit) {
        if (!(orgEinheit instanceof Team)) {
            throw new RuntimeException(String.format("Die OE : <%s> ist nicht eine Team",
                    orgEinheit.toString()));
        }
        super.addUnterGeordneteEinheit(orgEinheit);
    }

    @Override
    public void setUeberGeordneteEinheit(AbstractOE orgEinheit) {
        if (!(orgEinheit instanceof Abteilung)) {
            throw new RuntimeException(String.format("Die OE : <%s> ist nicht eine Abteilung",
                    orgEinheit.toString()));
        }
        super.setUeberGeordneteEinheit(orgEinheit);
    }

}
