package ch.helm.db;

import java.util.List;

import ch.helm.model.Mitarbeiter;
import ch.helm.model.OrgEinheit;

public interface OeDb {
    public void addOE(OrgEinheit oe);
    public void removeOE(OrgEinheit oe); 
    public OrgEinheit sucheOE(String name); 
    public boolean existsOE(OrgEinheit oe); 
    public List<OrgEinheit> getOEs(); 
}
