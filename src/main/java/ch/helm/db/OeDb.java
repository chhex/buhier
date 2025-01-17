package ch.helm.db;

import java.util.List;

import ch.helm.model.OrgEinheit;

public interface OeDb {
    void addOE(OrgEinheit oe);
    void removeOE(OrgEinheit oe);
    OrgEinheit sucheOE(String name);
    boolean existsOE(OrgEinheit oe);
    List<OrgEinheit> getOEs();
    void clear();
}
