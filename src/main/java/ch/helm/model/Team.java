package ch.helm.model;

public class Team extends AbstractOE {

    @Override
    public void addUnterGeordneteEinheit(OrgEinheit orgEinheit) {
        throw new RuntimeException(("Ein Tema kann keine untergeordneten OE-s haben ")); 
    }

    @Override
    public void setUeberGeordneteEinheit(OrgEinheit orgEinheit) {
        if (!(orgEinheit instanceof Sektion)) {
            throw new RuntimeException(String.format("Die OE : <%s> ist nicht eine Sektion",
                    orgEinheit.toString()));
        }
        super.setUeberGeordneteEinheit(orgEinheit);
    }

  


}
