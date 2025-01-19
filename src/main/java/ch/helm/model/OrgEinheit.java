package ch.helm.model;

import java.util.List;

public interface OrgEinheit {

    String getName();
    void setName(String name);
    Integer getBudget();
    void setBudget(Integer budget);
    List<OrgEinheit> getUnterGeordneteEinheiten();
    OrgEinheit getUeberGeordneteEinheit();
    boolean isUeberGeordneteOE(OrgEinheit einheit);
    boolean isUnterGeordneteOE(OrgEinheit einheit);
    void addUnterGeordneteEinheit(OrgEinheit orgEinheit);
    void removeUnterGeordneteEinheit(OrgEinheit orgEinheit);
    Mitarbeiter getChef();
    void setChef(Mitarbeiter chef);
    Mitarbeiter removeChef();
    boolean isChefVonOE(Mitarbeiter chef);
    List<Mitarbeiter> getMitarbeiter();
    void addMitarbeiter(Mitarbeiter mitarbeiter);
    void removeMitarbeiter(Mitarbeiter mitarbeiter);
    boolean isMitarbeiterVonOE(Mitarbeiter mitarbeiter);
    boolean isNotMitarbeiterVonOE(Mitarbeiter mitarbeiter);



}
