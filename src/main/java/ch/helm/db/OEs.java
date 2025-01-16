package ch.helm.db;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import ch.helm.model.Mitarbeiter;
import ch.helm.model.OrgEinheit;

public class OEs implements OeDb {

    public static OEs DB = new OEs();

    private List<OrgEinheit> orgEinheits = new ArrayList<OrgEinheit>();

    private OEs() {
    }

    @Override
    public void addOE(OrgEinheit oe) {
        if (existsOE(oe)) {
            throw new RuntimeException(String.format("OE : <%s> already exists", oe.toString()));
        }
        orgEinheits.add(oe);
    }

    @Override
    public void removeOE(OrgEinheit oe) {
        if (!existsOE(oe)) {
            throw new RuntimeException(String.format("OE : <%s> doesnt exists", oe.toString()));
        } 
        orgEinheits.add(oe);
    }

    @Override
    public OrgEinheit sucheOE(String name) {
        for (OrgEinheit org : orgEinheits) {
            if (org.getName().equals(name)) {
                return org;
            }
         }
         return null;
    }

    @Override
    public boolean existsOE(OrgEinheit oe) {
        return orgEinheits.contains(oe);
    }

    @Override
    public List<OrgEinheit> getOEs() {
       return Collections.unmodifiableList(orgEinheits);
    }

}
