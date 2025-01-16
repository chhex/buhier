package ch.helm.model;

import java.util.Collections;
import java.util.List;

public abstract class AbstractOE implements OrgEinheit {

    private String name;
    private OrgEinheit ueberGeordneteEinheit;
    private List<OrgEinheit> unterGeordneteEinheiten;
    private Mitarbeiter chef;
    private List<Mitarbeiter> mitarbeiters;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void addMitarbeiter(Mitarbeiter mitarbeiter) {
        if (isMitarbeiterVonOE(mitarbeiter)) {
            throw new RuntimeException(String.format("Mitarbieter : <%s> existiert bereits in OE: %s",
                    mitarbeiter.toString(), this.toString()));
        }
        mitarbeiters.add(mitarbeiter);
    }

    @Override
    public void removeMitarbeiter(Mitarbeiter mitarbeiter) {
        if (isNotMitarbeiterVonOE(mitarbeiter)) {
            throw new RuntimeException(String.format("Mitarbieter : <%s> arbeitet nicht in OE: %s",
                    mitarbeiter.toString(), this.toString()));
        }
        mitarbeiters.remove(mitarbeiter);

    }

    @Override
    public boolean isMitarbeiterVonOE(Mitarbeiter mitarbeiter) {
        if (mitarbeiters.contains(mitarbeiter)) {
            return true;
        }
        return false;
    }

    @Override
    public boolean isNotMitarbeiterVonOE(Mitarbeiter mitarbeiter) {
        return !isMitarbeiterVonOE(mitarbeiter);
    }

    @Override
    public void addUnterGeordneteEinheit(OrgEinheit orgEinheit) {
        if (!isUeberGeordneteOE(orgEinheit)) {
            throw new RuntimeException(String.format("OE : <%s> ist uebergeordnete OE von dieser OE: %s",
                    orgEinheit.toString(), this.toString()));
        }
        if (isUnterGeordneteOE(orgEinheit)) {
            throw new RuntimeException(String.format("OE : <%s> ist bereits untergeortnete OE von dieser OE: %s",
                    orgEinheit.toString(), this.toString()));
        }
        unterGeordneteEinheiten.add(orgEinheit);
    }

    @Override
    public void setUeberGeordneteEinheit(OrgEinheit orgEinheit) {
        if (isUeberGeordneteOE(orgEinheit)) {
            throw new RuntimeException(String.format("OE : <%s> ist bereits uebergeordnete OE von dieser OE: %s",
                    orgEinheit.toString(), this.toString()));
        }
        if (isUnterGeordneteOE(orgEinheit)) {
            throw new RuntimeException(String.format("OE : <%s> ist untergeortnete OE von dieser OE: %s",
                    orgEinheit.toString(), this.toString()));
        }
        ueberGeordneteEinheit = orgEinheit;
    }

    @Override
    public Mitarbeiter getChef() {
        return chef;
    }

    @Override
    public List<Mitarbeiter> getMitarbeiter() {
        return Collections.unmodifiableList(mitarbeiters);
    }

    @Override
    public OrgEinheit getUeberGeordneteEinheit() {
        return ueberGeordneteEinheit;
    }

    @Override
    public List<OrgEinheit> getUnterGeordneteEinheiten() {
        return Collections.unmodifiableList(unterGeordneteEinheiten);

    }

    @Override
    public void setChef(Mitarbeiter chef) {
        this.chef = chef;
        mitarbeiters.remove(chef);
    }

    @Override
    public boolean isChefVonOE(Mitarbeiter mitarbeiter) {
        if (chef != null && chef.equals(mitarbeiter)) {
            return true;
        }
        return false;
    }

    @Override
    public boolean isUeberGeordneteOE(OrgEinheit einheit) {
        if (ueberGeordneteEinheit != null && ueberGeordneteEinheit.equals(einheit)) {
            return true;
        }
        return false;
    }

    @Override
    public boolean isUnterGeordneteOE(OrgEinheit einheit) {
        if (unterGeordneteEinheiten.contains(einheit)) {
            return true;
        }
        return false;
    }

    @Override
    public Mitarbeiter removeChef() {
        var oldChef = chef; 
        chef = null; 
        return oldChef; 
    }

    @Override
    public void removeUnterGeordneteEinheit(OrgEinheit orgEinheit) {
        if (!isUnterGeordneteOE(orgEinheit)) {
            throw new RuntimeException(String.format("OE : <%s> ist nicht untergeortnete OE von dieser OE: %s",
                    orgEinheit.toString(), this.toString()));
        }
        unterGeordneteEinheiten.remove(orgEinheit); 
    }

}
