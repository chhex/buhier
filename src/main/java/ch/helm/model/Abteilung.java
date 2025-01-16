package ch.helm.model;

public class Abteilung extends AbstractOE {

    @Override
    public void addUnterGeordneteEinheit(OrgEinheit orgEinheit) {
        if (!(orgEinheit instanceof Sektion)) {
            throw new RuntimeException(String.format("Die OE : <%s> ist nicht eine Team",
                    orgEinheit.toString()));
        }
        super.addUnterGeordneteEinheit(orgEinheit);
    }

    @Override
    public void setUeberGeordneteEinheit(OrgEinheit orgEinheit) {
        throw new RuntimeException(String.format("Ein Abteilung kann keine Uebergeordnete OE: %s haben",
                orgEinheit.toString()));
    }

}
