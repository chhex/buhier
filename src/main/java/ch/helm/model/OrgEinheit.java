package ch.helm.model;

import java.util.List;

public interface OrgEinheit {

    public String getName();
    public void setName(String name); 
    public List<OrgEinheit> getUnterGeordneteEinheiten();
    public OrgEinheit getUeberGeordneteEinheit();
    public boolean isUeberGeordneteOE(OrgEinheit einheit); 
    public boolean isUnterGeordneteOE(OrgEinheit einheit); 
    public void setUeberGeordneteEinheit(OrgEinheit orgEinheit); 
    public void addUnterGeordneteEinheit(OrgEinheit orgEinheit); 
    public void removeUnterGeordneteEinheit(OrgEinheit orgEinheit); 
    public Mitarbeiter getChef();
    public void setChef(Mitarbeiter chef);
    public Mitarbeiter removeChef(); 
    public boolean isChefVonOE(Mitarbeiter chef);
    public List<Mitarbeiter> getMitarbeiter();
    public void addMitarbeiter(Mitarbeiter mitarbeiter);
    public void removeMitarbeiter(Mitarbeiter mitarbeiter);
    public boolean isMitarbeiterVonOE(Mitarbeiter mitarbeiter);
    public boolean isNotMitarbeiterVonOE(Mitarbeiter mitarbeiter);



}
