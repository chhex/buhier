package ch.helm.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public abstract class AbstractOE implements OrgEinheit {

    private String name;
    private Integer budget;
    private OrgEinheit ueberGeordneteEinheit;
    private final List<OrgEinheit> unterGeordneteEinheiten = new ArrayList<>();
    private Mitarbeiter chef;
    private final List<Mitarbeiter> mitarbeiters = new ArrayList<>();

    public AbstractOE(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getBudget() {
        return budget;
    }

    public void setBudget(Integer budget) {
        this.budget = budget;
    }

    @Override
    public void addMitarbeiter(Mitarbeiter mitarbeiter) {
        if (isMitarbeiterVonOE(mitarbeiter)) {
            throw new RuntimeException(String.format("Mitarbieter : <%s> existiert bereits in OE: %s",
                    mitarbeiter.toString(), this));
        }
        mitarbeiters.add(mitarbeiter);
        mitarbeiter.setOrgEinheit(this);
    }

    @Override
    public void removeMitarbeiter(Mitarbeiter mitarbeiter) {
        if (isNotMitarbeiterVonOE(mitarbeiter)) {
            throw new RuntimeException(String.format("Mitarbieter : <%s> arbeitet nicht in OE: %s",
                    mitarbeiter.toString(), this));
        }
        mitarbeiters.remove(mitarbeiter);
        mitarbeiter.setOrgEinheit(null);


    }

    @Override
    public boolean isMitarbeiterVonOE(Mitarbeiter mitarbeiter) {
        if (isChefVonOE(mitarbeiter)) {
            return true;
        }
        return mitarbeiters.contains(mitarbeiter);
    }

    @Override
    public boolean isNotMitarbeiterVonOE(Mitarbeiter mitarbeiter) {
        return !isMitarbeiterVonOE(mitarbeiter);
    }

    @Override
    public void addUnterGeordneteEinheit(OrgEinheit orgEinheit) {
        AbstractOE abstractOE = (AbstractOE) orgEinheit;
        if (isUnterGeordneteOE(orgEinheit)) {
            throw new RuntimeException(String.format("OE : <%s> ist bereits untergeortnete OE von dieser OE: %s",
                    orgEinheit.toString(), this));
        }
        abstractOE.setUeberGeordneteEinheit(this);
        unterGeordneteEinheiten.add(orgEinheit);
    }

    public void setUeberGeordneteEinheit(AbstractOE orgEinheit) {
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
        chef.setOrgEinheit(this);
    }

    @Override
    public boolean isChefVonOE(Mitarbeiter mitarbeiter) {
        return chef != null && chef.equals(mitarbeiter);
    }

    @Override
    public boolean isUeberGeordneteOE(OrgEinheit einheit) {
        return ueberGeordneteEinheit != null && ueberGeordneteEinheit.equals(einheit);
    }

    @Override
    public boolean isUnterGeordneteOE(OrgEinheit einheit) {
        return unterGeordneteEinheiten.contains(einheit);
    }

    @Override
    public Mitarbeiter removeChef() {
        var oldChef = chef; 
        chef = null;
        oldChef.setOrgEinheit(null);
        return oldChef; 
    }

    @Override
    public void removeUnterGeordneteEinheit(OrgEinheit orgEinheit) {
        if (!isUnterGeordneteOE(orgEinheit)) {
            throw new RuntimeException(String.format("OE : <%s> ist nicht untergeortnete OE von dieser OE: %s",
                    orgEinheit.toString(), this));
        }
        unterGeordneteEinheiten.remove(orgEinheit); 
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AbstractOE that)) return false;
        return name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "AbstractOE{" +
                "name='" + name + '\'' +
                ", budget=" + budget +
                '}';
    }
}
